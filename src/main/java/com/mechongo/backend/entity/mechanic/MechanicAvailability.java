package com.mechongo.backend.entity.mechanic;

public enum MechanicAvailability {
    ONLINE,
    OFFLINE,
    BUSY,
    ON_CALL,    // temporarily reserved for a call / job
    AWAY        // short term break
}
