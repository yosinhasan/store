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
import com.epam.pp.hasan.db.dao.ColorDAO;
import com.epam.pp.hasan.entity.Color;
import com.epam.pp.hasan.web.exception.DAOException;

/**
 * @author Yosin_Hasan
 */
public class MySqlColorDAOImpl extends AbstractDAOImpl implements ColorDAO {
	private static final Logger LOG = Logger.getLogger(MySqlColorDAOImpl.class);

	public MySqlColorDAOImpl() {
		initQuery(Tables.COLOR, Tables.COLOR_LABEL);
	}

	@Override
	public ArrayList<Color> readAll(Connection con) {
		ArrayList<Color> items = new ArrayList<>();
		Color item = null;
		select.clear();
		LOG.info("Query: " + select.getQuery());
		try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(select.getQuery())) {
			while (rs.next()) {
				item = extractColor(rs);
				items.add(item);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.COLOR_FIND_ALL_ERROR, ex);
			throw new DAOException(Messages.COLOR_FIND_ALL_ERROR, ex);
		}
		return items;
	}

	@Override
	public Color read(Connection con, String field, Object value) {
		if (field == null || field.isEmpty() || value == null) {
			return null;
		}
		select.clear();
		select.where(field, true);
		LOG.info("Query: " + select.getQuery());
		ResultSet rs = null;
		Color item = null;
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			int k = 1;
			pstmt.setObject(k, value);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			if (rs.next()) {
				item = extractColor(rs);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.COLOR_FIND_BY_FIELD_AND_VALUE, ex);
			throw new DAOException(Messages.COLOR_FIND_BY_FIELD_AND_VALUE, ex);
		} finally {
			close(rs);
		}
		return item;
	}

	@Override
	public final Boolean create(Connection con, Color object) {
		ResultSet rs = null;
		long id = -1;
		insert.clear();
		insert.field(new String[] {Fields.ENTITY_NAME});
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
			LOG.error(Messages.COLOR_CREATE_ERROR, ex);
			throw new DAOException(Messages.COLOR_CREATE_ERROR, ex);
		} finally {
			close(rs);
		}
		if (id > 0) {
			return true;
		}
		return false;
	}

	@Override
	public final Color read(Connection con, Long id) {
		ResultSet rs = null;
		Color item = null;
		select.clear();
		select.where(Fields.ENTITY_ID, true);
		LOG.info("Query: " + select.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			pstmt.setLong(1, id);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			if (rs.next()) {
				item = extractColor(rs);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.COLOR_FIND_BY_FIELD_ERROR, ex);
			throw new DAOException(Messages.COLOR_FIND_BY_FIELD_ERROR, ex);
		} finally {
			close(rs);
		}
		return item;
	}

	@Override
	public final Boolean update(Connection con, Color object) {
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
			LOG.error(Messages.COLOR_UPDATE_ERROR, ex);
			throw new DAOException(Messages.COLOR_UPDATE_ERROR, ex);
		}
		return true;
	}

	@Override
	public final Boolean delete(Connection con, Long id) {
		if (id < 0) {
			return false;
		}
		delete.clear();
		delete.where(Fields.ENTITY_ID, true); // "DELETE FROM `Color` WHERE `id`
		// = ?"
		LOG.info("Query: " + delete.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(delete.getQuery())) {
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(Messages.COLOR_DELETE_ERROR, ex);
			throw new DAOException(Messages.COLOR_DELETE_ERROR, ex);
		}
		return true;
	}

	@Override
	public ArrayList<Color> readAll(Connection con, Long productId) {
		select.clear();
		select.field(new String[] { Fields.ENTITY_ID, Fields.ENTITY_NAME });
		select.join(Tables.PRODUCT_COLOR, Tables.PRODUCT_COLOR_LABEL, Fields.PRODUCT_COLOR__COLOR_ID, Fields.ENTITY_ID);
		select.where(Fields.ENTITY_PRODUCT_ID, Tables.PRODUCT_COLOR_LABEL, true);
		ArrayList<Color> colors = new ArrayList<>();
		Color color;
		ResultSet rs = null;
		LOG.info("Query: " + select.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			pstmt.setLong(1, productId);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			while (rs.next()) {
				color = extractColor(rs);
				colors.add(color);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.COLOR_FIND_ALL_ERROR);
			throw new DAOException(Messages.COLOR_FIND_ALL_ERROR, ex);
		} finally {
			close(rs);
		}
		return colors;
	}

	private Color extractColor(final ResultSet rs) throws SQLException {
		Color item = new Color();
		item.setId(rs.getLong(Fields.ENTITY_ID));
		item.setName(rs.getString((Fields.ENTITY_NAME)));
		return item;
	}

}
