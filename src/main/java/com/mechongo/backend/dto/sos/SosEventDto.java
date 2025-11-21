package com.mechongo.backend.dto.sos;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * Event DTO for storing arbitrary SOS events.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SosEventDto extends BaseDto {
    @NotBlank
    @Size(min = 36, max = 36)
    private String sosRequestId;

    @NotBlank
    @Size(max = 128)
    private String eventType;

    @Size(max = 4000)
    private String dataJson;

    @Size(min = 36, max = 36)
    private String performedBy;

    private Instant occurredAt;
}
