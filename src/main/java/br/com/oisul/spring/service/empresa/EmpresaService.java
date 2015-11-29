package br.com.oisul.spring.service.empresa;

import br.com.oisul.spring.model.Empresa;
import br.com.oisul.spring.service.GenericService;

public interface EmpresaService extends GenericService {

	public void saveEmpresa(Empresa empresa);
	
	public Empresa getEmpresaById(Integer id);
	
	public Empresa findEmpresaByUsuario(Integer idUsuario);

}
