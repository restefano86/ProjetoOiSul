package br.com.oisul.spring.dao.perfilvenda;

import java.util.List;

import org.hibernate.Session;

import br.com.oisul.spring.dao.GenericDAOImpl;
import br.com.oisul.spring.model.PerfilVenda;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.utils.HQLBuilder;

public class PerfilVendaDAOImpl extends GenericDAOImpl<Integer, PerfilVenda> implements PerfilVendaDAO  {

	@SuppressWarnings("unchecked")
	@Override
	public List<PerfilVenda> findAllPerfilVendaByIdVenda(Integer idVenda) {
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
		hql.append("from PerfilVenda perfil");
		hql.appendFiltro("where perfil.idVenda = ?", idVenda);
		return (List<PerfilVenda>) hql.list();
	}

}
