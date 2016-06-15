package com.epam.pp.hasan.service.impl;

import com.epam.pp.hasan.db.dao.ProductDAO;
import com.epam.pp.hasan.db.manager.DBManager;
import com.epam.pp.hasan.db.transaction.ExecuteOperation;
import com.epam.pp.hasan.db.transaction.TransactionManager;
import com.epam.pp.hasan.entity.Product;
import com.epam.pp.hasan.service.ProductService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Yosin_Hasan
 */
public class MySqlProductServiceImpl implements ProductService {
	private static final Logger LOG = Logger.getLogger(MySqlProductServiceImpl.class);
	private ProductDAO productDAO;
	private DBManager manager = DBManager.getInstance();

	public MySqlProductServiceImpl(ProductDAO ProductDAO) {
		this.productDAO = ProductDAO;
	}

	@Override
	public Product findProduct(String field, Object value) {
		LOG.info("Find Product by field and value");
		Connection con = manager.getConnection();
		return (Product) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Product execute() {
				return productDAO.read(con, field, value);
			}
		});
	}

	@Override
	public Product findProduct(Long id) {
		LOG.info("Find Product by id");
		Connection con = manager.getConnection();
		return (Product) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Product execute() {
				return productDAO.read(con, id);
			}
		});
	}

	@Override
	public Boolean addProduct(Product Product) {
		LOG.info("Add Product");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return productDAO.create(con, Product);
			}
		});
	}

	@Override
	public Boolean updateProduct(Product Product) {
		LOG.info("Update Product");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return productDAO.update(con, Product);
			}
		});
	}

	@Override
	public Boolean deleteProduct(Long id) {
		LOG.info("Delete Product by id");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return productDAO.delete(con, id);
			}
		});
	}

	@Override
	public ArrayList<Product> findAll() {
		LOG.info("Find all products");
		Connection con = manager.getConnection();
		LOG.info("Connection ok");
		return (ArrayList<Product>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Product> execute() {
				return productDAO.readAll(con);
			}
		});
	}


	@Override
	public ArrayList<Product> findByCatId(Long catId, Integer limit, Integer offset) {
		LOG.info("Find all products by category id");
		Connection con = manager.getConnection();
		LOG.info("Connection ok");
		return (ArrayList<Product>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Product> execute() {
				return productDAO.readByCatId(con, catId, limit, offset);
			}
		});
	}

	@Override
	public ArrayList<Product> findByBrandId(Long brandId, Integer limit, Integer offset) {
		LOG.info("Find all products by brand id");
		Connection con = manager.getConnection();
		LOG.info("Connection ok");
		return (ArrayList<Product>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Product> execute() {
				return productDAO.readByBrandId(con, brandId, limit, offset);
			}
		});
	}

	@Override
	public ArrayList<Product> findAll(Integer limit, Integer offset) {
		LOG.info("Find all products by filter");
		Connection con = manager.getConnection();
		return (ArrayList<Product>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Product> execute() {
				return productDAO.readAll(con, limit, offset);
			}
		});
	}

	@Override
	public ArrayList<Product> findAll(HashMap<String, Object> params) {
		LOG.info("Find all products by filter");
		Connection con = manager.getConnection();
		return (ArrayList<Product>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Product> execute() {
				return productDAO.readAll(con, params);
			}
		});
	}

	@Override
	public Integer count() {
		LOG.info("Count products");
		Connection con = manager.getConnection();
		return (Integer) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Integer execute() {
				return productDAO.countProducts(con);
			}
		});
	}

	@Override
	public Integer count(HashMap<String, Object> params) {
		LOG.info("Count products by filter");
		Connection con = manager.getConnection();
		return (Integer) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Integer execute() {
				return productDAO.countProducts(con, params);
			}
		});
	}
}