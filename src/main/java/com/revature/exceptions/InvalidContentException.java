package com.revature.exceptions;

public class InvalidContentException extends RuntimeException {
	
	private static final long serialVersionUID = 2423577185681222199L;

	public InvalidContentException(String msg) {
		super(msg);
	}
}
