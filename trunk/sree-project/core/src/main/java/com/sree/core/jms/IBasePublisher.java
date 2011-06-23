package com.sree.core.jms;

/**
 * JMS Publisher API for publishing the information to the Queue/Topic 
 * 
 * @author Sree
 */
public interface IBasePublisher {
	
	/**
	 * Publishing the Object information to a specified destination Topic/Queue 
	 * 
	 * @param destination
	 * @param publishInfo
	 * @return
	 * @throws Exception
	 */
	public Object publish(String destination, final Object... publishInfo) throws Exception;
	
}
