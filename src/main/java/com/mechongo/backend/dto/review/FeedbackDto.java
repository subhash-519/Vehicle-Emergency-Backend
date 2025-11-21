package com.mechongo.backend.dto.review;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Feedback from users (non-review).
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto extends BaseDto {
    @Size(min = 36, max = 36)
    private String userId;

    @Size(max = 128)
    private String category;

    @NotBlank
    @Size(max = 255)
    private String subject;

    @NotBlank
    @Size(max = 4000)
    private String message;

    private Boolean handled = false;

    @Size(max = 128)
    private String source;
}
