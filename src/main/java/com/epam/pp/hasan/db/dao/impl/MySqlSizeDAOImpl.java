package com.epam.pp.hasan.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Fields;
import com.epam.pp.hasan.config.Messages;
import com.epam.pp.hasan.config.Tables;
import com.epam.pp.hasan.db.dao.SizeDAO;
import com.epam.pp.hasan.entity.Size;
import com.epam.pp.hasan.web.exception.DAOException;

/**
 * @author Yosin_Hasan
 */
public class MySqlSizeDAOImpl extends AbstractDAOImpl implements SizeDAO {
	private static final Logger LOG = Logger.getLogger(MySqlSizeDAOImpl.class);

	public MySqlSizeDAOImpl() {
		initQuery(Tables.SIZE, Tables.SIZE_LABEL);
	}

	@Override
	public ArrayList<Size> readAll(Connection con) {
		ArrayList<Size> items = new ArrayList<>();
		Size item = null;
		select.clear();
		LOG.info("Query: " + select.getQuery());
		try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(select.getQuery())) {
			while (rs.next()) {
				item = extractSize(rs);
				items.add(item);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.SIZE_FIND_ALL_ERROR, ex);
			throw new DAOException(Messages.SIZE_FIND_ALL_ERROR, ex);
		}
		return items;
	}

	@Override
	public Size read(Connection con, String field, Object value) {
		if (field == null || field.isEmpty() || value == null) {
			return null;
		}
		select.clear();
		select.where(field, true);
		ResultSet rs = null;
		Size item = null;
		LOG.info("Query: " + select.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			int k = 1;
			pstmt.setObject(k, value);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			if (rs.next()) {
				item = extractSize(rs);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.SIZE_FIND_BY_FIELD_AND_VALUE, ex);
			throw new DAOException(Messages.SIZE_FIND_BY_FIELD_AND_VALUE, ex);
		} finally {
			close(rs);
		}
		return item;
	}

	@Override
	public final Boolean create(Connection con, Size object) {
		ResultSet rs = null;
		long id = -1;
		insert.clear();
		insert.field(new String[]{Fields.ENTITY_NAME});
		LOG.info("Query: " + insert.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(insert.getQuery(), Statement.RETURN_GENERATED_KEYS)) {
			int k = 1;
			pstmt.setString(k++, object.getName());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				id = rs.getLong(1);
				object.setId(id);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.SIZE_CREATE_ERROR, ex);
			throw new DAOException(Messages.SIZE_CREATE_ERROR, ex);
		} finally {
			close(rs);
		}
		if (id > 0) {
			return true;
		}
		return false;
	}

	@Override
	public final Size read(Connection con, Long id) {
		ResultSet rs = null;
		Size item = null;
		select.clear();
		select.where(Fields.ENTITY_ID, true);
		LOG.info("Query: " + select.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			pstmt.setLong(1, id);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			if (rs.next()) {
				item = extractSize(rs);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.SIZE_FIND_BY_FIELD_ERROR, ex);
			throw new DAOException(Messages.SIZE_FIND_BY_FIELD_ERROR, ex);
		} finally {
			close(rs);
		}
		return item;
	}

	@Override
	public final Boolean update(Connection con, Size object) {
		if (object == null || object.getId() == null || object.getId() < 0) {
			return false;
		}
		update.clear();
		update.field(Fields.ENTITY_NAME);
		update.where(Fields.ENTITY_ID, true);
		LOG.info("Query: " + update.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(update.getQuery())) {
			int k = 1;
			pstmt.setString(k++, object.getName());
			pstmt.setLong(k, object.getId());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(Messages.SIZE_UPDATE_ERROR, ex);
			throw new DAOException(Messages.SIZE_UPDATE_ERROR, ex);
		}
		return true;
	}

	@Override
	public final Boolean delete(Connection con, Long id) {
		if (id < 0) {
			return false;
		}
		delete.clear();
		delete.where(Fields.ENTITY_ID, true); // "DELETE FROM `Size` WHERE `id`
		// = ?"
		LOG.info("Query: " + delete.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(delete.getQuery())) {
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(Messages.SIZE_DELETE_ERROR, ex);
			throw new DAOException(Messages.SIZE_DELETE_ERROR, ex);
		}
		return true;
	}

	@Override
	public ArrayList<Size> readAll(Connection con, Long productId) {
		select.clear();
		select.field(new String[] { Fields.ENTITY_ID, Fields.ENTITY_NAME });
		select.join(Tables.PRODUCT_SIZE, Tables.PRODUCT_SIZE_LABEL, Fields.PRODUCT_SIZE__SIZE_ID, Fields.ENTITY_ID);
		select.where(Fields.ENTITY_PRODUCT_ID, Tables.PRODUCT_SIZE_LABEL, true);
		ArrayList<Size> size = new ArrayList<>();
		Size item;
		ResultSet rs = null;
		LOG.info("Query: " + select.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			pstmt.setLong(1, productId);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			while (rs.next()) {
				item = extractSize(rs);
				size.add(item);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.SIZE_FIND_ALL_ERROR);
			throw new DAOException(Messages.SIZE_FIND_ALL_ERROR, ex);
		} finally {
			close(rs);
		}
		return size;
	}

	private Size extractSize(final ResultSet rs) throws SQLException {
		Size item = new Size();
		item.setId(rs.getLong(Fields.ENTITY_ID));
		item.setName(rs.getString((Fields.ENTITY_NAME)));
		return item;
	}

}
