package com.mechongo.backend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Verify OTP code sent to phone or email.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpVerificationRequest {

    // one of phone/email must be present; cross-field validation in service or custom validator
    @Pattern(regexp = "^\\+?[0-9\\-\\s]{6,32}$", message = "Invalid phone number")
    @Size(max = 32)
    private String phone;

    @jakarta.validation.constraints.Email(message = "Invalid email")
    @Size(max = 320)
    private String email;

    @NotBlank(message = "OTP code is required")
    @Size(min = 4, max = 8, message = "OTP length must be between 4 and 8")
    private String code;

    @Size(max = 64)
    private String purpose; // LOGIN, SIGNUP, PASSWORD_RESET, etc.

    @Size(max = 255)
    private String deviceId;
}
