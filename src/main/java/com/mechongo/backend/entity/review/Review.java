package com.mechongo.backend.entity.review;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.mechanic.MechanicProfile;
import com.mechongo.backend.entity.user.User;
import com.mechongo.backend.entity.sos.SosRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviews", indexes = {
        @Index(name = "idx_review_mechanic", columnList = "mechanic_id"),
        @Index(name = "idx_review_user", columnList = "user_id"),
        @Index(name = "idx_review_sos", columnList = "sos_request_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id", nullable = false)
    private MechanicProfile mechanic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sos_request_id")
    private SosRequest sosRequest;  //Optional link to the related SOS request.

    @Column(name = "rating", nullable = false)
    private Integer rating; // 1..5

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "comment", length = 2000)
    private String comment;

    @Column(name = "visible", nullable = false)
    private boolean visible = true;

    @Column(name = "reported", nullable = false)
    private boolean reported = false;

    @Column(name = "report_reason", length = 1000)
    private String reportReason;
}
