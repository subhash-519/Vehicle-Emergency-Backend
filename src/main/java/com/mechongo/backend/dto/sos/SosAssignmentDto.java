package com.mechongo.backend.dto.sos;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Assignment DTO linking mechanic to SOS request.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SosAssignmentDto extends BaseDto {
    @NotBlank
    @Size(min = 36, max = 36)
    private String sosRequestId;

    @NotBlank
    @Size(min = 36, max = 36)
    private String mechanicId;

    @Size(max = 64)
    private String assignmentStatus;

    @DecimalMin(value = "0.0")
    private Double distanceMeters;

    @Size(max = 1000)
    private String note;
}
