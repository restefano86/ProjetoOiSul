package br.com.oisul.spring.service.empresa;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import br.com.oisul.spring.dao.empresa.EmpresaDAO;
import br.com.oisul.spring.model.Empresa;
import br.com.oisul.spring.service.GenericService;
import br.com.oisul.spring.service.GenericServiceImpl;

public class EmpresaServiceImpl extends GenericServiceImpl implements EmpresaService {

	private Logger LOG = Logger.getLogger(GenericService.class);
	private EmpresaDAO empresaDAO;
	
	public void setEmpresaDAO(EmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}

	@Override
	@Transactional
	public void saveEmpresa(Empresa empresa) {
		this.empresaDAO.saveEntity(empresa);
		LOG.info(empresa.getIdEmpresa());
	}

	@Override
	@Transactional
	public Empresa getEmpresaById(Integer id) {
		return this.empresaDAO.getEntityById(id);
	}

	@Override
	@Transactional
	public Empresa findEmpresaByUsuario(Integer idUsuario) {
		return this.empresaDAO.findEmpresaByUsuario(idUsuario);
	}


}
