package com.mechongo.backend.dto.mechanic;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * DTO representing a mechanic's skill.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MechanicSkillDto extends BaseDto {
    @Size(min = 36, max = 36)
    private String mechanicId;

    @Size(max = 128)
    private String skill;

    @Size(max = 64)
    private String level; // beginner/intermediate/expert

    @Min(value = 0, message = "yearsExperience must be >= 0")
    private Integer yearsExperience;

    @Size(max = 1000)
    private String notes;
}
