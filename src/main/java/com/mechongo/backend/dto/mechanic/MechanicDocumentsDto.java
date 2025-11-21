package com.mechongo.backend.dto.mechanic;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Document metadata for mechanic verification.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MechanicDocumentsDto extends BaseDto {
    @Size(min = 36, max = 36)
    private String mechanicId;

    @Size(max = 64)
    private String documentType;

    @Size(max = 2000)
    private String documentUrl;

    @Size(max = 64)
    private String verificationStatus;

    @Size(max = 1000)
    private String note;
}
