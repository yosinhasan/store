
package com.epam.pp.hasan.service;

import com.epam.pp.hasan.entity.Discount;

import java.util.ArrayList;

/**
 * Interface for the Discount Service
 *
 * @author Yosin Hasan
 */
public interface DiscountService {
    /**
     * Find Discount by id.
     *
     * @param id id
     * @return Discount
     */
    Discount findDiscount(Long id);

    /**
     * Find Discount by field and value.
     *
     * @param field Discount field
     * @param value Discount field value
     * @return Discount
     */
    Discount findDiscount(String field, Object value);

    /**
     * Add Discount.
     *
     * @param item item to add
     * @return Boolean
     */
    Boolean addDiscount(Discount item);

    /**
     * Update Discount.
     *
     * @param item item to update
     * @return Boolean
     */
    Boolean updateDiscount(Discount item);

    /**
     * Delete Discount by id.
     *
     * @param id Discount id
     * @return Boolean
     */
    Boolean deleteDiscount(Long id);

    /**
     * Find all.
     *
     * @return ArrayList<Discount>
     */
    ArrayList<Discount> findAll();
}
