package br.com.oisul.spring.controllers.admin;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.service.empresa.EmpresaService;
import br.com.oisul.spring.service.venda.VendaService;
import br.com.oisul.spring.utils.UrlsAdmin;
import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class VendaController extends DefaultController  {
	
	private Integer LIMITE_VENDAS_CONSULTA = 100;
	private VendaService vendaService;
	
	@Autowired(required=true)
	@Qualifier(value="vendaService")
	public void setVendaService(VendaService vendaService){
		this.vendaService = vendaService;
	}


	@RequestMapping(value = "/abrirConPedidoConsultor", method = RequestMethod.GET)
	public String abrirConPedidoConsultor(Model model, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		try {
			Venda venda = new Venda();
			Usuario usuariologado = (Usuario) request.getSession().getAttribute("usuario");
			venda.setIdConsultor(usuariologado.getIdUsuario());

			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			model.addAttribute("venda", venda);
			venda.setDtContratoGerado(calendar.getTime());
			List<Venda> listaVendas = vendaService.findTopVendas(venda, LIMITE_VENDAS_CONSULTA);
			if(listaVendas.size() == LIMITE_VENDAS_CONSULTA){
				addMensagemAviso(model, "O limite para a consulta é de "+LIMITE_VENDAS_CONSULTA+" registros. Por favor, utilize os filtros e realize uma busca mais específica.");
			}
			model.addAttribute("listaVendas", listaVendas);
		} catch (Exception e) {
			addMensagemErroGenerica(model);
			e.printStackTrace();
		}
		return UrlsAdmin.ABRIR_PEDIDOS_CONSULTOR.url;
	}
	
	@RequestMapping(value = "/abrirConVendas", method = RequestMethod.GET)
	public String abrirConVendas(Model model, HttpServletRequest request) {
		if(!validateLoginAdmin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		try {
			Venda venda = new Venda();
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.WEEK_OF_YEAR, -1);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			model.addAttribute("venda", venda);
			venda.setDtContratoGerado(calendar.getTime());
			List<Venda> listaVendas = vendaService.findTopVendas(venda, LIMITE_VENDAS_CONSULTA);
			if(listaVendas.size() == LIMITE_VENDAS_CONSULTA){
				addMensagemAviso(model, "O limite para a consulta é de "+LIMITE_VENDAS_CONSULTA+" registros. Por favor, utilize os filtros e realize uma busca mais específica.");
			}
			model.addAttribute("listaVendas", listaVendas);
		} catch (Exception e) {
			addMensagemErroGenerica(model);
			e.printStackTrace();
		}
		return "admin/conPedidoAdmin";
	}
	
	@RequestMapping(value = "/consultarVendas", method = RequestMethod.POST)
	public String consultarVendas(Model model, @ModelAttribute("venda") Venda venda, HttpServletRequest request) {
		if(!validateLoginAdmin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		try {
			List<Venda> listaVendas = vendaService.findTopVendas(venda, LIMITE_VENDAS_CONSULTA);
			if(listaVendas.size() == LIMITE_VENDAS_CONSULTA){
				addMensagemAviso(model, "O limite para a consulta é de "+LIMITE_VENDAS_CONSULTA+" registros. Por favor, utilize os filtros e realize uma busca mais específica.");
			}
			model.addAttribute("listaVendas", listaVendas);
		} catch (Exception e) {
			addMensagemErroGenerica(model);
			e.printStackTrace();
		}
		return "admin/conPedidoAdmin";
	}
	
	@RequestMapping(value = "/consultarVendasConsultor", method = RequestMethod.POST)
	public String consultarVendasConsultor(Model model, @ModelAttribute("venda") Venda venda, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		try {
			Usuario usuariologado = (Usuario) request.getSession().getAttribute("usuario");
			venda.setIdConsultor(usuariologado.getIdUsuario());
			
			List<Venda> listaVendas = vendaService.findTopVendas(venda, LIMITE_VENDAS_CONSULTA);
			if(listaVendas.size() == LIMITE_VENDAS_CONSULTA){
				addMensagemAviso(model, "O limite para a consulta é de "+LIMITE_VENDAS_CONSULTA+" registros. Por favor, utilize os filtros e realize uma busca mais específica.");
			}
			model.addAttribute("listaVendas", listaVendas);
		} catch (Exception e) {
			addMensagemErroGenerica(model);
			e.printStackTrace();
		}
		return "admin/conPedidoConsultor";
	}
	


	
}
