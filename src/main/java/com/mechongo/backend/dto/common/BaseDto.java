package com.mechongo.backend.dto.common;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * Base DTO fields
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    private String id;
    private Instant createdAt;
    private Instant updatedAt;
    private Long version;
}
