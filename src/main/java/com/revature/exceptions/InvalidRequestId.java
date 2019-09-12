package com.revature.exceptions;

/**
 * This exception will be thrown specifically when an InvalidContentId
 * is suggested. 
 * @author 190617jta
 *
 */
public class InvalidRequestId extends RuntimeException {

	private static final long serialVersionUID = -6234915356525286536L;

	public InvalidRequestId(String msg) {
		super(msg);
	}
}
