package com.github.sacredrelict.springbootangularsqlite.data.repository.impl;

import com.github.sacredrelict.springbootangularsqlite.data.repository.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Oleg on 14.05.2016.
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Session sessionNew() {
		return sessionFactory.openSession();
	}

	@Override
	public Transaction beginTransaction() {
		return sessionNew().beginTransaction();
	}

	@Override
	public void commitTransaction() {
		sessionNew().getTransaction().commit();
	}

	@Override
	public void rollbackTransaction() {
		sessionNew().getTransaction().rollback();
	}

	@Override
	public void clearCache() {
		sessionFactory.getCache().evictEntityRegions();
		sessionFactory.getCache().evictCollectionRegions();
		sessionFactory.getCache().evictDefaultQueryRegion();
		sessionFactory.getCache().evictQueryRegions();
	}

	protected abstract String getEntityName();

	@Override
	public void save(final T entity) {
		sessionNew().saveOrUpdate(entity);
	}

	@Override
	public void delete(final T entity) {
		sessionNew().delete(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return sessionNew().createQuery("FROM " + getEntityName()).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findById(final Long id) {
		return (T) sessionNew().createQuery("FROM " + getEntityName() + " where id = :id").setParameter("id", id)
				.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByIds(final Collection<Long> ids) {
		if (ids == null || ids.isEmpty())
			return Collections.emptyList();

		final Query query = sessionNew().createQuery("FROM " + getEntityName() + " where id in (:ids)");
		query.setParameterList("ids", ids);
		return query.list();
	}

	protected Query getCacheableQuery(String query) {
		return sessionNew().createQuery(query).setCacheable(true);
	}

}
