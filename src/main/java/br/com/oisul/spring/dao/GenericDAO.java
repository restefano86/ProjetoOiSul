package br.com.oisul.spring.dao;

public interface GenericDAO<PK, T> {
	
	public void addEntity(T entity);
	
	public T getEntityById(Integer id);
	
	public void updateEntity(T entity);
	
	public void saveEntity(T entity);


}
