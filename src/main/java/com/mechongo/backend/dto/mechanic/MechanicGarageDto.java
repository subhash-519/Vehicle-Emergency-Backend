package com.mechongo.backend.dto.mechanic;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Garage DTO.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MechanicGarageDto extends BaseDto {
    @Size(min = 36, max = 36)
    private String mechanicId;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 500)
    private String addressLine1;

    @Size(max = 500)
    private String addressLine2;

    @Size(max = 128)
    private String city;

    @Size(max = 128)
    private String state;

    @Size(max = 128)
    private String country;

    @Size(max = 32)
    private String postalCode;

    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private Double latitude;

    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    private Double longitude;

    @Size(max = 256)
    private String openingHours;

    @Size(max = 32)
    private String contactNumber;

    private boolean primary = false;
}
