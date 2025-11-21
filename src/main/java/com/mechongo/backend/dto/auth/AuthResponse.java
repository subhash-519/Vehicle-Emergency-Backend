package com.mechongo.backend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Response returned on successful authentication.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    @NotBlank
    @Size(max = 4096)
    private String accessToken;

    @NotBlank
    @Size(max = 4096)
    private String refreshToken;

    /**
     * expiresIn in seconds
     */
    private Long expiresIn;

    @Size(max = 64)
    private String tokenType = "Bearer";

    // optional user id and role that frontend may need immediately
    @Size(min = 36, max = 36)
    private String userId;

    @Size(max = 64)
    private String role;
}
