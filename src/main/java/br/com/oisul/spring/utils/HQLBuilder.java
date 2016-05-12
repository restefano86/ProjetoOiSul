package br.com.oisul.spring.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

public class HQLBuilder {
	
	private StringBuilder hql;
	private Map<Integer, Object> parametros;
	private Query query;
	private Session session;
	private Integer maxResults;
	
	public HQLBuilder(Session session) {
		this.session = session;
		hql = new StringBuilder();
		parametros = new HashMap<Integer, Object>();
	}
	
	public void append(String appendHql){
		hql.append(appendHql).append(" ");
	}
	
	public void appendFiltro(String appendHql, String valor){
		if(StringUtils.isNotNulltOrBlank(valor)){
			appendFiltroByObject(appendHql, valor);
		}
	}

	public void appendFiltro(String appendHql, Date data){
		if(data != null){
			appendFiltroByObject(appendHql, data);
		}
	}

	public void appendFiltro(String appendHql, Double valor){
		if(valor != null){
			appendFiltroByObject(appendHql, valor);
		}
	}

	public void appendFiltro(String appendHql, Integer valor){
		if(valor != null){
			appendFiltroByObject(appendHql, valor);
		}
	}
	
	private void appendFiltroByObject(String appendHql, Object valor){
		hql.append(appendHql).append(" ");
		parametros.put(parametros.size(), valor);
	}
	
	public Object uniqueResult(){
		query = session.createQuery(hql.toString());
		addParametrosNaQuery();
		return query.uniqueResult();
	}
	
	public void setMaxResults(int maxResults){
		this.maxResults = maxResults;
	}
	
	public Query getQuery(){
		return this.query;
	}
	
	@SuppressWarnings("rawtypes")
	public List list(){
		query = session.createQuery(hql.toString());
		addParametrosNaQuery();
		if(maxResults != null){
			query.setMaxResults(maxResults);
		}
		return query.list();
	}
	
	private void addParametrosNaQuery(){
		for (Map.Entry<Integer,Object> parametro : parametros.entrySet()) {
			query.setParameter(parametro.getKey(), parametro.getValue());
		}
	}

}
