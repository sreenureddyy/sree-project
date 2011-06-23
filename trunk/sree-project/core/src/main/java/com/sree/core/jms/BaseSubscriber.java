package com.sree.core.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sree.sms.SmsMessage;
import com.sree.sms.Way2SmsUtil;

/**
 * Abstract Implementation of the JMS Subscriber
 * 
 * Author: Sree
 */
@Transactional(rollbackFor = { Exception.class })
@Service("alertSubscriber")
public class BaseSubscriber implements IBaseSubscriber {

	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Entry method of the MessageListener Passes a message to the listener.
	 * 
	 * @param msg
	 */
	public void onMessage(Message msg) {
		logger.info("Recieving message");
		try {
			// logger.info(" Subscribed Message " + msg.toString() + " for
			// Topic/Queue " + msg.getJMSDestination());
		
			if ( msg instanceof ActiveMQTextMessage ){
				ActiveMQTextMessage message = (ActiveMQTextMessage)msg;
				logger.info("Message is ::"+ message.getText());
			}else if ( msg instanceof ObjectMessage ) {
				SmsMessage message = (SmsMessage) ((ObjectMessage) msg).getObject();
				Way2SmsUtil.sendSMS(message.getUsername(), message.getPassword(), message.getMobile(), message.getMessage(), message.getName() );
			} else {
				
			}
			
		} catch (Exception ex) {
			try {
				if (msg instanceof TextMessage)
					logger.error("Message : " + ((TextMessage) msg).getText(),
							ex);
				else if (msg instanceof ObjectMessage) {
					logger.error("Message : "
							+ ((ObjectMessage) msg).getObject().toString(), ex);
				} else {
					logger.error("Error : " + ex.getMessage(), ex);
				}
			} catch (JMSException e) {
				logger
						.error(
								"Error while accessing the content (for logging error caused during message processing) from a JMS message ",
								e);
				// e.printStackTrace();
			}
		}
	}
	/**
	 * This method is used to process the message what is got from the publisher
	 * based on the subcribing Topic/Queue
	 * 
	 * @param msg
	 * @throws Exception
	 */
	// protected abstract void process(Message msg) throws Exception;
}
