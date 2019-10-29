package com.revature.exceptions;

/**
 * This custom exception will be thrown when a {@link com.revature.entities.Request Request} object is
 * found to be invalid (e.g. null when attempting to update the database).
 */
public class InvalidRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 2423577185681222199L;

	/**
	 * Passes a String message to the {@link RuntimeException RuntimeException} class's constructor
	 * @param msg
	 */
	public InvalidRequestException(String msg) {
		super(msg);
	}
}
