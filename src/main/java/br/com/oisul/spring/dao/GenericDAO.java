package br.com.oisul.spring.dao;

import java.util.List;

public interface GenericDAO<PK, T> {
	
	public void addEntity(T entity);
	
	public T getEntityById(Integer id);
	
	public void sessionFlush();
	
	public void updateEntity(T entity);
	
	public void saveEntity(T entity);

	public void saveAllEntity(List<T> entities);
	
	public void deleteEntity(T entity);

}
