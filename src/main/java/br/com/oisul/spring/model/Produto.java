package br.com.oisul.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="produto")
public class Produto implements ModelInterface {
	
	public static final String TP_MOVEL = "M";
	public static final String TP_FIXO = "F";
	public static final String TP_BANDALARGA = "B";

	@Id 
    @Column(name="idproduto", unique=true, nullable=false)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Integer idProduto;
	
	private Integer codigoOi;
	private Double vlplano;
	private String dePlano;
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getCodigoOi() {
		return codigoOi;
	}
	public void setCodigoOi(Integer codigoOi) {
		this.codigoOi = codigoOi;
	}
	public Double getVlplano() {
		return vlplano;
	}
	public void setVlplano(Double vlplano) {
		this.vlplano = vlplano;
	}
	public String getDePlano() {
		return dePlano;
	}
	public void setDePlano(String dePlano) {
		this.dePlano = dePlano;
	}
	@Override
	public Integer getId() {
		return getIdProduto();
	}
	
	
}
