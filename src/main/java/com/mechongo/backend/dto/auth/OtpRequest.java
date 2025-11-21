package com.mechongo.backend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Request to send an OTP to a phone or email.
 * Provide either phone or email.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpRequest {

    @Pattern(regexp = "^\\+?[0-9\\-\\s]{6,32}$", message = "Invalid phone number")
    @Size(max = 32)
    private String phone;

    @jakarta.validation.constraints.Email(message = "Invalid email")
    @Size(max = 320)
    private String email;

    @Size(max = 64)
    private String purpose; // LOGIN, SIGNUP, PASSWORD_RESET, VERIFY_KYC, etc.

    @Size(max = 255)
    private String deviceId;
}
