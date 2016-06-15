package com.epam.pp.hasan.service.impl;

import com.epam.pp.hasan.db.dao.ImagesDAO;
import com.epam.pp.hasan.db.manager.DBManager;
import com.epam.pp.hasan.db.transaction.ExecuteOperation;
import com.epam.pp.hasan.db.transaction.TransactionManager;
import com.epam.pp.hasan.entity.Images;
import com.epam.pp.hasan.service.ImagesService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author Yosin_Hasan
 */
public class MySqlImagesServiceImpl implements ImagesService {
	private static final Logger LOG = Logger.getLogger(MySqlImagesServiceImpl.class);
	private ImagesDAO imagesDAO;
	private DBManager manager = DBManager.getInstance();

	public MySqlImagesServiceImpl(ImagesDAO imagesDAO) {
		this.imagesDAO = imagesDAO;
	}

	@Override
	public Images findImages(String field, Object value) {
		LOG.info("Find Images by field and value");
		Connection con = manager.getConnection();
		return (Images) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Images execute() {
				return imagesDAO.read(con, field, value);
			}
		});
	}

	@Override
	public Images findImages(Long id) {
		LOG.info("Find Images by id");
		Connection con = manager.getConnection();
		return (Images) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Images execute() {
				return imagesDAO.read(con, id);
			}
		});
	}

	@Override
	public Boolean addImages(Images Images) {
		LOG.info("Add Images");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return imagesDAO.create(con, Images);
			}
		});
	}

	@Override
	public Boolean updateImages(Images Images) {
		LOG.info("Update Images");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return imagesDAO.update(con, Images);
			}
		});
	}

	@Override
	public Boolean deleteImages(Long id) {
		LOG.info("Delete Images by id");
		Connection con = manager.getConnection();
		return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public Boolean execute() {
				return imagesDAO.delete(con, id);
			}
		});
	}

	@Override
	public ArrayList<Images> findAll() {
		LOG.info("Find all Imagess");
		Connection con = manager.getConnection();
		return (ArrayList<Images>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Images> execute() {
				return imagesDAO.readAll(con);
			}
		}

		);
	}

	@Override
	public ArrayList<Images> findAll(Long productId) {
		LOG.info("Find all Imagess");
		Connection con = manager.getConnection();
		return (ArrayList<Images>) TransactionManager.execute(con, new ExecuteOperation() {
			@Override
			public ArrayList<Images> execute() {
				return imagesDAO.readAll(con, productId);
			}
		}

		);
	}
}