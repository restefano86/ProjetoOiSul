package br.com.oisul.spring.controllers.site;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import br.com.oisul.spring.model.Usuario;

public class DefaultController {
	
	protected boolean validateLogin(HttpServletRequest request){
		if(request.getSession().getAttribute("usuario") != null){
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if(!StringUtils.isEmpty(usuario.getEmail())){
				return true;
			}
		}
		return false;
	}

	protected boolean validateLoginConsultor(HttpServletRequest request){
		if(request.getSession().getAttribute("usuario") != null){
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if(usuario.isConsultor() || usuario.isAdmin()){
				return true;
			}
		}
		return false;
	}

	protected boolean validateLoginAdmin(HttpServletRequest request){
		if(request.getSession().getAttribute("usuario") != null){
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if(usuario.isAdmin()){
				return true;
			}
		}
		return false;
	}

	protected void addMensagemAviso(Model model, String mensagem){
		model.addAttribute("mensagemAviso", mensagem);
	}
	
	protected void addMensagemErro(Model model, String mensagem){
		model.addAttribute("mensagemErro", mensagem);
	}
	
	protected void addMensagemSucesso(Model model, String mensagem){
		model.addAttribute("mensagemSucesso", mensagem);
	}
	
	protected void addMensagemErroGenerica(Model model){
		model.addAttribute("mensagemErro", "Ocorreu um problema na sua requisição. Por favor, tente novamente mais tarde.");
	}
	
	protected Integer getIdUsuarioFromSession(HttpServletRequest request){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		return usuario.getIdUsuario();
	}
}
