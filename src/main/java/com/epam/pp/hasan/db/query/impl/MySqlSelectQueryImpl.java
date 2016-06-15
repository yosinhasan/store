package com.epam.pp.hasan.db.query.impl;

import com.epam.pp.hasan.db.query.SelectQuery;

/**
 * @author Yosin_Hasan
 */
public class MySqlSelectQueryImpl extends AbstractGenericQuery implements SelectQuery {
    private StringBuilder field;
    private StringBuilder order;
    private String limit;
    private StringBuilder join;
    private StringBuilder groupBy;
    private String having;
    private String distinct;

    public MySqlSelectQueryImpl(String table, String label) {
        super(table, label);
    }

    @Override
    public void field(String[] fields) {
        this.field = new StringBuilder();
        int size = fields.length;
        for (int i = 0; i < size; i++) {
            if (i + 1 == size) {
                this.field.append(label + ".`" + fields[i] + "` ");
            } else {
                this.field.append(label + ".`" + fields[i] + "`, ");
            }
        }
    }

    @Override
    public void field(String field) {
        if (this.field == null) {
            this.field = new StringBuilder(label + ".`" + field + "`");
        } else {
            this.field.append("," + label + ".`" + field + "`");
        }
    }

    @Override
    public void order(String field) {
        order(field, false);
    }

    @Override
    public void limit(Integer limit) {
        this.limit = " LIMIT " + limit;
    }

    @Override
    public void limit(Integer offset, Integer limit) {
        this.limit = " LIMIT " + offset + ", " + limit;
    }

    @Override
    public void order(String field, boolean desc) {
        if (this.order == null) {
            this.order = new StringBuilder();
            this.order.append(" ORDER BY " + this.label + ".`" + field + "`");
        } else {
            this.order.append(", " + this.label + ".`" + field + "`");
        }
        if (desc) {
            this.order.append(" DESC");
        }
    }

    @Override
    public void join(String table, String label, String tableField, String field) {
        if (join == null) {
            join = new StringBuilder();
        }
        join.append(" INNER JOIN `" + table + "` " + label);
        join.append(" ON " + label + ".`" + tableField + "` = " + this.label + ".`" + field + "`");

    }

    @Override
    public void groupBy(String field) {
        String group = this.label + ".`" + field + "` ";
        addGroupBy(group);

    }

    @Override
    public void groupBy(String label, String field) {
        String group = label + ".`" + field + "` ";
        addGroupBy(group);
    }

    private void addGroupBy(String group) {
        if (groupBy == null) {
            groupBy = new StringBuilder(" GROUP BY ");
            groupBy.append(group);
        } else {
            groupBy.append(", " + group);
        }

    }

    @Override
    public void aggregate(String aggregate) {
        if (this.field == null) {
            this.field = new StringBuilder(aggregate);
        } else {
            this.field.append(", " + aggregate);
        }
    }

    @Override
    public void having(String clause) {
        having = " HAVING " + clause;
    }

    @Override
    public void clear() {
        field = null;
        order = null;
        limit = null;
        join = null;
        groupBy = null;
        having = null;
        where = null;
        distinct = null;
    }

    @Override
    public void like(String label, String field, boolean and) {
        String clause = label + ".`" + field + "` LIKE ? ";
        addWhere(clause, and);

    }

    @Override
    public void like(String field, boolean and) {
        String clause = "`" + field + "`  LIKE ? ";
        addWhere(clause, and);
    }

    @Override
    public void between(String field, boolean and) {
        String clause = "`" + field + "` BETWEEN ? AND ? ";
        addWhere(clause, and);
    }

    @Override
    public void between(String label, String field, boolean and) {
        String clause = label + ".`" + field + "` BETWEEN ? AND ? ";
        addWhere(clause, and);
    }

    @Override
    public void in(String field, Integer paramsAmount, boolean not, boolean and) {
        String clause = getInClause(null, field, paramsAmount, not);
        addWhere(clause, and);
    }

    @Override
    public void in(String label, String field, Integer paramsAmount, boolean not, boolean and) {
        String clause = getInClause(label, field, paramsAmount, not);
        addWhere(clause, and);
    }

    @Override
    public void distinct(String field) {
        distinct = "DISTINCT " + this.label + ".`" + field + "`";
    }

    @Override
    public void distinct(String field, String label) {
        distinct = "DISTINCT " + label + ".`" + field + "`";
    }

    @Override
    public String getQuery() {
        StringBuilder query = new StringBuilder("SELECT ");
        if (distinct != null) {
            query.append(distinct);
            if (field != null) {
                query.append(",");
                query.append(field);
            }
        } else {
            if (field == null) {
                query.append(" * ");
            } else {
                query.append(field);
            }
        }
        query.append(" FROM `" + table + "` " + label);
        if (join != null) {
            query.append(join);
        }
        if (where != null) {
            query.append(where);
        }
        if (groupBy != null) {
            query.append(groupBy);
        }
        if (having != null) {
            query.append(having);
        }
        if (order != null) {
            query.append(order);
        }
        if (limit != null) {
            query.append(limit);
        }
        return query.toString();
    }


    private String getInClause(String label, String field, Integer paramsAmount, boolean not) {
        StringBuilder builder = new StringBuilder();
        if (label != null) {
            builder.append(label + ".");
        }
        builder.append("`" + field + "`");
        if (not) {
            builder.append(" NOT ");
        }
        builder.append(" IN (?");
        if (paramsAmount > 1) {
            for (int i = 1; i < paramsAmount; i++) {
                builder.append(", ?");
            }
        }
        builder.append(") ");
        return builder.toString();
    }

}
