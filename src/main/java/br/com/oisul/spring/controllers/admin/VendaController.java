package br.com.oisul.spring.controllers.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.oisul.spring.controllers.site.DefaultController;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.service.empresa.EmpresaService;
import br.com.oisul.spring.service.venda.VendaService;
import br.com.oisul.spring.utils.UrlsAdmin;
import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class VendaController extends DefaultController  {
	
	private VendaService vendaService;
	
	@Autowired(required=true)
	@Qualifier(value="vendaService")
	public void setVendaService(VendaService vendaService){
		this.vendaService = vendaService;
	}


	@RequestMapping(value = "/abrirConPedidoConsultor", method = RequestMethod.GET)
	public String aquisicaoAdminPasso0(Model model, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		try {
			List<Venda> listaVendas = vendaService.findTopVendas(new Venda(), 100);
			model.addAttribute("listaVendas", listaVendas);
		} catch (Exception e) {
			addMensagemErroGenerica(model);
			e.printStackTrace();
		}
		return UrlsAdmin.ABRIR_PEDIDOS_CONSULTOR.url;
	}

	
}
