package com.mechongo.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Login using email+password or phone+password.
 * Server should accept either email or phone (or both) and validate existence.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    // either email or phone must be provided (enforce in service with cross-field validation)
    @Email(message = "Invalid email")
    @Size(max = 320)
    private String email;

    @Size(max = 32)
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 128, message = "Password length must be between 8 and 128")
    private String password;

    // optional: device info for push token linking
    @Size(max = 255)
    private String deviceId;

    @Size(max = 128)
    private String devicePlatform; // web | android | ios
}
