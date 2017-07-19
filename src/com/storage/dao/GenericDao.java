package com.storage.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface GenericDao<T> {
	void save(T entity);

	void update(T entity);

	void delete(T entity);

	T findById(Class<T> entityClass, Serializable id);

	List<T> findAll(Class<T> entityClass);

	List<T> findByNamedQuery(String queryName, Object... values);

	List<T> findByCriteria(DetachedCriteria criteria);

}
