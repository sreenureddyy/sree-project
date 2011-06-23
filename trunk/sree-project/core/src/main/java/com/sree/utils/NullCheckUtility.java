package com.sree.utils;

import java.util.List;
import java.util.Map;

import javax.naming.directory.DirContext;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.sree.common.exception.NullCheckException;

/**
 * This class performs null checks for all possible objects 
 * @author Sreenivasa
 */

public class NullCheckUtility {

	/**
	 * This method will do the null check and throws ApplicationUtilityException
	 * if null founds
	 * 
	 * @param args - Object to check ,If it is null or not 
	 * @throws NullCheckException - Exception thrown if object found null
	 */
	public static void checkForNull(String msgId, Object args)
			throws NullCheckException {
			NullCheckUtility.msgId = msgId;
			if (null == args) {
				throw new NullCheckException(msgId);		/* If object is found null throw exception */
			} else if (args instanceof String) {
				nullString(args);							/* If null check is done for String */
			} else if (args instanceof Integer) {
				nullInteger(args);							/* If null check is done for Integer */
			} else if (args instanceof Map) {
				nullMap(args);								/* If null check is done for Map */
			} else if (args instanceof List) {
				nullList(args);								/* If null check is done for List */
			} else if (args instanceof Document) {
				nullObject(args);							/* If null check is done for Document */
			} else if (args instanceof Node) {
				nullObject(args);							/* If null check is done for Node */
			} else if (args instanceof Integer[]) {
				nullIntObject(args);						/* If null check is done for Integer[] */
			} else if (args instanceof DirContext) {
				nullDirContextObject(args);					/* If null check is done for DirContext */
			} 
	}

	/**
	 * This method perform null check against String
	 * 
	 * @param objString - Passed String object for null check 
	 * @throws NullCheckException - Exception thrown if String is found null
	 */
	private static void nullString(Object objString)
			throws NullCheckException {
		if (null == objString || ((String) objString).trim().length() <= 0)
			throw new NullCheckException(msgId);
	}

	/**
	 * This method perform null check against Integer
	 * 
	 * @param objInteger - Passed Integer object for null check
	 * @throws NullCheckException - Exception thrown if String is found null
	 */
	private static void nullInteger(Object objInteger)
			throws NullCheckException {
		if (null == objInteger)
			throw new NullCheckException(msgId);
	}

	/**
	 * This method perform null check against Map
	 * 
	 * @param objMap - Passed String for null check
	 * @throws NullCheckException - Exception thrown if String is found null
	 */
	private static void nullMap(Object objMap)
			throws NullCheckException {
		if (null == objMap || ((Map<?, ?>) objMap).size() <= 0)
			throw new NullCheckException(msgId);
	}

	/**
	 * This method perform null check against List
	 * 
	 * @param objList - Passed List object for null check
	 * @throws NullCheckException - Exception thrown if List object is found null
	 */
	private static void nullList(Object objList)
			throws NullCheckException {
		if (null == objList || ((List<?>) objList).size() <= 0)
			throw new NullCheckException(msgId);
	}

	/**
	 * This method perform null check against Node
	 * 
	 * @param objAnyObject - Passed Object object for null check
	 * @throws NullCheckException - Exception thrown if passed Object is found null
	 */
	private static void nullObject(Object objAnyObject)
			throws NullCheckException {
		if (null == objAnyObject)
			throw new NullCheckException(msgId);
	}

	/**
	 * This method perform null check against Integer Array
	 * 
	 * @param nullIntObject - Passed Integer[] Object for null check
	 * @throws NullCheckException - Exception thrown if Integer[] Object is found null
	 */
	private static void nullIntObject(Object nullIntObject)
			throws NullCheckException {
		if (null == nullIntObject || ((Integer[]) nullIntObject).length == 0)
			throw new NullCheckException(msgId);
	}

	/**
	 * This method perform null check against Directory Context objects
	 * 
	 * @param nullDirContextObject - Passed DirContext Object for null check
	 * @throws NullCheckException - Exception thrown if DirContext is found null
	 */
	private static void nullDirContextObject(Object nullDirContextObject)
			throws NullCheckException {
		if (null == nullDirContextObject)
			throw new NullCheckException(msgId);
	}

	/** Message to display if any object is found null */
	private static String msgId;
}
