package br.com.oisul.spring.controllers.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.oisul.spring.controllers.site.DefaultController;
import br.com.oisul.spring.model.Empresa;
import br.com.oisul.spring.service.empresa.EmpresaService;
import br.com.oisul.spring.utils.UrlsAdmin;
import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class AquisicaoAdminController extends DefaultController {
	
	private EmpresaService empresaService;

	@Autowired(required=true)
	@Qualifier(value="empresaService")
	public void setEmpresaService(EmpresaService service){
		this.empresaService = service;
	}

	@RequestMapping(value = "/aquisicaoAdminPasso0", method = RequestMethod.GET)
	public String aquisicaoAdminPasso0(Model model, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsAdmin.AQUISICAO_PASSO_0.url;
	}

	@RequestMapping(value = "/aquisicaoAdminPasso1", method = RequestMethod.GET)
	public String aquisicaoPasso1(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsAdmin.AQUISICAO_PASSO_1.url;
	}
	
	@RequestMapping(value = "/aquisicaoAdminPasso2", method = RequestMethod.GET)
	public String aquisicaoPasso2(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		Empresa empresa = empresaService.findEmpresaByUsuario(getIdUsuarioFromSession(request));
		if(empresa == null){
			empresa = new Empresa();
		} 
		model.addAttribute("empresa", empresa);
		return UrlsAdmin.AQUISICAO_PASSO_2.url;
	}

	
	@RequestMapping(value = "/aquisicaoAdminPasso3", method = RequestMethod.GET)
	public String aquisicaoPasso3(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsAdmin.AQUISICAO_PASSO_3.url;
	}
	
	@RequestMapping(value = "/aquisicaoAdminPasso4", method = RequestMethod.GET)
	public String aquisicaoPasso4(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsAdmin.AQUISICAO_PASSO_4.url;
	}
	
	@RequestMapping(value = "/aquisicaoAdminPasso5", method = RequestMethod.GET)
	public String aquisicaoPasso5(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsAdmin.AQUISICAO_PASSO_5.url;
	}
	
	
}
