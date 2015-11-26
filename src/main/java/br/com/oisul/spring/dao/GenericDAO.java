package br.com.oisul.spring.dao;

public interface GenericDAO<PK, T> {
	
	public void addEntity(T entity);
	
	public T getEntityById(Integer id);

}
