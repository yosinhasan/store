package com.epam.pp.hasan.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.web.filter.gzip.GZipServletResponseWrapper;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public class GZipServletFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(GZipServletFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("Gzip filer init started");
		LOG.info("Gzip filer init ended");
	}

	@Override
	public void destroy() {
		LOG.debug("Gzip filter destruction starts");
		// no op
		LOG.debug("Gzip filter destruction finished");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOG.info("Gzip filer started");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (acceptsGZipEncoding(httpRequest)) {
			GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(httpResponse);
			chain.doFilter(request, gzipResponse);
			gzipResponse.close();
		} else {
			chain.doFilter(request, response);
		}
		LOG.info("Gzip filer ended");

	}

	private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
		String acceptEncoding = httpRequest.getHeader("Accept-Encoding");
		LOG.info("Encoding: " + acceptEncoding);
		return acceptEncoding != null && acceptEncoding.indexOf("gzip") != -1;
	}

}
