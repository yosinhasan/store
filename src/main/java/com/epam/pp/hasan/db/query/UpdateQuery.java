package com.epam.pp.hasan.db.query;

/**
 * @author Yosin_Hasan
 */
public interface UpdateQuery extends GenericQuery {
    /**
     * <p>Add field to update query,
     * the added field will indicate the value of what field will be updated in table.</p>
     *
     * @param field
     */
    void field(String field);

    /**
     * <p>Add fields to update query,
     * the added fields will indicate the value of what fields will be updated in table.</p>
     *
     * @param fields
     */
    void field(String[] fields);
}
