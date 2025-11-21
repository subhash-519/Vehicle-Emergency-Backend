package com.mechongo.backend.entity.payout;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.mechanic.MechanicProfile;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payout_requests", indexes = {
        @Index(name = "idx_payout_mechanic", columnList = "mechanic_id"),
        @Index(name = "idx_payout_status", columnList = "status"),
        @Index(name = "idx_payout_provider_txn", columnList = "provider_txn_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayoutRequest extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id", nullable = false)
    private MechanicProfile mechanic;

    @Column(name = "amount", precision = 19, scale = 4, nullable = false)
    private BigDecimal amount;

    @Column(name = "method", length = 64)
    private String method;

    @Column(name = "provider_txn_id", length = 255)
    private String providerTxnId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PayoutStatus status = PayoutStatus.PENDING;

    @Column(name = "requested_at")
    private Instant requestedAt;

    @Column(name = "processed_at")
    private Instant processedAt;

    @Column(name = "failure_reason", length = 1000)
    private String failureReason;

    @Column(name = "metadata", columnDefinition = "LONGTEXT")
    private String metadata;
}
