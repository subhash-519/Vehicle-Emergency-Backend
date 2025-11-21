package com.mechongo.backend.entity.logs;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "api_request_logs", indexes = {
        @Index(name = "idx_api_method", columnList = "http_method"),
        @Index(name = "idx_api_status", columnList = "status_code"),
        @Index(name = "idx_api_path", columnList = "path")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequestLog extends BaseEntity {

    @Column(name = "http_method", length = 16)
    private String httpMethod;  // GET, POST, etc.

    @Column(name = "path", length = 1000)
    private String path;

    @Column(name = "status_code")
    private Integer statusCode;

    @Column(name = "ip_address", length = 64)
    private String ipAddress;

    @Column(name = "latency_ms")
    private Long latencyMs;

    @Column(name = "request_body", columnDefinition = "LONGTEXT")
    private String requestBody;

    @Column(name = "response_body", columnDefinition = "LONGTEXT")
    private String responseBody;

    @Column(name = "headers", columnDefinition = "LONGTEXT")
    private String headers;

    @Column(name = "metadata", columnDefinition = "LONGTEXT")
    private String metadata; // device info, user agent, etc.
}
