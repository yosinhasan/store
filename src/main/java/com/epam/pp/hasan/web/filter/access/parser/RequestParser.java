package com.epam.pp.hasan.web.filter.access.parser;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public interface RequestParser {
	void parse(HttpServletRequest request);

	String getAction();

	Map<String, String> getQuery();
}
