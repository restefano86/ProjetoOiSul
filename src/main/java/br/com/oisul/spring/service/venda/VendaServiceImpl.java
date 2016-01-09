package br.com.oisul.spring.service.venda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.oisul.spring.dao.empresa.EmpresaDAO;
import br.com.oisul.spring.dao.perfilvenda.PerfilVendaDAO;
import br.com.oisul.spring.dao.usuario.UsuarioDAO;
import br.com.oisul.spring.dao.venda.VendaDAO;
import br.com.oisul.spring.dao.vendadocumento.VendaDocumentoDAO;
import br.com.oisul.spring.exceptions.BusinessException;
import br.com.oisul.spring.model.PerfilVenda;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.model.VendaDocumento;
import br.com.oisul.spring.model.VendaItem;
import br.com.oisul.spring.reports.contrato.RelContrato;
import br.com.oisul.spring.reports.contrato.RelOiInformacoesMaisCelular;
import br.com.oisul.spring.reports.contrato.RelTermoPortabilidade;

@Service
public class VendaServiceImpl implements VendaService {
	
	private VendaDAO vendaDAO;
	private EmpresaDAO empresaDAO;
	private PerfilVendaDAO perfilVendaDAO;
	private VendaDocumentoDAO vendaDocumentoDAO;
	private UsuarioDAO usuarioDAO;
	
	public void setVendaDAO(VendaDAO vendaDAO) {
		this.vendaDAO = vendaDAO;
	}
	public void setEmpresaDAO(EmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}
	public void setPerfilVendaDAO(PerfilVendaDAO perfilVendaDAO) {
		this.perfilVendaDAO = perfilVendaDAO;
	}
	public void setVendaDocumentoDAO(VendaDocumentoDAO vendaDocumentoDAO) {
		this.vendaDocumentoDAO = vendaDocumentoDAO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	@Override
	@Transactional	
	public void gerarVenda(Venda venda) throws Exception{
		try {
			if(venda.getIdVenda() == null){
				venda.setDtContratoGerado(new Date());
				venda.setIdSituacao(1);
			} else {
				for (VendaItem item : venda.getItens()) {
					item.setIdVenda(venda.getId());
				}
			}
			empresaDAO.saveEntity(venda.getEmpresa());
			
			venda.setIdEmpresa(venda.getEmpresa().getId());
			
			vendaDAO.saveEntity(venda);
			atualizaPerfisVenda(venda);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	@Transactional		
	public void geraDocumentosNovaVenda(Venda venda) {

		List<VendaDocumento> doctosGerados = vendaDocumentoDAO.findVendaDocumentosGeradosNoFilesByVenda(venda.getIdVenda());
		for (VendaDocumento docto : doctosGerados) {
			vendaDocumentoDAO.deleteEntity(docto);
		}
		//salva contrato
		System.out.println("####### PREPARANDO PARA GERAR CONTRATO");
		RelContrato relContrato = new RelContrato();
		byte[] fileRelContrato = relContrato.geraRelatorio(venda.getIdVenda());
		VendaDocumento vendaDocumento = new VendaDocumento();
		vendaDocumento.setIdVenda(venda.getIdVenda());
		vendaDocumento.setFlOrigem("S");
		vendaDocumento.setNmDocumento("fichaPedidoEmpresarial.pdf");
		vendaDocumento.setFile(fileRelContrato);
		vendaDocumentoDAO.saveEntity(vendaDocumento);
		System.out.println("####### GEROU CONTRATO");
		
		//salva docto informações
		System.out.println("####### PREPARANDO PARA GERAR INFORMAÇÕES");
		RelOiInformacoesMaisCelular relOiInformacoesMaisCelular = new RelOiInformacoesMaisCelular();
		byte[] fileRelOiInformacoesMaisCelular = relOiInformacoesMaisCelular.geraRelatorio(venda.getIdVenda());
		VendaDocumento doctoInfo = new VendaDocumento();
		doctoInfo.setIdVenda(venda.getIdVenda());
		doctoInfo.setFlOrigem("S");
		doctoInfo.setNmDocumento("OiInformacoesMaisCelular.pdf");
		doctoInfo.setFile(fileRelOiInformacoesMaisCelular);
		vendaDocumentoDAO.saveEntity(doctoInfo);
		System.out.println("####### GEROU INFORMAÇÕES");
		
		//salva docto portabilidade
		System.out.println("####### PREPARANDO PARA GERAR TERMO DE PORTABILIDADE");
		RelTermoPortabilidade relTermoPortabilidade = new RelTermoPortabilidade();
		byte[] fileRelTermoPortabilidade = relTermoPortabilidade.geraRelatorio(venda.getIdVenda());
		VendaDocumento doctoPortabilidade = new VendaDocumento();
		doctoPortabilidade.setIdVenda(venda.getIdVenda());
		doctoPortabilidade.setFlOrigem("S");
		doctoPortabilidade.setNmDocumento("OiTermoPortabilidade.pdf");
		doctoPortabilidade.setFile(fileRelTermoPortabilidade);
		vendaDocumentoDAO.saveEntity(doctoPortabilidade);
		System.out.println("####### GEROU TERMO DE PORTABILIDADE");
		venda.setDocumentosGerados(vendaDocumentoDAO.findVendaDocumentosGeradosNoFilesByVenda(venda.getIdVenda()));
	}
	private void atualizaPerfisVenda(Venda venda) throws BusinessException, CloneNotSupportedException {
		List<PerfilVenda> perfisBanco = perfilVendaDAO.findAllPerfilVendaByIdVenda(venda.getIdVenda());
		for (PerfilVenda perfilBanco : perfisBanco) {
			perfilVendaDAO.deleteEntity(perfilBanco);
		}
		
		List<PerfilVenda> listaPerfis = this.geraPerfisVenda(venda.getItens());
		for (PerfilVenda perfilVenda : listaPerfis) {
			perfilVenda.setIdvenda(venda.getIdVenda());
		}
		perfilVendaDAO.saveAllEntity(listaPerfis);
	}
	
	public void validaPerfisVenda(List<VendaItem> vendaItens) throws BusinessException, CloneNotSupportedException{
		List<PerfilVenda> perfis = geraPerfisVenda(vendaItens);
		if(perfis.size() > 6){
			throw new BusinessException("Os acessos cadastrados compreendem uma quantidade de perfis maior do que a quantidade suporteda pelo contrato. Entre em contato com a empresa para maiores informações sobre como proceder");
		}
	}

	public List<PerfilVenda> geraPerfisVenda(List<VendaItem> vendaItens) throws BusinessException, CloneNotSupportedException{
		List<PerfilVenda> listPerfil = new ArrayList<PerfilVenda>();
		
		List<VendaItem> itensClonados = new ArrayList<VendaItem>();
		for (VendaItem item : vendaItens) {
			itensClonados.add(item.clone());
		}
		
		Collections.sort(itensClonados, new Comparator<VendaItem>() {
			@Override
			public int compare(VendaItem i1, VendaItem i2) {
				int c;
				c = i1.getIdProduto().compareTo(i2.getIdProduto());
				if (c == 0)
					c = i1.getNuDdd().compareTo(i2.getNuDdd());
				if (c == 0)
					c = i1.getFlTipoChip().compareTo(i2.getFlTipoChip());
				return c;
			}
		});
		   
		PerfilVenda perfil = new PerfilVenda();   
		for (VendaItem item : itensClonados) {
			boolean isMesmoProduto = item.getIdProduto().equals(perfil.getIdproduto());
			boolean isMesmoDdd = item.getNuDdd().equals(perfil.getNuddd());
			boolean isMesmoTipoChip = item.getFlTipoChip().equals(perfil.getFltipochip());
			if(isMesmoProduto && isMesmoDdd && isMesmoTipoChip){
				perfil.setQtacessos(perfil.getQtacessos()+1);
			} else {
				if(perfil.getIdproduto()!= null){
					perfil.setNuPerfil(listPerfil.size()+1);
					listPerfil.add(perfil);
					perfil = new PerfilVenda();
				}
				perfil = new PerfilVenda();
				perfil.setIdproduto(item.getIdProduto());
				perfil.setNuddd(item.getNuDdd());
				perfil.setFltipochip(item.getFlTipoChip());
				perfil.setQtacessos(1);
			}
		}
		perfil.setNuPerfil(listPerfil.size()+1);
		listPerfil.add(perfil); 
		return listPerfil;
	}

	@Transactional	
	public VendaDocumento getVendaDocumentoByVisualizacao(Integer idVendaDocumento) throws Exception{
		return vendaDocumentoDAO.getEntityById(idVendaDocumento);
	}
	
	@Transactional	
	public Venda findVendaEdicao(Integer idVenda) throws Exception{
		Venda venda = vendaDAO.getVendaFetchById(idVenda);
		venda.setDocumentosGerados(vendaDocumentoDAO.findVendaDocumentosGeradosNoFilesByVenda(venda.getIdVenda()));
		return venda;
	}
	
	@Transactional	
	public List<Venda> findTopVendas(Venda venda, Integer limite) throws Exception{
		return vendaDAO.findTopVendas(venda, limite);
	}

	@Transactional
	public void adicionaArquivo(Venda venda, byte[] file, String filename) throws Exception{
		try{
			VendaDocumento vendaDocumento = new VendaDocumento();
			vendaDocumento.setIdVenda(venda.getIdVenda());
			vendaDocumento.setFlOrigem("A");
			vendaDocumento.setNmDocumento(filename);
			vendaDocumento.setFile(file);
			vendaDocumentoDAO.saveEntity(vendaDocumento);
			venda.setDocumentosInseridos(vendaDocumentoDAO.findVendaDocumentosInseridosNoFilesByVenda(venda.getIdVenda()));
		} catch(Exception e){
			throw e;
		}
		
	}
	
	@Transactional
	public void finalizaVenda(Venda venda) throws Exception{
		try{
			venda.setIdSituacao(2);
			vendaDAO.saveEntity(venda);
		} catch(Exception e){
			throw e;
		}
		
	}
}
