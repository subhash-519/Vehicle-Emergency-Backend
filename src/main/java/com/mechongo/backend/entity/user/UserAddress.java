package com.mechongo.backend.entity.user;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "user_addresses", indexes = {
        @Index(name = "idx_useraddress_user", columnList = "user_id"),
        @Index(name = "idx_useraddress_label", columnList = "label")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "label", length = 64)
    private String label; // e.g., HOME, WORK, GARAGE

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

    @Column(name = "is_default", nullable = false)
    private boolean defaultAddress = false;
}
