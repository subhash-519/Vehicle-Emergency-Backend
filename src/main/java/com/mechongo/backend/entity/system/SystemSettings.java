package com.mechongo.backend.entity.system;

import com.mechongo.backend.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "system_settings", uniqueConstraints = {
        @UniqueConstraint(name = "uk_setting_key", columnNames = "setting_key")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemSettings extends BaseEntity {

    @Column(name = "setting_key", nullable = false, length = 255)
    private String key;

    @Column(name = "setting_value", nullable = false, columnDefinition = "LONGTEXT")
    private String value;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "editable", nullable = false)
    private boolean editable = true;
}
