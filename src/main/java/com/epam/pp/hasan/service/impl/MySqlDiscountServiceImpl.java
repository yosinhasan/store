package com.epam.pp.hasan.service.impl;

import com.epam.pp.hasan.db.dao.DiscountDAO;
import com.epam.pp.hasan.db.manager.DBManager;
import com.epam.pp.hasan.db.transaction.ExecuteOperation;
import com.epam.pp.hasan.db.transaction.TransactionManager;
import com.epam.pp.hasan.entity.Discount;
import com.epam.pp.hasan.service.DiscountService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public class MySqlDiscountServiceImpl implements DiscountService {
	private static final Logger LOG = Logger.getLogger(MySqlDiscountServiceImpl.class);
	private DiscountDAO discountDAO;
	private DBManager manager = DBManager.getInstance();

	public MySqlDiscountServiceImpl(DiscountDAO discountDAO) {
		this.discountDAO = discountDAO;
	}

	@Override
	public Discount findDiscount(String field, Object value) {
		LOG.info("Find Discount by field and value");
		Connection con = manager.getConnection();
		return (Discount) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Discount execute() {
				return discountDAO.read(con, field, value);
			}
		});
	}

	@Override
	public Discount findDiscount(Long id) {
		LOG.info("Find Discount by id");
		Connection con = manager.getConnection();
		return (Discount) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Discount execute() {
				return discountDAO.read(con, id);
			}
		});
	}

	@Override
	public Boolean addDiscount(Discount Discount) {
		LOG.info("Add Discount");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return discountDAO.create(con, Discount);
			}
		});
	}

	@Override
	public Boolean updateDiscount(Discount Discount) {
		LOG.info("Update Discount");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return discountDAO.update(con, Discount);
			}
		});
	}

	@Override
	public Boolean deleteDiscount(Long id) {
		LOG.info("Delete Discount by id");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return discountDAO.delete(con, id);
			}
		});
	}

	@Override
	public ArrayList<Discount> findAll() {
		LOG.info("Find all Discounts");
		Connection con = manager.getConnection();
		return (ArrayList<Discount>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Discount> execute() {
				return discountDAO.readAll(con);
			}
		}

		);
	}
}