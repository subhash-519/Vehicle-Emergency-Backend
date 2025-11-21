package com.mechongo.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Sign up DTO for new users.
 * At least one of email or phone should be provided - enforce this in service layer or via custom validator.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 255)
    private String name;

    @Email(message = "Invalid email")
    @Size(max = 320)
    private String email;

    // simple phone validation (digits, + allowed). Adjust the pattern to your country rules if needed.
    @Pattern(regexp = "^\\+?[0-9\\-\\s]{6,32}$", message = "Invalid phone number")
    @Size(max = 32)
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 128)
    private String password;

    // optional referral code
    @Size(max = 64)
    private String referralCode;

    // optional marketing consent
    private Boolean acceptMarketing = false;

    // optional device info
    @Size(max = 255)
    private String deviceId;

    @Size(max = 128)
    private String devicePlatform;
}
