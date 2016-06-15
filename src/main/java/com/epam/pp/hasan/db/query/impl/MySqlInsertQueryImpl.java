package com.epam.pp.hasan.db.query.impl;

import com.epam.pp.hasan.db.query.InsertQuery;

/**
 * @author Yosin_Hasan
 */
public class MySqlInsertQueryImpl implements InsertQuery {
    private String table;
    private StringBuilder field;
    private StringBuilder value;

    public MySqlInsertQueryImpl(String table) {
        this.table = table;
    }

    @Override
    public void field(String[] fields) {
        this.field = new StringBuilder("(");
        this.value = new StringBuilder(" VALUES (");
        int size = fields.length;
        for (int i = 0; i < size; i++) {
            if (i + 1 == size) {
                this.field.append("`" + fields[i] + "`");
                this.value.append("?");
            } else {
                this.field.append("`" + fields[i] + "`, ");
                this.value.append("?,");
            }
        }
        this.field.append(") ");
        this.value.append(")");


    }

    @Override
    public void field(String[] fields, Integer valuesToInsert) {
        int size = fields.length;
        this.field = new StringBuilder("(");
        this.value = new StringBuilder(" VALUES ");
        for (int i = 0; i < size; i++) {
            if (i + 1 == size) {
                this.field.append("`" + fields[i] + "`");
            } else {
                this.field.append("`" + fields[i] + "`, ");
            }
        }
        this.field.append(")");
        for (int i = 0; i < valuesToInsert; i++) {
            this.value.append("(");
            for (int j = 0; j < size; j++) {
                if (j + 1 == size) {
                    this.value.append("?");
                } else {
                    this.value.append("?,");
                }
            }
            this.value.append(")");
            if (i + 1 != valuesToInsert) {
                this.value.append(",");
            }
        }
    }

    @Override
    public void clear() {
        field = null;
        value = null;
    }

    @Override
    public String getQuery() {
        return "INSERT INTO `" + table + "` " + field + value;
    }
}
