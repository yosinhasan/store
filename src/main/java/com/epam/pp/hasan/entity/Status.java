package com.epam.pp.hasan.entity;

/**
 * @author Yosin_Hasan
 */
public enum Status {
	AVAILABLE, NOT_AVAILABLE;

	public static Status getStatus(int index) {
		if (index < 0 || index > 1) {
			return Status.NOT_AVAILABLE;
		}
		return Status.values()[index];
	}
}
