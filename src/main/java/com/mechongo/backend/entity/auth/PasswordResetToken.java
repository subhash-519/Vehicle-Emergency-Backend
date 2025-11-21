package com.mechongo.backend.entity.auth;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "password_reset_tokens", indexes = {
        @Index(name = "idx_prt_user", columnList = "user_id"),
        @Index(name = "idx_prt_token_hash", columnList = "token_hash")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "token_hash", length = 64, nullable = false)
    private String tokenHash;

    @Lob
    @Column(name = "token_text", columnDefinition = "LONGTEXT")
    private String tokenText;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @Column(name = "used", nullable = false)
    private boolean used = false;

    @Column(name = "purpose", length = 64)
    private String purpose; // e.g., RESET_PASSWORD, VERIFY_EMAIL

    @Column(name = "requested_by_ip", length = 45)
    private String requestedByIp;

    @Column(name = "consumed_at")
    private Instant consumedAt;
}
