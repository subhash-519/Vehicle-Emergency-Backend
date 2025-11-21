package com.mechongo.backend.dto.payment;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Refund DTO.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RefundDto extends BaseDto {
    @Size(min = 36, max = 36)
    private String userId;

    @Size(min = 36, max = 36)
    private String paymentDetailId;

    @Size(max = 128)
    private String providerTxnId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;

    @Size(max = 64)
    private String status;

    private Instant requestedAt;
    private Instant processedAt;

    @Size(max = 1000)
    private String reason;

    @Size(max = 4000)
    private String metadata;
}
