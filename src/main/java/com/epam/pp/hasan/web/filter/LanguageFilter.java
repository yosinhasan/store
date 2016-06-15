package com.epam.pp.hasan.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Constants;
import com.epam.pp.hasan.util.LocaleUtil;
import com.epam.pp.hasan.web.filter.language.LangFilter;
import com.epam.pp.hasan.web.filter.language.LocaleProvider;
import com.epam.pp.hasan.web.filter.language.impl.PlainFilter;

/**
 * Language filter.
 * 
 * @author Yosin_Hasan
 * 
 */
public class LanguageFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(LanguageFilter.class);
	private ArrayList<Locale> locales;
	private Locale defaultLocale;
	private LangFilter filter;

	@Override
	public final void destroy() {
		LOG.debug("Language Filter destruction starts");
		// no op
		LOG.debug("Language Filter destruction finished");
	}

	@Override
	public final void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		LOG.debug("Language Filter starts");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String lang = request.getParameter("lang");
		LOG.info("Lang:" + lang);
		Locale locale = getLocale(httpRequest);
		LOG.info("Locale to save" + locale);
		filter.saveLocale(httpRequest, httpResponse, locale);
		Config.set(httpRequest.getSession(), Config.FMT_LOCALE, locale);
		LOG.debug("Language Filter finished");
		if (lang != null) {
			String referer = httpRequest.getHeader("referer");
			if (referer == null) {
				referer = request.getServletContext().getContextPath() + "/index.html";
			}
			httpResponse.sendRedirect(referer);
		} else {
			chain.doFilter(request, response);
		}
	}

	private Locale getLocale(HttpServletRequest request) {
		Locale locale = null;
		LOG.info("Init locale started");
		String lang = request.getParameter("lang");
		LOG.info("Lang:" + lang);
		if (lang == null || lang.length() != 2) {
			locale = filter.processRequest(request);
			LOG.info("Found locale: " + locale);
		} else {
			lang = lang.toLowerCase();
			Locale tmpLocale = new Locale(lang);
			locale = LocaleUtil.getLocale(locales, tmpLocale);
		}
		if (locale == null) {
			locale = defaultLocale;
		}
		LOG.info("Init locale ended");
		return locale;
	}

	@Override
	public final void init(final FilterConfig fConfig) throws ServletException {
		LOG.debug("Language Filter initialization starts");
		locales = new ArrayList<>();
		String loc = fConfig.getInitParameter("available");
		String[] locs = loc.trim().split("\\s");
		for (String locale : locs) {
			locales.add(Locale.forLanguageTag(locale));
		}
		LOG.info("Available locales: " + locales);
		defaultLocale = Locale.forLanguageTag(fConfig.getInitParameter("default"));
		LOG.info("Default locale: " + defaultLocale);
		String localeMethod = fConfig.getServletContext().getInitParameter("localeMethod");
		String time = fConfig.getServletContext().getInitParameter("localeCookieLifeTime");
		Long cookieTime = Long.parseLong(time);
		LOG.info("Locale method: " + localeMethod);
		initFilter(localeMethod, cookieTime);
		LOG.info("Cookie time: " + time);
		LOG.debug("Language Filter initialization finished");
	}

	private void initFilter(String localeMethod, Long time) {
		LangFilter plain = new PlainFilter(null, locales);
		LocaleProvider provider = new LocaleProvider(localeMethod, plain, Constants.LOCALE_NAME, locales, time);
		filter = provider.getLocaleInstance();
	}

}