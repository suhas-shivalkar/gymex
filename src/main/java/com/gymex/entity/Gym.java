package com.gymex.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "gyms")
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String name;
    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User ownerId;
    @Column(precision = 9, scale = 6, nullable = false)
    private BigDecimal latitude;
    @Column(precision = 9, scale = 6, nullable = false)
    private BigDecimal longitude;
    private String facilities;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
