package br.com.oisul.spring.dao.venda;

import org.hibernate.Session;

import br.com.oisul.spring.dao.GenericDAOImpl;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.utils.HQLBuilder;

public class VendaDAOImpl extends GenericDAOImpl<Integer, Venda> implements VendaDAO {
	
	public Venda getVendaFetchById(Integer idVenda) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
		hql.append("from Venda venda");
		hql.append("inner join fetch venda.itens itens");
		hql.append("inner join fetch venda.consultor consultor");
		hql.append("inner join fetch venda.usuario usuario");
		hql.append("inner join fetch venda.situacao situacao");
		hql.append("inner join fetch venda.empresa empresa");
		hql.appendFiltro("where venda.idVenda = ? ", idVenda);
		return (Venda) hql.uniqueResult();
	}

}
