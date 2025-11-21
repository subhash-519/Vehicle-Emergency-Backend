package com.mechongo.backend.dto.review;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Review DTO: rating is int (1-5).
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto extends BaseDto {
    @NotBlank
    @Size(min = 36, max = 36)
    private String mechanicId;

    @NotBlank
    @Size(min = 36, max = 36)
    private String userId;

    @Size(min = 36, max = 36)
    private String sosRequestId;

    @NotNull
    @Min(value = 1, message = "rating must be >= 1")
    @Max(value = 5, message = "rating must be <= 5")
    private Integer rating; // 1..5

    @Size(max = 255)
    private String title;

    @Size(max = 2000)
    private String comment;

    private Boolean visible = true;
    private Boolean reported = false;

    @Size(max = 1000)
    private String reportReason;
}
