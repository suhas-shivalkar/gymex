package com.gymex.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "membership_plans")
@Getter
@Setter

public class MembershipPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String name;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;
    private Integer accessLevel;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
