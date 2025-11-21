package com.mechongo.backend.dto.user;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * DTO for user preferences/settings.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserSettingsDto extends BaseDto {
    @Size(min = 36, max = 36)
    private String userId;

    @NotNull(message = "notificationsEnabled required")
    private Boolean notificationsEnabled;

    private Boolean smsEnabled;
    private Boolean emailEnabled;

    @Size(max = 16)
    private String preferredCurrency;

    @Size(max = 64)
    private String preferredPaymentMethod;

    private Boolean receivePromos;
}
