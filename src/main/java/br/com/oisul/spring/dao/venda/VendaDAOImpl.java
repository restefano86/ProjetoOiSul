package br.com.oisul.spring.dao.venda;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.oisul.spring.dao.GenericDAOImpl;
import br.com.oisul.spring.model.SituacaoVenda;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.utils.HQLBuilder;

public class VendaDAOImpl extends GenericDAOImpl<Integer, Venda> implements VendaDAO {
	
	public Venda getVendaFetchById(Integer idVenda) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
		hql.append("from Venda venda");
		hql.append("inner join fetch venda.itens itens");
		hql.append("left join fetch itens.operadora operadora");
		hql.append("left join fetch itens.produto produto");
		hql.append("left join fetch itens.produtoBL produtoBL");
//		hql.append("inner join fetch venda.consultor consultor");
//		hql.append("inner join fetch venda.usuario usuario");
//		hql.append("inner join fetch venda.situacao situacao");
		hql.append("inner join fetch venda.empresa empresa");
		hql.appendFiltro("where venda.idVenda = ? ", idVenda);
		return (Venda) hql.uniqueResult();
	}

	public List<Venda> findTopVendas(Venda venda, Integer limite) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
		List<Venda> vendas = new ArrayList<Venda>();
		
		hql.append("from Venda venda");
		hql.append("left join fetch venda.consultor consultor");
//		hql.append("inner join fetch venda.usuario usuario");
//		hql.append("inner join fetch venda.situacao situacao");
		hql.append("inner join fetch venda.empresa empresa");
		hql.append("where 1=1");
		hql.appendFiltro("and venda.idVenda = ? ", venda.getId());
		hql.appendFiltro("and venda.dtContratoGerado >= ? ", venda.getDtContratoGerado());
		hql.appendFiltro("and venda.idConsultor = ? ", venda.getIdConsultor());
		
		if(venda.getConsultor().getNome() != null){
			hql.appendFiltro("and upper(venda.consultor.nome) like upper(?) ", "%"+venda.getConsultor().getNome()+"%");
		}
		if(venda.getEmpresa().getDeRazaoSocial() != null){
			hql.appendFiltro("and upper(venda.empresa.deRazaoSocial) like upper(?) ", "%"+venda.getEmpresa().getDeRazaoSocial()+"%");
		}
		
		if(venda.getSituacao().getDeSituacao() != null){
			hql.appendFiltro("and upper(venda.situacao.deSituacao) like upper(?) ", "%"+venda.getSituacao().getDeSituacao()+"%");
		}
		
		hql.append("order by venda.dtContratoGerado desc");
		
		hql.setMaxResults(limite);
		
		vendas = (List<Venda>) hql.list();
		
		for (Venda v : vendas) {
			HQLBuilder h = new HQLBuilder(session);
			h.append("from SituacaoVenda sit where sit.idSituacaoVenda = "+v.getIdSituacao());
			v.setSituacao((SituacaoVenda) h.uniqueResult());
		}
		
		return vendas;
	}

}
