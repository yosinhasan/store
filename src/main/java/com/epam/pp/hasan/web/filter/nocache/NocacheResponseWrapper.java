package com.epam.pp.hasan.web.filter.nocache;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author Yosin_Hasan
 *
 */
public class NocacheResponseWrapper extends HttpServletResponseWrapper {

	public NocacheResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws java.io.IOException {
		ServletResponse response = this.getResponse();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-control", "no-cache, max-age=0");
		return super.getOutputStream();
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		ServletResponse response = this.getResponse();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-control", "no-cache, max-age=0");
		return super.getWriter();
	}

}
