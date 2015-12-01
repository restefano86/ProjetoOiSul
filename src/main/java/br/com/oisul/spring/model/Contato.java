package br.com.oisul.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="contato")
public class Contato implements ModelInterface {
	
	@Id 
    @Column(name="idContato", unique=true, nullable=false)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer idContato;
	
	private String nmContato;
	private String deTelefone;
	private String deEmail;
	private String deMensagem;
	private String deProduto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtContato;
	
	@Override
	public Integer getId() {
		return getIdContato();      
	}
		
	public Integer getIdContato() {
		return idContato;
	}



public void setIdContato(Integer idContato) {
		this.idContato = idContato;
	}




	public String getNmContato() {
		return nmContato;
	}




	public void setNmContato(String nmContato) {
		this.nmContato = nmContato;
	}




	public String getDeTelefone() {
		return deTelefone;
	}




	public void setDeTelefone(String deTelefone) {
		this.deTelefone = deTelefone;
	}




	public String getDeEmail() {
		return deEmail;
	}




	public void setDeEmail(String deEmail) {
		this.deEmail = deEmail;
	}




	public String getDeMensagem() {
		return deMensagem;
	}




	public void setDeMensagem(String deMensagem) {
		this.deMensagem = deMensagem;
	}




	public String getDeProduto() {
		return deProduto;
	}




	public void setDeProduto(String deProduto) {
		this.deProduto = deProduto;
	}




	public Date getDtContato() {
		return dtContato;
	}




	public void setDtContato(Date dtContato) {
		this.dtContato = dtContato;
	}

}
