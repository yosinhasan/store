package com.epam.pp.hasan.db.query.impl;

import com.epam.pp.hasan.db.query.GenericQuery;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public abstract class AbstractGenericQuery implements GenericQuery {
    protected String table;
    protected String label;
    protected StringBuilder where;

    public AbstractGenericQuery(String table, String label) {
        this.table = table;
        this.label = label;
    }

    @Override
    public void where(String field, boolean and) {
        String clause = "`" + field + "` = ? ";
        addWhere(clause, and);
    }

    @Override
    public void where(String field, String label, boolean and) {
        String clause = label + ".`" + field + "` = ? ";
        addWhere(clause, and);
    }

    protected void addWhere(String clause, boolean and) {
        if (where == null) {
            where = new StringBuilder(" WHERE ");
            where.append(clause);
        } else {
            if (and) {
                where.append("AND " + clause);
            } else {
                where.append("OR " + clause);
            }
        }
    }
}
