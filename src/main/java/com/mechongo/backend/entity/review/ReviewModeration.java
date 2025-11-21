package com.mechongo.backend.entity.review;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "review_moderation", indexes = {
        @Index(name = "idx_moderation_review", columnList = "review_id"),
        @Index(name = "idx_moderation_moderator", columnList = "moderator_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModeration extends BaseEntity {

    @Column(name = "review_id", length = 36, nullable = false)
    private String reviewId; // store FK as string to avoid heavy joins in moderation panel

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id")
    private User moderator; // the staff/admin who took action

    @Column(name = "action", length = 64, nullable = false)
    private String action; // HIDE, UNHIDE, WARNING_ISSUED, REMOVE, RESTORE

    @Column(name = "reason", length = 2000)
    private String reason;

    @Column(name = "actioned_at")
    private Instant actionedAt;
}
