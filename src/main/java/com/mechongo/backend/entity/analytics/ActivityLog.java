package com.mechongo.backend.entity.analytics;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "activity_logs", indexes = {
        @Index(name = "idx_activity_user", columnList = "user_id"),
        @Index(name = "idx_activity_event", columnList = "event_type")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * LOGIN, LOGOUT, SOS_SUBMITTED, PAYMENT_SUCCESS, PAGE_VIEW, BUTTON_CLICK, etc.
     */
    @Column(name = "event_type", length = 128, nullable = false)
    private String eventType;

    @Column(name = "metadata", columnDefinition = "LONGTEXT")
    private String metadata;
}
