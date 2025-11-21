package com.mechongo.backend.entity.user;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_profiles", indexes = {
        @Index(name = "idx_userprofile_user", columnList = "user_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "first_name", length = 150)
    private String firstName;

    @Column(name = "last_name", length = 150)
    private String lastName;

    @Column(name = "display_name", length = 200)
    private String displayName;

    @Column(name = "date_of_birth")
    private String dateOfBirth; // ISO yyyy-MM-dd or use LocalDate in DTOs/services

    @Column(name = "gender", length = 32)
    private String gender;

    @Column(name = "bio", length = 1000)
    private String bio;

    @Column(name = "profile_pic_url", length = 1000)
    private String profilePicUrl;

    @Column(name = "preferred_language", length = 50)
    private String preferredLanguage;

    @Column(name = "locale", length = 32)
    private String locale;
}
