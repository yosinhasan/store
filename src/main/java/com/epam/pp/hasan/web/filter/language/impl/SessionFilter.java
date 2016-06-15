package com.epam.pp.hasan.web.filter.language.impl;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.web.filter.language.LangFilter;
/**
 * 
 * @author Yosin_Hasan
 *
 */
public class SessionFilter extends LangFilter {
	private static final Logger LOG = Logger.getLogger(SessionFilter.class);

	public SessionFilter(LangFilter successor, ArrayList<Locale> locales, String localeStoreName, Long time) {
		super(successor, locales, localeStoreName, time);
	}

	@Override
	public Locale processRequest(HttpServletRequest request) {
		LOG.info("Session filter started");
		HttpSession session = request.getSession();
		Locale locale = (Locale) session.getAttribute(this.localeStoreName);
		LOG.info("Locale: " + locale);
		LOG.info("Session filter ended");
		if (locale == null) {
			if (successor != null) {
				return successor.processRequest(request);
			}
		}

		return locale;
	}

	@Override
	public void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		LOG.info("Save locale started");
		LOG.info("Locale to save: " + locale);
		request.getSession().setAttribute(localeStoreName, locale);
		LOG.info("Save locale ended");
	}

}
