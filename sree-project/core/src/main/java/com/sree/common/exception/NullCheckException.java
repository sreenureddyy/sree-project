package com.sree.common.exception;

/**
 * This will be thrown when any null check fails
 * 
 * @author Sreenivasa
 */
@SuppressWarnings("serial")
public class NullCheckException extends Exception {

	/** Default constructor */
	public NullCheckException() {
	}

	/**
	 * This constructor will pass message to it's super class
	 * 
	 * @param message -
	 *            Message Id refering to actual Message
	 */
	public NullCheckException(String message) {
		super(message);
	}

	/**
	 * This constructor will pass Throwale object to it's super class
	 * 
	 * @param cause -
	 *            Actual Exception thrown to super class
	 */
	public NullCheckException(Throwable cause) {
		super(cause);
	}

	/**
	 * This constructor will pass message and Throwale object to it's super
	 * class
	 * 
	 * @param message -
	 *            Message Id refering to actual Message
	 * @param cause -
	 *            Actual Exception thrown to super class
	 */
	public NullCheckException(String message, Throwable cause) {
		super(message, cause);
	}

}
