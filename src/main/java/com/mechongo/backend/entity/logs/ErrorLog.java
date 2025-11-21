package com.mechongo.backend.entity.logs;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "error_logs", indexes = {
        @Index(name = "idx_error_level", columnList = "error_level"),
        @Index(name = "idx_error_code", columnList = "error_code")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorLog extends BaseEntity {

    @Column(name = "error_level", length = 32)
    private String errorLevel; // INFO, WARNING, ERROR, CRITICAL

    @Column(name = "error_code", length = 64)
    private String errorCode;

    @Column(name = "message", length = 2000)
    private String message;

    @Column(name = "stack_trace", columnDefinition = "LONGTEXT")
    private String stackTrace;

    @Column(name = "metadata", columnDefinition = "LONGTEXT")
    private String metadata; // device info, request info, etc.
}
