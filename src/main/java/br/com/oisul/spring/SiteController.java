package br.com.oisul.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class SiteController extends DefaultController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "site/index";
	}
	
	@RequestMapping(value = "/solucoesMovel", method = RequestMethod.GET)
	public String solucoesMovel(Model model) {
		return "site/solucoesMovel";
	}
	
	@RequestMapping(value = "/solucoesFixo", method = RequestMethod.GET)
	public String solucoesFixo(Model model) {
		return "site/solucoesFixo";
	}
	
	@RequestMapping(value = "/parceiros", method = RequestMethod.GET)
	public String parceiros(Model model) {
		return "site/parceiros";
	}
	
	@RequestMapping(value = "/contato", method = RequestMethod.GET)
	public String contato(Model model) {
		return "site/contato";
	}
	
	@RequestMapping(value = "/aquisicaoPasso1", method = RequestMethod.GET)
	public String aquisicaoPasso1(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.HOME.url;};
		
		return UrlsSite.AQUISICAO_PASSO_1.url;
	}
	
	
	
	
	

}
