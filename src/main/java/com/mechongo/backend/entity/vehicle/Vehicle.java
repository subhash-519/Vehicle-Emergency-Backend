package com.mechongo.backend.entity.vehicle;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles", indexes = {
        @Index(columnList = "user_id", name = "idx_vehicle_user"),
        @Index(columnList = "reg_no", name = "idx_vehicle_reg")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    private String make;
    private String model;

    private Integer year;

    @Column(name = "reg_no")
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private String color;
}
