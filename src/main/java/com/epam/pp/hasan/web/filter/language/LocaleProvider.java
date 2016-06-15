package com.epam.pp.hasan.web.filter.language;

import java.util.ArrayList;
import java.util.Locale;

import com.epam.pp.hasan.config.Constants;
import com.epam.pp.hasan.web.filter.language.impl.CookieFilter;
import com.epam.pp.hasan.web.filter.language.impl.SessionFilter;

/**
 * @author Yosin_Hasan
 *
 */
public class LocaleProvider {
	private String method;
	private LangFilter successor;
	private String localeName;
	private ArrayList<Locale> locales;
	private Long time;

	public LocaleProvider(String method, LangFilter successor, String localeName, ArrayList<Locale> locales,
			Long time) {
		this.method = method;
		this.successor = successor;
		this.localeName = localeName;
		this.locales = locales;
		this.time = time;
	}

	public LangFilter getLocaleInstance() {
		LangFilter instance = null;
		switch (method) {
		case Constants.SESSION:
			instance = new SessionFilter(successor, locales, localeName, time);
			break;
		case Constants.COOKIE:
			instance = new CookieFilter(successor, locales, localeName, time);
			break;
		default:
			instance = new SessionFilter(successor, locales, localeName, time);
			break;
		}
		return instance;
	}
}
