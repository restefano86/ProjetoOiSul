package br.com.oisul.spring;

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

	protected void addMensagemAviso(Model model, String mensagem){
		model.addAttribute("mensagemAviso", mensagem);
	}
	
	protected void addMensagemErro(Model model, String mensagem){
		model.addAttribute("mensagemErro", mensagem);
	}
}
