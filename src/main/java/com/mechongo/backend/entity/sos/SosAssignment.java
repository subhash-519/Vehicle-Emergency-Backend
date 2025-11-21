package com.mechongo.backend.entity.sos;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.mechanic.MechanicProfile;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sos_assignments", indexes = {
        @Index(name = "idx_sos_assignment_sos", columnList = "sos_request_id"),
        @Index(name = "idx_sos_assignment_mechanic", columnList = "mechanic_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SosAssignment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sos_request_id", nullable = false)
    private SosRequest sosRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id", nullable = false)
    private MechanicProfile mechanic;

    @Column(name = "assignment_status", length = 64, nullable = false)
    private String assignmentStatus;

    @Column(name = "distance_meters")
    private Double distanceMeters;

    @Column(name = "note", length = 500)
    private String note;
}
