package com.epam.pp.hasan.entity;

/**
 * @author Yosin_Hasan
 */
public enum Sex {
    MALE,
    FEMALE;

    public static Sex getSex(int value) {
        if (value > 1 || value < 0) {
            return MALE;
        }
        return Sex.values()[value];
    }
}
