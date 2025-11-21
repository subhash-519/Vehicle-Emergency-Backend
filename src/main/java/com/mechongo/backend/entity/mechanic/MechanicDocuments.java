package com.mechongo.backend.entity.mechanic;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mechanic_documents", indexes = {
        @Index(name = "idx_mechdocs_mechanic", columnList = "mechanic_id"),
        @Index(name = "idx_mechdocs_type", columnList = "document_type")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MechanicDocuments extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id", nullable = false)
    private MechanicProfile mechanic;

    @Column(name = "document_type", length = 128, nullable = false)
    private String documentType; // e.g., "LICENSE", "VEHICLE_REGISTRATION", "ID_PROOF"

    @Column(name = "document_url", length = 1000, nullable = false)
    private String documentUrl;

    @Column(name = "verification_status", length = 64)
    private String verificationStatus; // PENDING, VERIFIED, REJECTED

    @Column(name = "note", length = 1000)
    private String note;
}
