package com.goldskyer.tquant.storage.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.LockMode;

public interface BaseDao
{

	public abstract Object add(Object entity);

	public abstract void addOrModify(Object entity);

	public abstract void delete(Object entity);

	public abstract void delete(Class entityClass, Serializable id);

	public abstract void modify(Object entity);

	public abstract <T> T query(Class<T> entityClass, Serializable id);

	public abstract <T> T query(Class<T> entityClass, Serializable id, LockMode lockMode);

	public abstract List query(String queryString);

	public abstract List query(String queryString, Map paraMap);

	public abstract List query(String queryString, Object... values);

	public abstract List query(String queryString, Integer firstResult, Integer maxResults);

	public abstract List query(String queryString, Map paraMap, Integer firstResult, Integer maxResults);

	public abstract Object queryUnique(String queryString);

	public abstract Object queryUnique(String queryString, Map paraMap);

	public abstract Object queryUnique(String queryString, Object... values);

	public abstract long count(String queryString);

	public abstract long count(String queryString, Map paraMap);

	public abstract long count(String queryString, Object... values);

	public abstract int execute(String statement);

	public abstract int execute(String statement, Map paraMap);

	public abstract int execute(String statement, Object... values);

	public abstract void deleteAll(Class entityClass, List<Serializable> ids);

	public abstract <T> Set<T> query(Class<T> entityClass, Serializable[] ids);

	public List querySql(final String queryString, final Map paraMap, final Integer firstResult,
			final Integer maxResults);

	public long countSql(final String queryString, final Map paraMap);

	public Object querySqlUnique(final String queryString, final Map paraMap);

}
