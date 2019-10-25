package com.revature.exceptions;

/**
 * This custom exception will be thrown when a {@link com.revature.entities.Content Content} object is
 * found to be invalid (e.g. null when attempting to update the database).
 */
public class InvalidContentException extends RuntimeException {
	
	private static final long serialVersionUID = 2423577185681222199L;

	/**
	 * Passes a String message to the {@link RuntimeException RuntimeException} class's constructor
	 * @param msg
	 */
	public InvalidContentException(String msg) {
		super(msg);
	}
}
