package com.epam.pp.hasan.web.exception;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public class DAOException extends RuntimeException {
	private static final long serialVersionUID = -8682246992274691804L;

	public DAOException(String s, Exception ex) {
        super(s, ex);
    }
}
