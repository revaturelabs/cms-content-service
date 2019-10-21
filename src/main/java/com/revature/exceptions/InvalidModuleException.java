package com.revature.exceptions;

public class InvalidModuleException extends RuntimeException {

	/**
	 * This exception will be called when an InvalidModule is used.
	 * @author 190617jta
	 */
	private static final long serialVersionUID = 6916781474204717188L;

	public InvalidModuleException(String msg) {
		super(msg);
	}
}
