package com.mechongo.backend.entity.mechanic;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mechanic_rating_summary", indexes = {
        @Index(name = "idx_rating_summary_mechanic", columnList = "mechanic_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MechanicRatingSummary extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id", nullable = false, unique = true)
    private MechanicProfile mechanic;

    @Column(name = "total_ratings", nullable = false)
    private Integer totalRatings = 0;

    @Column(name = "total_reviews", nullable = false)
    private Integer totalReviews = 0;

    @Column(name = "average_rating")
    private Double averageRating = 0.0;

    @Column(name = "last_recalculated_at")
    private java.time.Instant lastRecalculatedAt;
}
