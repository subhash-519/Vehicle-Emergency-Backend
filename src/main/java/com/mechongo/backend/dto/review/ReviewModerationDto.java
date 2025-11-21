package com.mechongo.backend.dto.review;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * DTO for ReviewModeration entity.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModerationDto extends BaseDto {

    @NotBlank(message = "Review ID is required")
    @Size(min = 36, max = 36)
    private String reviewId; // FK as string, required

    @Size(min = 36, max = 36)
    private String moderatorId;

    @NotBlank(message = "Action is required")
    @Size(max = 64)
    private String action; // HIDE, UNHIDE, WARNING_ISSUED, REMOVE, RESTORE

    @Size(max = 2000, message = "Reason is too long")
    private String reason;

    private Instant actionedAt;
}
