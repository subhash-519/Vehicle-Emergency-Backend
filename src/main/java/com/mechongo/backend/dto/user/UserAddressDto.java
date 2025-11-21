package com.mechongo.backend.dto.user;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * DTO for user addresses. Location represented as lat/lon to keep DTO simple.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressDto extends BaseDto {
    @Size(min = 36, max = 36)
    private String userId;

    @Size(max = 64)
    private String label; // e.g., HOME, WORK

    @NotBlank(message = "addressLine1 required")
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

    @DecimalMin(value = "-90.0", message = "latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "latitude must be <= 90")
    private Double latitude;

    @DecimalMin(value = "-180.0", message = "longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "longitude must be <= 180")
    private Double longitude;

    private boolean defaultAddress = false;
}
