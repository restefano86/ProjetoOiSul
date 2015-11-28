package br.com.oisul.spring.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;

public class HQLBuilder {
	
	StringBuilder hql;
	Map<Integer, Object> parametros;
	Query query;
	Session session;
	
	public HQLBuilder(Session session) {
		this.session = session;
		hql = new StringBuilder();
		parametros = new HashMap<Integer, Object>();
	}
	
	public void append(String appendHql){
		hql.append(appendHql).append(" ");
	}
	
	public void appendFiltro(String appendHql, Object valor){
		hql.append(appendHql).append(" ");
		parametros.put(parametros.size(), valor);
	}
	
	public Object uniqueResult(){
		query = session.createQuery(hql.toString());
		addParametrosNaQuery();
		return query.uniqueResult();
	}
	
	private void addParametrosNaQuery(){
		for (Map.Entry<Integer,Object> parametro : parametros.entrySet()) {
			query.setParameter(parametro.getKey(), parametro.getValue());
		}
	}

}
