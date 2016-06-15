package com.epam.pp.hasan.db.dao.impl;

import com.epam.pp.hasan.config.Fields;
import com.epam.pp.hasan.config.Messages;
import com.epam.pp.hasan.config.Tables;
import com.epam.pp.hasan.db.dao.DiscountDAO;
import com.epam.pp.hasan.entity.Discount;
import com.epam.pp.hasan.web.exception.DAOException;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Yosin_Hasan on 24.05.16.
 */
public class MySqlDiscountDAOImpl extends AbstractDAOImpl implements DiscountDAO {
    private static final Logger LOG = Logger.getLogger(MySqlDiscountDAOImpl.class);

    public MySqlDiscountDAOImpl() {
        initQuery(Tables.DISCOUNT, Tables.DISCOUNT_LABEL);
    }

    @Override
    public ArrayList<Discount> readAll(Connection con) {
        ArrayList<Discount> items = new ArrayList<>();
        Discount item = null;
        select.clear();
        LOG.info("Query: " + select.getQuery());
        try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(select.getQuery())) {
            while (rs.next()) {
                item = extractDiscount(rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.DISCOUNT_FIND_ALL_ERROR, ex);
            throw new DAOException(Messages.DISCOUNT_FIND_ALL_ERROR, ex);
        }
        return items;
    }

    @Override
    public Discount read(Connection con, String field, Object value) {
        if (field == null || field.isEmpty() || value == null) {
            return null;
        }
        select.clear();
        select.where(field, true);
        LOG.info("Query: " + select.getQuery());
        ResultSet rs = null;
        Discount item = null;
        try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
            int k = 1;
            pstmt.setObject(k, value);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            if (rs.next()) {
                item = extractDiscount(rs);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.DISCOUNT_FIND_BY_FIELD_AND_VALUE, ex);
            throw new DAOException(Messages.DISCOUNT_FIND_BY_FIELD_AND_VALUE, ex);
        } finally {
            close(rs);
        }
        return item;
    }

    @Override
    public final Boolean create(Connection con, Discount object) {
        ResultSet rs = null;
        long id = -1;
        insert.clear();
        insert.field(new String[]{Fields.DISCOUNT_DISCOUNT});
        LOG.info("Query: " + insert.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(insert.getQuery(), Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            pstmt.setInt(k++, object.getDiscount());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getLong(1);
                object.setId(id);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.DISCOUNT_CREATE_ERROR, ex);
            throw new DAOException(Messages.DISCOUNT_CREATE_ERROR, ex);
        } finally {
            close(rs);
        }
        if (id > 0) {
            return true;
        }
        return false;
    }

    @Override
    public final Discount read(Connection con, Long id) {
        ResultSet rs = null;
        Discount item = null;
        select.clear();
        select.where(Fields.ENTITY_ID, true);
        LOG.info("Query: " + select.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
            pstmt.setLong(1, id);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            if (rs.next()) {
                item = extractDiscount(rs);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.DISCOUNT_FIND_BY_FIELD_ERROR, ex);
            throw new DAOException(Messages.DISCOUNT_FIND_BY_FIELD_ERROR, ex);
        } finally {
            close(rs);
        }
        return item;
    }

    @Override
    public final Boolean update(Connection con, Discount object) {
        if (object == null || object.getId() == null || object.getId() < 0) {
            return false;
        }
        update.clear();
        update.field(Fields.DISCOUNT_DISCOUNT);
        update.where(Fields.ENTITY_ID, true);
        LOG.info("Query: " + update.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(update.getQuery())) {
            int k = 1;
            pstmt.setInt(k++, object.getDiscount());
            pstmt.setLong(k, object.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(Messages.DISCOUNT_UPDATE_ERROR, ex);
            throw new DAOException(Messages.DISCOUNT_UPDATE_ERROR, ex);
        }
        return true;
    }

    @Override
    public final Boolean delete(Connection con, Long id) {
        if (id < 0) {
            return false;
        }
        delete.clear();
        delete.where(Fields.ENTITY_ID, true);
        LOG.info("Query: " + delete.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(delete.getQuery())) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(Messages.DISCOUNT_DELETE_ERROR, ex);
            throw new DAOException(Messages.DISCOUNT_DELETE_ERROR, ex);
        }
        return true;
    }

    private Discount extractDiscount(final ResultSet rs) throws SQLException {
        Discount item = new Discount();
        item.setId(rs.getLong(Fields.ENTITY_ID));
        item.setDiscount(rs.getInt(Fields.DISCOUNT_DISCOUNT));
        return item;
    }

}
