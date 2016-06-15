package com.epam.pp.hasan.web.filter.access.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.epam.pp.hasan.entity.Role;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Constraint", propOrder = { "urlPattern", "role" })
public class Constraint {

	@XmlElement(name = "url-pattern", required = true)
	protected String urlPattern;
	@XmlElement(required = true)
	protected List<Role> role;

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String value) {
		this.urlPattern = value;
	}

	public List<Role> getRole() {
		if (role == null) {
			role = new ArrayList<Role>();
		}
		return this.role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((urlPattern == null) ? 0 : urlPattern.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Constraint other = (Constraint) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (urlPattern == null) {
			if (other.urlPattern != null)
				return false;
		} else if (!urlPattern.equals(other.urlPattern))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Constraint [urlPattern=" + urlPattern + ", role=" + role + "]";
	}

}
