package com.mechongo.backend.entity.auth;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "otp_verifications", indexes = {
        @Index(name = "idx_otp_user", columnList = "user_id"),
        @Index(name = "idx_otp_target", columnList = "target"),
        @Index(name = "idx_otp_code_hash", columnList = "code_hash")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpVerification extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "target", length = 255, nullable = false)
    private String target;

    @Column(name = "purpose", length = 128, nullable = false)
    private String purpose;

    @Column(name = "code_hash", length = 512, nullable = false)
    private String codeHash;

    @Column(name = "attempts", nullable = false)
    private int attempts = 0;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "consumed", nullable = false)
    private boolean consumed = false;

    @Column(name = "channel", length = 64)
    private String channel;

    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadata;
}
