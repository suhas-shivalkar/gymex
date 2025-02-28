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
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User userId;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;
    @Column( nullable = false, updatable = false)
    private LocalDateTime paymentTime;

}
