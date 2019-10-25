package com.revature.exceptions;

/**
 * This custom exception will be thrown when invalid input is found when a search is made.
 */
public class InvalidSearchException extends RuntimeException {

	private static final long serialVersionUID = 8223426408754344315L;

	/**
	 * Passes a String message to the {@link RuntimeException RuntimeException} class's constructor
	 * @param msg
	 */
	public InvalidSearchException(String msg) {
		super(msg);
	}
}
