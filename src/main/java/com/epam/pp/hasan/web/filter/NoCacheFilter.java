package com.epam.pp.hasan.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.web.filter.nocache.NocacheResponseWrapper;

/**
 * No cache filter.
 * 
 * @author Yosin_Hasan
 * 
 */
public class NoCacheFilter implements Filter {
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(NoCacheFilter.class);

	@Override
	public final void destroy() {
		LOG.debug("No cache filter destruction starts");
		// no op
		LOG.debug("No cache filter destruction finished");
	}

	@Override
	public final void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		LOG.debug("Filter starts");
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		NocacheResponseWrapper wrappedResponse = new NocacheResponseWrapper(httpResponse);
		LOG.debug("Filter finished");
		chain.doFilter(request, wrappedResponse);
	}

	@Override
	public final void init(final FilterConfig fConfig) throws ServletException {
		LOG.debug("No cache filter initialization starts");
		LOG.debug("No cache Filter initialization finished");
	}

}