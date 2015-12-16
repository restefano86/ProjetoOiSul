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
	private Integer idperfilvenda;
	
	private Integer idproduto;
	private Integer idvenda;
	private Integer nuddd;
	private Integer qtacessos;
	private String fltipochip;
	private Integer nuPerfil;
	
	@Override
	public Integer getId() {
		return getIdperfilvenda();
	}

	public Integer getIdperfilvenda() {
		return idperfilvenda;
	}

	public void setIdperfilvenda(Integer idperfilvenda) {
		this.idperfilvenda = idperfilvenda;
	}

	public Integer getNuddd() {
		return nuddd;
	}

	public void setNuddd(Integer nuddd) {
		this.nuddd = nuddd;
	}

	public Integer getQtacessos() {
		return qtacessos;
	}

	public void setQtacessos(Integer qtacessos) {
		this.qtacessos = qtacessos;
	}

	public String getFltipochip() {
		return fltipochip;
	}

	public void setFltipochip(String fltipochip) {
		this.fltipochip = fltipochip;
	}

	public Integer getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Integer idproduto) {
		this.idproduto = idproduto;
	}

	public Integer getIdvenda() {
		return idvenda;
	}

	public void setIdvenda(Integer idvenda) {
		this.idvenda = idvenda;
	}

	public Integer getNuPerfil() {
		return nuPerfil;
	}

	public void setNuPerfil(Integer nuPerfil) {
		this.nuPerfil = nuPerfil;
	}
	

}
