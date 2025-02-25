package com.gymex.entity;


import com.gymex.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "gym_images")
@Getter
@Setter

public class GymImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "image_id")
    private UUID id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "gym_id")
    private Gym gymId;
    private String imageUrl;

}
