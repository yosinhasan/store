package com.epam.pp.hasan.db.dao.impl;

import com.epam.pp.hasan.config.Fields;
import com.epam.pp.hasan.config.Messages;
import com.epam.pp.hasan.config.Tables;
import com.epam.pp.hasan.db.dao.ImagesDAO;
import com.epam.pp.hasan.entity.Images;
import com.epam.pp.hasan.web.exception.DAOException;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Yosin_Hasan
 */
public class MySqlImagesDAOImpl extends AbstractDAOImpl implements ImagesDAO {
    private static final Logger LOG = Logger.getLogger(MySqlImagesDAOImpl.class);

    public MySqlImagesDAOImpl() {
        initQuery(Tables.IMAGES, Tables.IMAGES_LABEL);
    }

    @Override
    public ArrayList<Images> readAll(Connection con) {
        ArrayList<Images> items = new ArrayList<>();
        Images item = null;
        select.clear();
        LOG.info("Query: " + select.getQuery());
        try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(select.getQuery())) {
            while (rs.next()) {
                item = extractImages(rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.IMAGES_FIND_ALL_ERROR, ex);
            throw new DAOException(Messages.IMAGES_FIND_ALL_ERROR, ex);
        }
        return items;
    }

    @Override
    public Images read(Connection con, String field, Object value) {
        if (field == null || field.isEmpty() || value == null) {
            return null;
        }
        select.clear();
        select.where(field, true);
        LOG.info("Query: " + select.getQuery());
        ResultSet rs = null;
        Images item = null;
        try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
            int k = 1;
            pstmt.setObject(k, value);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            if (rs.next()) {
                item = extractImages(rs);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.IMAGES_FIND_BY_FIELD_AND_VALUE, ex);
            throw new DAOException(Messages.IMAGES_FIND_BY_FIELD_AND_VALUE, ex);
        } finally {
            close(rs);
        }
        return item;
    }

    @Override
    public final Boolean create(Connection con, Images object) {
        ResultSet rs = null;
        long id = -1;
        insert.clear();
        insert.field(new String[]{Fields.ENTITY_PRODUCT_ID, Fields.ENTITY_NAME});
        LOG.info("Query: " + insert.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(insert.getQuery(), Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            pstmt.setInt(k++, object.getProductId());
            pstmt.setString(k, object.getImage());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getLong(1);
                object.setId(id);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.IMAGES_CREATE_ERROR, ex);
            throw new DAOException(Messages.IMAGES_CREATE_ERROR, ex);
        } finally {
            close(rs);
        }
        if (id > 0) {
            return true;
        }
        return false;
    }

    @Override
    public final Images read(Connection con, Long id) {
        ResultSet rs = null;
        Images item = null;
        select.clear();
        select.where(Fields.ENTITY_ID, true);
        LOG.info("Query: " + select.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
            pstmt.setLong(1, id);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            if (rs.next()) {
                item = extractImages(rs);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.IMAGES_FIND_BY_FIELD_ERROR, ex);
            throw new DAOException(Messages.IMAGES_FIND_BY_FIELD_ERROR, ex);
        } finally {
            close(rs);
        }
        return item;
    }

    @Override
    public final Boolean update(Connection con, Images object) {
        if (object == null || object.getId() == null || object.getId() < 0) {
            return false;
        }
        update.clear();
        update.field(Fields.ENTITY_PRODUCT_ID);
        update.field(Fields.ENTITY_NAME);
        update.where(Fields.ENTITY_ID, true);
        LOG.info("Query: " + update.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(update.getQuery())) {
            int k = 1;
            pstmt.setString(k++, object.getImage());
            pstmt.setInt(k++, object.getProductId());
            pstmt.setLong(k, object.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(Messages.IMAGES_UPDATE_ERROR, ex);
            throw new DAOException(Messages.IMAGES_UPDATE_ERROR, ex);
        }
        return true;
    }

    @Override
    public final Boolean delete(Connection con, Long id) {
        if (id < 0) {
            return false;
        }
        delete.clear();
        delete.where(Fields.ENTITY_ID, true); // "DELETE FROM `Images` WHERE
        // `id`
        // = ?"
        LOG.info("Query: " + delete.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(delete.getQuery())) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(Messages.IMAGES_DELETE_ERROR, ex);
            throw new DAOException(Messages.IMAGES_DELETE_ERROR, ex);
        }
        return true;
    }

    @Override
    public ArrayList<Images> readAll(Connection con, Long productId) {
        ArrayList<Images> items = new ArrayList<>();
        Images item;
        select.clear();
        select.where(Fields.ENTITY_PRODUCT_ID, true);
        select.order(Fields.ENTITY_NAME);
        LOG.info("Query: " + select.getQuery());
        ResultSet rs = null;
        try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
            pstmt.setLong(1, productId);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            while (rs.next()) {
                item = extractImages(rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.IMAGES_FIND_BY_FIELD_ERROR, ex);
            throw new DAOException(Messages.IMAGES_FIND_BY_FIELD_ERROR, ex);
        } finally {
            close(rs);
        }
        return items;
    }

    private Images extractImages(final ResultSet rs) throws SQLException {
        Images item = new Images();
        item.setId(rs.getLong(Fields.ENTITY_ID));
        item.setProductId(rs.getInt(Fields.ENTITY_PRODUCT_ID));
        item.setImage(rs.getString((Fields.ENTITY_NAME)));
        return item;
    }

}
