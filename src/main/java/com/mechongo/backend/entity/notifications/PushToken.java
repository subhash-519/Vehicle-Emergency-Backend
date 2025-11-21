package com.mechongo.backend.entity.notifications;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "push_tokens", uniqueConstraints = {
        @UniqueConstraint(name = "uk_push_token_value", columnNames = "token")
}, indexes = {
        @Index(name = "idx_push_token_user", columnList = "user_id"),
        @Index(name = "idx_push_token_platform", columnList = "platform")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushToken extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "token", nullable = false, length = 1000)
    private String token;

    @Column(name = "platform", length = 50)
    private String platform;  //Device or platform (ANDROID, IOS, WEB)

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadata;
}
