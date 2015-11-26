package br.com.oisul.spring.model;
// Generated 22/11/2015 18:30:21 by Hibernate Tools 4.3.1.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario{

	@Id 
    @Column(name="idUsuario", unique=true, nullable=false)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
     private Integer idUsuario;
	
     private String email;
     private String senha;
     private String nome;
     private String ddd;
     private String telefone;
     private String isAtivado;

    public Usuario() {
    }

	
    public Usuario(int idUsuario, String email, String senha) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.senha = senha;
    }
    public Usuario(int idUsuario, String email, String senha, String nome, String ddd, String telefone, String isAtivado) {
       this.idUsuario = idUsuario;
       this.email = email;
       this.senha = senha;
       this.nome = nome;
       this.ddd = ddd;
       this.telefone = telefone;
       this.isAtivado = isAtivado;
    }
   
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getSenha() {
        return this.senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public String getDdd() {
        return this.ddd;
    }
    
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    
    public String getTelefone() {
        return this.telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    public String getIsAtivado() {
        return this.isAtivado;
    }
    
    public void setIsAtivado(String isAtivado) {
        this.isAtivado = isAtivado;
    }




}


