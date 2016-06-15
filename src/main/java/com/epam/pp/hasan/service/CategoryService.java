
package com.epam.pp.hasan.service;

import com.epam.pp.hasan.entity.Category;

import java.util.ArrayList;

/**
 * Interface for the Category Service
 *
 * @author Yosin Hasan
 */
public interface CategoryService {
    /**
     * Find category by id.
     *
     * @param id id
     * @return Category
     */
    Category findCategory(Long id);

    /**
     * Find category by field and value.
     *
     * @param field category field
     * @param value category field value
     * @return Category
     */
    Category findCategory(String field, Object value);

    /**
     * Add Category.
     *
     * @param category Category to add
     * @return Boolean
     */
    Boolean addCategory(Category category);

    /**
     * Update Category.
     *
     * @param category category to update
     * @return Boolean
     */
    Boolean updateCategory(Category category);

    /**
     * Delete Category by id.
     *
     * @param id Category id
     * @return Boolean
     */
    Boolean deleteCategory(Long id);

    /**
     * Find all.
     *
     * @return ArrayList<Category>
     */
    ArrayList<Category> findAll();
}
