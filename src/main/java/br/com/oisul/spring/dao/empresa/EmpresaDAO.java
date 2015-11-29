package br.com.oisul.spring.dao.empresa;

import br.com.oisul.spring.dao.GenericDAO;
import br.com.oisul.spring.model.Empresa;

public interface EmpresaDAO extends GenericDAO<Integer, Empresa>{
	
	public Empresa findEmpresaByUsuario(Integer idUsuario);

}
