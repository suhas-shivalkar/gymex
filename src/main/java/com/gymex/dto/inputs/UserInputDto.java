package com.gymex.dto.inputs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gymex.enums.Status;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInputDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    @NotBlank(message = "Role is required")
    @Pattern(regexp = "^(admin|member|gym_owner)$", message = "Role must be one of: admin, member, gym_owner")
    private String role;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90 degrees")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90 degrees")
    private BigDecimal currentLatitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180 degrees")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180 degrees")
    private BigDecimal currentLongitude;

    @NotNull(message = "Status is required")
    @Pattern(regexp = "^(ACTIVE|INACTIVE|EXPIRED)$", message = "Status must be one of: ACTIVE,INACTIVE, EXPIRED")
    private String status;

    public Status getStatus(){
        return Status.valueOf(status);
    }
}
