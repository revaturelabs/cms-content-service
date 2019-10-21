package com.revature.exceptions;

/**
 * This custom exception will be displayed when called upon, that 
 * being, when an InvalidContent is done.
 * @author 190617jta
 *
 */
public class InvalidContentException extends RuntimeException {
	
	private static final long serialVersionUID = 2423577185681222199L;

	public InvalidContentException(String msg) {
		super(msg);
	}
}
