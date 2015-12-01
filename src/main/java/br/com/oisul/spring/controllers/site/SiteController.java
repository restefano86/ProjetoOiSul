package br.com.oisul.spring.controllers.site;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.oisul.spring.model.Contato;
import br.com.oisul.spring.model.Usuario;
import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class SiteController extends DefaultController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return UrlsSite.HOME.url;
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
		model.addAttribute("contato", new Contato());
		return "site/contato";
	}
	

	
	@RequestMapping(value = "/cadastroNaoLogado", method = RequestMethod.GET)
	public String cadastroNaoLogado(Model model) {
		addMensagemAviso(model, "É necessário estar logado para iniciar o processo de contratação.<br> Faça o login na parte superior do site ou realize o cadastro abaixo.");
		model.addAttribute("usuario", new Usuario());
		return UrlsSite.CADASTROUSUARIO.url;
	}
	
	@RequestMapping(value = "/aquisicaoPasso0", method = RequestMethod.GET)
	public String aquisicaoPasso0(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsSite.AQUISICAO_PASSO_0.url;
	}
	
	@RequestMapping(value = "/aquisicaoPasso1", method = RequestMethod.GET)
	public String aquisicaoPasso1(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsSite.AQUISICAO_PASSO_1.url;
	}
	
	@RequestMapping(value = "/aquisicaoPasso3", method = RequestMethod.GET)
	public String aquisicaoPasso3(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsSite.AQUISICAO_PASSO_3.url;
	}
	
	@RequestMapping(value = "/aquisicaoPasso4", method = RequestMethod.GET)
	public String aquisicaoPasso4(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsSite.AQUISICAO_PASSO_4.url;
	}
	
	@RequestMapping(value = "/aquisicaoPasso5", method = RequestMethod.GET)
	public String aquisicaoPasso5(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsSite.AQUISICAO_PASSO_5.url;
	}
	
	
	
	
	

}
