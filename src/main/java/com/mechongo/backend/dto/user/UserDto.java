package com.mechongo.backend.dto.user;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Main User DTO exchanged over APIs.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    @NotBlank(message = "Name is required")
    @Size(max = 255)
    private String name;

    @Email(message = "Invalid email")
    @Size(max = 320)
    private String email;

    @NotBlank(message = "Phone is required")
    @Size(max = 32)
    private String phone;

    @NotBlank(message = "Role is required")
    @Size(max = 64)
    private String role; // maps to UserRole enum string

    @NotBlank(message = "Status is required")
    @Size(max = 64)
    private String status; // maps to UserStatus enum string

    @Size(max = 2000)
    private String profilePicUrl;

    private boolean active = true;
}
