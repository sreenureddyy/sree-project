/**
 * 
 */
package com.sree.Exception;

/**
 * @author srinivasr
 *
 */
@SuppressWarnings("serial")
public class BaseException extends Exception {
	public BaseException(){
		super();
	}
	
	public BaseException(String msg){
		super(msg);
	}
	
	public BaseException(Exception exception){
		super(exception);
	}
	
	public BaseException(String msg, Exception e){
		super(msg, e);
	}
}
