-- Drop tables if they exist
DROP TABLE IF EXISTS admin_logs CASCADE;
DROP TABLE IF EXISTS gym_images CASCADE;
DROP TABLE IF EXISTS payments CASCADE;
DROP TABLE IF EXISTS gym_attendance CASCADE;
DROP TABLE IF EXISTS gyms CASCADE;
DROP TABLE IF EXISTS user_memberships CASCADE;
DROP TABLE IF EXISTS membership_plans CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Drop function if exists
DROP FUNCTION IF EXISTS generate_uuid_v7;

-- Create function to generate UUID v7
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

-- Create tables
CREATE TABLE users (
    id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    current_latitude DECIMAL(9,6),
    current_longitude DECIMAL(9,6),
    status VARCHAR(10)  NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
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
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    membership_id UUID REFERENCES membership_plans(id) ON DELETE CASCADE,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(10) CHECK (status IN ('active', 'expired')) NOT NULL
);

CREATE TABLE gyms (
    id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    owner_id UUID REFERENCES users(id) ON DELETE CASCADE,
    latitude DECIMAL(9,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL,
    facilities TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE gym_attendance (
    id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    gym_id UUID REFERENCES gyms(id) ON DELETE CASCADE,
    visit_time TIMESTAMP DEFAULT NOW()
);

CREATE TABLE payments (
    id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    amount DECIMAL(10,2) NOT NULL,
    payment_time TIMESTAMP DEFAULT NOW()
);

CREATE TABLE gym_images (
    id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    gym_id UUID REFERENCES gyms(id) ON DELETE CASCADE,
    image_url TEXT NOT NULL
);

CREATE TABLE admin_logs (
    id UUID DEFAULT generate_uuid_v7() PRIMARY KEY,
    admin_id UUID REFERENCES users(id) ON DELETE CASCADE,
    action TEXT NOT NULL,
    log_time TIMESTAMP DEFAULT NOW()
);
