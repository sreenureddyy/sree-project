/**
 * 
 */
package com.sree.web;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sree.domain.User;

/**
 * @author sreenivasa
 * 
 */
@SuppressWarnings("serial")
@Component(value = "userLogin")
@Scope(value = "session")
public class UserLogin extends BaseBean {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(UserLogin.class);
	UserDetails user = new User();

	public UserDetails getUser() {
		return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}
}
