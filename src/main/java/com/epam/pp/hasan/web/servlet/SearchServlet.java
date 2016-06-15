package com.epam.pp.hasan.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Path;
import com.epam.pp.hasan.db.dao.factory.DAOFactory;
import com.epam.pp.hasan.db.dao.factory.impl.MySqlDAOFactory;
import com.epam.pp.hasan.entity.Product;
import com.epam.pp.hasan.service.ProductService;
import com.epam.pp.hasan.service.impl.MySqlProductServiceImpl;
import com.epam.pp.hasan.util.ValidateUtil;

/**
 * 
 * @author Yosin_Hasan
 *
 */
@WebServlet(urlPatterns = { "/search.html" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SearchServlet.class);
	private ProductService productService;

	@Override
	public void init() throws ServletException {
		DAOFactory factory = new MySqlDAOFactory();
		productService = new MySqlProductServiceImpl(factory.getProductDAO());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("Servlet: products. Method: Get");
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		handler(request, response);
	}

	private void handler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("Handler started");
		HashMap<String, Object> serviceParams = new HashMap<>();
		LOG.info("Init service params");
		initServiceParams(serviceParams, request);
		ArrayList<Product> items = null;
		Integer count = 0;
		if (serviceParams.get("name") != null) {
			items = productService.findAll(serviceParams);
			count = productService.count(serviceParams);
		}
		LOG.info("Found items: " + items);
		request.setAttribute("products", items);
		request.setAttribute("count", count);
		request.setAttribute("page", serviceParams.get("page"));
		serviceParams.clear();
		request.getRequestDispatcher(Path.SEARCH).forward(request, response);
	}

	private void initServiceParams(HashMap<String, Object> serviceParams, HttpServletRequest request) {
		String[] name = getNames(request, "search");
		LOG.info("Search value: " + name);
		Integer limit = getInteger(request, "limit");
		Integer page = getInteger(request, "page");
		Integer offset = 0;
		if (page == null) {
			page = 1;
		}
		if (limit == null) {
			limit = Integer.parseInt(getServletContext().getInitParameter("productsOnPage"));
		}
		if (limit != null && page != null) {
			offset = limit * (page - 1);
		}
		serviceParams.put("limit", limit);
		serviceParams.put("offset", offset);
		serviceParams.put("name", name);
		serviceParams.put("page", page);
	}

	private Integer getInteger(HttpServletRequest request, String param) {
		String arg = request.getParameter(param);
		LOG.info("Param: " + param + " = " + arg);
		if (arg != null) {
			if (ValidateUtil.isValidNumber(arg)) {
				Integer value = Integer.parseInt(arg);
				LOG.info("Parsed value of " + param + " : " + value);
				return value;
			}
		}
		return null;
	}

	private String[] getNames(HttpServletRequest request, String key) {
		String param = request.getParameter(key);
		if (param != null && !param.isEmpty()) {
			ArrayList<String> names = new ArrayList<>();
			String[] keys = param.split("\\s");
			for (String name : keys) {
				if (ValidateUtil.isValidString(name)) {
					names.add(name);
				}
			}
			if (names.size() > 0) {
				return names.toArray(new String[] {});
			}
		}
		return null;
	}

}
