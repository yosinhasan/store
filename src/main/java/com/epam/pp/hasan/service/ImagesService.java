
package com.epam.pp.hasan.service;

import com.epam.pp.hasan.entity.Images;

import java.util.ArrayList;

/**
 * Interface for the Images Service
 *
 * @author Yosin Hasan
 */
public interface ImagesService {
	/**
	 * Find Images by id.
	 *
	 * @param id
	 *            id
	 * @return Images
	 */
	Images findImages(Long id);

	/**
	 * Find Images by field and value.
	 *
	 * @param field
	 *            Images field
	 * @param value
	 *            Images field value
	 * @return Images
	 */
	Images findImages(String field, Object value);

	/**
	 * Add Images.
	 *
	 * @param item
	 *            item to add
	 * @return Boolean
	 */
	Boolean addImages(Images item);

	/**
	 * Update Images.
	 *
	 * @param item
	 *            item to update
	 * @return Boolean
	 */
	Boolean updateImages(Images item);

	/**
	 * Delete Images by id.
	 *
	 * @param id
	 *            Images id
	 * @return Boolean
	 */
	Boolean deleteImages(Long id);

	/**
	 * Find all.
	 *
	 * @return ArrayList<Images>
	 */
	ArrayList<Images> findAll();

	/**
	 * Find all by product id.
	 *
	 * @param productId
	 *            product id
	 * @return ArrayList<Images>
	 */
	ArrayList<Images> findAll(Long productId);
}
