package com.gymex.dto.responses;

import com.gymex.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String role;
    private BigDecimal currentLatitude;
    private BigDecimal currentLongitude;
    private Status status;
    private LocalDateTime createdAt;
}
