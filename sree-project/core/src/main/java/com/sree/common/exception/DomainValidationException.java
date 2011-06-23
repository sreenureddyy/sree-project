package com.sree.common.exception;

@SuppressWarnings("serial")
public class DomainValidationException extends BaseException {
	public DomainValidationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DomainValidationException(String errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	
	public DomainValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DomainValidationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public String getErrorKey(){
		return getMessage();
	}
}
