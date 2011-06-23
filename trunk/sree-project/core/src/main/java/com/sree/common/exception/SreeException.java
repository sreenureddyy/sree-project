/**
 * 
 */
package com.sree.common.exception;

/**
 * @author srinivasr
 *
 */
@SuppressWarnings("serial")
public class SreeException extends Exception {
	
	public SreeException(){
		super();
	}

	public SreeException(String message) {
		super(message);
	}
	
	public SreeException(String message, Exception e) {
		super(message, e);
	}
	
}
