package com.mechongo.backend.entity.sos;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.mechanic.MechanicProfile;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "sos_requests", indexes = {
        @Index(name = "idx_sos_requester", columnList = "requester_id"),
        @Index(name = "idx_sos_status", columnList = "status"),
        @Index(name = "idx_sos_assigned_mechanic", columnList = "assigned_mechanic_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SosRequest extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_mechanic_id")
    private MechanicProfile assignedMechanic;

    @Column(name = "pickup_location", columnDefinition = "geometry(Point,4326)", nullable = false)
    private Point pickupLocation;

    @Column(name = "drop_location", columnDefinition = "geometry(Point,4326)")
    private Point dropLocation;

    @Column(name = "description", length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SosStatus status = SosStatus.REQUESTED;

    @Column(name = "estimated_amount", precision = 19, scale = 4)
    private BigDecimal estimatedAmount;

    @Column(name = "actual_amount", precision = 19, scale = 4)
    private BigDecimal actualAmount;

    @Column(name = "priority")
    private Integer priority = 5;

    @Column(name = "scheduled_at")
    private Instant scheduledAt;

    @Column(name = "expected_arrival_at")
    private Instant expectedArrivalAt;

    @Column(name = "canceled_by", length = 36)
    private String canceledBy; // userId or system identifier

    @Column(name = "cancel_reason", length = 1000)
    private String cancelReason;
}
