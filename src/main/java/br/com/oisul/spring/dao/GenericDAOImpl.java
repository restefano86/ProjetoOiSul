package br.com.oisul.spring.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.oisul.spring.dao.usuario.UsuarioDAOImpl;
import br.com.oisul.spring.model.ModelInterface;
import br.com.oisul.spring.model.Person;

@Repository
public class GenericDAOImpl<PK, T extends ModelInterface> implements GenericDAO<PK, T>{
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioDAOImpl.class);
	protected SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public void addEntity(T entity) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(entity);
		logger.info("Person saved successfully, Person Details="+entity);
	}
	
	public void sessionFlush() {
		Session session = this.sessionFactory.getCurrentSession();
		session.flush();
		logger.info("############# FLUSH REALIZADO COM SUCESSO");
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
    
	public void updateEntity(T entity) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(entity);
		logger.info(getTypeClass()+" updated successfully");
	}

	public void saveEntity(T entity) {
		if(entity.getId() != null){
			this.updateEntity(entity);
		} else {
			this.addEntity(entity);
		}
	}

	public void deleteEntity(T entity) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(entity);
		logger.info(getTypeClass()+" DELETADO successfully");
	}

	public void saveAllEntity(List<T> entities) {
		for (T entity : entities) {
			if(entity.getId() != null){
				this.updateEntity(entity);
			} else {
				this.addEntity(entity);
			}
		}
	}



}
