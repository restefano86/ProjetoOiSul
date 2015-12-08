package br.com.oisul.spring.service.situacaovenda;

import org.springframework.stereotype.Service;

import br.com.oisul.spring.dao.situacaovenda.SituacaoVendaDAO;

@Service
public class SituacaoVendaServiceImpl implements SituacaoVendaService {
	
	private SituacaoVendaDAO situacaoVendaDAO;
	
	public void setSituacaoVendaDAO(SituacaoVendaDAO situacaoVendaDAO) {
		this.situacaoVendaDAO = situacaoVendaDAO;
	}




}
