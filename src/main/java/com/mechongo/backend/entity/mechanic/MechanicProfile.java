package com.mechongo.backend.entity.mechanic;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "mechanic_profiles", indexes = {
        @Index(name = "idx_mech_user", columnList = "user_id"),
        @Index(name = "idx_mech_availability", columnList = "availability")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MechanicProfile extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "current_location", columnDefinition = "geometry(Point,4326)")
    private Point currentLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability", nullable = false)
    private MechanicAvailability availability = MechanicAvailability.OFFLINE;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "primary_speciality", length = 255)
    private String primarySpeciality;

    @Column(name = "service_radius_meters")
    private Integer serviceRadiusMeters;

    @Column(name = "verified", nullable = false)
    private boolean verified = false;
}
