package com.epam.pp.hasan.db.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.db.query.DeleteQuery;
import com.epam.pp.hasan.db.query.InsertQuery;
import com.epam.pp.hasan.db.query.SelectQuery;
import com.epam.pp.hasan.db.query.UpdateQuery;
import com.epam.pp.hasan.db.query.factory.QueryFactory;
import com.epam.pp.hasan.db.query.factory.impl.MySqlQueryFactoryImpl;
import com.epam.pp.hasan.web.exception.DAOException;

/**
 * @author Yosin_Hasan
 */
public abstract class AbstractDAOImpl {
    private static final Logger LOG = Logger.getLogger(AbstractDAOImpl.class);
    protected SelectQuery select;
    protected InsertQuery insert;
    protected UpdateQuery update;
    protected DeleteQuery delete;
    protected QueryFactory factory;


    protected void initQuery(String table, String label) {
        LOG.info("Init query factory");
        factory = new MySqlQueryFactoryImpl();
        insert = factory.getInsert(table);
        select = factory.getSelect(table, label);
        update = factory.getUpdate(table);
        delete = factory.getDelete(table);
    }
    protected void close(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close result set", ex);
                throw new DAOException("Cannot close result set", ex);
            }
        }
    }
}
