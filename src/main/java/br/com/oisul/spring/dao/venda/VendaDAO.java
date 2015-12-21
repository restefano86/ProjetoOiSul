package br.com.oisul.spring.dao.venda;

import java.util.List;

import br.com.oisul.spring.dao.GenericDAO;
import br.com.oisul.spring.model.Venda;

public interface VendaDAO extends GenericDAO<Integer, Venda> {
	
	public Venda getVendaFetchById(Integer idVenda) throws Exception;
	
	public List<Venda> findTopVendas(Venda venda, Integer limite) throws Exception;
	
}
