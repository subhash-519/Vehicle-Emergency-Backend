package com.mechongo.backend.dto.payment;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Payment detail DTO.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailDto extends BaseDto {
    @Size(min = 36, max = 36)
    private String userId;

    @Size(min = 36, max = 36)
    private String sosRequestId;

    @Size(max = 64)
    private String method;

    @Size(max = 64)
    private String provider;

    @Size(max = 128)
    private String providerTxnId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;

    @Size(max = 64)
    private String status;

    @Size(max = 8)
    private String currency;

    private Instant capturedAt;

    @Size(max = 4000)
    private String metadata;
}
