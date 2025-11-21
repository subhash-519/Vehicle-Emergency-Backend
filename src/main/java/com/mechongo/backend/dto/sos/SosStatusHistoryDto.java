package com.mechongo.backend.dto.sos;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * Status history entries for SOS requests.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SosStatusHistoryDto extends BaseDto {
    @NotBlank
    @Size(min = 36, max = 36)
    private String sosRequestId;

    @NotBlank
    @Size(max = 64)
    private String status;

    @Size(max = 2000)
    private String notes;

    @Size(min = 36, max = 36)
    private String changedBy;

    private Instant changedAt;
}
