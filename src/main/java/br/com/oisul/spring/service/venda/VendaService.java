package br.com.oisul.spring.service.venda;

import java.util.List;

import br.com.oisul.spring.exceptions.BusinessException;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.model.VendaItem;

public interface VendaService {
	
	public void gerarVenda(Venda venda) throws BusinessException;
	
	public void validaPerfisVenda(List<VendaItem> vendaItens) throws BusinessException, CloneNotSupportedException;

}
