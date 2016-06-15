
package com.epam.pp.hasan.service;

import com.epam.pp.hasan.entity.User;

import java.util.ArrayList;

/**
 * Interface for the User Service
 *
 * @author Yosin Hasan
 */
public interface UserService {
	/**
	 * Find user by email.
	 *
	 * @param email
	 *            email
	 * @return User
	 */
	User findUser(String email);

	/**
	 * Find user by field and value.
	 *
	 * @param field
	 *            user field
	 * @param value
	 *            user field value
	 * @return User
	 */
	User findUser(String field, Object value);

	/**
	 * Find user by user id.
	 *
	 * @param id
	 *            id
	 * @return User
	 */

	User findUser(Long id);

	/**
	 * Add user.
	 *
	 * @param user
	 *            user to add
	 * @return Boolean
	 */
	Boolean addUser(User user);

	/**
	 * Update user.
	 *
	 * @param user
	 *            user to update
	 * @return Boolean
	 */
	Boolean updateUser(User user);

	/**
	 * Delete user by id.
	 *
	 * @param id
	 *            user id
	 * @return Boolean
	 */
	Boolean deleteUser(Long id);

	/**
	 * Find all.
	 *
	 * @return ArrayList<Category>
	 */
	ArrayList<User> findAll();
}
