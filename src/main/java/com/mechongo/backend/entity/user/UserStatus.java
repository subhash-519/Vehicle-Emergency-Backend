package com.mechongo.backend.entity.user;

public enum UserStatus {
    ACTIVE,
    INACTIVE,
    PENDING,    // not fully onboarded / awaiting verification
    SUSPENDED,
    BANNED
}
