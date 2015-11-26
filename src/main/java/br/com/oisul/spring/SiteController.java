package br.com.oisul.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.oisul.spring.model.Usuario;

@Controller
public class SiteController {
	
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
	
	
	
	
	

}
