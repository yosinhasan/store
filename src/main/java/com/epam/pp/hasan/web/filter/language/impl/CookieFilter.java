package com.epam.pp.hasan.web.filter.language.impl;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.web.filter.language.LangFilter;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public class CookieFilter extends LangFilter {
	private static final Logger LOG = Logger.getLogger(CookieFilter.class);

	public CookieFilter(LangFilter successor, ArrayList<Locale> locales, String localeStoreName, Long time) {
		super(successor, locales, localeStoreName, time);
	}

	@Override
	public Locale processRequest(HttpServletRequest request) {
		LOG.info("Cookie filter started");
		Cookie[] cookies = request.getCookies();
		Locale locale = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(this.localeStoreName)) {
				locale = Locale.forLanguageTag(cookie.getValue());
				break;
			}
		}
		LOG.info("Locale: " + locale);
		LOG.info("Cookie filter ended");
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
		LOG.info("Locale to save: " + locale.toLanguageTag());
		Cookie cookie = new Cookie(localeStoreName, locale.toLanguageTag());
		cookie.setMaxAge(time.intValue());
		response.addCookie(cookie);
		LOG.info("Save locale ended");
	}

}
