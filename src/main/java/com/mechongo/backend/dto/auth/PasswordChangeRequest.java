package com.mechongo.backend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Change password: user provides current password and new password (authenticated endpoint).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeRequest {

    @NotBlank(message = "Current password is required")
    @Size(min = 8, max = 128)
    private String currentPassword;

    @NotBlank(message = "New password is required")
    @Size(min = 8, max = 128)
    private String newPassword;
}
