package com.mechongo.backend.entity.notifications;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.notifications.NotificationChannel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification_templates", indexes = {
        @Index(name = "idx_template_name", columnList = "name"),
        @Index(name = "idx_template_channel", columnList = "channel")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTemplate extends BaseEntity {

    @Column(name = "name", nullable = false, length = 255)
    private String name; // e.g., SOS_ASSIGNED, PAYMENT_SUCCESS

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", nullable = false, length = 32)
    private NotificationChannel channel; // PUSH, SMS, EMAIL

    @Column(name = "subject", length = 500)
    private String subject; // for email

    @Column(name = "body", columnDefinition = "LONGTEXT")
    private String body; // template text with placeholders

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "remarks", length = 1000)
    private String remarks;
}
