package com.epam.pp.hasan.web.filter.access.xml;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class SecurityFactory {

	public SecurityFactory() {
	}

	public Security createSecurity() {
		return new Security();
	}

	public Constraint createConstraint() {
		return new Constraint();
	}

}
