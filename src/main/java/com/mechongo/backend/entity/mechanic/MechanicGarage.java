package com.mechongo.backend.entity.mechanic;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "mechanic_garages", indexes = {
        @Index(name = "idx_garage_mechanic", columnList = "mechanic_id"),
        @Index(name = "idx_garage_name", columnList = "name")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MechanicGarage extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id", nullable = false)
    private MechanicProfile mechanic;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "address_line1", length = 500)
    private String addressLine1;

    @Column(name = "address_line2", length = 500)
    private String addressLine2;

    @Column(name = "city", length = 128)
    private String city;

    @Column(name = "state", length = 128)
    private String state;

    @Column(name = "country", length = 128)
    private String country;

    @Column(name = "postal_code", length = 32)
    private String postalCode;

    @Column(name = "location", columnDefinition = "geometry(Point,4326)")
    private Point location;

    @Column(name = "opening_hours", length = 255)
    private String openingHours; // e.g., "Mon-Sat 09:00-18:00"

    @Column(name = "contact_number", length = 64)
    private String contactNumber;

    @Column(name = "is_primary", nullable = false)
    private boolean primaryGarage = false;
}
