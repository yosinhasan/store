package com.epam.pp.hasan.db.dao.factory;

import com.epam.pp.hasan.db.dao.*;

/**
 * @author Yosin_Hasan
 */
public interface DAOFactory {

	UserDAO getUserDAO();

	CategoryDAO getCategoryDao();

	BrandDAO getBrandDAO();

	ProductDAO getProductDAO();

	DiscountDAO getDiscountDAO();

	ImagesDAO getImagesDAO();

	ColorDAO getColorDAO();

	SizeDAO getSizeDAO();
	OrderDAO getOrderDAO();
}
