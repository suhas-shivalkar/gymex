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
public class GymAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "attendance_id")
    private UUID id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User userId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "gym_id")
    private Gym gymId;
    @Column( nullable = false, updatable = false)
    private LocalDateTime visitTime;

}
