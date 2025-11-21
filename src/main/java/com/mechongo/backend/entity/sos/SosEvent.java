package com.mechongo.backend.entity.sos;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sos_events", indexes = {
        @Index(name = "idx_sos_event_sos", columnList = "sos_request_id"),
        @Index(name = "idx_sos_event_type", columnList = "event_type")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SosEvent extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sos_request_id", nullable = false)
    private SosRequest sosRequest;

    /**
     * event types: MECHANIC_ACCEPTED, USER_CANCELED, ETA_UPDATED, LOCATION_UPDATED, PAYMENT_COMPLETED, etc.
     */
    @Column(name = "event_type", length = 128, nullable = false)
    private String eventType;

    @Column(name = "data_json", columnDefinition = "LONGTEXT")
    private String dataJson;

    @Column(name = "performed_by", length = 36)
    private String performedBy; // user ID, mechanic ID, system
}
