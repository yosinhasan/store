package com.epam.pp.hasan.db.query;

/**
 * @author Yosin_Hasan
 */
public interface InsertQuery {

    /**
     * <p>Add fields to insert query,
     * the added fields will indicate the value of what fields will be inserted to table.</p>
     *
     * @param fields fields to add to insert query
     */
    void field(String[] fields);


    /**
     * <p>Add fields to insert query,
     * the added fields will indicate the value of what fields will be inserted to table.</p>
     *
     * @param valuesToInsert values to insert
     * @param fields         fields to add to insert query
     */
    void field(String[] fields, Integer valuesToInsert);


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
