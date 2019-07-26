package com.revature.exceptions;

public class InvalidSearchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8223426408754344315L;

	public InvalidSearchException(String msg) {
		super(msg);
	}
}
