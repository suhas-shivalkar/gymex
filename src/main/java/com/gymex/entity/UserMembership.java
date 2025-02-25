package com.gymex.entity;


import com.gymex.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_memberships")
@Getter
@Setter

public class UserMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private MembershipPlan membershipPlanId;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User userId;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;
    @Enumerated(EnumType.STRING)
    private Status status;

}
