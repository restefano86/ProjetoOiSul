package br.com.oisul.spring.controllers.site;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.oisul.spring.model.Empresa;
import br.com.oisul.spring.model.Usuario;
import br.com.oisul.spring.service.empresa.EmpresaService;
import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class AquisicaoController extends DefaultController {

	private EmpresaService empresaService;
	
	@Autowired(required=true)
	@Qualifier(value="empresaService")
	public void setUsuarioService(EmpresaService service){
		this.empresaService = service;
	}

	@RequestMapping(value = "/aquisicaoPasso2", method = RequestMethod.GET)
	public String aquisicaoPasso2(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		Empresa empresa = empresaService.findEmpresaByUsuario(getIdUsuarioFromSession(request));
		if(empresa == null){
			empresa = new Empresa();
		} 
		model.addAttribute("empresa", empresa);
		return UrlsSite.AQUISICAO_PASSO_2.url;
	}
	
	@RequestMapping(value = "/addEmpresa", method = RequestMethod.POST)
	public String addEmpresa(@ModelAttribute("empresa") Empresa empresa, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;}
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		empresa.setIdUsuario(usuario.getIdUsuario());
		empresaService.saveEmpresa(empresa);
		return UrlsSite.AQUISICAO_PASSO_3.url;
	}
	
	
	
	
}
