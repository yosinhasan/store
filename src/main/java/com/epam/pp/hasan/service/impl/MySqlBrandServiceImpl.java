package com.epam.pp.hasan.service.impl;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.db.dao.BrandDAO;
import com.epam.pp.hasan.db.manager.DBManager;
import com.epam.pp.hasan.db.transaction.ExecuteOperation;
import com.epam.pp.hasan.db.transaction.TransactionManager;
import com.epam.pp.hasan.entity.Brand;
import com.epam.pp.hasan.service.BrandService;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public class MySqlBrandServiceImpl implements BrandService {
	private static final Logger LOG = Logger.getLogger(MySqlBrandServiceImpl.class);
	private BrandDAO brandDAO;
	private DBManager manager = DBManager.getInstance();

	public MySqlBrandServiceImpl(BrandDAO BrandDAO) {
		this.brandDAO = BrandDAO;
	}

	@Override
	public Brand findBrand(String field, Object value) {
		LOG.info("Find Brand by field and value");
		Connection con = manager.getConnection();
		return (Brand) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Brand execute() {
				return brandDAO.read(con, field, value);
			}
		});
	}

	@Override
	public Brand findBrand(Long id) {
		LOG.info("Find Brand by id");
		Connection con = manager.getConnection();
		return (Brand) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Brand execute() {
				return brandDAO.read(con, id);
			}
		});
	}

	@Override
	public Boolean addBrand(Brand Brand) {
		LOG.info("Add Brand");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return brandDAO.create(con, Brand);
			}
		});
	}

	@Override
	public Boolean updateBrand(Brand Brand) {
		LOG.info("Update Brand");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return brandDAO.update(con, Brand);
			}
		});
	}

	@Override
	public Boolean deleteBrand(Long id) {
		LOG.info("Delete Brand by id");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return brandDAO.delete(con, id);
			}
		});
	}

	@Override
	public ArrayList<Brand> findAll() {
		LOG.info("Find all brands");
		Connection con = manager.getConnection();
		return (ArrayList<Brand>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Brand> execute() {
				return brandDAO.readAll(con);
			}
		}

		);
	}
}