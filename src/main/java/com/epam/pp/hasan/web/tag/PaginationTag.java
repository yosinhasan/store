package com.epam.pp.hasan.web.tag;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.util.ValidateUtil;

/**
 * Tag for gaining amount of finance.
 *
 * @author Yosin_Hasan
 */
public class PaginationTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Integer count;
	private String url;
	private String query;
	private Logger LOG = Logger.getLogger(PaginationTag.class);

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public final String getUrl() {
		return url;
	}

	public final void setUrl(String url) {
		this.url = url;
	}

	public static Map<String, String> splitQuery(String text) {
		Map<String, String> query_pairs = new LinkedHashMap<String, String>();
		if (text != null && text.length() > 0) {
			String query = text;
			String[] pairs = query.split("&");
			for (String pair : pairs) {
				int idx = pair.indexOf("=");
				query_pairs.put(pair.substring(0, idx), pair.substring(idx + 1));
			}
		}
		return query_pairs;

	}

	@Override
	public final int doStartTag() throws JspException {
		LOG.info("Pagination tag started");
		JspWriter out = pageContext.getOut();
		ServletRequest request = pageContext.getRequest();
		StringBuilder urlBuilder = new StringBuilder(
				pageContext.getServletContext().getContextPath() + "/" + url + ".html?");
		String path;
		String productsOnPage = pageContext.getServletContext().getInitParameter("productsOnPage");
		String lim = request.getParameter("limit");
		Integer limit = Integer.parseInt(productsOnPage);
		LOG.info("Limit: " + lim);
		if (ValidateUtil.isValidNumber(lim)) {
			limit = Integer.parseInt(lim);
		}
		LOG.info("Query" + query);
		Map<String, String> queryString = splitQuery(query);
		LOG.info(queryString);
		int page = 1;
		LOG.info("count: " + count);
		LOG.info("url" + url);
		if (url.equals("search")) {
			urlBuilder.append("search=" + queryString.get("search") + "&");
		}
		urlBuilder.append("page=");
		path = urlBuilder.toString();
		int pages = count / limit;
		String pageStr = request.getParameter("page");
		if (ValidateUtil.isValidNumber(pageStr)) {
			page = Integer.parseInt(pageStr);
			if (page > pages) {
				page = pages;
			}
		}
		try {
			out.write("<nav>");
			out.write("<ul class='pagination'>");
			if (pages > 1) {
				if (page - 2 >= 1) {
					out.write("<li><a href='" + path + (page - 1) + "'>&Lt;</a></li>");
					out.write("<li><a href='" + path + (page - 2) + "'>" + (page - 2) + "</a ></li>");
					out.write("<li><a href='" + path + (page - 1) + "'>" + (page - 1) + "</a ></li>");
				} else if (page - 1 >= 1) {
					out.write("<li><a href='" + path + (page - 1) + "'>&Lt;</a></li>");
					out.write("<li><a href='" + path + (page - 1) + "'>" + (page - 1) + "</a ></li>");
				}
				out.write("<li class='active'><a href='" + path + page + "'>" + page + "</a ></li>");
				if (page + 2 <= pages) {
					out.write("<li><a href='" + path + (page + 1) + "'>" + (page + 1) + "</a ></li>");
					out.write("<li><a href='" + path + (page + 2) + "'>" + (page + 2) + "</a ></li>");
					out.write("<li><a href='" + path + (page + 1) + "'>&Gt;</a></li>");
				} else if (page + 1 <= pages) {
					out.write("<li><a href='" + path + (page + 1) + "'>" + (page + 1) + "</a ></li>");
					out.write("<li><a href='" + path + (page + 1) + "'>&Gt;</a></li>");
				}

			}
			out.write("</ul>");
			out.write("</nav>");
		} catch (IOException ex) {
			LOG.error("Error occured during writing to output stream", ex);
		}
		LOG.info("Pagination tag ended");
		return SKIP_BODY;

	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
