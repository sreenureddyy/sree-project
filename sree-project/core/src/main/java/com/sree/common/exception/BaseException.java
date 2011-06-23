package com.sree.common.exception;

public class BaseException extends Exception {
	
	private static final long serialVersionUID = 3824140957216582208L;

	private String errorCode;
	
	public BaseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public BaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public String getErrorMessage(){
		return null;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
