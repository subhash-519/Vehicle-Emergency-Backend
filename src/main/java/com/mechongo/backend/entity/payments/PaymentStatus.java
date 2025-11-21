package com.mechongo.backend.entity.payments;

public enum PaymentStatus {
    PENDING,        // created but not processed
    AUTHORIZED,     // authorized (card/UPI)
    CAPTURED,       // captured / completed
    FAILED,         // failed
    REFUNDED,       // refunded fully or partially
    CANCELLED       // cancelled before capture
}
