package com.mechongo.backend.entity.sos;

public enum SosStatus {
    REQUESTED,          // User created SOS request
    ASSIGNED,           // Mechanic assigned
    MECHANIC_EN_ROUTE,  // Mechanic is on the way
    MECHANIC_ARRIVED,   // Mechanic reached the location
    WORK_IN_PROGRESS,   // SOS work started
    COMPLETED,          // Service completed
    CANCELED,           // Cancelled by user or system
    REJECTED            // Rejected by mechanics or system
}
