package com.revature.exceptions;

public class InvalidContentId extends RuntimeException {

	private static final long serialVersionUID = -6234915356525286536L;

	public InvalidContentId(String msg) {
		super(msg);
	}
}
