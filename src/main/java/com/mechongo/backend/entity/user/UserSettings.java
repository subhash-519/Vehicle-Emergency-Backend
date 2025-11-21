package com.mechongo.backend.entity.user;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_settings", indexes = {
        @Index(name = "idx_usersettings_user", columnList = "user_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSettings extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "notifications_enabled", nullable = false)
    private boolean notificationsEnabled = true;

    @Column(name = "sms_enabled", nullable = false)
    private boolean smsEnabled = true;

    @Column(name = "email_enabled", nullable = false)
    private boolean emailEnabled = true;

    @Column(name = "preferred_currency", length = 16)
    private String preferredCurrency = "INR";

    @Column(name = "preferred_payment_method", length = 64)
    private String preferredPaymentMethod;

    @Column(name = "receive_promos", nullable = false)
    private boolean receivePromos = true;
}
