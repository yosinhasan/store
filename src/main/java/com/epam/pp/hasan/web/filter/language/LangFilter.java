package com.epam.pp.hasan.web.filter.language;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class LangFilter {

	protected LangFilter successor;
	protected ArrayList<Locale> locales;
	protected String localeStoreName;
	protected Long time;

	public LangFilter(LangFilter successor, ArrayList<Locale> locales, String localeStoreName, Long time) {
		this.successor = successor;
		this.locales = locales;
		this.localeStoreName = localeStoreName;
		this.time = time;
	}

	/**
	 * Process request.
	 * 
	 * @param request
	 *            request
	 */
	abstract public Locale processRequest(HttpServletRequest request);

	/**
	 * Save locale either in session or cookie.
	 * 
	 * @param request
	 *            request
	 */
	abstract public void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale);

}
