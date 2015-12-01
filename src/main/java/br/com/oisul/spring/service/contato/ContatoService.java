package br.com.oisul.spring.service.contato;

import br.com.oisul.spring.model.Contato;
import br.com.oisul.spring.service.GenericService;

public interface ContatoService extends GenericService  {

	public void saveContato(Contato contato) throws Exception;
}
