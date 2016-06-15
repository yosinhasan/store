package com.epam.pp.hasan.db.dao.impl;

import com.epam.pp.hasan.config.Fields;
import com.epam.pp.hasan.config.Messages;
import com.epam.pp.hasan.config.Tables;
import com.epam.pp.hasan.db.dao.OrderDAO;
import com.epam.pp.hasan.db.query.InsertQuery;
import com.epam.pp.hasan.db.query.impl.MySqlInsertQueryImpl;
import com.epam.pp.hasan.entity.Order;
import com.epam.pp.hasan.entity.OrderProduct;
import com.epam.pp.hasan.entity.OrderStatus;
import com.epam.pp.hasan.web.exception.DAOException;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Yosin_Hasan
 */
public class MySqlOrderDAOImpl extends AbstractDAOImpl implements OrderDAO {
    private static final Logger LOG = Logger.getLogger(MySqlOrderDAOImpl.class);

    public MySqlOrderDAOImpl() {
        initQuery(Tables.ORDER, Tables.ORDER_LABEL);
    }

    @Override
    public ArrayList<Order> readAll(Connection con) {
        ArrayList<Order> items = new ArrayList<>();
        Order item;
        select.clear();
        LOG.info("Query: " + select.getQuery());
        try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(select.getQuery())) {
            while (rs.next()) {
                item = extractOrder(rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ORDER_FIND_ALL_ERROR, ex);
            throw new DAOException(Messages.ORDER_FIND_ALL_ERROR, ex);
        }
        return items;
    }

    @Override
    public Order read(Connection con, String field, Object value) {
        select.clear();
        select.where(field, true);
        select.join(Tables.ORDER_PRODUCT, Tables.ORDER_PRODUCT_LABEL, Fields.ORDER_PRODUCT_ORDER_ID, Fields.ENTITY_ID);
        LOG.info("Query: " + select.getQuery());
        ResultSet rs = null;
        Order item = null;
        OrderProduct orderProduct = null;
        ArrayList<OrderProduct> items = null;
        try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
            int k = 1;
            pstmt.setObject(k, value);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            items = new ArrayList<>();
            if (rs.next()) {
                item = extractOrder(rs);
                orderProduct = extractOrderProduct(rs);
                items.add(orderProduct);
            }
            while (rs.next()) {
                orderProduct = extractOrderProduct(rs);
                items.add(orderProduct);
            }
            item.setOrderProducts(items);
        } catch (SQLException ex) {
            LOG.error(Messages.ORDER_FIND_BY_FIELD_AND_VALUE, ex);
            throw new DAOException(Messages.ORDER_FIND_BY_FIELD_AND_VALUE, ex);
        } finally {
            close(rs);
        }
        return item;
    }


    @Override
    public final Order read(Connection con, Long id) {
        ResultSet rs = null;
        Order item = null;
        OrderProduct orderProduct = null;
        ArrayList<OrderProduct> items = new ArrayList<>();
        select.clear();
        select.where(Fields.ENTITY_ID, true);
        select.join(Tables.ORDER_PRODUCT, Tables.ORDER_PRODUCT_LABEL, Fields.ORDER_PRODUCT_ORDER_ID, Fields.ENTITY_ID);
        LOG.info("Query: " + select.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
            pstmt.setLong(1, id);
            LOG.info(pstmt);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            if (rs.next()) {
                item = extractOrder(rs);
                orderProduct = extractOrderProduct(rs);
                items.add(orderProduct);
            }
            while (rs.next()) {
                orderProduct = extractOrderProduct(rs);
                items.add(orderProduct);
            }
            item.setOrderProducts(items);

        } catch (SQLException ex) {
            LOG.error(Messages.ORDER_FIND_BY_FIELD_ERROR, ex);
            throw new DAOException(Messages.ORDER_FIND_BY_FIELD_ERROR, ex);
        } finally {
            close(rs);
        }
        return item;
    }

    @Override
    public Boolean create(Connection con, Order order) {
        long id = createMethod(con, order);
        ArrayList<OrderProduct> orderProducts = order.getOrderProducts();
        InsertQuery insertQuery = new MySqlInsertQueryImpl(Tables.ORDER_PRODUCT);
        String[] fields = new String[]{
                Fields.ORDER_PRODUCT_NAME,
                Fields.ORDER_PRODUCT_AMOUNT,
                Fields.ORDER_PRODUCT_PRICE,
                Fields.ORDER_PRODUCT_PRODUCT_ID,
                Fields.ORDER_PRODUCT_ORDER_ID};
        insertQuery.field(fields, orderProducts.size());
        ResultSet rs = null;
        LOG.info("Query: " + insertQuery.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(insertQuery.getQuery())) {
            int k = 1;
            Iterator<OrderProduct> iter = orderProducts.iterator();
            OrderProduct orderProduct;
            while (iter.hasNext()) {
                orderProduct = iter.next();
                pstmt.setString(k++, orderProduct.getProductName());
                pstmt.setInt(k++, orderProduct.getProductAmount());
                pstmt.setBigDecimal(k++, orderProduct.getProductPrice());
                pstmt.setLong(k++, orderProduct.getProductId());
                pstmt.setLong(k++, id);
            }
            LOG.info(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(Messages.ORDER_CREATE_ERROR, ex);
            throw new DAOException(Messages.ORDER_CREATE_ERROR, ex);
        } finally {
            close(rs);
        }
        return true;
    }

    @Override
    public final Boolean update(Connection con, Order object) {
        if (object == null || object.getId() == null || object.getId() < 0) {
            return false;
        }
        update.clear();
        update.field(new String[]{Fields.ORDER_STATUS, Fields.ORDER_DETAIL, Fields.ORDER_DATE, Fields.ORDER_USER_ID, Fields.ORDER_TOTAL_PRICE});
        update.where(Fields.ENTITY_ID, true);
        LOG.info("Query: " + update.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(update.getQuery())) {
            int k = 1;
            pstmt.setInt(k++, object.getStatus().ordinal());
            pstmt.setString(k++, object.getDetail());
            pstmt.setString(k++, object.getOrderDate());
            pstmt.setLong(k++, object.getUserId());
            pstmt.setBigDecimal(k++, object.getTotalPrice());
            pstmt.setLong(k, object.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(Messages.ORDER_UPDATE_ERROR, ex);
            throw new DAOException(Messages.ORDER_UPDATE_ERROR, ex);
        }
        return true;
    }

    @Override
    public final Boolean delete(Connection con, Long id) {
        delete.clear();
        delete.where(Fields.ENTITY_ID, true);
        LOG.info("Query: " + delete.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(delete.getQuery())) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(Messages.ORDER_DELETE_ERROR, ex);
            throw new DAOException(Messages.ORDER_DELETE_ERROR, ex);
        }
        return true;
    }

    private Order extractOrder(final ResultSet rs) throws SQLException {
        Order item = new Order();
        item.setId(rs.getLong(Fields.ENTITY_ID));
        item.setDetail(rs.getString(Fields.ORDER_DETAIL));
        item.setOrderDate(rs.getString(Fields.ORDER_DATE));
        item.setStatus(OrderStatus.getStatus(rs.getInt(Fields.ORDER_STATUS)));
        item.setUserId(rs.getLong(Fields.ORDER_USER_ID));
        item.setTotalPrice(rs.getBigDecimal(Fields.ORDER_TOTAL_PRICE));
        item.setPaymentId(rs.getInt(Fields.ORDER_PAYMENT_ID));
        item.setUserAddress(rs.getString(Fields.ORDER_USER_ADDRESS));
        item.setCreditCardNumber(rs.getLong(Fields.ORDER_CREDIT_CARD_NUMBER));
        return item;
    }

    private OrderProduct extractOrderProduct(ResultSet rs) throws SQLException {
        String name = rs.getString(Fields.ORDER_PRODUCT_NAME);
        Long id = rs.getLong(Fields.ORDER_PRODUCT_PRODUCT_ID);
        Integer amount = rs.getInt(Fields.ORDER_PRODUCT_AMOUNT);
        BigDecimal price = rs.getBigDecimal(Fields.ORDER_PRODUCT_PRICE);
        Long orderId = rs.getLong(Fields.ORDER_PRODUCT_ORDER_ID);
        return new OrderProduct(id, name, amount, price, orderId);
    }

    private Long createMethod(Connection con, Order object) {
        ResultSet rs = null;
        long id = -1;
        insert.clear();
        insert.field(new String[]{
                Fields.ORDER_STATUS,
                Fields.ORDER_DETAIL,
                Fields.ORDER_DATE,
                Fields.ORDER_USER_ID,
                Fields.ORDER_TOTAL_PRICE,
                Fields.ORDER_PAYMENT_ID,
                Fields.ORDER_USER_ADDRESS,
                Fields.ORDER_CREDIT_CARD_NUMBER});
        LOG.info("Query: " + insert.getQuery());
        try (PreparedStatement pstmt = con.prepareStatement(insert.getQuery(), Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            pstmt.setInt(k++, object.getStatus().ordinal());
            pstmt.setString(k++, object.getDetail());
            pstmt.setString(k++, object.getOrderDate());
            pstmt.setLong(k++, object.getUserId());
            pstmt.setBigDecimal(k++, object.getTotalPrice());
            pstmt.setInt(k++, object.getPaymentId());
            pstmt.setString(k++, object.getUserAddress());
            pstmt.setLong(k, object.getCreditCardNumber());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getLong(1);
                object.setId(id);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ORDER_CREATE_ERROR, ex);
            throw new DAOException(Messages.ORDER_CREATE_ERROR, ex);
        } finally {
            close(rs);
        }
        return id;
    }

}
