package com.epam.pp.hasan.db.dao.impl;

import com.epam.pp.hasan.config.Fields;
import com.epam.pp.hasan.config.Messages;
import com.epam.pp.hasan.config.Tables;
import com.epam.pp.hasan.db.dao.CategoryDAO;
import com.epam.pp.hasan.entity.Category;
import com.epam.pp.hasan.web.exception.DAOException;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Yosin_Hasan on 24.05.16.
 */
public class MySqlCategoryDAOImpl extends AbstractDAOImpl implements CategoryDAO {
    private static final Logger LOG = Logger.getLogger(MySqlCategoryDAOImpl.class);

    public MySqlCategoryDAOImpl() {
        initQuery(Tables.CATEGORY, Tables.CATEGORY_LABEL);
    }

    @Override
    public ArrayList<Category> readAll(Connection con) {
        ArrayList<Category> categories = new ArrayList<>();
        Category category = null;
        select.clear();
        LOG.info("Query: " + select.getQuery());
        try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(select.getQuery())) {
            while (rs.next()) {
                category = extractCategory(rs);
                categories.add(category);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.CATEGORY_FIND_ALL_ERROR, ex);
            throw new DAOException(Messages.CATEGORY_FIND_ALL_ERROR, ex);
        }
        return categories;
    }

    @Override
    public Category read(Connection con, String field, Object value) {
        if (field == null || field.isEmpty() || value == null) {
            return null;
        }
        select.clear();
        select.where(field, true);
        LOG.info("Query: " + select.getQuery());
        ResultSet rs = null;
        Category category = null;
        try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
            int k = 1;
            pstmt.setObject(k, value);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            if (rs.next()) {
                category = extractCategory(rs);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.CATEGORY_FIND_BY_FIELD_AND_VALUE, ex);
            throw new DAOException(Messages.CATEGORY_FIND_BY_FIELD_AND_VALUE, ex);
        } finally {
            close(rs);
        }
        return category;
    }

    @Override
    public final Boolean create(Connection con, Category object) {
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
            LOG.error(Messages.CATEGORY_CREATE_ERROR, ex);
            throw new DAOException(Messages.CATEGORY_CREATE_ERROR, ex);
        } finally {
            close(rs);
        }
        if (id > 0) {
            return true;
        }
        return false;
    }

    @Override
    public final Category read(Connection con, Long id) {
        ResultSet rs = null;
        Category category = null;
        select.clear();
        select.where(Fields.ENTITY_ID, true);
        LOG.info("Query: " + select.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
            pstmt.setLong(1, id);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            if (rs.next()) {
                category = extractCategory(rs);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.CATEGORY_FIND_BY_FIELD_ERROR, ex);
            throw new DAOException(Messages.CATEGORY_FIND_BY_FIELD_ERROR, ex);
        } finally {
            close(rs);
        }
        return category;
    }

    @Override
    public final Boolean update(Connection con, Category object) {
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
            LOG.error(Messages.CATEGORY_UPDATE_ERROR, ex);
            throw new DAOException(Messages.CATEGORY_UPDATE_ERROR, ex);
        }
        return true;
    }

    @Override
    public final Boolean delete(Connection con, Long id) {
        if (id < 0) {
            return false;
        }
        delete.clear();
        delete.where(Fields.ENTITY_ID, true); // "DELETE FROM `category` WHERE
        // `id` = ?"
        LOG.info("Query: " + delete.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(delete.getQuery())) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(Messages.CATEGORY_DELETE_ERROR, ex);
            throw new DAOException(Messages.CATEGORY_DELETE_ERROR, ex);
        }
        return true;
    }

    private Category extractCategory(final ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong(Fields.ENTITY_ID));
        category.setName(rs.getString((Fields.ENTITY_NAME)));
        return category;
    }

}
