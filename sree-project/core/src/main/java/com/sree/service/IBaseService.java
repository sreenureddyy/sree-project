package com.sree.service;

import java.util.List;

import org.hibernate.Session;

import com.sree.domain.User;

@SuppressWarnings("unchecked")
public interface IBaseService {
	public void save(Object obj);

	public void delete(Object object);

	public List find(String queryName, Object... objects);

	public Session getSession();

	public List<User> find(int firstRow, int numberOfRows, String sortField,
			boolean descending, Class clazz);

	public Object find(Class clazz, Long id);
}
