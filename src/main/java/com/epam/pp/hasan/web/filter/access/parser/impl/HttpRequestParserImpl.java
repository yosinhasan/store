package com.epam.pp.hasan.web.filter.access.parser.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.web.filter.access.parser.RequestParser;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public class HttpRequestParserImpl implements RequestParser {
	private static final Logger LOG = Logger.getLogger(HttpRequestParserImpl.class);
	private String action;
	private Map<String, String> query;

	public HttpRequestParserImpl() {
		query = new HashMap<>();
	}

	@Override
	public void parse(HttpServletRequest req) {
		LOG.info("Request parser started");
		String request = req.getRequestURI();
		LOG.info("URI to parse -> " + request);
		if (request == null || request.isEmpty()) {
			action = null;
			query.clear();
			return;
		}
		String[] values = request.split("/");
		if (values.length <= 0) {
			action = null;
			query.clear();
		}
		action = values[values.length - 1];
		parseQuery(query, req.getQueryString());
		LOG.info("Action -> " + action);
		LOG.info("Query string -> " + query);
		LOG.info("Request parser started");
	}

	private void parseQuery(Map<String, String> query, String queryString) {
		LOG.info("Parsing query string started");
		LOG.info("Query to parse -> " + queryString);
		if (queryString == null || query == null || queryString.isEmpty()) {
			return;
		}
		if (queryString.indexOf("&") != -1) {
			String[] queries = queryString.split("&");
			String[] values = null;
			for (int i = 0; i < queries.length; i++) {
				values = queries[i].split("=");
				if (values.length == 2) {
					query.put(values[0], values[1]);
				}
			}
		} else {
			String[] values = queryString.split("=");
			if (values.length == 2) {
				query.put(values[0], values[1]);
			}
		}
		LOG.info("Parsing query string ended");
	}

	@Override
	public String getAction() {
		return action;
	}

	@Override
	public Map<String, String> getQuery() {
		return query;
	}
}
