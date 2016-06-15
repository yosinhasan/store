package com.epam.pp.hasan.service.impl;

import com.epam.pp.hasan.db.dao.OrderDAO;
import com.epam.pp.hasan.db.manager.DBManager;
import com.epam.pp.hasan.db.transaction.ExecuteOperation;
import com.epam.pp.hasan.db.transaction.TransactionManager;
import com.epam.pp.hasan.entity.Order;
import com.epam.pp.hasan.entity.OrderProduct;
import com.epam.pp.hasan.service.OrderService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author Yosin_Hasan
 */
public class MySqlOrderServiceImpl implements OrderService {
    private static final Logger LOG = Logger.getLogger(MySqlOrderServiceImpl.class);
    private OrderDAO orderDAO;
    private DBManager manager = DBManager.getInstance();

    public MySqlOrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Order findOrder(String field, Object value) {
        LOG.info("Find Order by field and value");
        Connection con = manager.getConnection();
        return (Order) TransactionManager.execute(con, new ExecuteOperation() {
            @Override
            public Order execute() {
                return orderDAO.read(con, field, value);
            }
        });
    }

    @Override
    public Order findOrder(Long id) {
        LOG.info("Find Order by id");
        Connection con = manager.getConnection();
        return (Order) TransactionManager.execute(con, new ExecuteOperation() {
            @Override
            public Order execute() {
                return orderDAO.read(con, id);
            }
        });
    }

    @Override
    public Boolean addOrder(Order Order) {
        LOG.info("Add Order");
        Connection con = manager.getConnection();
        return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
            @Override
            public Boolean execute() {
                return orderDAO.create(con, Order);
            }
        });
    }

    @Override
    public Boolean updateOrder(Order Order) {
        LOG.info("Update Order");
        Connection con = manager.getConnection();
        return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
            @Override
            public Boolean execute() {
                return orderDAO.update(con, Order);
            }
        });
    }

    @Override
    public Boolean deleteOrder(Long id) {
        LOG.info("Delete Order by id");
        Connection con = manager.getConnection();
        return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
            @Override
            public Boolean execute() {
                return orderDAO.delete(con, id);
            }
        });
    }

    @Override
    public ArrayList<Order> findAll() {
        LOG.info("Find all Orders");
        Connection con = manager.getConnection();
        return (ArrayList<Order>) TransactionManager.execute(con, new ExecuteOperation() {
                    @Override
                    public ArrayList<Order> execute() {
                        return orderDAO.readAll(con);
                    }
                }

        );
    }
}