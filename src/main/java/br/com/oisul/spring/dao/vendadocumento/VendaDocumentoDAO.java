package br.com.oisul.spring.dao.vendadocumento;

import java.util.List;

import br.com.oisul.spring.dao.GenericDAO;
import br.com.oisul.spring.model.VendaDocumento;

public interface VendaDocumentoDAO extends GenericDAO<Integer, VendaDocumento>  {

	public List<VendaDocumento> findVendaDocumentosNoFilesByVenda(Integer idVenda);
	
}
