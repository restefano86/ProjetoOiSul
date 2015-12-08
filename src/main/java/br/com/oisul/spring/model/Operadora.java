package br.com.oisul.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="operadora")
public class Operadora implements ModelInterface {

	@Id 
    @Column(name="idoperadora", unique=true, nullable=false)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer idOperadora;
	
	private String nmOperadora;

	public Integer getIdOperadora() {
		return idOperadora;
	}

	public void setIdOperadora(Integer idOperadora) {
		this.idOperadora = idOperadora;
	}

	public String getNmOperadora() {
		return nmOperadora;
	}

	public void setNmOperadora(String nmOperadora) {
		this.nmOperadora = nmOperadora;
	}

	@Override
	public Integer getId() {
		return getIdOperadora();
	}

}
