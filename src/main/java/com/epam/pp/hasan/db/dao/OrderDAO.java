package com.epam.pp.hasan.db.dao;

import com.epam.pp.hasan.entity.Order;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author Yosin_Hasan
 */
public interface OrderDAO extends GenericDAO<Order> {
    /**
     * Get all orders from order table.
     *
     * @param con connection to database
     * @return ArrayList<Order>
     */
    ArrayList<Order> readAll(Connection con);

}
