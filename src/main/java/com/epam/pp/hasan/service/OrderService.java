
package com.epam.pp.hasan.service;

import java.util.ArrayList;

import com.epam.pp.hasan.entity.Order;

/**
 * Interface for the Order Service
 *
 * @author Yosin Hasan
 */
public interface OrderService {
    /**
     * Find Order by id.
     *
     * @param id id
     * @return Order
     */
    Order findOrder(Long id);

    /**
     * Find Order by field and value.
     *
     * @param field Order field
     * @param value Order field value
     * @return Order
     */
    Order findOrder(String field, Object value);

    /**
     * Add Order.
     *
     * @param item item to add
     * @return Boolean
     */
    Boolean addOrder(Order item);

    /**
     * Update Order.
     *
     * @param item item to update
     * @return Boolean
     */
    Boolean updateOrder(Order item);

    /**
     * Delete Order by id.
     *
     * @param id Order id
     * @return Boolean
     */
    Boolean deleteOrder(Long id);

    /**
     * Find all.
     *
     * @return ArrayList<Order>
     */
    ArrayList<Order> findAll();

}
