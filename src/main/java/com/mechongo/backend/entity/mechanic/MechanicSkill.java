package com.mechongo.backend.entity.mechanic;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mechanic_skills", uniqueConstraints = {
        @UniqueConstraint(name = "uk_mech_skill", columnNames = {"mechanic_id", "skill"})
}, indexes = {
        @Index(name = "idx_mechskill_mechanic", columnList = "mechanic_id"),
        @Index(name = "idx_mechskill_skill", columnList = "skill")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MechanicSkill extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id", nullable = false)
    private MechanicProfile mechanic;

    @Column(name = "skill", nullable = false, length = 200)
    private String skill;

    @Column(name = "level", length = 64)
    private String level; // e.g., Beginner, Intermediate, Expert

    @Column(name = "years_experience")
    private Integer yearsExperience;

    @Column(name = "notes", length = 1000)
    private String notes;
}
