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
import com.epam.pp.hasan.db.dao.UserDAO;
import com.epam.pp.hasan.entity.Role;
import com.epam.pp.hasan.entity.Sex;
import com.epam.pp.hasan.entity.User;
import com.epam.pp.hasan.web.exception.DAOException;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public class MySqlUserDAOImpl extends AbstractDAOImpl implements UserDAO {
	private static final Logger LOG = Logger.getLogger(MySqlUserDAOImpl.class);

	public MySqlUserDAOImpl() {
		initQuery(Tables.USER, Tables.USER_LABEL);
	}

	@Override
	public ArrayList<User> readAll(Connection con) {
		ArrayList<User> users = new ArrayList<>();
		User user;
		select.clear();
		LOG.info("Query: " + select.getQuery());
		try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(select.getQuery())) {
			while (rs.next()) {
				user = extractUser(rs);
				users.add(user);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.USER_FIND_ALL_ERROR, ex);
			throw new DAOException(Messages.USER_FIND_ALL_ERROR, ex);
		}
		LOG.info("Found users: " + users);
		return users;
	}

	@Override
	public User read(Connection con, String field, Object value) {
		if (field == null || field.isEmpty() || value == null) {
			return null;
		}
		select.clear();
		select.where(field, true);
		LOG.info("Query: " + select.getQuery());
		ResultSet rs = null;
		User user = null;
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			int k = 1;
			pstmt.setObject(k, value);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.USER_FIND_BY_FIELD_AND_VALUE, ex);
			throw new DAOException(Messages.USER_FIND_BY_FIELD_AND_VALUE, ex);
		} finally {
			close(rs);
		}
		LOG.info("Found user: " + user);
		return user;
	}

	@Override
	public final Boolean create(Connection con, User object) {
		ResultSet rs = null;
		long id = -1;
		insert.clear();
		insert.field(new String[] { Fields.USER_FIRST_NAME, Fields.USER_LAST_NAME, Fields.USER_EMAIL, Fields.USER_PHONE,
				Fields.USER_PASSWORD, Fields.USER_SEX, Fields.USER_ROLE });

		LOG.info("Query: " + insert.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(insert.getQuery(), Statement.RETURN_GENERATED_KEYS)) {
			int k = 1;
			pstmt.setString(k++, object.getName());
			pstmt.setString(k++, object.getSurname());
			pstmt.setString(k++, object.getEmail());
			pstmt.setString(k++, object.getPhone());
			pstmt.setString(k++, object.getPassword());
			pstmt.setInt(k++, object.getSex().ordinal());
			pstmt.setInt(k, object.getRole().ordinal());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				id = rs.getLong(1);
				object.setId(id);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.USER_CREATE_ERROR, ex);
			throw new DAOException(Messages.USER_CREATE_ERROR, ex);
		} finally {
			close(rs);
		}
		if (id > 0) {
			return true;
		}
		return false;
	}

	@Override
	public final User read(Connection con, Long id) {
		ResultSet rs = null;
		User user = null;
		select.clear();
		select.where(Fields.ENTITY_ID, true); // "SELECT * FROM `user` WHERE
												// `id` = ?";
		LOG.info("Query: " + select.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			pstmt.setLong(1, id);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.USER_FIND_BY_FIELD_ERROR, ex);
			throw new DAOException(Messages.USER_FIND_BY_FIELD_ERROR, ex);
		} finally {
			close(rs);
		}
		LOG.info("Found user: " + user);
		return user;
	}

	@Override
	public final Boolean update(Connection con, User object) {
		if (object == null || object.getId() == null || object.getId() < 0) {
			return false;
		}
		update.clear();
		update.field(new String[] { Fields.USER_FIRST_NAME, Fields.USER_LAST_NAME, Fields.USER_EMAIL, Fields.USER_PHONE,
				Fields.USER_PASSWORD, Fields.USER_SEX, Fields.USER_ROLE });
		update.where(Fields.ENTITY_ID, true);
		LOG.info("Query: " + update.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(update.getQuery())) {
			int k = 1;
			pstmt.setString(k++, object.getName());
			pstmt.setString(k++, object.getSurname());
			pstmt.setString(k++, object.getEmail());
			pstmt.setString(k++, object.getPhone());
			pstmt.setString(k++, object.getPassword());
			pstmt.setInt(k++, object.getSex().ordinal());
			pstmt.setInt(k++, object.getRole().ordinal());
			pstmt.setLong(k, object.getId());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(Messages.USER_UPDATE_ERROR, ex);
			throw new DAOException(Messages.USER_UPDATE_ERROR, ex);
		}
		return true;
	}

	@Override
	public final Boolean delete(Connection con, Long id) {
		if (id < 0) {
			return false;
		}
		delete.clear();
		delete.where(Fields.ENTITY_ID, true); // "DELETE FROM `user` WHERE `id`
												// = ?"
		LOG.info("Query: " + delete.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(delete.getQuery())) {
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(Messages.USER_DELETE_ERROR, ex);
			throw new DAOException(Messages.USER_DELETE_ERROR, ex);
		}
		return true;
	}

	private User extractUser(final ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(Fields.ENTITY_ID));
		user.setEmail(rs.getString(Fields.USER_EMAIL));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setName(rs.getString(Fields.USER_FIRST_NAME));
		user.setSurname(rs.getString(Fields.USER_LAST_NAME));
		user.setPhone(rs.getString(Fields.USER_PHONE));
		user.setSex(Sex.getSex(rs.getInt(Fields.USER_SEX)));
		user.setRole(Role.getRole(rs.getInt(Fields.USER_ROLE)));
		return user;
	}
}
