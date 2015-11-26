package br.com.oisul.spring.dao;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.oisul.spring.dao.usuario.UsuarioDAOImpl;

@Repository
public class GenericDAOImpl<PK, T> implements GenericDAO<PK, T>{
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioDAOImpl.class);
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public void addEntity(T entity) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(entity);
		logger.info("Person saved successfully, Person Details="+entity);
	}
	
	public T getEntityById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();		
		@SuppressWarnings("unchecked")
		T entity = (T) session.load(getTypeClass(), new Integer(id));
		logger.info("Person loaded successfully, Person details="+entity);
		return entity;
	}
	
    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }


}
