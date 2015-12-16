package br.com.oisul.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vendadocumento")
public class VendaDocumento implements ModelInterface {

	@Id 
    @Column(name="idvendadocumento", unique=true, nullable=false)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer idVendaDocumento;
	
	private Integer idVenda;
	private String nmDocumento;
	private byte[] file;
	private String flOrigem;
	
	public Integer getIdVendaDocumento() {
		return idVendaDocumento;
	}
	public void setIdVendaDocumento(Integer idVendaDocumento) {
		this.idVendaDocumento = idVendaDocumento;
	}
	public Integer getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}
	public String getNmDocumento() {
		return nmDocumento;
	}
	public void setNmDocumento(String nmDocumento) {
		this.nmDocumento = nmDocumento;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getFlOrigem() {
		return flOrigem;
	}
	public void setFlOrigem(String flOrigem) {
		this.flOrigem = flOrigem;
	}
	@Override
	public Integer getId() {
		return getIdVendaDocumento();
	}
	
	

}
