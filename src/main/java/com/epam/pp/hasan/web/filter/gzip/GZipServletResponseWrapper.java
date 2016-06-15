package com.epam.pp.hasan.web.filter.gzip;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author Yosin_Hasan
 *
 */
public class GZipServletResponseWrapper extends HttpServletResponseWrapper {
	private ServletOutputStream gzipOutputStream = null;
	private PrintWriter printWriter = null;

	public GZipServletResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		HttpServletResponse response = (HttpServletResponse) this.getResponse();
		String type = (response != null) ? response.getContentType() : null;
		if (type != null && type.toLowerCase().startsWith("text/html")) {
			response.setHeader("Content-Encoding", "gzip");
			if (this.printWriter != null) {
				throw new IllegalStateException("PrintWriter obtained already - cannot get OutputStream");
			}
			if (this.gzipOutputStream == null) {
				this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
			}
			return this.gzipOutputStream;
		} else {
			return super.getOutputStream();
		}
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		HttpServletResponse response = (HttpServletResponse) this.getResponse();
		String type = (response != null) ? response.getContentType() : null;
		if (type != null && type.toLowerCase().startsWith("text/html")) {
			response.setHeader("Content-Encoding", "gzip");
			if (this.printWriter == null && this.gzipOutputStream != null) {
				throw new IllegalStateException("OutputStream obtained already - cannot get PrintWriter");
			}
			if (this.printWriter == null) {
				this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
				this.printWriter = new PrintWriter(
						new OutputStreamWriter(this.gzipOutputStream, getResponse().getCharacterEncoding()));
			}
			return this.printWriter;
		} else {
			return super.getWriter();
		}
	}
	public void close() throws IOException {
		if (this.printWriter != null) {
			this.printWriter.close();
		}
		if (this.gzipOutputStream != null) {
			this.gzipOutputStream.close();
		}
	}

}
