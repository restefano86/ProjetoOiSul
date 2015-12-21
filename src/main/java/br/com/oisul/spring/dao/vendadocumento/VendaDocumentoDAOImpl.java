package br.com.oisul.spring.dao.vendadocumento;

import java.util.List;

import org.hibernate.Session;

import br.com.oisul.spring.dao.GenericDAOImpl;
import br.com.oisul.spring.model.VendaDocumento;
import br.com.oisul.spring.utils.HQLBuilder;

public class VendaDocumentoDAOImpl extends GenericDAOImpl<Integer, VendaDocumento> implements VendaDocumentoDAO  {

	@SuppressWarnings("unchecked")
	public List<VendaDocumento> findVendaDocumentosGeradosNoFilesByVenda(Integer idVenda){
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
		hql.append("from VendaDocumento vendadocumento");
		hql.append("where vendadocumento.flOrigem = 'S'");
		hql.appendFiltro("and vendadocumento.idVenda = ?", idVenda);
		return (List<VendaDocumento>) hql.list();		
	}
	
	@SuppressWarnings("unchecked")
	public List<VendaDocumento> findVendaDocumentosInseridosNoFilesByVenda(Integer idVenda){
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
		hql.append("from VendaDocumento vendadocumento");
		hql.append("where vendadocumento.flOrigem = 'A'");
		hql.appendFiltro("and vendadocumento.idVenda = ?", idVenda);
		return (List<VendaDocumento>) hql.list();		
	}
	
}
