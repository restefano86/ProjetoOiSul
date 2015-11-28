package br.com.oisul.spring;

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
import br.com.oisul.spring.service.usuario.UsuarioService;
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
		return "usuario";
	}
	
	@RequestMapping(value = "/abrirCadUsuarioSite", method = RequestMethod.GET)
	public String testeBanana(Model model) {
		
		Usuario usuario = new Usuario();
		usuario.setNome("Rafael");
		usuario.setEmail("rafael.estefano.rosa@gmail.com");
		usuario.setDdd("48");
		usuario.setTelefone("3246-8159");
		
		model.addAttribute("usuario", usuario);
		return "site/cadastro";
	}
	
	//For add and update person both
	@RequestMapping(value= "/addUsuario", method = RequestMethod.POST)
	public String addUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request){
		String url = request.getRequestURL().toString().replace("addUsuario", "");
		usuarioService.saveUsuario(usuario, url);
		return "redirect:/abrirCadUsuarioSite";
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
