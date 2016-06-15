package com.epam.pp.hasan.service.impl;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Fields;
import com.epam.pp.hasan.db.dao.UserDAO;
import com.epam.pp.hasan.db.manager.DBManager;
import com.epam.pp.hasan.db.transaction.ExecuteOperation;
import com.epam.pp.hasan.db.transaction.TransactionManager;
import com.epam.pp.hasan.entity.User;
import com.epam.pp.hasan.service.UserService;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public class MySqlUserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(MySqlUserServiceImpl.class);
    private UserDAO userDAO;
    private DBManager manager = DBManager.getInstance();

    public MySqlUserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User findUser(String email) {
        LOG.info("Find user by email");
        Connection con = manager.getConnection();
        return (User) TransactionManager.execute(con, new ExecuteOperation() {
            @Override
            public User execute() {
                return userDAO.read(con, Fields.USER_EMAIL, email);
            }
        });
    }

    @Override
    public User findUser(String field, Object value) {
        LOG.info("Find user by field and value");
        Connection con = manager.getConnection();
        return (User) TransactionManager.execute(con, new ExecuteOperation() {
            @Override
            public User execute() {
                return userDAO.read(con, field, value);
            }
        });
    }

    @Override
    public User findUser(Long id) {
        LOG.info("Find user by id");
        Connection con = manager.getConnection();
        return (User) TransactionManager.execute(con, new ExecuteOperation() {
            @Override
            public User execute() {
                return userDAO.read(con, id);
            }
        });
    }

    @Override
    public Boolean addUser(User user) {
        LOG.info("Add user");
        Connection con = manager.getConnection();
        return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
            @Override
            public Boolean execute() {
                return userDAO.create(con, user);
            }
        });
    }

    @Override
    public Boolean updateUser(User user) {
        LOG.info("Update user");
        Connection con = manager.getConnection();
        return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
            @Override
            public Boolean execute() {
                return userDAO.update(con, user);
            }
        });
    }

    @Override
    public Boolean deleteUser(Long id) {
        LOG.info("Delete user by id");
        Connection con = manager.getConnection();
        return (Boolean) TransactionManager.execute(con, new ExecuteOperation() {
            @Override
            public Boolean execute() {
                return userDAO.delete(con, id);
            }
        });
    }

    @Override
    public ArrayList<User> findAll() {
        LOG.info("Find all users");
        Connection con = manager.getConnection();
        return (ArrayList<User>) TransactionManager.execute(con, new
                ExecuteOperation() {
                    @Override
                    public ArrayList<User> execute() {
                        return userDAO.readAll(con);
                    }
                }

        );
    }
}
