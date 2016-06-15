package com.epam.pp.hasan.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.pp.hasan.db.dao.factory.DAOFactory;
import com.epam.pp.hasan.db.dao.factory.impl.MySqlDAOFactory;
import com.epam.pp.hasan.entity.Product;
import com.epam.pp.hasan.service.ProductService;
import com.epam.pp.hasan.service.impl.MySqlProductServiceImpl;
import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Path;
/**
 * @author Yosin_Hasan
 */
@WebServlet(urlPatterns = {"/index.html"})
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(IndexServlet.class);
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        LOG.info("Start init index servlet");
        DAOFactory factory = new MySqlDAOFactory();
        productService = new MySqlProductServiceImpl(factory.getProductDAO());
        LOG.info("End init index servlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("Servlet: index. Method: Get");
        Integer limit = Integer.parseInt(getServletContext().getInitParameter("productsOnPage"));
        ArrayList<Product> items = productService.findAll(limit, null);
        request.setAttribute("products", items);
        request.getRequestDispatcher(Path.INDEX).forward(request, response);
    }

}
