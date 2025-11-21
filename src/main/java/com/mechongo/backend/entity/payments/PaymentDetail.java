package com.mechongo.backend.entity.payments;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.sos.SosRequest;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payment_details", indexes = {
        @Index(name = "idx_payment_user", columnList = "user_id"),
        @Index(name = "idx_payment_sos", columnList = "sos_request_id"),
        @Index(name = "idx_payment_provider_txn", columnList = "provider_txn_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetail extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sos_request_id")
    private SosRequest sosRequest;

    @Enumerated(EnumType.STRING)
    @Column(name = "method", length = 32)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", length = 32)
    private PaymentProvider provider;

    @Column(name = "provider_txn_id", length = 255)
    private String providerTxnId;

    @Column(name = "amount", precision = 19, scale = 4, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 32, nullable = false)
    private PaymentStatus status;

    @Column(name = "currency", length = 8)
    private String currency = "INR";

    @Column(name = "captured_at")
    private Instant capturedAt;

    @Column(name = "metadata", columnDefinition = "LONGTEXT")
    private String metadata; // provider response or JSON details
}
