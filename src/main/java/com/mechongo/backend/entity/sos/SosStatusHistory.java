package com.mechongo.backend.entity.sos;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sos_status_history", indexes = {
        @Index(name = "idx_sos_status_history_sos", columnList = "sos_request_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SosStatusHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sos_request_id", nullable = false)
    private SosRequest sosRequest;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SosStatus status;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "changed_by", length = 36)
    private String changedBy; // user id, mechanic id, or SYSTEM
}
