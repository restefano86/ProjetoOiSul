package br.com.oisul.spring.controllers.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.oisul.spring.controllers.site.DefaultController;
import br.com.oisul.spring.model.Usuario;
import br.com.oisul.spring.service.empresa.EmpresaService;
import br.com.oisul.spring.service.usuario.UsuarioService;
import br.com.oisul.spring.utils.UrlsAdmin;
import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class UsuarioAdminController extends DefaultController {
	
	private UsuarioService usuarioService;
	
	@Autowired(required=true)
	@Qualifier(value="usuarioService")
	public void setUsuarioService(UsuarioService service){
		this.usuarioService = service;
	}


	@RequestMapping(value = "/abrirConUsuarios", method = RequestMethod.GET)
	public String abrirConUsuarios(Model model, HttpServletRequest request) {
		if(!validateLoginAdmin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		model.addAttribute("usuario", new Usuario());
		return UrlsAdmin.CONSULTA_USUARIOS_ADMIN.url;
	}

	@RequestMapping(value = "/consultarUsuarios", method = RequestMethod.POST)
	public String consultarUsuarios(Model model, @ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		try{
		if(!validateLoginAdmin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		List<Usuario> listaUsuarios = usuarioService.findUsuario(usuario);
		model.addAttribute("listaUsuarios", listaUsuarios);
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErroGenerica(model);
		}
		return UrlsAdmin.CONSULTA_USUARIOS_ADMIN.url;
	}

	@RequestMapping(value = "/abrirCadUsuario", method = RequestMethod.GET)
	public String abrirCadUsuario(Model model, HttpServletRequest request) {
		if(!validateLoginAdmin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		Usuario usuario = new Usuario();
		usuario.setIsAtivado("S");
		model.addAttribute("usuario", usuario);
		return UrlsAdmin.CADASTRO_USUARIOS_ADMIN.url;
	}

	@RequestMapping(value = "/editarUsuarioAdmin", method = RequestMethod.GET)
	public String editarUsuarioAdmin(Model model, HttpServletRequest request) {
		if(!validateLoginAdmin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		Usuario usuario = usuarioService.getUsuarioById(idUsuario);
		model.addAttribute("usuario", usuario);
		return UrlsAdmin.CADASTRO_USUARIOS_ADMIN.url;
	}

	@RequestMapping(value = "/salvarUsuarioAdmin", method = RequestMethod.POST)
	public String salvarUsuarioAdmin(Model model, @ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		if(!validateLoginAdmin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		try {
			String msgSucesso = "Cadastro realizado com sucesso.";
			if(usuario.getIdUsuario() != null){
				msgSucesso = "Dados atualizados com sucesso";
			}
			
			usuarioService.salvarUsuarioAdmin(usuario);
			model.addAttribute("usuario", usuario);
			addMensagemSucesso(model, msgSucesso);
		} catch (Exception e) {
			addMensagemErroGenerica(model);
		}
		return UrlsAdmin.CADASTRO_USUARIOS_ADMIN.url;
	}

}
