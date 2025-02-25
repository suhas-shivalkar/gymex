CREATE OR REPLACE FUNCTION generate_uuid_v7()
RETURNS UUID AS $$
DECLARE
    ts_millis BIGINT;
    rand_bytes BYTEA;
    uuid_bytes BYTEA;
BEGIN
    -- Get current timestamp in milliseconds
    ts_millis := (EXTRACT(EPOCH FROM clock_timestamp()) * 1000)::BIGINT;

    -- Generate 10 random bytes
    rand_bytes := gen_random_bytes(10);

    -- Construct UUID v7 (timestamp + randomness)
    uuid_bytes := set_bit(set_bit(set_bit(set_bit(
        lpad(encode(int8send(ts_millis), 'hex') || encode(rand_bytes, 'hex'), 32, '0')::BYTEA,
        49, 0), 50, 1), 51, 1), 52, 1);

    RETURN encode(uuid_bytes, 'hex')::UUID;
END;
$$ LANGUAGE plpgsql;


create table users(
user_id uuid default generate_uuid_v7() primary key,
name varchar(255) not null,
email varchar(255) unique not null,
password varchar(255) not null,
role varchar(255) not null,
current_latitude decimal(9,6),
current_longitude decimal(9,6),
status VARCHAR(10) CHECK (status IN ('active', 'inactive')) NOT NULL,
created_at timestamp default now()

);
CREATE TABLE membership_plans (
    id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    access_level INT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);
CREATE TABLE user_memberships (
    id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    user_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
    membership_id UUID REFERENCES membership_plans(membership_id) ON DELETE CASCADE,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(10) CHECK (status IN ('active', 'expired')) NOT NULL
);
CREATE TABLE gyms (
    gym_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    owner_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
    latitude DECIMAL(9,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL,
	facilities TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);
CREATE TABLE gym_attendance (
    attendance_id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    user_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
    gym_id BIGSERIAL REFERENCES gyms(gym_id) ON DELETE CASCADE,
    visit_time TIMESTAMP DEFAULT NOW()
);
CREATE TABLE payments (
    payment_id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    user_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
     amount DECIMAL(10,2) NOT NULL,
    payment_time TIMESTAMP DEFAULT NOW()
);
CREATE TABLE gym_images (
    image_id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    gym_id BIGSERIAL REFERENCES gyms(gym_id) ON DELETE CASCADE,
	image_url TEXT NOT NULL

);
CREATE TABLE admin_logs (
    log_id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    admin_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
    action TEXT NOT NULL,
    log_time TIMESTAMP DEFAULT NOW()
);