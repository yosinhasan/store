package com.epam.pp.hasan.web.filter.access.map;

import java.util.Map;

import com.epam.pp.hasan.entity.Role;

public class AccessMapHolder {
	private Map<Role, AccessMap> map;

	public AccessMapHolder(Map<Role, AccessMap> map) {
		this.map = map;
	}

	public AccessMap get(final Role role) {
		if (role == null || !map.containsKey(role)) {
			return map.get(Role.USER);
		}
		return map.get(role);
	}

}
