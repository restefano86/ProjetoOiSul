package br.com.oisul.spring.dao.perfilvenda;

import java.util.List;

import org.hibernate.Session;

import br.com.oisul.spring.dao.GenericDAOImpl;
import br.com.oisul.spring.model.PerfilVenda;
import br.com.oisul.spring.model.Produto;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<PerfilVenda> findAllPerfilVendaFetchByIdVenda(Integer idVenda) {
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
		hql.append("from PerfilVenda perfil");
		hql.appendFiltro("where perfil.idVenda = ?", idVenda);
		List<PerfilVenda> perfis = (List<PerfilVenda>) hql.list();
		
		for (PerfilVenda perfilVenda : perfis) {
			hql = new HQLBuilder(session);
			hql.append("from Produto produto");
			hql.appendFiltro("where produto.idProduto = ?", perfilVenda.getIdProduto());
			Produto produto = (Produto) hql.uniqueResult();
			
			Produto produtoBL = null;
			if(perfilVenda.getIdProdutoBL() != null){
				hql = new HQLBuilder(session);
				hql.append("from Produto produto");
				hql.appendFiltro("where produto.idProduto = ?", perfilVenda.getIdProdutoBL());
				produtoBL = (Produto) hql.uniqueResult();
			} 
			
			perfilVenda.setProduto(produto);
			perfilVenda.setProdutoBL(produtoBL);
		}
		
		return perfis;
	}

}
