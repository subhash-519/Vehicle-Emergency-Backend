package com.mechongo.backend.entity.wallet;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "wallet_transactions", indexes = {
        @Index(name = "idx_wtxn_user", columnList = "user_id"),
        @Index(name = "idx_wtxn_type", columnList = "wallet_type")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "wallet_type", nullable = false, length = 32)
    private WalletType walletType;

    @Column(name = "amount", precision = 19, scale = 4, nullable = false)
    private BigDecimal amount;

   // CREDIT or DEBIT
    @Column(name = "transaction_type", length = 32, nullable = false)
    private String transactionType;

    // Optional reference (SOS request, payment ID, refund ID, etc.)
    @Column(name = "reference_id", length = 255)
    private String referenceId;

    @Column(name = "description", length = 1000)
    private String description;
}
