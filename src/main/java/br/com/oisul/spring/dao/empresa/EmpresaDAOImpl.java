package br.com.oisul.spring.dao.empresa;

import org.hibernate.Session;

import br.com.oisul.spring.dao.GenericDAOImpl;
import br.com.oisul.spring.model.Empresa;
import br.com.oisul.spring.utils.HQLBuilder;

public class EmpresaDAOImpl extends GenericDAOImpl<Integer, Empresa> implements EmpresaDAO {
	
	public Empresa findEmpresaByUsuario(Integer idUsuario) {
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
		hql.append("from Empresa empresa");
		hql.append("where 1=1");
		hql.appendFiltro("and empresa.idUsuario = ?", idUsuario);
		return (Empresa) hql.uniqueResult();
	}

}
