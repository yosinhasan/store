package com.epam.pp.hasan.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.pp.hasan.entity.User;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public class ValidateUtil {
	/**
	 * Check if user entered valid number.
	 *
	 * @param number
	 *            user input to check
	 * @return true if entered text is valid number, false otherwise
	 */
	public static boolean isValidNumber(final String number) {
		if (number == null || number.isEmpty()) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[0-9]+$");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}

	/**
	 * Check if user entered valid number.
	 *
	 * @param number
	 *            user input to check
	 * @return true if entered text is valid number, false otherwise
	 */
	public static boolean isValidNumber(final Integer number) {
		if (number == null || number <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * Check if user entered data has string format.
	 *
	 * @param string
	 *            string to check
	 * @return true, if string has valid format
	 */
	public static boolean isValidString(final String string) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[a-zA-Z\u0430-\u044F\u0410-\u042F\u0451\u0401\\s]{3,}$");
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}

	/**
	 * Check if user entered data has date format.
	 *
	 * @param date
	 *            date to check
	 * @return true, if data has date format
	 */
	public static boolean isValidDate(final String date) {
		if (date == null || date.isEmpty()) {
			return false;
		}
		Pattern pattern = Pattern.compile(
				"^(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))$");
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}

	/**
	 * Check whether inputted string has phone format.
	 *
	 * @param phone
	 *            phone
	 * @return boolean
	 */
	public static boolean isValidPhone(final String phone) {
		return true;
	}

	/**
	 * Check whether string length greater than given length param.
	 *
	 * @param string
	 *            string to check
	 * @param length
	 *            length
	 * @return boolean
	 */
	public static boolean isValidLength(final String string, final int length) {
		if (string == null || string.isEmpty() || length <= 0) {
			return false;
		}
		return string.length() > length;
	}

	/**
	 * Check whether string length greater than given length param.
	 *
	 * @param string
	 *            string to check
	 * @param length
	 *            length
	 * @param maxlength
	 *            max length
	 * @return boolean
	 */
	public static boolean isValidLength(final String string, final int length, final int maxlength) {
		if (string == null || string.isEmpty() || length <= 0) {
			return false;
		}
		int size = string.length();
		return (size >= length) && (size <= maxlength);
	}

	/**
	 * Check whether inputted string has valid email format
	 *
	 * @param email
	 *            email to check
	 * @return boolean
	 */
	public static boolean isValidEmail(final String email) {
		Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Validates user parameters.
	 * 
	 * @param user
	 *            user to validate
	 * @return boolean
	 */
	public static boolean validateUser(final User user) {
		String name = user.getName();
		String surname = user.getSurname();
		String email = user.getEmail();
		String phone = user.getPhone();
		String password = user.getPassword();
		String repeatPassword = user.getRepeatPassword();
		if (!isValidString(name) || !isValidLength(name, 5)) {
			return false;
		}
		if (!isValidString(surname) || !isValidLength(surname, 5)) {
			return false;
		}
		if (!isValidEmail(email)) {
			return false;
		}
		if (!isValidPhone(phone)) {
			return false;
		}
		if (!isValidLength(password, 6, 20) || !isValidLength(repeatPassword, 6, 20)
				|| !password.equals(repeatPassword)) {
			return false;
		}
		return true;
	}
}
