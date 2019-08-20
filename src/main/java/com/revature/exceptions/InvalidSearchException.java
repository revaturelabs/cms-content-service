package com.revature.exceptions;

public class InvalidSearchException extends RuntimeException {

	/**
	 * Exception will be thrown when an InvalidSearch is done.
	 * @author 190617jta
	 */
	private static final long serialVersionUID = 8223426408754344315L;

	public InvalidSearchException(String msg) {
		super(msg);
	}
}
