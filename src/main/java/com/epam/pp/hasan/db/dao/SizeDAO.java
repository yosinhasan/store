package com.epam.pp.hasan.db.dao;

import com.epam.pp.hasan.entity.Size;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author Yosin_Hasan
 */
public interface SizeDAO extends GenericDAO<Size> {
    /**
     * Get available size of product.
     *
     * @param productId product id
     * @param con       connection
     * @return ArrayList<Size>
     */
    ArrayList<Size> readAll(Connection con, Long productId);
}
