package com.mechongo.backend.dto.mechanic;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * Aggregate rating summary for a mechanic.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MechanicRatingSummaryDto extends BaseDto {
    @Size(min = 36, max = 36)
    private String mechanicId;

    @Min(0)
    private Integer totalRatings;

    @Min(0)
    private Integer totalReviews;

    @DecimalMin(value = "0.0")
    private Double averageRating;

    private Instant lastRecalculatedAt;
}
