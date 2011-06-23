package com.sree.core.jms;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * Base JMS Publisher Implementation of the Publishing API.
 * 
 * Author: Sree
 */
@Service(value = "basePublisher")
public class BasePublisher implements IBasePublisher {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private JmsTemplate template;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.srit.erp.jms.IPublisher#publish(java.lang.String,
	 *      java.lang.Object[])
	 */
	public Object publish(final String destination,
			final Object... objInformation) throws Exception {
		this.template.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				Message message = null;
				if (objInformation[0] instanceof String) {
					message = session.createTextMessage();
					((TextMessage) message).setText((String) objInformation[0]);
					logger.info(" Publishing text message " + objInformation
							+ " Destination " + destination);
				} else if (objInformation[0] instanceof Object) {
					message = session.createObjectMessage();
					((ObjectMessage) message)
							.setObject((Serializable) objInformation[0]);
					logger.info(" Publishing Object message " + objInformation
							+ " Destination " + destination);
				} else if (objInformation instanceof Object[]) {
					message = session.createObjectMessage();
					((ObjectMessage) message)
							.setObject((Serializable) objInformation);
					logger.info(" Publishing Object message " + objInformation
							+ " Destination " + destination);
				}
				return message;
			}
		});
		// No result returned back. This is a asynchronous comminucation hence,
		// a return object is not
		// expected from the destination.
		return null;
	}

}
