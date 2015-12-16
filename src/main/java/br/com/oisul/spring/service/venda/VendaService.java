package br.com.oisul.spring.service.venda;

import java.util.List;

import br.com.oisul.spring.exceptions.BusinessException;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.model.VendaDocumento;
import br.com.oisul.spring.model.VendaItem;

public interface VendaService {
	
	public void gerarVenda(Venda venda) throws BusinessException, Exception;
	
	public void geraDocumentosNovaVenda(Venda venda) throws Exception;
	
	public void validaPerfisVenda(List<VendaItem> vendaItens) throws BusinessException, CloneNotSupportedException;
	
	public VendaDocumento getVendaDocumentoByVisualizacao(Integer idVendaDocumento) throws Exception;

}
