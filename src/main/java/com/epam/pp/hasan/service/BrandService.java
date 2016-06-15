
package com.epam.pp.hasan.service;

import com.epam.pp.hasan.entity.Brand;

import java.util.ArrayList;

/**
 * Interface for the Brand Service
 *
 * @author Yosin Hasan
 */
public interface BrandService {
	/**
	 * Find Brand by id.
	 *
	 * @param id
	 *            id
	 * @return Brand
	 */
	Brand findBrand(Long id);

	/**
	 * Find Brand by field and value.
	 *
	 * @param field
	 *            Brand field
	 * @param value
	 *            Brand field value
	 * @return Brand
	 */
	Brand findBrand(String field, Object value);

	/**
	 * Add Brand.
	 *
	 * @param item
	 *            item to add
	 * @return Boolean
	 */
	Boolean addBrand(Brand item);

	/**
	 * Update Brand.
	 *
	 * @param item
	 *            item to update
	 * @return Boolean
	 */
	Boolean updateBrand(Brand item);

	/**
	 * Delete Brand by id.
	 *
	 * @param id
	 *            Brand id
	 * @return Boolean
	 */
	Boolean deleteBrand(Long id);

	/**
	 * Find all.
	 *
	 * @return ArrayList<Brand>
	 */
	ArrayList<Brand> findAll();
}
