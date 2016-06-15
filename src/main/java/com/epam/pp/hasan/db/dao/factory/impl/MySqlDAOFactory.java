package com.epam.pp.hasan.db.dao.factory.impl;

import com.epam.pp.hasan.db.dao.*;
import com.epam.pp.hasan.db.dao.factory.DAOFactory;
import com.epam.pp.hasan.db.dao.impl.*;

/**
 * @author Yosin_Hasan
 */
public class MySqlDAOFactory implements DAOFactory {
    @Override
    public CategoryDAO getCategoryDao() {
        return new MySqlCategoryDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySqlUserDAOImpl();
    }

    @Override
    public BrandDAO getBrandDAO() {
        return new MySqlBrandDAOImpl();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new MySqlProductDAOImpl();
    }

    @Override
    public DiscountDAO getDiscountDAO() {
        return new MySqlDiscountDAOImpl();
    }

    @Override
    public ImagesDAO getImagesDAO() {
        return new MySqlImagesDAOImpl();
    }

    @Override
    public ColorDAO getColorDAO() {
        return new MySqlColorDAOImpl();
    }

    @Override
    public SizeDAO getSizeDAO() {
        return new MySqlSizeDAOImpl();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new MySqlOrderDAOImpl();
    }
}
