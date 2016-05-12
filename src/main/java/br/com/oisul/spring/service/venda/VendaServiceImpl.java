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
import br.com.oisul.spring.model.Empresa;
import br.com.oisul.spring.model.PerfilVenda;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.model.VendaDocumento;
import br.com.oisul.spring.model.VendaItem;
import br.com.oisul.spring.reports.contrato.fixo.RelContratoFixo;
import br.com.oisul.spring.reports.contrato.movel.RelContrato;
import br.com.oisul.spring.reports.contrato.movel.RelContratoMovel;
import br.com.oisul.spring.reports.contrato.movel.RelOiInformacoesMaisCelular;
import br.com.oisul.spring.reports.contrato.movel.RelTermoPortabilidade;

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
			
			Empresa e = venda.getEmpresa();
			venda.setEmpresa(null);
			venda.setUsuario(null);
			venda.setConsultor(null);
			venda.setSituacao(null);
			vendaDAO.saveEntity(venda);
			venda.setEmpresa(e);
			
			atualizaPerfisVenda(venda);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	@Transactional		
	public void geraDocumentosNovaVenda(Venda venda) throws Exception {
		if(venda.getIsVendaFixo()){
			geraDocumentosNovaVendaFixo(venda);
		} else {
			geraDocumentosNovaVendaMovel(venda);
		}
	}
	
	@Override
	@Transactional		
	public void geraDocumentosNovaVendaMovel(Venda venda) throws Exception {

		deletaDoctosGeradosVenda(venda);
		//salva contrato
		RelContratoMovel relContratoMovel = new RelContratoMovel();
		Venda vendaBanco = findVendaByRelContratoFixo(venda.getIdVenda());
		List<PerfilVenda> perfis = perfilVendaDAO.findAllPerfilVendaFetchByIdVenda(venda.getIdVenda());
		vendaBanco.setPerfis(perfis);
		byte[] fileRelContratoMovel = relContratoMovel.geraRelatorio(vendaBanco);
		
		VendaDocumento vendaDocumento = new VendaDocumento();
		vendaDocumento.setIdVenda(venda.getIdVenda());
		vendaDocumento.setFlOrigem("S");
		vendaDocumento.setNmDocumento("fichaPedidoEmpresarial.pdf");
		vendaDocumento.setFile(fileRelContratoMovel);
		vendaDocumentoDAO.saveEntity(vendaDocumento);
		
		//salva docto informações
		RelOiInformacoesMaisCelular relOiInformacoesMaisCelular = new RelOiInformacoesMaisCelular();
		byte[] fileRelOiInformacoesMaisCelular = relOiInformacoesMaisCelular.geraRelatorio(venda.getIdVenda());
		VendaDocumento doctoInfo = new VendaDocumento();
		doctoInfo.setIdVenda(venda.getIdVenda());
		doctoInfo.setFlOrigem("S");
		doctoInfo.setNmDocumento("OiInformacoesMaisCelular.pdf");
		doctoInfo.setFile(fileRelOiInformacoesMaisCelular);
		vendaDocumentoDAO.saveEntity(doctoInfo);
		
		//salva docto portabilidade
		RelTermoPortabilidade relTermoPortabilidade = new RelTermoPortabilidade();
		byte[] fileRelTermoPortabilidade = relTermoPortabilidade.geraRelatorio(venda.getIdVenda());
		VendaDocumento doctoPortabilidade = new VendaDocumento();
		doctoPortabilidade.setIdVenda(venda.getIdVenda());
		doctoPortabilidade.setFlOrigem("S");
		doctoPortabilidade.setNmDocumento("OiTermoPortabilidade.pdf");
		doctoPortabilidade.setFile(fileRelTermoPortabilidade);
		vendaDocumentoDAO.saveEntity(doctoPortabilidade);
		
		venda.setDocumentosGerados(vendaDocumentoDAO.findVendaDocumentosGeradosNoFilesByVenda(venda.getIdVenda()));
	}

	@Override
	@Transactional		
	public void geraDocumentosNovaVendaFixo(Venda venda) throws Exception {

		deletaDoctosGeradosVenda(venda);
		
		//salva contrato
		RelContratoFixo relContratoFixo = new RelContratoFixo();
		Venda vendaBanco = findVendaByRelContratoFixo(venda.getIdVenda());
		List<PerfilVenda> perfis = perfilVendaDAO.findAllPerfilVendaFetchByIdVenda(venda.getIdVenda());
		vendaBanco.setPerfis(perfis);
		byte[] fileRelContratoFixo = relContratoFixo.geraRelatorio(vendaBanco);
		
		VendaDocumento vendaDocumento = new VendaDocumento();
		vendaDocumento.setIdVenda(vendaBanco.getIdVenda());
		vendaDocumento.setFlOrigem("S");
		vendaDocumento.setNmDocumento("contratoFixoBandaLarga.pdf");
		vendaDocumento.setFile(fileRelContratoFixo);
		vendaDocumentoDAO.saveEntity(vendaDocumento);
		
		venda.setDocumentosGerados(vendaDocumentoDAO.findVendaDocumentosGeradosNoFilesByVenda(venda.getIdVenda()));
	}
	
	private void deletaDoctosGeradosVenda(Venda venda) {
		List<VendaDocumento> doctosGerados = vendaDocumentoDAO.findVendaDocumentosGeradosNoFilesByVenda(venda.getIdVenda());
		for (VendaDocumento docto : doctosGerados) {
			vendaDocumentoDAO.deleteEntity(docto);
		}
	}
	
	private void atualizaPerfisVenda(Venda venda) throws BusinessException, CloneNotSupportedException, Exception {
		try {
		List<PerfilVenda> perfisBanco = perfilVendaDAO.findAllPerfilVendaByIdVenda(venda.getIdVenda());
		for (PerfilVenda perfilBanco : perfisBanco) {
			perfilVendaDAO.deleteEntity(perfilBanco);
		}
		
		List<PerfilVenda> listaPerfis;
		if(venda.getIsVendaFixo()){
			listaPerfis = this.geraPerfisVendaFixo(venda.getItens());
		} else {
			listaPerfis = this.geraPerfisVendaMovel(venda.getItens());
		}
			
		
		for (PerfilVenda perfilVenda : listaPerfis) {
			perfilVenda.setIdVenda(venda.getIdVenda());
		}
		perfilVendaDAO.saveAllEntity(listaPerfis);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void validaPerfisVendaMovel(List<VendaItem> vendaItens) throws BusinessException, CloneNotSupportedException{
		List<PerfilVenda> perfis = geraPerfisVendaMovel(vendaItens);
		if(perfis.size() > 6){
			throw new BusinessException("Os acessos cadastrados compreendem uma quantidade de perfis maior do que a quantidade suporteda pelo contrato. Entre em contato com a empresa para maiores informações sobre como proceder");
		}
	}

	public void validaPerfisVendaFixo(List<VendaItem> vendaItens) throws BusinessException, CloneNotSupportedException{
		List<PerfilVenda> perfis = geraPerfisVendaFixo(vendaItens);
		if(perfis.size() > 5){
			throw new BusinessException("Os acessos cadastrados compreendem uma quantidade de perfis maior do que a quantidade suporteda pelo contrato. Entre em contato com a empresa para maiores informações sobre como proceder");
		}
	}

	public List<PerfilVenda> geraPerfisVendaMovel(List<VendaItem> vendaItens) throws BusinessException, CloneNotSupportedException{
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
			boolean isMesmoProduto = item.getIdProduto().equals(perfil.getIdProduto());
			boolean isMesmoDdd = item.getNuDdd().equals(perfil.getNuDdd());
			boolean isMesmoTipoChip = item.getFlTipoChip().equals(perfil.getFlTipoChip());
			if(isMesmoProduto && isMesmoDdd && isMesmoTipoChip){
				perfil.setQtAcessos(perfil.getQtAcessos()+1);
			} else {
				if(perfil.getIdProduto()!= null){
					perfil.setNuPerfil(listPerfil.size()+1);
					listPerfil.add(perfil);
					perfil = new PerfilVenda();
				}
				perfil = new PerfilVenda();
				perfil.setIdProduto(item.getIdProduto());
				perfil.setNuDdd(item.getNuDdd());
				perfil.setFlTipoChip(item.getFlTipoChip());
				perfil.setQtAcessos(1);
			}
		}
		perfil.setNuPerfil(listPerfil.size()+1);
		listPerfil.add(perfil); 
		return listPerfil;
	}

	public List<PerfilVenda> geraPerfisVendaFixo(List<VendaItem> vendaItens) throws BusinessException, CloneNotSupportedException{
		List<PerfilVenda> listPerfil = new ArrayList<PerfilVenda>();
		
		List<VendaItem> itensClonados = new ArrayList<VendaItem>();
		for (VendaItem item : vendaItens) {
			itensClonados.add(item.clone());
		}
		
		Collections.sort(itensClonados, new Comparator<VendaItem>() {
			@Override
			public int compare(VendaItem i1, VendaItem i2) {
				int c;
				c = i1.getFlPortabilidade().compareTo(i2.getFlPortabilidade());
				if (c == 0)
					c = i1.getIdProduto().compareTo(i2.getIdProduto());
				if (c == 0)
					c = i1.getNuDdd().compareTo(i2.getNuDdd());
				if (c == 0)
					if(i1.getIdProdutoBL() == null && i2.getIdProdutoBL() == null){
						c = 0;
					} else if(i1.getIdProdutoBL() == null && i2.getIdProdutoBL() != null){
						c = 1;
					} else if(i1.getIdProdutoBL() != null && i2.getIdProdutoBL() == null){
						c = -1;
					} else {
						c = i1.getIdProdutoBL().compareTo(i2.getIdProdutoBL());
					}
				return c;
			}
		});
		   
		PerfilVenda perfil = new PerfilVenda();   
		for (VendaItem item : itensClonados) {
			boolean isMesmoProduto = item.getIdProduto().equals(perfil.getIdProduto());
			boolean isMesmoDdd = item.getNuDdd().equals(perfil.getNuDdd());
			boolean isMesmoProtudoBL = (item.getIdProdutoBL() == null && perfil.getIdProdutoBL() == null) || (item.getIdProdutoBL() != null && item.getIdProdutoBL().equals(perfil.getIdProdutoBL()));
			boolean isPortabilidade = VendaItem.FL_PORTABILIDADE_SIM.equals(item.getFlPortabilidade());
			if(!isPortabilidade && isMesmoProduto && isMesmoDdd && isMesmoProtudoBL){
				perfil.setQtAcessos(perfil.getQtAcessos()+1);
			} else {
				if(perfil.getIdProduto()!= null){
					perfil.setNuPerfil(listPerfil.size()+1);
					listPerfil.add(perfil);
					perfil = new PerfilVenda();
				}
				perfil = new PerfilVenda();
				perfil.setIdProduto(item.getIdProduto());
				perfil.setNuDdd(item.getNuDdd());
				perfil.setIdProdutoBL(item.getIdProdutoBL());
				perfil.setQtAcessos(1);
				perfil.setFlPortabilidade(item.getFlPortabilidade());
			}
		}
		perfil.setNuPerfil(listPerfil.size()+1);
		listPerfil.add(perfil); 
		System.out.println("##############################################################");
		for (PerfilVenda p : listPerfil) {
			System.out.println(p.getNuPerfil()+"-"+p.getIdProduto()+"-"+p.getNuDdd()+"-"+p.getIdProdutoBL()+"-"+p.getQtAcessos());
		}
		System.out.println("##############################################################");
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
	public Venda findVendaByRelContratoFixo(Integer idVenda) throws Exception{
		Venda venda = vendaDAO.getVendaFetchById(idVenda);
		return venda;
	}
	
	@Transactional	
	public Venda findVendaByRelContratoMovel(Integer idVenda) throws Exception{
		Venda venda = vendaDAO.getVendaFetchById(idVenda);
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
	public List<VendaDocumento> findVendaDocumentosInseridos(Integer idVenda) throws Exception{
		try{
			return vendaDocumentoDAO.findVendaDocumentosInseridosNoFilesByVenda(idVenda);
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
