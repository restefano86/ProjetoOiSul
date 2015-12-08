package br.com.oisul.spring.service.venda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.oisul.spring.dao.venda.VendaDAO;
import br.com.oisul.spring.exceptions.BusinessException;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.model.VendaItem;

@Service
public class VendaServiceImpl implements VendaService {
	
	private VendaDAO vendaDAO;
	
	public void setVendaDAO(VendaDAO vendaDAO) {
		this.vendaDAO = vendaDAO;
	}
	
	public void gerarVenda(Venda venda) throws BusinessException{
		
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
			boolean isMesmoProduto = item.getIdProduto().equals(perfil.getCodigoOferta());
			boolean isMesmoDdd = item.getNuDdd().equals(perfil.getNuDdd());
			boolean isMesmoTipoChip = item.getFlTipoChip().equals(perfil.getFlTipoChip());
			if(isMesmoProduto && isMesmoDdd && isMesmoTipoChip){
				perfil.setQtAcessos(perfil.getQtAcessos()+1);
			} else {
				if(perfil.getCodigoOferta()!= null){
					listPerfil.add(perfil);
					perfil = new PerfilVenda();
				}
				perfil = new PerfilVenda();
				perfil.setCodigoOferta(item.getIdProduto());
				perfil.setNuDdd(item.getNuDdd());
				perfil.setFlTipoChip(item.getFlTipoChip());
				perfil.setQtAcessos(1);
			}
		}
		listPerfil.add(perfil); 
		return listPerfil;
	}


}
