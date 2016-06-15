package com.epam.pp.hasan.db.query.factory.impl;

import com.epam.pp.hasan.db.query.DeleteQuery;
import com.epam.pp.hasan.db.query.InsertQuery;
import com.epam.pp.hasan.db.query.SelectQuery;
import com.epam.pp.hasan.db.query.UpdateQuery;
import com.epam.pp.hasan.db.query.factory.QueryFactory;
import com.epam.pp.hasan.db.query.impl.MySqlDeleteQueryImpl;
import com.epam.pp.hasan.db.query.impl.MySqlInsertQueryImpl;
import com.epam.pp.hasan.db.query.impl.MySqlSelectQueryImpl;
import com.epam.pp.hasan.db.query.impl.MySqlUpdateQueryImpl;

/**
 * @author Yosin_Hasan
 */
public class MySqlQueryFactoryImpl implements QueryFactory {

    @Override
    public SelectQuery getSelect(String table, String label) {
        return new MySqlSelectQueryImpl(table, label);
    }

    @Override
    public UpdateQuery getUpdate(String table) {
        return new MySqlUpdateQueryImpl(table);
    }

    @Override
    public DeleteQuery getDelete(String table) {
        return new MySqlDeleteQueryImpl(table);
    }

    @Override
    public InsertQuery getInsert(String table) {
        return new MySqlInsertQueryImpl(table);
    }
}
