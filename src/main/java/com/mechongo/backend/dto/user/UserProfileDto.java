package com.mechongo.backend.dto.user;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * DTO for detailed user profile info.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto extends BaseDto {
    @Size(min = 36, max = 36, message = "userId must be UUID string")
    private String userId;

    @Size(max = 128)
    private String firstName;

    @Size(max = 128)
    private String lastName;

    @Size(max = 256)
    private String displayName;

    @Past(message = "dateOfBirth must be in the past")
    private LocalDate dateOfBirth;

    @Size(max = 32)
    private String gender;

    @Size(max = 2000)
    private String bio;

    @Size(max = 2000)
    private String profilePicUrl;

    @Size(max = 32)
    private String preferredLanguage;

    @Size(max = 32)
    private String locale;
}
