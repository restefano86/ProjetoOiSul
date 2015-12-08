package br.com.oisul.spring.controllers.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.oisul.spring.controllers.site.DefaultController;
import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class AdminController extends DefaultController {
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return "admin/index";
	}

	@RequestMapping(value = "/abrirAlterarSenha", method = RequestMethod.GET)
	public String abrirAlterarSenha(Model model, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return "admin/alterarSenha";
	}

	@RequestMapping(value = "/abrirConVendas", method = RequestMethod.GET)
	public String abrirConVendas(Model model, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return "admin/conVendas";
	}

	@RequestMapping(value = "/abrirConUsuarios", method = RequestMethod.GET)
	public String abrirConUsuarios(Model model, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return "admin/conUsuario";
	}

}
