package com.mechongo.backend.entity.analytics;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "metric_events", indexes = {
        @Index(name = "idx_metric_type", columnList = "metric_type"),
        @Index(name = "idx_metric_category", columnList = "category")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricEvent extends BaseEntity {

    @Column(name = "metric_type", length = 128, nullable = false)
    private String metricType; // SOS_COMPLETED, REVIEW_SUBMITTED, MECHANIC_ONLINE

    @Column(name = "category", length = 128)
    private String category; // business category or grouping

    @Column(name = "value", length = 255)
    private String value; // numeric / string metric value (store simple values here)

    @Column(name = "data", columnDefinition = "LONGTEXT")
    private String data; // JSON payload for additional details
}
