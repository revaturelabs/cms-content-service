package com.revature.exceptions;

/**
 * This custom exception will be thrown when an invalid (non-numeric) contentId is found.
 */
public class InvalidContentId extends RuntimeException {

	private static final long serialVersionUID = -6234915356525286536L;

	/**
	 * Passes a String message to the {@link RuntimeException RuntimeException} class's constructor
	 * @param msg
	 */
	public InvalidContentId(String msg) {
		super(msg);
	}
}
