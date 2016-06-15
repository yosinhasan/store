package com.epam.pp.hasan.web.servlet;

import com.epam.pp.hasan.config.Path;
import com.epam.pp.hasan.db.dao.factory.DAOFactory;
import com.epam.pp.hasan.db.dao.factory.impl.MySqlDAOFactory;
import com.epam.pp.hasan.entity.Product;
import com.epam.pp.hasan.service.*;
import com.epam.pp.hasan.service.impl.*;
import com.epam.pp.hasan.util.OrderUtil;
import com.epam.pp.hasan.util.ValidateUtil;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Yosin_Hasan
 *
 */
@WebServlet(urlPatterns = { "/products.html" })
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ProductsServlet.class);
	private CategoryService categoryService;
	private BrandService brandService;
	private ProductService productService;
	private DiscountService discountService;
	private ColorService colorService;

	@Override
	public void init() throws ServletException {
		DAOFactory factory = new MySqlDAOFactory();
		categoryService = new MySqlCategoryServiceImpl(factory.getCategoryDao());
		brandService = new MySqlBrandServiceImpl(factory.getBrandDAO());
		productService = new MySqlProductServiceImpl(factory.getProductDAO());
		discountService = new MySqlDiscountServiceImpl(factory.getDiscountDAO());
		colorService = new MySqlColorServiceImpl(factory.getColorDAO());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("Servlet: products. Method: Get");
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("Servlet: products. Method: Get");
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		if (ajax) {
			ajaxHandler(request, response);
		} else {
			handler(request, response);
		}
	}

	private void ajaxHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LOG.info("Ajax handler started");
		HashMap<String, Object> serviceParams = new HashMap<>();
		LOG.info("Init service params");
		initServiceParams(serviceParams, request);
		ArrayList<Product> items = productService.findAll(serviceParams);
		HashMap<String, Object> jsonObject = new HashMap<>();
		jsonObject.put("count", productService.count(serviceParams));
		jsonObject.put("limit", serviceParams.get("limit"));
		jsonObject.put("page", serviceParams.get("page"));
		jsonObject.put("items", items);
		serviceParams.clear();
		String json = new Gson().toJson(jsonObject);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void handler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("Handler started");
		HashMap<String, Object> serviceParams = new HashMap<>();
		LOG.info("Init service params");
		initServiceParams(serviceParams, request);
		ArrayList<Product> items = productService.findAll(serviceParams);
		request.setAttribute("discounts", discountService.findAll());
		request.setAttribute("categories", categoryService.findAll());
		request.setAttribute("brands", brandService.findAll());
		request.setAttribute("products", items);
		request.setAttribute("count", productService.count(serviceParams));
		request.setAttribute("colors", colorService.findAll());
		request.setAttribute("orders", OrderUtil.getProductOrder());
		request.setAttribute("page", serviceParams.get("page"));
		serviceParams.clear();
		request.getRequestDispatcher(Path.PRODUCTS).forward(request, response);
	}

	private void initServiceParams(HashMap<String, Object> serviceParams, HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		Long[] catIds = getIds(params, "catIds");
		Long[] brandIds = getIds(params, "brandIds");
		Integer startPrice = getInteger(request, "startPrice");
		Integer endPrice = getInteger(request, "endPrice");
		Long[] colorIds = getIds(params, "colorIds");
		Long discountId = getLong(request, "discountId");
		Integer orderId = getInteger(request, "orderId");
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
		Boolean desc = getBoolean(request, "desc");
		serviceParams.put("catIds", catIds);
		serviceParams.put("brandIds", brandIds);
		serviceParams.put("startPrice", startPrice);
		serviceParams.put("endPrice", endPrice);
		serviceParams.put("colorIds", colorIds);
		serviceParams.put("discountId", discountId);
		serviceParams.put("orderId", orderId);
		serviceParams.put("limit", limit);
		serviceParams.put("offset", offset);
		serviceParams.put("desc", desc);
		serviceParams.put("page", page);

	}

	private Boolean getBoolean(HttpServletRequest request, String param) {
		String arg = request.getParameter(param);
		LOG.info("Param: " + param + " = " + arg);
		if (arg != null) {
			if (ValidateUtil.isValidNumber(arg)) {
				return Integer.parseInt(arg) == 1;
			}
		}
		return false;
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

	private Long getLong(HttpServletRequest request, String param) {
		String arg = request.getParameter(param);
		LOG.info("Param: " + param + " = " + arg);
		if (arg != null) {
			if (ValidateUtil.isValidNumber(arg)) {
				Long value = Long.parseLong(arg);
				LOG.info("Parsed value of " + param + " : " + value);
				return value;
			}
		}
		return null;
	}

	private Long[] getIds(Map<String, String[]> params, String key) {
		if (params.containsKey(key)) {
			Long id;
			String[] ids = params.get(key);
			LOG.info("Param: " + key + " = " + ids);
			if (ids != null && ids.length >= 1) {
				ArrayList<Long> items = new ArrayList<>();
				for (String value : ids) {
					if (ValidateUtil.isValidNumber(value)) {
						id = Long.parseLong(value);
						LOG.info("Parsed value of " + key + " : " + id);
						items.add(id);
					}
				}
				return items.toArray(new Long[] {});
			}
		}
		return null;
	}

}
