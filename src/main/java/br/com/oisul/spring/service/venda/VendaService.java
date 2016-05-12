package br.com.oisul.spring.service.venda;

import java.util.List;

import br.com.oisul.spring.exceptions.BusinessException;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.model.VendaDocumento;
import br.com.oisul.spring.model.VendaItem;

public interface VendaService {
	
	public void gerarVenda(Venda venda) throws BusinessException, Exception;
	
	public void geraDocumentosNovaVenda(Venda venda) throws Exception;
	
	public void validaPerfisVendaMovel(List<VendaItem> vendaItens) throws BusinessException, CloneNotSupportedException;

	public void validaPerfisVendaFixo(List<VendaItem> vendaItens) throws BusinessException, CloneNotSupportedException;
	
	public VendaDocumento getVendaDocumentoByVisualizacao(Integer idVendaDocumento) throws Exception;

	public Venda findVendaEdicao(Integer idVenda) throws Exception;
	
	public Venda findVendaByRelContratoFixo(Integer idVenda) throws Exception;

	public Venda findVendaByRelContratoMovel(Integer idVenda) throws Exception;
	
	public List<Venda> findTopVendas(Venda venda, Integer limite) throws Exception;

	public void adicionaArquivo(Venda venda, byte[] file, String filename) throws Exception;

	public void finalizaVenda(Venda venda) throws Exception;

	public void geraDocumentosNovaVendaMovel(Venda venda) throws Exception;

	public void geraDocumentosNovaVendaFixo(Venda venda) throws Exception;
	
	public List<VendaDocumento> findVendaDocumentosInseridos(Integer idVenda) throws Exception;
}
