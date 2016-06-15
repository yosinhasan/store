package com.epam.pp.hasan.db.query.impl;

import com.epam.pp.hasan.db.query.UpdateQuery;

/**
 * @author Yosin_Hasan
 */
public class MySqlUpdateQueryImpl extends AbstractGenericQuery implements UpdateQuery {
    private StringBuilder field;

    public MySqlUpdateQueryImpl(String table) {
        super(table, null);
    }

    @Override
    public void field(String field) {
        if (this.field == null) {
            this.field = new StringBuilder("`" + field + "` = ? ");
        } else {
            this.field.append(", `" + field + "` = ?");
        }
    }

    @Override
    public void field(String[] fields) {
        this.field = new StringBuilder();
        int size = fields.length;
        for (int i = 0; i < size; i++) {
            if (i + 1 == size) {
                this.field.append("`" + fields[i] + "` = ? ");
            } else {
                this.field.append("`" + fields[i] + "` = ?, ");
            }
        }
    }

    @Override
    public String getQuery() {
        StringBuilder query = new StringBuilder("UPDATE `" + table + "` SET " + field);
        if (where != null) {
            query.append(where);
        }
        return query.toString();
    }

    @Override
    public void clear() {
        field = null;
        where = null;
    }
}
