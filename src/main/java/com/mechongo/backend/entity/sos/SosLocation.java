package com.mechongo.backend.entity.sos;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "sos_locations", indexes = {
        @Index(name = "idx_sos_location_sos", columnList = "sos_request_id"),
        @Index(name = "idx_sos_location_type", columnList = "location_type")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SosLocation extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sos_request_id", nullable = false)
    private SosRequest sosRequest;

    @Column(name = "location_type", length = 64)
    private String locationType;

    @Column(name = "location", columnDefinition = "geometry(Point,4326)", nullable = false)
    private Point location;

    @Column(name = "note", length = 500)
    private String note;
}
