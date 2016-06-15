package com.epam.pp.hasan.db.dao;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * 
 * @author Yosin_Hasan
 *
 * @param <T>
 */
public interface GenericDAO<T> {
	/**
	 * Add specific object to respective table.
	 *
	 * @param object
	 *            object
	 * @param con
	 *            connection to database
	 * @return Boolean
	 */
	Boolean create(Connection con, T object);

	/**
	 * Get object from respective table by id.
	 *
	 * @param id
	 *            object id
	 * @param con
	 *            connection to database
	 * @return T object
	 */
	T read(Connection con, Long id);

	/**
	 * Update object in respective table.
	 *
	 * @param object
	 *            object to update
	 * @param con
	 *            connection to database
	 * @return Boolean
	 */
	Boolean update(Connection con, T object);

	/**
	 * Delete object from respective table by id.
	 *
	 * @param id
	 *            object id
	 * @param con
	 *            connection to database
	 * @return Boolean
	 */
	Boolean delete(Connection con, Long id);

	/**
	 * Find entity from respective table by field and value.
	 *
	 * @param field
	 *            user field
	 * @param value
	 *            user value
	 * @param con
	 *            connection to database
	 * @return T
	 */
	T read(Connection con, String field, Object value);

	/**
	 * Get all objects from table.
	 *
	 * @param con
	 *            connection to database
	 * @return ArrayList<T>
	 */
	ArrayList<T> readAll(Connection con);
}
