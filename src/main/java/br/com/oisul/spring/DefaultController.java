package br.com.oisul.spring;

import javax.servlet.http.HttpServletRequest;

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

}
