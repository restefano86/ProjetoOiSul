package br.com.oisul.spring.controllers.site;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.oisul.spring.model.Usuario;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.reports.contrato.fixo.RelContratoFixo;
import br.com.oisul.spring.reports.contrato.movel.RelContrato;
import br.com.oisul.spring.reports.contrato.movel.RelTermoPortabilidade;
import br.com.oisul.spring.service.usuario.UsuarioService;
import br.com.oisul.spring.utils.UrlsAdmin;
import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class UsuarioController extends DefaultController {
	
	private UsuarioService usuarioService;
	
	@Autowired(required=true)
	@Qualifier(value="usuarioService")
	public void setUsuarioService(UsuarioService service){
		this.usuarioService = service;
	}
	
	@RequestMapping(value = "/testeUsuario", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("usuario", new Usuario());
		RelContrato rel = new RelContrato();
		rel.geraRelatorio(20);
		System.out.println("Gerou o relat�rio");
		return "usuario";
	}
	
	@RequestMapping(value = "/abrirCadUsuarioSite", method = RequestMethod.GET)
	public String abrirCadUsuarioSite(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "site/cadastro";
	}
	
	@RequestMapping(value = "/testeRelatorio", method = RequestMethod.GET)
	public String testeRelatorio(Model model) {
		try {
			
//		model.addAttribute("usuario", new Usuario());
//		RelTermoPortabilidade rel = new RelTermoPortabilidade();
//		rel.geraRelatorio(47);
		RelContratoFixo relContratoFixo = new RelContratoFixo();
		relContratoFixo.geraRelatorio(new Venda());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "site/cadastro";
	}
	
	//For add and update person both
	@RequestMapping(value= "/addUsuario", method = RequestMethod.POST)
	public String addUsuario(Model model, @ModelAttribute("usuario") Usuario usuario, HttpServletRequest request){
		try {
			String url = request.getRequestURL().toString().replace("addUsuario", "");
			usuarioService.saveUsuario(usuario, url);
			model.addAttribute("usuario", new Usuario());
			addMensagemSucesso(model, "Seu cadastro foi realizado com sucesso.<br>Por Favor, verifique seu e-mail para realizar a confirma��o do cadastro.");
		} catch (Exception e) {
			addMensagemErroGenerica(model);
		}
		return "site/cadastro";
	}

	@RequestMapping(value= "/alterarSenha", method = RequestMethod.POST)
	public String alterarSenha(Model model, @ModelAttribute("usuario") Usuario usuario, HttpServletRequest request){
		try {
			Usuario usrSessao = (Usuario) request.getSession().getAttribute("usuario");
			usrSessao.setSenha(usuario.getSenha());
			usuario = usrSessao;
			usuarioService.alteraSenha(usuario);
			request.getSession().setAttribute("usuario", usuario);
			usuario = new Usuario();
			addMensagemSucesso(model, "Senha alterada com sucesso;");
		} catch (Exception e) {
			addMensagemErroGenerica(model);
		}
		return "admin/alterarSenha";
	}

	@RequestMapping(value = "/confirmarUsuario", method = RequestMethod.GET)
	public String confirmarUsuario(@RequestParam("idUsuario") String strIdUsuario, HttpServletRequest request) {
		try {
			Integer idUsuario = Integer.parseInt(strIdUsuario);
			Usuario usuario = usuarioService.ativaUsuario(idUsuario);
			if(usuario != null){
				request.getSession().setAttribute("usuario", usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "site/confirmadoSucesso";
	}

	@RequestMapping(value = "/loginUsuario", method = RequestMethod.POST)
	public String loginUsuario(Model model, @ModelAttribute("usuario") Usuario usr, HttpServletRequest request) {
		try {
			Usuario usuario = usuarioService.findUsuarioLogin(usr);
			if(usuario != null){
				request.getSession().setAttribute("usuario", usuario);
				if(usuario.isConsultor()){
					return UrlsAdmin.HOME.url;
				} else if(usuario.isAdmin()) {
					return UrlsAdmin.HOME.url;
				}
				return UrlsSite.HOME.url;
			} else {
				addMensagemErro(model, "Usu�rio ou senha incorretos. Se voc� n�o possui um usu�rio cadastrado, preencha o formul�rio abaixo, caso contr�rio, forne�a os dados corretos para acesso.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return UrlsSite.CADASTROUSUARIO.url;
	}

	@RequestMapping(value = "/logoutUsuario", method = RequestMethod.GET)
	public String logoutUsuario(HttpServletRequest request) {
		try {
			request.getSession().setAttribute("usuario", null);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "site/index";
	}

	



}
