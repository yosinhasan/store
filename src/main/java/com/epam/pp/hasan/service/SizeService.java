
package com.epam.pp.hasan.service;

import com.epam.pp.hasan.entity.Size;

import java.util.ArrayList;

/**
 * Interface for the Size Service
 *
 * @author Yosin Hasan
 */
public interface SizeService {
	/**
	 * Find Size by id.
	 *
	 * @param id
	 *            id
	 * @return Size
	 */
	Size findSize(Long id);

	/**
	 * Find Size by field and value.
	 *
	 * @param field
	 *            Size field
	 * @param value
	 *            Size field value
	 * @return Size
	 */
	Size findSize(String field, Object value);

	/**
	 * Add Size.
	 *
	 * @param item
	 *            item to add
	 * @return Boolean
	 */
	Boolean addSize(Size item);

	/**
	 * Update Size.
	 *
	 * @param item
	 *            item to update
	 * @return Boolean
	 */
	Boolean updateSize(Size item);

	/**
	 * Delete Size by id.
	 *
	 * @param id
	 *            Size id
	 * @return Boolean
	 */
	Boolean deleteSize(Long id);

	/**
	 * Find all.
	 *
	 * @return ArrayList<Size>
	 */
	ArrayList<Size> findAll();

	/**
	 * Find all by product id.
	 *
	 * @param productId
	 *            product id
	 * @return ArrayList<Size>
	 */
	ArrayList<Size> findAll(Long productId);
}
