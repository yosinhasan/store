package com.epam.pp.hasan.web.filter.language.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.util.LocaleUtil;
import com.epam.pp.hasan.web.filter.language.LangFilter;
/**
 * 
 * @author Yosin_Hasan
 *
 */
public class PlainFilter extends LangFilter {
	private static final Logger LOG = Logger.getLogger(PlainFilter.class);

	public PlainFilter(LangFilter successor, ArrayList<Locale> locales) {
		super(successor, locales, null, null);
	}

	public PlainFilter(LangFilter successor, ArrayList<Locale> locales, String localeStoreName, Long time) {
		super(successor, locales, localeStoreName, time);
	}

	@Override
	public Locale processRequest(HttpServletRequest request) {
		LOG.info("Plain filter started");
		Enumeration<Locale> requestLocales = request.getLocales();
		Locale tmpLocale = null;
		Locale locale = null;
		while (requestLocales.hasMoreElements()) {
			tmpLocale = requestLocales.nextElement();
			locale = LocaleUtil.getLocale(locales, tmpLocale);
			if (locale != null) {
				break;
			}
		}
		LOG.info("Locale: " + locale);
		LOG.info("Plain filter ended");
		if (locale == null) {
			if (successor != null) {
				return successor.processRequest(request);
			}
		}
		return locale;
	}

	@Override
	public void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		throw new UnsupportedOperationException();
	}
}
