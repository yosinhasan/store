package com.epam.pp.hasan.db.query;

/**
 * @author Yosin_Hasan
 */
public interface SelectQuery extends GenericQuery {
    /**
     * <p>
     * Add distinct field to select query.
     * </p>
     *
     * @param field distinct field to add
     */
    void distinct(String field);

    /**
     * <p>
     * Add distinct field to select query.
     * </p>
     *
     * @param field distinct field
     * @param label field label
     */
    void distinct(String field, String label);

    /**
     * <p>
     * Add fields to select query. The added fields will define which table
     * columns will be taken.
     * </p>
     *
     * @param fields fields to add to select query
     */
    void field(String[] fields);

    /**
     * <p>
     * Add field to select query. The added field will define which table
     * columns will be taken.
     * </p>
     *
     * @param field field to add to select query
     */
    void field(String field);


    /**
     * <p>
     * Add field to select query. The added field will define by which table
     * column will be ordered the table.
     * </p>
     *
     * @param field field to add to select query
     */
    void order(String field);

    /**
     * <p>
     * Add field to select query. The added field will define by which table
     * column will be ordered the table.
     * </p>
     *
     * @param field field to add to select query
     * @param desc  defines whether to add DESC string before order field, if the
     *              value of desc param is false ASC string will be written
     */
    void order(String field, boolean desc);

    /**
     * <p>
     * Add limit.
     * </p>
     *
     * @param limit limit to add
     */
    void limit(Integer limit);

    /**
     * <p>
     * Add limit with offset.
     * </p>
     *
     * @param offset offset
     * @param limit  limit
     */
    void limit(Integer offset, Integer limit);

    /**
     * <p>
     * Adds inner join clause to select query.
     * </p>
     *
     * @param table      table name to join
     * @param label      label of table name
     * @param tableField field of table to join
     * @param field      field of main table
     */
    void join(String table, String label, String tableField, String field);

    /**
     * <p>
     * Adds group by clause to select query.
     * </p>
     *
     * @param field field to add to group by clause
     */
    void groupBy(String field);

    /**
     * <p>
     * Adds group by clause to select query.
     * </p>
     *
     * @param label label to add before adding table field
     * @param field field to add to group by clause
     */
    void groupBy(String label, String field);

    /**
     * <p>
     * Adds aggregate function to select query.
     * </p>
     *
     * @param aggregate aggregate function
     */
    void aggregate(String aggregate);

    /**
     * <p>
     * Adds having clause to select query.
     * </p>
     *
     * @param clause having clause to add to select query
     */
    void having(String clause);

    /**
     * <p>
     * Adds pattern matching string.
     * </p>
     *
     * @param label table label
     * @param field table field
     * @param and   defines whether to add AND string before adding the new field,
     *              in case if the value of param and is false OR string will be
     *              added by default
     */
    void like(String label, String field, boolean and);

    /**
     * <p>
     * Adds pattern matching string.
     * </p>
     *
     * @param field table field
     * @param and   defines whether to add AND string before adding the new field,
     *              in case if the value of param and is false OR string will be
     *              added by default
     */
    void like(String field, boolean and);

    /**
     * <p>
     * Adds between clause to where clause.
     * </p>
     *
     * @param field table field
     * @param and   defines whether to add AND string before adding the new field,
     *              in case if the value of param and is false OR string will be
     *              added by default
     */
    void between(String field, boolean and);

    /**
     * <p>Adds between clause to where clause.</p>
     *
     * @param label table label
     * @param field table field
     * @param and   defines whether to add AND string before adding the new field,
     *              in case if the value of param and is false OR string will be
     *              added by default
     */
    void between(String label, String field, boolean and);

    /**
     * <p>Adds IN clause to where clause.</p>
     *
     * @param field        table field to add to in clause
     * @param paramsAmount amount of params which will be written in brackets to in clause
     * @param not          defines whether to add not operator before in
     * @param and          defines whether to add AND string before adding the new field,
     *                     in case if the value of param and is false OR string will be
     *                     added by default
     */
    void in(String field, Integer paramsAmount, boolean not, boolean and);

    /**
     * <p>Adds IN clause to where clause.</p>
     *
     * @param label        table label
     * @param field        table field
     * @param paramsAmount amount of params which will be written in brackets to in clause
     * @param not          defines whether to add not operator before in
     * @param and          defines whether to add AND string before adding the new field,
     *                     in case if the value of param and is false OR string will be
     *                     added by default
     */
    void in(String label, String field, Integer paramsAmount, boolean not, boolean and);
}
