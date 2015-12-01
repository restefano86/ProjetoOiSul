package br.com.oisul.spring.controllers.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.oisul.spring.controllers.site.DefaultController;
import br.com.oisul.spring.utils.UrlsAdmin;
import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class PedidoController extends DefaultController {
	
	@RequestMapping(value = "/abrirConPedidoConsultor", method = RequestMethod.GET)
	public String aquisicaoAdminPasso0(Model model, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsAdmin.ABRIR_PEDIDOS_CONSULTOR.url;
	}


}
