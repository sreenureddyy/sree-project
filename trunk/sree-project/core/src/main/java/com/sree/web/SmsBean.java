/**
 * 
 */
package com.sree.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sree.core.jms.IBasePublisher;
import com.sree.service.IBaseService;
import com.sree.sms.SmsMessage;

/**
 * @author YSReddi
 * 
 */
@SuppressWarnings("serial")
@Component("smsBean")
@Scope(value = "request")
public class SmsBean extends BaseBean {
	private static Logger log = Logger.getLogger(SmsBean.class);

	@Autowired
	private IBaseService baseService;

	@Autowired
	private IBasePublisher basePublisher;

	private SmsMessage message = new SmsMessage();

	public IBasePublisher getBasePublisher() {
		return basePublisher;
	}

	public void setBasePublisher(IBasePublisher basePublisher) {
		this.basePublisher = basePublisher;
	}

	public IBaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	public void sendMessage() {
		log.info("Sending Message...");
		message.setUsername("9886919190");
		message.setPassword("trinay");
		message.setName("Sree");
		try {
			basePublisher.publish("AlertSubscriber", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		message = new SmsMessage();
	}

	public SmsMessage getMessage() {
		return message;
	}

	public void setMessage(SmsMessage message) {
		this.message = message;
	}
}
