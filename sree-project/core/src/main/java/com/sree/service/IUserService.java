package com.sree.service;

import java.util.HashMap;
import java.util.List;

import com.sree.domain.User;

public interface IUserService {

	public List<User> findAllUsers();

	public void save(User p);

	public void merge(User p);

	public void remove(User p);

	public User getUserById(Long id);

	public Long getCount(HashMap<String, Object> filterMap);

	public List<User> getRange(Integer firstRow, Integer maxResult,
			String sortField, HashMap<String, Object> filterMap,
			boolean descending);

}
