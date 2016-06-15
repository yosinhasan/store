package com.epam.pp.hasan.db.dao.impl;

import com.epam.pp.hasan.config.Fields;
import com.epam.pp.hasan.config.Messages;
import com.epam.pp.hasan.config.Tables;
import com.epam.pp.hasan.db.dao.BrandDAO;
import com.epam.pp.hasan.entity.Brand;
import com.epam.pp.hasan.web.exception.DAOException;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Yosin_Hasan
 */
public class MySqlBrandDAOImpl extends AbstractDAOImpl implements BrandDAO {
    private static final Logger LOG = Logger.getLogger(MySqlBrandDAOImpl.class);

    public MySqlBrandDAOImpl() {
        initQuery(Tables.BRAND, Tables.BRAND_LABEL);
    }

    @Override
    public ArrayList<Brand> readAll(Connection con) {
        ArrayList<Brand> items = new ArrayList<>();
        Brand item = null;
        select.clear();
        LOG.info("Query: " + select.getQuery());
        try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(select.getQuery())) {
            while (rs.next()) {
                item = extractBrand(rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.BRAND_FIND_ALL_ERROR, ex);
            throw new DAOException(Messages.BRAND_FIND_ALL_ERROR, ex);
        }
        return items;
    }

    @Override
    public Brand read(Connection con, String field, Object value) {
        if (field == null || field.isEmpty() || value == null) {
            return null;
        }
        select.clear();
        select.where(field, true);
        LOG.info("Query: " + select.getQuery());
        ResultSet rs = null;
        Brand item = null;
        try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
            int k = 1;
            pstmt.setObject(k, value);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            if (rs.next()) {
                item = extractBrand(rs);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.BRAND_FIND_BY_FIELD_AND_VALUE, ex);
            throw new DAOException(Messages.BRAND_FIND_BY_FIELD_AND_VALUE, ex);
        } finally {
            close(rs);
        }
        return item;
    }

    @Override
    public final Boolean create(Connection con, Brand object) {
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
            LOG.error(Messages.BRAND_CREATE_ERROR, ex);
            throw new DAOException(Messages.BRAND_CREATE_ERROR, ex);
        } finally {
            close(rs);
        }
        if (id > 0) {
            return true;
        }
        return false;
    }

    @Override
    public final Brand read(Connection con, Long id) {
        ResultSet rs = null;
        Brand item = null;
        select.clear();
        select.where(Fields.ENTITY_ID, true);
        LOG.info("Query: " + select.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
            pstmt.setLong(1, id);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            if (rs.next()) {
                item = extractBrand(rs);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.BRAND_FIND_BY_FIELD_ERROR, ex);
            throw new DAOException(Messages.BRAND_FIND_BY_FIELD_ERROR, ex);
        } finally {
            close(rs);
        }
        return item;
    }

    @Override
    public final Boolean update(Connection con, Brand object) {
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
            LOG.error(Messages.BRAND_UPDATE_ERROR, ex);
            throw new DAOException(Messages.BRAND_UPDATE_ERROR, ex);
        }
        return true;
    }

    @Override
    public final Boolean delete(Connection con, Long id) {
        if (id < 0) {
            return false;
        }
        delete.clear();
        delete.where(Fields.ENTITY_ID, true); // "DELETE FROM `Brand` WHERE `id`
        // = ?"
        LOG.info("Query: " + delete.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(delete.getQuery())) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(Messages.BRAND_DELETE_ERROR, ex);
            throw new DAOException(Messages.BRAND_DELETE_ERROR, ex);
        }
        return true;
    }

    private Brand extractBrand(final ResultSet rs) throws SQLException {
        Brand item = new Brand();
        item.setId(rs.getLong(Fields.ENTITY_ID));
        item.setName(rs.getString((Fields.ENTITY_NAME)));
        return item;
    }

}
