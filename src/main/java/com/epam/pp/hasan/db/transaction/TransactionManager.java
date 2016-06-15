package com.epam.pp.hasan.db.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.web.exception.DAOException;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public final class TransactionManager {
	private static final Logger log = Logger.getLogger(TransactionManager.class);

	public static Object execute(Connection con, ExecuteOperation operation) {
		Object value = null;
		try {
			con.setAutoCommit(false);
			value = operation.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			log.error("Cannot execute operation", ex);
			throw new DAOException("Cannot execute operation", ex);
		} finally {
			if (con != null) {
				close(con);
			}
		}
		return value;
	}

	private static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException ex) {
			log.error("Cannot close connection", ex);
			throw new DAOException("Cannot close connection", ex);
		}
	}

	private static void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				log.error("Cannot rollback", ex);
				throw new DAOException("Cannot rollback", ex);
			}
		}
	}
}
