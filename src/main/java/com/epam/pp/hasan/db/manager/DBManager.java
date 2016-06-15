package com.epam.pp.hasan.db.manager;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.web.exception.DBException;

/**
 * @author Yosin_Hasan
 */
public class DBManager {
    private static final Logger LOG = Logger.getLogger(DBManager.class);
    private static DBManager instance;
    private DataSource ds;

    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }


    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/store");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            LOG.error("Cannot obtain data source", ex);
            throw new DBException("Cannot obtain data source", ex);
        }
    }

    /**
     * Returns a DB connection from the Pool Connections.
     *
     * @return DB connection.
     */
    public Connection getConnection() {
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOG.error("Error cannot obtain connection", ex);
            throw new DBException("Error cannot obtain connection", ex);
        }
        return con;
    }
}
