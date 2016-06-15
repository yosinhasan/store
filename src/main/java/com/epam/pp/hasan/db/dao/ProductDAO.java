package com.epam.pp.hasan.db.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.epam.pp.hasan.entity.Product;
/**
 * @author Yosin_Hasan
 */
public interface ProductDAO extends GenericDAO<Product> {
	ArrayList<Product> readAll(Connection con, Integer limit, Integer offset);

	ArrayList<Product> readAll(Connection con, HashMap<String, Object> params);

	Integer countProducts(Connection con);

	Integer countProducts(Connection con, HashMap<String, Object> params);

	ArrayList<Product> readByCatId(Connection con, Long catId, Integer limit, Integer offset);

	ArrayList<Product> readByBrandId(Connection con, Long brandId, Integer limit, Integer offset);
}
