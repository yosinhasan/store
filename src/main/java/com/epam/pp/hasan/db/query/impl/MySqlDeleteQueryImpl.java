package com.epam.pp.hasan.db.query.impl;

import com.epam.pp.hasan.db.query.DeleteQuery;

/**
 * @author Yosin_Hasan
 */
public class MySqlDeleteQueryImpl extends AbstractGenericQuery implements DeleteQuery {
    public MySqlDeleteQueryImpl(String table) {
        super(table, null);
    }

    @Override
    public String getQuery() {
        String query = "DELETE FROM `" + table + "` ";
        if (where != null) {
            query += where.toString();
        }
        return query;
    }

    @Override
    public void clear() {
        where = null;
    }
}
