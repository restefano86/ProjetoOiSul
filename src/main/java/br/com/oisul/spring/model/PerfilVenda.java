package br.com.oisul.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perfilvenda")
public class PerfilVenda implements ModelInterface {
	
	@Id 
    @Column(name="idperfilvenda", unique=true, nullable=false)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Integer idPerfilVenda;
	
	private Integer idProduto;
	private Integer idVenda;
	private Integer nuDdd;
	private Integer qtAcessos;
	private String flTipoChip;
	private Integer nuPerfil;
	private Integer idProdutoBL;
	
	@Override
	public Integer getId() {
		return getIdPerfilVenda();
	}


	public Integer getNuPerfil() {
		return nuPerfil;
	}

	public void setNuPerfil(Integer nuPerfil) {
		this.nuPerfil = nuPerfil;
	}


	public Integer getIdPerfilVenda() {
		return idPerfilVenda;
	}


	public void setIdPerfilVenda(Integer idPerfilVenda) {
		this.idPerfilVenda = idPerfilVenda;
	}


	public Integer getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}


	public Integer getIdVenda() {
		return idVenda;
	}


	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}


	public Integer getNuDdd() {
		return nuDdd;
	}


	public void setNuDdd(Integer nuDdd) {
		this.nuDdd = nuDdd;
	}


	public Integer getQtAcessos() {
		return qtAcessos;
	}


	public void setQtAcessos(Integer qtAcessos) {
		this.qtAcessos = qtAcessos;
	}


	public String getFlTipoChip() {
		return flTipoChip;
	}


	public void setFlTipoChip(String flTipoChip) {
		this.flTipoChip = flTipoChip;
	}


	public Integer getIdProdutoBL() {
		return idProdutoBL;
	}


	public void setIdProdutoBL(Integer idProdutoBL) {
		this.idProdutoBL = idProdutoBL;
	}
	

}
