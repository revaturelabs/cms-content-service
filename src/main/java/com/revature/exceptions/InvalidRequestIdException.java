package com.revature.exceptions;

/**
 * This custom exception will be thrown when an invalid (non-numeric) requestId is found.
 */
public class InvalidRequestIdException extends RuntimeException {
	
	private static final long serialVersionUID = 2423577185681222199L;

	/**
	 * Passes a String message to the {@link RuntimeException RuntimeException} class's constructor
	 * @param msg
	 */
	public InvalidRequestIdException(String msg) {
		super(msg);
	}
}
