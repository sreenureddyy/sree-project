package com.sree.sms;

import java.io.Serializable;

/**
 * @author YSReddi
 * 
 */
@SuppressWarnings("serial")
public class SmsMessage implements Serializable{
	
	private String username;
	
	private String password;
	
	private String mobile;
	
	private String message;
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}