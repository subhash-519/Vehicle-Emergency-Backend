package com.mechongo.backend.dto.sos;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Additional SOS locations (waypoints, reported spots).
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SosLocationDto extends BaseDto {
    @NotBlank
    @Size(min = 36, max = 36)
    private String sosRequestId;

    @NotBlank
    @Size(max = 64)
    private String locationType; // PICKUP, DROP, WAYPOINT

    @NotNull
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private Double latitude;

    @NotNull
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    private Double longitude;

    @Size(max = 500)
    private String note;
}
