package com.sree.common.exception;



public class ApplicationServiceException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5925303829509080814L;

	public ApplicationServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ApplicationServiceException(String errorCode, String message) {
		super(errorCode, message);
	}
	
	public ApplicationServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ApplicationServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ApplicationServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public ApplicationServiceException(String errorCode, String message, Throwable cause) {
		this(message, cause);
		this.setErrorCode(errorCode);
	}

}
