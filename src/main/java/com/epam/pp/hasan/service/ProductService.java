
package com.epam.pp.hasan.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.epam.pp.hasan.entity.Product;

/**
 * Interface for the Product Service
 *
 * @author Yosin_Hasan
 */
public interface ProductService {
    /**
     * Find Product by id.
     *
     * @param id id
     * @return Product
     */
    Product findProduct(Long id);

    /**
     * Find Product by field and value.
     *
     * @param field Product field
     * @param value Product field value
     * @return Product
     */
    Product findProduct(String field, Object value);

    /**
     * Add Product.
     *
     * @param item item to add
     * @return Boolean
     */
    Boolean addProduct(Product item);

    /**
     * Update Product.
     *
     * @param item item to update
     * @return Boolean
     */
    Boolean updateProduct(Product item);

    /**
     * Delete Product by id.
     *
     * @param id Product id
     * @return Boolean
     */
    Boolean deleteProduct(Long id);

    /**
     * Find all.
     *
     * @return ArrayList<Product>
     */
    ArrayList<Product> findAll();

    ArrayList<Product> findByCatId(Long catId, Integer limit, Integer offset);

    ArrayList<Product> findByBrandId(Long brandId, Integer limit, Integer offset);

    ArrayList<Product> findAll(HashMap<String, Object> params);

    ArrayList<Product> findAll(Integer limit, Integer offset);

    Integer count();

    Integer count(HashMap<String, Object> params);

}
