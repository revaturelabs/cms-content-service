package com.revature.exceptions;

/**
 * This exception will be called when a {@link com.revature.entities.Module Module}'s subject is empty or is longer than 254 characters..
 */
public class InvalidModuleException extends RuntimeException {

	
	private static final long serialVersionUID = 6916781474204717188L;

	/**
	 * Passes a String message to the {@link RuntimeException RuntimeException} class's constructor
	 * @param msg
	 */
	public InvalidModuleException(String msg) {
		super(msg);
	}
}
