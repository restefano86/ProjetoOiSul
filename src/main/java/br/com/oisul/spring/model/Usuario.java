package br.com.oisul.spring.model;
// Generated 22/11/2015 18:30:21 by Hibernate Tools 4.3.1.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import br.com.oisul.spring.utils.FormatadorUtil;

@Entity
@Table(name="usuario")
public class Usuario implements ModelInterface{
	
	public static final String TP_USUARIO_BASICO = "B";
	public static final String TP_USUARIO_CONSULTOR = "C";
	public static final String TP_USUARIO_ADMINISTRADOR = "A";

	public static final String TP_USUARIO_BASICO_FMT = "Básico";
	public static final String TP_USUARIO_CONSULTOR_FMT = "Consultor";
	public static final String TP_USUARIO_ADMINISTRADOR_FMT = "Admim";

	@Id 
    @Column(name="idusuario", unique=true, nullable=false)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
     private Integer idUsuario;
	
     private String email;
     private String senha;
     private String nome;
     private String ddd;
     private String telefone;
     private String isAtivado;
     private String tpUsuario;
     private String nuCpf;

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
        if(isAtivado == null || isAtivado.isEmpty()){
        	isAtivado = "N";
        }
    	this.isAtivado = isAtivado;
    }

	@Override
	public Integer getId() {
		return getIdUsuario();
	}


	public String getTpUsuario() {
		return tpUsuario;
	}


	public void setTpUsuario(String tpUsuario) {
		this.tpUsuario = tpUsuario;
	}

	public String getTpUsuarioFmt() {
		if(isConsultor()){
			return TP_USUARIO_CONSULTOR_FMT;
		} else if(isAdmin()){
			return TP_USUARIO_ADMINISTRADOR_FMT;
		} else {
			return TP_USUARIO_BASICO_FMT;
		}
	}

	public boolean isConsultor(){
		if(TP_USUARIO_CONSULTOR.equals(getTpUsuario())){
			return true;
		}
		return false;
	}
	public boolean isAdmin(){
		if(TP_USUARIO_ADMINISTRADOR.equals(getTpUsuario())){
			return true;
		}
		return false;
	}
	
	public String getNuCpf() {
		return nuCpf;
	}
	public void setNuCpf(String nuCpf) {
		this.nuCpf = nuCpf;
	}
	public String getNuCpfFmt() {
		return FormatadorUtil.formataCpf(nuCpf);
	}
	public void setNuCpfFmt(String nuCpfFmt) {
		if(!StringUtils.isEmpty(nuCpfFmt)){
			this.nuCpf = FormatadorUtil.desformataCpf(nuCpfFmt);
		}
	}



}


