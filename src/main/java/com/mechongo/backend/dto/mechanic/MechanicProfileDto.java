package com.mechongo.backend.dto.mechanic;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Mechanic profile DTO.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MechanicProfileDto extends BaseDto {
    @Size(min = 36, max = 36)
    private String userId;

    @DecimalMin(value = "-90.0", message = "latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "latitude must be <= 90")
    private Double currentLatitude;

    @DecimalMin(value = "-180.0", message = "longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "longitude must be <= 180")
    private Double currentLongitude;

    @NotNull(message = "availability required")
    @Size(max = 64)
    private String availability; // MechanicAvailability enum

    @DecimalMin(value = "0.0", message = "rating must be >= 0")
    private Double rating;

    @DecimalMin(value = "0.0", message = "experienceYears must be >= 0")
    private Integer experienceYears;

    @Size(max = 255)
    private String primarySpeciality;

    private Integer serviceRadiusMeters;

    private Boolean verified;

    private List<MechanicSkillDto> skills;
}
