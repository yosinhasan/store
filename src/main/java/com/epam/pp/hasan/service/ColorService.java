
package com.epam.pp.hasan.service;

import com.epam.pp.hasan.entity.Color;

import java.util.ArrayList;

/**
 * Interface for the Color Service
 *
 * @author Yosin Hasan
 */
public interface ColorService {
    /**
     * Find Color by id.
     *
     * @param id id
     * @return Color
     */
    Color findColor(Long id);

    /**
     * Find Color by field and value.
     *
     * @param field Color field
     * @param value Color field value
     * @return Color
     */
    Color findColor(String field, Object value);

    /**
     * Add Color.
     *
     * @param item item to add
     * @return Boolean
     */
    Boolean addColor(Color item);

    /**
     * Update Color.
     *
     * @param item item to update
     * @return Boolean
     */
    Boolean updateColor(Color item);

    /**
     * Delete Color by id.
     *
     * @param id Color id
     * @return Boolean
     */
    Boolean deleteColor(Long id);

    /**
     * Find all.
     *
     * @return ArrayList<Color>
     */
    ArrayList<Color> findAll();

    /**
     * Find all by product id.
     *
     * @param productId product id
     * @return ArrayList<Color>
     */
    ArrayList<Color> findAll(Long productId);
}
