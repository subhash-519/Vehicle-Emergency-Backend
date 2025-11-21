package com.mechongo.backend.dto.payment;

import com.mechongo.backend.dto.common.BaseDto;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Payout request DTO for mechanics.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PayoutRequestDto extends BaseDto {
    @NotBlank
    @Size(min = 36, max = 36)
    private String mechanicId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;

    @Size(max = 64)
    private String method;

    @Size(max = 128)
    private String providerTxnId;

    @Size(max = 64)
    private String status;

    private Instant requestedAt;
    private Instant processedAt;

    @Size(max = 1000)
    private String failureReason;

    @Size(max = 4000)
    private String metadata;
}
