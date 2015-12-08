package br.com.oisul.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="situacaovenda")
public class SituacaoVenda implements ModelInterface {

	@Id 
    @Column(name="idsituacaovenda", unique=true, nullable=false)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer idSituacaoVenda;
	
	private String nmSituacao;
	private String deSituacao;
	public Integer getIdSituacaoVenda() {
		return idSituacaoVenda;
	}
	public void setIdSituacaoVenda(Integer idSituacaoVenda) {
		this.idSituacaoVenda = idSituacaoVenda;
	}
	public String getNmSituacao() {
		return nmSituacao;
	}
	public void setNmSituacao(String nmSituacao) {
		this.nmSituacao = nmSituacao;
	}
	public String getDeSituacao() {
		return deSituacao;
	}
	public void setDeSituacao(String deSituacao) {
		this.deSituacao = deSituacao;
	}
	@Override
	public Integer getId() {
		return getIdSituacaoVenda();
	}
	
	
}
