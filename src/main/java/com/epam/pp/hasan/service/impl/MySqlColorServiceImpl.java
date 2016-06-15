package com.epam.pp.hasan.service.impl;

import com.epam.pp.hasan.db.dao.ColorDAO;
import com.epam.pp.hasan.db.manager.DBManager;
import com.epam.pp.hasan.db.transaction.ExecuteOperation;
import com.epam.pp.hasan.db.transaction.TransactionManager;
import com.epam.pp.hasan.entity.Color;
import com.epam.pp.hasan.service.ColorService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author Yosin_Hasan
 */
public class MySqlColorServiceImpl implements ColorService {
	private static final Logger LOG = Logger.getLogger(MySqlColorServiceImpl.class);
	private ColorDAO colorDAO;
	private DBManager manager = DBManager.getInstance();

	public MySqlColorServiceImpl(ColorDAO colorDAO) {
		this.colorDAO = colorDAO;
	}

	@Override
	public Color findColor(String field, Object value) {
		LOG.info("Find Color by field and value");
		Connection con = manager.getConnection();
		return (Color) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Color execute() {
				return colorDAO.read(con, field, value);
			}
		});
	}

	@Override
	public Color findColor(Long id) {
		LOG.info("Find Color by id");
		Connection con = manager.getConnection();
		return (Color) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Color execute() {
				return colorDAO.read(con, id);
			}
		});
	}

	@Override
	public Boolean addColor(Color Color) {
		LOG.info("Add Color");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return colorDAO.create(con, Color);
			}
		});
	}

	@Override
	public Boolean updateColor(Color Color) {
		LOG.info("Update Color");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return colorDAO.update(con, Color);
			}
		});
	}

	@Override
	public Boolean deleteColor(Long id) {
		LOG.info("Delete Color by id");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return colorDAO.delete(con, id);
			}
		});
	}

	@Override
	public ArrayList<Color> findAll() {
		LOG.info("Find all Colors");
		Connection con = manager.getConnection();
		return (ArrayList<Color>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Color> execute() {
				return colorDAO.readAll(con);
			}
		}

		);
	}

	@Override
	public ArrayList<Color> findAll(Long productId) {
		LOG.info("Find all Colors");
		Connection con = manager.getConnection();
		return (ArrayList<Color>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Color> execute() {
				return colorDAO.readAll(con, productId);
			}
		}

		);
	}
}