package com.epam.pp.hasan.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Constants;
import com.epam.pp.hasan.config.Path;
import com.epam.pp.hasan.db.dao.factory.DAOFactory;
import com.epam.pp.hasan.db.dao.factory.impl.MySqlDAOFactory;
import com.epam.pp.hasan.entity.Brand;
import com.epam.pp.hasan.entity.Category;
import com.epam.pp.hasan.entity.Product;
import com.epam.pp.hasan.service.BrandService;
import com.epam.pp.hasan.service.CategoryService;
import com.epam.pp.hasan.service.ColorService;
import com.epam.pp.hasan.service.ImagesService;
import com.epam.pp.hasan.service.ProductService;
import com.epam.pp.hasan.service.SizeService;
import com.epam.pp.hasan.service.impl.MySqlBrandServiceImpl;
import com.epam.pp.hasan.service.impl.MySqlCategoryServiceImpl;
import com.epam.pp.hasan.service.impl.MySqlColorServiceImpl;
import com.epam.pp.hasan.service.impl.MySqlImagesServiceImpl;
import com.epam.pp.hasan.service.impl.MySqlProductServiceImpl;
import com.epam.pp.hasan.service.impl.MySqlSizeServiceImpl;
import com.epam.pp.hasan.util.ValidateUtil;

/**
 * 
 * @author Yosin_Hasan
 *
 */
@WebServlet(urlPatterns = { "/product.html" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ProductServlet.class);
	private CategoryService categoryService;
	private BrandService brandService;
	private ProductService productService;
	private ImagesService imagesService;
	private ColorService colorService;
	private SizeService sizeService;

	@Override
	public void init() throws ServletException {
		DAOFactory factory = new MySqlDAOFactory();
		categoryService = new MySqlCategoryServiceImpl(factory.getCategoryDao());
		brandService = new MySqlBrandServiceImpl(factory.getBrandDAO());
		productService = new MySqlProductServiceImpl(factory.getProductDAO());
		imagesService = new MySqlImagesServiceImpl(factory.getImagesDAO());
		colorService = new MySqlColorServiceImpl(factory.getColorDAO());
		sizeService = new MySqlSizeServiceImpl(factory.getSizeDAO());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("Servlet: product. Method: Get");
		String id = request.getParameter("id");
		Product item = null;
		ArrayList<Product> items = null;
		Category category = null;
		Brand brand = null;
		if (ValidateUtil.isValidNumber(id)) {
			Long productId = Long.parseLong(id);
			item = productService.findProduct(productId);
			if (item != null) {
				category = categoryService.findCategory(item.getCatId());
				brand = brandService.findBrand(item.getBrandId());
				item.setColor(colorService.findAll(item.getId()));
				item.setSize(sizeService.findAll(item.getId()));
				item.setImages(imagesService.findAll(item.getId()));
				items = productService.findByBrandId(item.getBrandId(), Constants.LIMIT, null);
				if (items == null || items.size() <= 1) {
					items = productService.findByCatId(item.getCatId(), Constants.LIMIT, null);
				}
			}
		}
		if (items == null) {
			items = productService.findAll(Constants.LIMIT, null);
		}
		request.setAttribute("category", category);
		request.setAttribute("brand", brand);
		request.setAttribute("relatedProducts", items);
		request.setAttribute("product", item);
		request.getRequestDispatcher(Path.PRODUCT).forward(request, response);
	}

}
