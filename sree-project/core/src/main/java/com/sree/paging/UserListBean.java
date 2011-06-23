package com.sree.paging;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sree.domain.User;
import com.sree.service.IUserService;

@SuppressWarnings("serial")
@Component(value = "userListBean")
@Scope(value = "request")
public class UserListBean extends PaginatingDataModel<User, Long> {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(UserListBean.class);

	@Autowired
	private IUserService userService;

	@Override
	public List<User> findObjects(int firstRow, int numberOfRows,
			String sortField, HashMap<String, Object> filterMap,
			boolean descending) {
		return userService.getRange(firstRow, numberOfRows, sortField,
				filterMap, descending);
	}

	@Override
	public Long getId(User user) {
		return user.getId();
	}

	@Override
	public Long getNumRecords(HashMap<String, Object> filterMap) {
		return userService.getCount(filterMap);
	}

	@Override
	public User getObjectById(Long id) {
		return userService.getUserById(id);
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
