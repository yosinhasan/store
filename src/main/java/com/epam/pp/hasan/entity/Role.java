package com.epam.pp.hasan.entity;

/**
 * Role entity.
 * 
 * @author Yosin_Hasan
 * 
 */

public enum Role {

	ADMIN, USER;
	public static Role getRole(int index) {
		if (index >= Role.values().length) {
			index = Role.USER.ordinal();
		}
		return Role.values()[index];
	}

	public String getName() {
		return name().toLowerCase();
	}

}
