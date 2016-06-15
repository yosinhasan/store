package com.epam.pp.hasan.entity;

/**
 * @author Yosin_Hasan
 */
public enum OrderStatus {
    ACCEPTED,
    SUBMITED,
    IN_PROCESS,
    SENT,
    COMPLETED,
    REJECTED;

    public static OrderStatus getStatus(int index) {
        if (index < 0 || index > 5) {
            return OrderStatus.ACCEPTED;
        }
        return OrderStatus.values()[index];
    }
}
