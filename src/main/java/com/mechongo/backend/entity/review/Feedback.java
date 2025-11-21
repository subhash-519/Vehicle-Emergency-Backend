package com.mechongo.backend.entity.review;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "feedbacks", indexes = {
        @Index(name = "idx_feedback_user", columnList = "user_id"),
        @Index(name = "idx_feedback_category", columnList = "category")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feedback extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // optional

    @Column(name = "category", length = 128)
    private String category; // e.g., "UX", "PAYMENT", "MECHANIC"

    @Column(name = "subject", length = 255)
    private String subject;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "handled", nullable = false)
    private boolean handled = false;

    @Column(name = "source", length = 64)
    private String source; // web, android, ios
}
