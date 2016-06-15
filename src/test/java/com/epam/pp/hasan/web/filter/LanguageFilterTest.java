/**
 * 
 */
package com.epam.pp.hasan.web.filter;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.epam.pp.hasan.config.Constants;

/**
 * @author Yosin_Hasan
 *
 */
public class LanguageFilterTest {
	private LanguageFilter filter;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private FilterConfig fConfig;
	private FilterChain chain;
	private ServletContext context;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		fConfig = Mockito.mock(FilterConfig.class);
		session = Mockito.mock(HttpSession.class);
		chain = Mockito.mock(FilterChain.class);
		context = Mockito.mock(ServletContext.class);
		when(request.getSession()).thenReturn(session);
		when(fConfig.getServletContext()).thenReturn(context);
		when(fConfig.getInitParameter("available")).thenReturn("ru-RU en-US");
		when(fConfig.getInitParameter("default")).thenReturn("en-US");
		when(context.getInitParameter("localeMethod")).thenReturn("session");
		when(context.getInitParameter("localeCookieLifeTime")).thenReturn("3600");

		doThrow(IllegalArgumentException.class).when(response).sendRedirect("/");
		doThrow(NullPointerException.class).when(chain).doFilter(request, response);
		filter = new LanguageFilter();
		filter.init(fConfig);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testLangRu() {
		when(request.getParameter("lang")).thenReturn("ru");
		when(request.getHeader("referer")).thenReturn("/");
		try {
			filter.doFilter(request, response, chain);
		} catch (IOException | ServletException e) {
			fail();
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLangEn() {
		when(request.getParameter("lang")).thenReturn("en");
		when(request.getHeader("referer")).thenReturn("/");
		try {
			filter.doFilter(request, response, chain);
		} catch (IOException | ServletException e) {
			fail();
		}
	}

	@Test(expected = NullPointerException.class)
	public void testLangNull() {
		when(request.getParameter("lang")).thenReturn(null);
		try {
			filter.doFilter(request, response, chain);
		} catch (IOException | ServletException e) {
			fail();
		}
	}

	@Test(expected = NullPointerException.class)
	public void testLocaleRu() {
		Locale ru = Locale.forLanguageTag("ru-RU");
		when(session.getAttribute(Constants.LOCALE_NAME)).thenReturn(ru);
		try {
			filter.doFilter(request, response, chain);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}
}
