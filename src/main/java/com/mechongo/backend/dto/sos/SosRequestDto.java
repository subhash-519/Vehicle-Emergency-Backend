package com.mechongo.backend.dto.sos;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * SOS request DTO.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SosRequestDto extends BaseDto {
    @NotBlank
    @Size(min = 36, max = 36)
    private String requesterId;

    @Size(min = 36, max = 36)
    private String assignedMechanicId;

    @NotNull(message = "pickupLatitude required")
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private Double pickupLatitude;

    @NotNull(message = "pickupLongitude required")
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    private Double pickupLongitude;

    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private Double dropLatitude;

    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    private Double dropLongitude;

    @Size(max = 2000)
    private String description;

    @NotBlank
    @Size(max = 64)
    private String status; // SosStatus

    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal estimatedAmount;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal actualAmount;

    @Min(1)
    private Integer priority;

    private Instant scheduledAt;
    private Instant expectedArrivalAt;

    @Size(min = 36, max = 36)
    private String canceledBy;

    @Size(max = 1000)
    private String cancelReason;
}
