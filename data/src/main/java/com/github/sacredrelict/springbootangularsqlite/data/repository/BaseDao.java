package com.github.sacredrelict.springbootangularsqlite.data.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

/**
 * Created by Oleg on 14.05.2016.
 */
public interface BaseDao<T> {

	Session session();

	Session sessionNew();

	Transaction beginTransaction();

	void commitTransaction();

	void rollbackTransaction();

	void clearCache();

	void save(T entity);

	void delete(T entity);

	List<T> findAll();

	T findById(Long id);

	List<T> findByIds(Collection<Long> ids);

}
