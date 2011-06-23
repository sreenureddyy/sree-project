package com.sree.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sree.Exception.BaseException;
import com.sree.common.exception.ApplicationServiceException;
import com.sree.common.exception.DomainValidationException;
import com.sree.common.exception.IntegrationServiceException;
import com.sree.domain.User;
import com.sree.dao.IGenericDAO;
import com.sree.dao.IUserDAO;

@SuppressWarnings("unchecked")
@Transactional(rollbackFor = { ApplicationServiceException.class,
		IntegrationServiceException.class, DomainValidationException.class,
		BaseException.class })
@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Override
	public List<User> findAllUsers() {
		return userDAO.findAll(User.class);
	}

	@Override
	public Long getCount(HashMap<String, Object> filterMap) {
		return userDAO.getCount(User.class, filterMap);
	}

	@Override
	public List<User> getRange(Integer firstRow, Integer maxResult,
			String sortField, HashMap<String, Object> filterMap,
			boolean descending) {
		List<User> list = null;
		if (descending) {
			list = userDAO.findByFilter(User.class, firstRow, maxResult,
					sortField, IGenericDAO.SORT_ORDER.DESC, filterMap);
		} else {
			list = userDAO.findByFilter(User.class, firstRow, maxResult,
					sortField, IGenericDAO.SORT_ORDER.ASC, filterMap);
		}

		return list;
	}

	@Override
	public User getUserById(Long id) {
		User p = (User) userDAO.findById(User.class, id);
		return p;
	}

	@Override
	public void merge(User p) {
		userDAO.merge(p);
	}

	@Override
	public void remove(User p) {
		userDAO.remove(p);
	}

	@Override
	public void save(User p) {
		userDAO.persist(p);
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}


}
