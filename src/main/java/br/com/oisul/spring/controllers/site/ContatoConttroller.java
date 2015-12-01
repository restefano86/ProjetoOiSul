package br.com.oisul.spring.controllers.site;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.oisul.spring.model.Contato;
import br.com.oisul.spring.service.contato.ContatoService;
import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class ContatoConttroller extends DefaultController {

	private ContatoService contatoService;
	
	@Autowired(required=true)
	@Qualifier(value="contatoService")
	public void setContatoService(ContatoService service){
		this.contatoService = service;
	}	
	
	@RequestMapping(value = "/enviarContato", method = RequestMethod.POST)
	public String enviarContato(Model model, @ModelAttribute("contato") Contato contato, HttpServletRequest request) {
		try {
			contatoService.saveContato(contato);
		} catch (Exception e) {
			addMensagemErroGenerica(model);
		}
		model.addAttribute("contato", new Contato());
		addMensagemSucesso(model, "Contato realizado com sucesso, Em breve entraremos em contato.");
		return UrlsSite.CONTATO.url;
	}
	

	
	
	
}
