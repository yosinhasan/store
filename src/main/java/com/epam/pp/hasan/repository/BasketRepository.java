
package com.epam.pp.hasan.repository;

import java.math.BigDecimal;
import java.util.Map;

import com.epam.pp.hasan.entity.Product;

/**
 * Interface for the Basket DAO
 *
 * @author Yosin_Hasan
 */
public interface BasketRepository {
	/**
	 * Adds product to basket.
	 *
	 * @param item
	 *            product
	 * @param amount
	 *            amount
	 * @return Boolean
	 */
	Boolean addProduct(Product item, Integer amount);

	/**
	 * Adds product to basket.
	 *
	 * @param item
	 *            product
	 * @return Boolean
	 */
	Boolean addProduct(Product item);

	/**
	 * Removes product from basket.
	 *
	 * @param item
	 *            product
	 * @return Boolean
	 */
	Boolean removeProduct(Product item);

	/**
	 * Updates product amount.
	 * 
	 * @param id
	 *            product id
	 * @param amount
	 *            amount to update
	 * @return Boolean
	 */
	Boolean updateProduct(Long id, Integer amount);

	/**
	 * Removes product from basket by id.
	 *
	 * @param id
	 *            product
	 * @return Boolean
	 */
	Boolean removeProduct(Long id);

	/**
	 * Get all products from basket.
	 *
	 * @return Map<Product, Integer>
	 */
	Map<Product, Integer> findAll();

	/**
	 * Counts amount of products in basket.
	 *
	 * @return Integer
	 */
	Integer countAmount();

	/**
	 * Counts total summ of products in basket.
	 *
	 * @return Integer
	 */
	BigDecimal countSumm();

	/**
	 * Clear basket.
	 */
	void removeAll();
}
