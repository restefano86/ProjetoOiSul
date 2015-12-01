package br.com.oisul.spring.service.contato;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import br.com.oisul.spring.dao.contato.ContatoDAO;
import br.com.oisul.spring.model.Contato;
import br.com.oisul.spring.service.GenericServiceImpl;
import br.com.oisul.spring.utils.EmailUtils;

public class ContatoServiceImpl  extends GenericServiceImpl implements ContatoService {

	private Logger LOG = Logger.getLogger(ContatoServiceImpl.class);
	private ContatoDAO contatoDAO;
	
	public void setContatoDAO(ContatoDAO contatoDAO) {
		this.contatoDAO = contatoDAO;
	}

	@Override
	@Transactional
	public void saveContato(Contato contato) throws Exception {
		try{
			this.contatoDAO.saveEntity(contato);
	     	String assunto = "Contato através do site";
	     	StringBuilder conteudo = new StringBuilder();
	     	conteudo.append("<h1>Contato através do site</h1><br>");
	     	conteudo.append("Mensagem deixada no site:");
	     	conteudo.append("<br><b>Nome:</b>");
	     	conteudo.append(contato.getNmContato());
	     	conteudo.append("<br><b>Telefone:</b>");
	     	conteudo.append(contato.getDeTelefone());
	     	conteudo.append("<br><b>E-mail:</b>");
	     	conteudo.append(contato.getDeEmail());
	     	conteudo.append("<br><b>Produto:</b>");
	     	conteudo.append(contato.getDeProduto());
	     	conteudo.append("<br><b>Mensagem:</b>");
	     	conteudo.append(contato.getDeMensagem());
			EmailUtils.enviaEmail(EmailUtils.CONTATO_EMAIL_SITE, conteudo.toString(), assunto);
		} catch(Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}
	}

	
}
