package com.epam.pp.hasan.service.impl;

import com.epam.pp.hasan.db.dao.SizeDAO;
import com.epam.pp.hasan.db.manager.DBManager;
import com.epam.pp.hasan.db.transaction.ExecuteOperation;
import com.epam.pp.hasan.db.transaction.TransactionManager;
import com.epam.pp.hasan.entity.Size;
import com.epam.pp.hasan.service.SizeService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author Yosin_Hasan
 */
public class MySqlSizeServiceImpl implements SizeService {
	private static final Logger LOG = Logger.getLogger(MySqlSizeServiceImpl.class);
	private SizeDAO sizeDAO;
	private DBManager manager = DBManager.getInstance();

	public MySqlSizeServiceImpl(SizeDAO sizeDAO) {
		this.sizeDAO = sizeDAO;
	}

	@Override
	public Size findSize(String field, Object value) {
		LOG.info("Find Size by field and value");
		Connection con = manager.getConnection();
		return (Size) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Size execute() {
				return sizeDAO.read(con, field, value);
			}
		});
	}

	@Override
	public Size findSize(Long id) {
		LOG.info("Find Size by id");
		Connection con = manager.getConnection();
		return (Size) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Size execute() {
				return sizeDAO.read(con, id);
			}
		});
	}

	@Override
	public Boolean addSize(Size Size) {
		LOG.info("Add Size");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return sizeDAO.create(con, Size);
			}
		});
	}

	@Override
	public Boolean updateSize(Size Size) {
		LOG.info("Update Size");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return sizeDAO.update(con, Size);
			}
		});
	}

	@Override
	public Boolean deleteSize(Long id) {
		LOG.info("Delete Size by id");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return sizeDAO.delete(con, id);
			}
		});
	}

	@Override
	public ArrayList<Size> findAll() {
		LOG.info("Find all Sizes");
		Connection con = manager.getConnection();
		return (ArrayList<Size>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Size> execute() {
				return sizeDAO.readAll(con);
			}
		}

		);
	}

	@Override
	public ArrayList<Size> findAll(Long productId) {
		LOG.info("Find all Sizes");
		Connection con = manager.getConnection();
		return (ArrayList<Size>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Size> execute() {
				return sizeDAO.readAll(con, productId);
			}
		}

		);
	}
}