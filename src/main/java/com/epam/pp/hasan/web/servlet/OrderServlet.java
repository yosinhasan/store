package com.epam.pp.hasan.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.db.dao.factory.DAOFactory;
import com.epam.pp.hasan.db.dao.factory.impl.MySqlDAOFactory;
import com.epam.pp.hasan.entity.Order;
import com.epam.pp.hasan.entity.OrderProduct;
import com.epam.pp.hasan.entity.OrderStatus;
import com.epam.pp.hasan.entity.Product;
import com.epam.pp.hasan.entity.User;
import com.epam.pp.hasan.service.BasketService;
import com.epam.pp.hasan.service.OrderService;
import com.epam.pp.hasan.service.impl.MySqlOrderServiceImpl;
import com.epam.pp.hasan.util.ValidateUtil;

/**
 * @author Yosin_Hasan
 */
@WebServlet(urlPatterns = { "/order.html" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(OrderServlet.class);
	private OrderService orderService;
	private BasketService basketService;

	@Override
	public void init() throws ServletException {
		DAOFactory factory = new MySqlDAOFactory();
		orderService = new MySqlOrderServiceImpl(factory.getOrderDAO());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("Servlet: products. Method: Get");
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		basketService = (BasketService) session.getAttribute("basketService");
		User user = (User) session.getAttribute("user");
		String method = request.getParameter("method");
		String localaddress = request.getParameter("localaddress");
		String creditNumber = request.getParameter("creditcardnumber");
		LOG.info("Local address: " + localaddress);
		LOG.info("Credit card number: " + creditNumber);
		boolean failed = true;
		if (user != null) {
			if (ValidateUtil.isValidNumber(method)) {
				Integer paymentMethod = Integer.parseInt(method);
				Long creditCardNumber = 0L;
				String userAddress = "";
				if (ValidateUtil.isValidNumber(creditNumber)) {
					creditCardNumber = Long.parseLong(creditNumber);
				}
				if (ValidateUtil.isValidString(localaddress)) {
					userAddress = localaddress;
				}
				if (basketService != null && (creditCardNumber != null || userAddress != null)) {
					saveOrder(userAddress, paymentMethod, creditCardNumber);
					failed = false;
					session.setAttribute("basketService", basketService);
				}

			}
		}
		if (failed) {
			session.setAttribute("referer", getServletContext().getContextPath() + "/basket.html");
			response.sendRedirect("signin.html");
		} else {
			response.sendRedirect("products.html");
		}
	}

	private void saveOrder(String userAddress, Integer paymentMethod, Long creditCardNumber) {
		Map<Product, Integer> basket = basketService.findAll();
		ArrayList<OrderProduct> items = new ArrayList<OrderProduct>();
		OrderProduct item = null;
		Product prod = null;
		Map.Entry<Product, Integer> entry = null;
		Iterator<Map.Entry<Product, Integer>> iter = basket.entrySet().iterator();
		while (iter.hasNext()) {
			entry = iter.next();
			prod = entry.getKey();
			item = new OrderProduct(prod.getId(), prod.getName(), entry.getValue(), prod.getPrice(), null);
			items.add(item);
		}
		Order order = new Order();
		order.setDetail("Accepted");
		order.setStatus(OrderStatus.ACCEPTED);
		order.setTotalPrice(basketService.countSumm());
		order.setUserId(1L);
		order.setOrderProducts(items);
		order.setUserAddress(userAddress);
		order.setPaymentId(paymentMethod);
		order.setCreditCardNumber(creditCardNumber);
		LOG.info("Save order");
		orderService.addOrder(order);
		basketService.clear();
	}
}