package com.epam.pp.hasan.db.dao;

import com.epam.pp.hasan.entity.Color;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public interface ColorDAO extends GenericDAO<Color> {
    /**
     * Get available colors of product.
     *
     * @param con       Connection
     * @param productId product id
     * @return ArrayList<Color>
     */
    ArrayList<Color> readAll(Connection con, Long productId);
}
