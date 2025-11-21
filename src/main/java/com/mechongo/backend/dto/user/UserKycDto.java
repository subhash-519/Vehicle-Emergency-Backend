package com.mechongo.backend.dto.user;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * DTO for KYC documents and status.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserKycDto extends BaseDto {
    @Size(min = 36, max = 36)
    private String userId;

    @NotBlank(message = "KYC type is required")
    @Size(max = 64)
    private String kycType; // e.g., PAN, DRIVING_LICENSE, AADHAAR

    @Size(max = 128)
    private String kycIdNumber;

    @Size(max = 2000)
    private String documentUrl;

    @Size(max = 64)
    private String verificationStatus; // maps to VerificationStatus enum

    @Size(max = 2000)
    private String rejectionReason;
}
