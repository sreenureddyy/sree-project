package com.sree.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.sree.domain.User;

@Repository("baseDao")
@SuppressWarnings("unchecked")
public class BaseDao implements IBaseDao {

	private static final Logger log = Logger.getLogger(BaseDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	public Session getSession() {
		return (Session) entityManager.getDelegate();
	}

	public void save(Object object) {
		getSession().saveOrUpdate(object);
	}

	public void update(Object object) {
		entityManager.merge(object);
	}

	public void delete(Object object) {
		getSession().delete(object);
	}

	public List find(String queryName, Object... objects) {
		if (queryName == null) {
			throw new IllegalArgumentException("queryName should not be null");
		}
		Query query = null;
		try {
			query = entityManager.createNamedQuery(queryName);

			if (objects != null && objects.length > 0) {
				for (int i = 0; i < objects.length; i++) {
					query.setParameter(i + 1, objects[i]);
				}
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return query.getResultList();
	}

	public List find(String queryName) {
		return find(queryName, new Object[]{});
	}

	@Override
	public List<User> find(int firstRow, int numberOfRows, String sortField,
			boolean descending, Class clazz) {
		log.info("FirstRow :: " + firstRow + " NumberOfRows :: "
						+ numberOfRows + " SortField :: " + sortField
						+ " Descending :: " + descending);
		Criteria criteria = getSession().createCriteria(clazz);
		criteria.setFirstResult(firstRow);
		criteria.setMaxResults(numberOfRows);
		if (descending)
			criteria.addOrder(Order.desc(sortField));
		else
			criteria.addOrder(Order.asc(sortField));

		return criteria.list();
	}

	@Override
	public Object find(Class clazz, Long id) {
		return getSession().get(clazz, id);
	}

}
