package com.epam.pp.hasan.db.query.factory;

import com.epam.pp.hasan.db.query.DeleteQuery;
import com.epam.pp.hasan.db.query.InsertQuery;
import com.epam.pp.hasan.db.query.SelectQuery;
import com.epam.pp.hasan.db.query.UpdateQuery;

/**
 * @author Yosin_Hasan
 */
public interface QueryFactory {
    /**
     * Get new select query.
     *
     * @param table table name
     * @param label label for table name
     * @return SelectQuery
     */
    SelectQuery getSelect(String table, String label);

    /**
     * Get new update query.
     *
     * @param table table name
     * @return UpdateQuery
     */
    UpdateQuery getUpdate(String table);

    /**
     * Get new delete query.
     *
     * @param table table name
     * @return DeleteQuery
     */
    DeleteQuery getDelete(String table);

    /**
     * Get new insert query.
     *
     * @param table table name
     * @return InsertQuery
     */
    InsertQuery getInsert(String table);

}
