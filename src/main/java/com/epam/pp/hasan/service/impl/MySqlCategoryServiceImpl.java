package com.epam.pp.hasan.service.impl;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.db.dao.CategoryDAO;
import com.epam.pp.hasan.db.manager.DBManager;
import com.epam.pp.hasan.db.transaction.ExecuteOperation;
import com.epam.pp.hasan.db.transaction.TransactionManager;
import com.epam.pp.hasan.entity.Category;
import com.epam.pp.hasan.service.CategoryService;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public class MySqlCategoryServiceImpl implements CategoryService {
	private static final Logger LOG = Logger.getLogger(MySqlCategoryServiceImpl.class);
	private CategoryDAO categoryDAO;
	private DBManager manager = DBManager.getInstance();

	public MySqlCategoryServiceImpl(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public Category findCategory(String field, Object value) {
		LOG.info("Find Category by field and value");
		Connection con = manager.getConnection();
		return (Category) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Category execute() {
				return categoryDAO.read(con, field, value);
			}
		});
	}

	@Override
	public Category findCategory(Long id) {
		LOG.info("Find Category by id");
		Connection con = manager.getConnection();
		return (Category) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Category execute() {
				return categoryDAO.read(con, id);
			}
		});
	}

	@Override
	public Boolean addCategory(Category category) {
		LOG.info("Add Category");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return categoryDAO.create(con, category);
			}
		});
	}

	@Override
	public Boolean updateCategory(Category category) {
		LOG.info("Update Category");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return categoryDAO.update(con, category);
			}
		});
	}

	@Override
	public Boolean deleteCategory(Long id) {
		LOG.info("Delete Category by id");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return categoryDAO.delete(con, id);
			}
		});
	}

	@Override
	public ArrayList<Category> findAll() {
		LOG.info("Find all categories");
		Connection con = manager.getConnection();
		return (ArrayList<Category>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Category> execute() {
				return categoryDAO.readAll(con);
			}
		}

		);
	}
}