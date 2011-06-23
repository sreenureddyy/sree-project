package com.sree.dao;

import java.util.List;

import org.hibernate.Session;

import com.sree.domain.User;

@SuppressWarnings("unchecked")
public interface IBaseDao {

	public void save(Object object);

	public void update(Object object);

	public void delete(Object object);

	public List find(String queryName, Object... objects);

	public List find(String queryName);

	public Session getSession();

	public List<User> find(int firstRow, int numberOfRows, String sortField,
			boolean descending, Class clazz);

	public Object find(Class clazz, Long id);
}
