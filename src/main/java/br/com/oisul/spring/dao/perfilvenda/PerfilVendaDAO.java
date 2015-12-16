package br.com.oisul.spring.dao.perfilvenda;

import java.util.List;

import br.com.oisul.spring.dao.GenericDAO;
import br.com.oisul.spring.model.PerfilVenda;

public interface PerfilVendaDAO extends GenericDAO<Integer, PerfilVenda> {
	
	public List<PerfilVenda> findAllPerfilVendaByIdVenda(Integer idVenda);

}
