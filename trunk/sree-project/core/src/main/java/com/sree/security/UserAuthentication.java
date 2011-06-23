/**
 * 
 */
package com.sree.security;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sree.domain.Authority;
import com.sree.domain.User;
import com.sree.service.IBaseService;

/**
 * @author YSReddi
 * 
 */
@SuppressWarnings("unchecked")
public class UserAuthentication implements UserDetailsService {

	@Autowired
	private IBaseService baseService;
	private static Logger log = Logger.getLogger(UserAuthentication.class);

	public IBaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		log.info("Log in User name :: " + username);
		List<User> userList = baseService.find("findUserByUserName", username);
		List<Authority> userAuthorities = baseService.find("getUserAuthorities", username);
		
		if (userList.size() == 0 || userAuthorities.size() == 0) {
			throw new UsernameNotFoundException(username + " not found");
		}
			
		User user = userList.get(0);
		
		for (Authority authority : userAuthorities) {
			user.getAuthorities().add(new GrantedAuthorityImpl(authority.getAuthority()));
		}
		
		return user;
	}

}
