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
 * Request to initiate password reset (send OTP or email link).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetRequest {

    @jakarta.validation.constraints.Email(message = "Invalid email")
    @Size(max = 320)
    private String email;

    @Pattern(regexp = "^\\+?[0-9\\-\\s]{6,32}$", message = "Invalid phone number")
    @Size(max = 32)
    private String phone;

    // optional: where to send (OTP or EMAIL_LINK)
    @Size(max = 64)
    private String method;
}
