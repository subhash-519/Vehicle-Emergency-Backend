package com.mechongo.backend.entity.payments;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "refunds", indexes = {
        @Index(name = "idx_refund_user", columnList = "user_id"),
        @Index(name = "idx_refund_payment", columnList = "payment_detail_id"),
        @Index(name = "idx_refund_provider_txn", columnList = "provider_txn_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Refund extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "payment_detail_id", length = 36)
    private String paymentDetailId;

    @Column(name = "provider_txn_id", length = 255)
    private String providerTxnId;

    @Column(name = "amount", precision = 19, scale = 4, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 32, nullable = false)
    private PaymentStatus status;

    @Column(name = "requested_at")
    private Instant requestedAt;

    @Column(name = "processed_at")
    private Instant processedAt;

    @Column(name = "reason", length = 1000)
    private String reason;

    @Column(name = "metadata", columnDefinition = "LONGTEXT")
    private String metadata;
}
