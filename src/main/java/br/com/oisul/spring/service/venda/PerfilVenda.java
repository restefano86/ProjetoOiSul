package br.com.oisul.spring.service.venda;

public class PerfilVenda {
	
	private Integer codigoOferta;
	private Integer nuDdd;
	private Integer qtAcessos;
	private Double vlAssinatura;
	private String flTipoChip;
	private Double vlChip;
	private String formaPagamento;
	
	public Integer getCodigoOferta() {
		return codigoOferta;
	}
	public void setCodigoOferta(Integer codigoOferta) {
		this.codigoOferta = codigoOferta;
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
	public Double getVlAssinatura() {
		return vlAssinatura;
	}
	public void setVlAssinatura(Double vlAssinatura) {
		this.vlAssinatura = vlAssinatura;
	}
	public String getFlTipoChip() {
		return flTipoChip;
	}
	public void setFlTipoChip(String flTipoChip) {
		this.flTipoChip = flTipoChip;
	}
	public Double getVlChip() {
		return vlChip;
	}
	public void setVlChip(Double vlChip) {
		this.vlChip = vlChip;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

}
