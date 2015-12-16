package br.com.oisul.spring.controllers.admin;

import java.io.ByteArrayInputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.oisul.spring.controllers.site.DefaultController;
import br.com.oisul.spring.exceptions.BusinessException;
import br.com.oisul.spring.model.Empresa;
import br.com.oisul.spring.model.Usuario;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.model.VendaDocumento;
import br.com.oisul.spring.service.empresa.EmpresaService;
import br.com.oisul.spring.service.venda.VendaService;
import br.com.oisul.spring.utils.UrlsAdmin;
import br.com.oisul.spring.utils.UrlsSite;

@Controller
public class AquisicaoAdminController extends DefaultController {
	
	private EmpresaService empresaService;
	private VendaService vendaService;

	@Autowired(required=true)
	@Qualifier(value="empresaService")
	public void setEmpresaService(EmpresaService service){
		this.empresaService = service;
	}

	@Autowired(required=true)
	@Qualifier(value="vendaService")
	public void setVendaService(VendaService service){
		this.vendaService = service;
	}

	@RequestMapping(value = "/aquisicaoAdminPasso0", method = RequestMethod.GET)
	public String aquisicaoAdminPasso0(Model model, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		request.getSession().setAttribute("venda", new Venda());
		return UrlsAdmin.AQUISICAO_PASSO_0.url;
	}

	@RequestMapping(value = "/aquisicaoAdminPasso1", method = RequestMethod.GET)
	public String aquisicaoPasso1(Model model, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		Venda vendaSessao = (Venda) request.getSession().getAttribute("venda");
		if(vendaSessao != null){
			model.addAttribute("venda", vendaSessao);
		}
		return UrlsAdmin.AQUISICAO_PASSO_1.url;
	}
	
	@RequestMapping(value = "/aquisicaoAdminPasso2", method = RequestMethod.POST)
	public String aquisicaoPasso2(Model model, @ModelAttribute("venda") Venda venda, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		try {
			Venda vendaSessao = (Venda) request.getSession().getAttribute("venda");
			vendaSessao.setItens(venda.getItens());
			vendaService.validaPerfisVenda(venda.getItens());
			Empresa empresa = empresaService.findEmpresaByUsuario(getIdUsuarioFromSession(request));
			model.addAttribute("empresa", vendaSessao.getEmpresa());
			return UrlsAdmin.AQUISICAO_PASSO_2.url;
		} catch (BusinessException e) {
			addMensagemAviso(model, e.getMessage());
			e.printStackTrace();
			return UrlsAdmin.AQUISICAO_PASSO_1.url;
		} catch (Exception e) {
			addMensagemErroGenerica(model);
			e.printStackTrace();
			return UrlsAdmin.AQUISICAO_PASSO_1.url;
		}
	}

	@RequestMapping(value = "/aquisicaoAdminVoltarPasso2", method = RequestMethod.GET)
	public String aquisicaoAdminVoltarPasso2(Model model, @ModelAttribute("venda") Venda venda, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		try {
			Venda vendaSessao = (Venda) request.getSession().getAttribute("venda");
			model.addAttribute("empresa", vendaSessao.getEmpresa());
			return UrlsAdmin.AQUISICAO_PASSO_2.url;
		} catch (Exception e) {
			addMensagemErroGenerica(model);
			e.printStackTrace();
			return UrlsAdmin.AQUISICAO_PASSO_1.url;
		}
	}
	
	
	@RequestMapping(value = "/aquisicaoAdminPasso3", method = RequestMethod.POST)
	public String aquisicaoPasso3(Model model, @ModelAttribute("empresa") Empresa empresa, HttpServletRequest request) {
		if(!validateLoginConsultor(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		Venda vendaSessao = (Venda) request.getSession().getAttribute("venda");
		vendaSessao.setEmpresa(empresa);
		return UrlsAdmin.AQUISICAO_PASSO_4.url;
	}
	
	@RequestMapping(value = "/aquisicaoAdminPasso4", method = RequestMethod.GET)
	public String aquisicaoPasso4(Model model,@ModelAttribute("empresa") Empresa empresa, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsAdmin.AQUISICAO_PASSO_4.url;
	}
	
	@RequestMapping(value = "/aquisicaoAdminPasso5", method = RequestMethod.GET)
	public String aquisicaoPasso5(Model model, HttpServletRequest request) {
		if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
		return UrlsAdmin.AQUISICAO_PASSO_5.url;
	}
	
	@RequestMapping(value = "/geraContratoAdmin", method = RequestMethod.GET)
	public String geraContratoAdmin(Model model, HttpServletRequest request) {
		try {
			if(!validateLogin(request)){return UrlsSite.CADASTRONAOLOGADO.url;};
			Venda venda = (Venda) request.getSession().getAttribute("venda");
			Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuario");
			if(venda.getIdUsuario() == null){
				venda.setIdUsuario(usuarioSessao.getIdUsuario());
			}
			if(venda.getIdConsultor() == null){
				venda.setIdConsultor(usuarioSessao.getIdUsuario());
			}
			vendaService.gerarVenda(venda);
			vendaService.geraDocumentosNovaVenda(venda);
			request.getSession().setAttribute("venda", venda);
			model.addAttribute("venda", venda);
		} catch (BusinessException e) {
			addMensagemAviso(model, e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			addMensagemErroGenerica(model);
			e.printStackTrace();
		}
		return UrlsAdmin.AQUISICAO_PASSO_4.url;
	}
	
	@RequestMapping(value = "/visualizarDocumentoVenda", method = RequestMethod.GET)
	public String visualizarDocumentoVenda(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			Integer idVendaDocumento = Integer.parseInt(request.getParameter("idVendaDocumento"));
			VendaDocumento vendaDocumento = vendaService.getVendaDocumentoByVisualizacao(idVendaDocumento);
			String mimeType= URLConnection.guessContentTypeFromName(vendaDocumento.getNmDocumento());
	        if(mimeType==null){
	            System.out.println("mimetype is not detectable, will take default");
	            mimeType = "application/octet-stream";
	        }
	        response.setContentType(mimeType);
	        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + vendaDocumento.getNmDocumento() +"\""));
	        ByteArrayInputStream bis = new ByteArrayInputStream(vendaDocumento.getFile());
	        FileCopyUtils.copy(bis, response.getOutputStream());
		} catch (BusinessException e) {
			addMensagemAviso(model, e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			addMensagemErroGenerica(model);
			e.printStackTrace();
		}
		return null;
	}
	
}
