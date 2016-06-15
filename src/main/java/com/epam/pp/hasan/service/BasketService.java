package com.epam.pp.hasan.service;

import java.math.BigDecimal;
import java.util.Map;

import com.epam.pp.hasan.entity.Product;

/**
 * @author Yosin_Hasan
 */
public interface BasketService {
	/**
	 * <p>
	 * Adds product to basket.
	 * </p>
	 *
	 * @param item
	 *            product
	 * @param amount
	 *            amount
	 * @return Boolean
	 */
	Boolean addProduct(Product item, Integer amount);

	/**
	 * <p>
	 * Adds product to basket.
	 * </p>
	 *
	 * @param item
	 *            product
	 * @return Boolean
	 */
	Boolean addProduct(Product item);

	/**
	 * <p>
	 * Removes product from basket.
	 * </p>
	 *
	 * @param item
	 *            product
	 * @return Boolean
	 */
	Boolean removeProduct(Product item);

	/**
	 * <p>
	 * Get all products from basket.
	 * </p>
	 *
	 * @return Map<Product, Integer>
	 */
	Map<Product, Integer> findAll();

	/**
	 * <p>
	 * Counts amount of products in basket.
	 * </p>
	 *
	 * @return Integer
	 */
	Integer countAmount();

	/**
	 * <p>
	 * Counts total summ of products in basket.
	 * </p>
	 *
	 * @return BigDecimal
	 */
	BigDecimal countSumm();

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
	 * <p>
	 * Clear basket.
	 * </p>
	 */
	void clear();
}
