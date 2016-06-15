package com.epam.pp.hasan.db.query;

/**
 * @author Yosin_Hasan
 */
public interface GenericQuery {
    /**
     * Add new field to where clause.
     *
     * @param field field to add to where clause
     * @param and   defines whether to add AND string before adding the new field,
     *              in case if the value of param and  is false OR string will be added by default
     */
    void where(String field, boolean and);

    /**
     * Add new field with label to where clause.
     *
     * @param field field to add to where clause
     * @param label label to add before field
     * @param and   defines whether to add AND string before adding the new field,
     *              in case if the value of param and  is false OR string will be added by default
     */
    void where(String field, String label, boolean and);

    /**
     * Get generated query.
     *
     * @return String
     */
    String getQuery();

    /**
     * Clear inputted data from query.
     */
    void clear();
}
