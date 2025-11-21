package com.mechongo.backend.entity.referral;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "referrals", uniqueConstraints = {
        @UniqueConstraint(name = "uk_referrer_referred", columnNames = {"referrer_id", "referred_id"})
}, indexes = {
        @Index(name = "idx_referrer", columnList = "referrer_id"),
        @Index(name = "idx_referred", columnList = "referred_id"),
        @Index(name = "idx_code", columnList = "referral_code")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Referral extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referrer_id")
    private User referrer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referred_id")
    private User referred;

    @Column(name = "referral_code", length = 64)
    private String referralCode;

    @Column(name = "rewarded", nullable = false)
    private boolean rewarded = false;

    @Column(name = "rewarded_at")
    private Instant rewardedAt;

    @Column(name = "metadata", columnDefinition = "LONGTEXT")
    private String metadata;
}
