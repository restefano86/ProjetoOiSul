package br.com.oisul.spring.dao.vendadocumento;

import java.util.List;

import org.hibernate.Session;

import br.com.oisul.spring.dao.GenericDAOImpl;
import br.com.oisul.spring.model.VendaDocumento;
import br.com.oisul.spring.utils.HQLBuilder;

public class VendaDocumentoDAOImpl extends GenericDAOImpl<Integer, VendaDocumento> implements VendaDocumentoDAO  {

	@SuppressWarnings("unchecked")
	public List<VendaDocumento> findVendaDocumentosNoFilesByVenda(Integer idVenda){
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
//		hql.append("Select  ");
//		hql.append("vendadocumento.idVendaDocumento as idVendaDocumento,  ");
//		hql.append("vendadocumento.idVenda as idVenda,  ");
//		hql.append("vendadocumento.nmDocumento as nmDocumento,  ");
//		hql.append("vendadocumento.flOrigem as florigem  ");
		hql.append("from VendaDocumento vendadocumento");
		hql.appendFiltro("where vendadocumento.idVenda = ?", idVenda);
		return (List<VendaDocumento>) hql.list();		
	}
	
}
