package com.epam.pp.hasan.web.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Path;
import com.epam.pp.hasan.db.dao.factory.DAOFactory;
import com.epam.pp.hasan.db.dao.factory.impl.MySqlDAOFactory;
import com.epam.pp.hasan.entity.Product;
import com.epam.pp.hasan.repository.impl.BasketRepositoryImpl;
import com.epam.pp.hasan.service.BasketService;
import com.epam.pp.hasan.service.ProductService;
import com.epam.pp.hasan.service.impl.BasketServiceImpl;
import com.epam.pp.hasan.service.impl.MySqlProductServiceImpl;
import com.epam.pp.hasan.util.ValidateUtil;
import com.google.gson.Gson;

/**
 * @author Yosin_Hasan
 */
@WebServlet(urlPatterns = {"/basket.html"})
public class BasketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(BasketServlet.class);
    private ProductService productService;
    private BasketService basketService;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("Servlet: products. Method: Post");
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

    private void initBasketService(HttpServletRequest request) {
        basketService = (BasketService) request.getSession().getAttribute("basketService");
        if (basketService == null) {
            basketService = new BasketServiceImpl(new BasketRepositoryImpl());
        }
    }

    private void ajaxHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        initBasketService(request);
        String action = request.getParameter("action");
        doAction(request, action);
        HashMap<String, Object> ob = new HashMap<>();
        ob.put("amount", basketService.countAmount());
        ob.put("summ", basketService.countSumm());
        String respond = new Gson().toJson(ob);
        LOG.info("Summ : " + basketService.countSumm());
        session.setAttribute("basketService", basketService);
        response.getWriter().write(respond);
    }

    private void doAction(HttpServletRequest request, String action) {
        Long productId = getLong(request, "productId");
        Integer productAmount = getInt(request, "amount");
        if (ValidateUtil.isValidString(action)) {
            switch (action) {
                case "clear":
                    LOG.info("Action: clear");
                    basketService.clear();
                    break;
                case "delete":
                    LOG.info("Action: delete");
                    LOG.info("Id: " + productId);
                    Boolean isDeleted = basketService.removeProduct(productId);
                    LOG.info("Deleted: " + isDeleted);
                    break;
                case "update":
                    LOG.info("Action: update");
                    LOG.info("Id: " + productId);
                    if (productAmount > 0) {
                        basketService.updateProduct(productId, productAmount);
                    }
                    break;
                case "addItem":
                    LOG.info("Action: add item");
                    LOG.info("Id: " + productId);
                    Product item = productService.findProduct(productId);
                    if (item != null) {
                        basketService.addProduct(item);
                    }
                    break;
            }
        }
    }

    private Integer getInt(HttpServletRequest request, String param) {
        String id = request.getParameter(param);
        if (ValidateUtil.isValidNumber(id)) {
            return Integer.parseInt(id);
        }
        return null;
    }

    private Long getLong(HttpServletRequest request, String param) {
        String id = request.getParameter(param);
        if (ValidateUtil.isValidNumber(id)) {
            return Long.parseLong(id);
        }
        return null;
    }

    private void handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(Path.BASKET).forward(request, response);
    }
}
