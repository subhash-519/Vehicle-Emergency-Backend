package com.mechongo.backend.entity.user;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * UserKYC: KYC records for users (IDs, vehicle docs, etc.).
 * Keep payloads minimal here; store document URLs in a document table if needed.
 */
@Entity
@Table(name = "user_kyc", indexes = {
        @Index(name = "idx_userkyc_user", columnList = "user_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserKYC extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "kyc_id_number", length = 128)
    private String kycIdNumber;

    @Column(name = "kyc_type", length = 64)
    private String kycType; // e.g., "Aadhaar", "VoterID", "Passport", "DRIVING_LICENSE"

    @Column(name = "document_url", length = 1000)
    private String documentUrl;

    @Column(name = "verification_status", length = 64)
    private String verificationStatus; // PENDING, VERIFIED, REJECTED (string to keep migrations simple)

    @Column(name = "rejection_reason", length = 1000)
    private String rejectionReason;
}
