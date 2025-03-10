package com.gymex.entity;

import com.gymex.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
@ToString(exclude ="password")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String role;
    @Column(name = "current_latitude", precision = 9, scale = 6, nullable = false)
    private BigDecimal currentLatitude;
    @Column(name = "current_longitude", precision = 9, scale = 6, nullable = false)
    private BigDecimal currentLongitude;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
