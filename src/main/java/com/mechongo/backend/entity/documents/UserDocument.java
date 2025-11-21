package com.mechongo.backend.entity.documents;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_documents", indexes = {
        @Index(name = "idx_user_document_user", columnList = "user_id"),
        @Index(name = "idx_user_document_type", columnList = "document_type"),
        @Index(name = "idx_user_document_status", columnList = "verification_status")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDocument extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false, length = 64)
    private DocumentType documentType;

    @Column(name = "document_url", nullable = false, length = 1000)
    private String documentUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", nullable = false, length = 32)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    @Column(name = "remarks", length = 1000)
    private String remarks;
}
