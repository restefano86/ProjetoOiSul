package br.com.oisul.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import br.com.oisul.spring.utils.FormatadorUtil;

@Entity
@Table(name="empresa")
public class Empresa implements ModelInterface {

	@Id 
    @Column(name="idempresa", unique=true, nullable=false)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer idEmpresa;

	private Integer idUsuario;
	private String deRazaoSocial;
	private String nuCnpj;
	private String nmRepLegal;
	private String nuCpfRepLegal;
	private Integer nuDiaVencimento;
	private String deEndereco;
	private String nmBairro;
	private Integer nuCep;
	private String nmMunicipio;
	private String deUf;
	private Integer nuDddFixo;
	private Integer nuTelFixo;
	private Integer nuDddCelular;
	private Integer nuTelCelular;
	private String deEmail;
	private String deEnderecoCob;
	private String nmBairroCob;
	private String nmMunicipioCob;
	private String deUfCob;
	private Integer nuCepCob;
	private String nmGestorConta;
	private String nuCpfGestorConta;
	private String deEmailGestorConta;
	private String flAceitaApenasFixo;
	private String flAceitaInternetMenor;
	
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getDeRazaoSocial() {
		return deRazaoSocial;
	}
	public void setDeRazaoSocial(String deRazaoSocial) {
		this.deRazaoSocial = deRazaoSocial;
	}
	public String getNuCnpj() {
		return nuCnpj;
	}
	public void setNuCnpj(String nuCnpj) {
		this.nuCnpj = nuCnpj;
	}
	public String getNuCnpjFmt() {
		return FormatadorUtil.formataCnpj(nuCnpj);
	}
	public void setNuCnpjFmt(String nuCnpjFmt) {
		if(!StringUtils.isEmpty(nuCnpjFmt)){
			this.nuCnpj = FormatadorUtil.desformataCnpj(nuCnpjFmt);
		}
	}
	public String getNmRepLegal() {
		return nmRepLegal;
	}
	public void setNmRepLegal(String nmRepLegal) {
		this.nmRepLegal = nmRepLegal;
	}
	public String getNuCpfRepLegal() {
		return nuCpfRepLegal;
	}
	public void setNuCpfRepLegal(String nuCpfRepLegal) {
		this.nuCpfRepLegal = nuCpfRepLegal;
	}
	public String getNuCpfRepLegalFmt() {
		return FormatadorUtil.formataCpf(nuCpfRepLegal);
	}
	public void setNuCpfRepLegalFmt(String nuCpfFmt) {
		if(!StringUtils.isEmpty(nuCpfFmt)){
			this.nuCpfRepLegal = FormatadorUtil.desformataCpf(nuCpfFmt);
		}
	}
	public Integer getNuDiaVencimento() {
		return nuDiaVencimento;
	}
	public void setNuDiaVencimento(Integer nuDiaVencimento) {
		this.nuDiaVencimento = nuDiaVencimento;
	}
	public String getDeEndereco() {
		return deEndereco;
	}
	public void setDeEndereco(String deEndereco) {
		this.deEndereco = deEndereco;
	}
	public String getNmBairro() {
		return nmBairro;
	}
	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}
	public Integer getNuCep() {
		return nuCep;
	}
	public void setNuCep(Integer nuCep) {
		this.nuCep = nuCep;
	}
	public String getNuCepFmt() {
		return FormatadorUtil.formataCep(nuCep);
	}
	public void setNuCepFmt(String nuCepFmt) {
		if(!StringUtils.isEmpty(nuCepFmt)){
			this.nuCep = FormatadorUtil.desformataCep(nuCepFmt);
		}	
	}
	public String getNmMunicipio() {
		return nmMunicipio;
	}
	public void setNmMunicipio(String nmMunicipio) {
		this.nmMunicipio = nmMunicipio;
	}
	public String getDeUf() {
		return deUf;
	}
	public void setDeUf(String deUf) {
		this.deUf = deUf;
	}
	public Integer getNuDddFixo() {
		return nuDddFixo;
	}
	public void setNuDddFixo(Integer nuDddFixo) {
		this.nuDddFixo = nuDddFixo;
	}
	public Integer getNuTelFixo() {
		return nuTelFixo;
	}
	public void setNuTelFixo(Integer nuTelFixo) {
		this.nuTelFixo = nuTelFixo;
	}
	public String getNuTelFixoFmt() {
		return FormatadorUtil.formataTelefone(getNuTelFixo());
	}
	public void setNuTelFixoFmt(String nuTelFixoFmt) {
		if(!StringUtils.isEmpty(nuTelFixoFmt)){
			setNuTelFixo(FormatadorUtil.desformataTelefone(nuTelFixoFmt));
		}
	}
	public Integer getNuDddCelular() {
		return nuDddCelular;
	}
	public void setNuDddCelular(Integer nuDddCelular) {
		this.nuDddCelular = nuDddCelular;
	}
	public Integer getNuTelCelular() {
		return nuTelCelular;
	}
	public void setNuTelCelular(Integer nuTelCelular) {
		this.nuTelCelular = nuTelCelular;
	}
	public String getNuTelCelularFmt() {
		return FormatadorUtil.formataTelefone(getNuTelCelular());
	}
	public void setNuTelCelularFmt(String nuTelCelularFmt) {
		if(!StringUtils.isEmpty(nuTelCelularFmt)){
			setNuTelCelular(FormatadorUtil.desformataTelefone(nuTelCelularFmt));
		}
	}
	public String getDeEmail() {
		return deEmail;
	}
	public void setDeEmail(String deEmail) {
		this.deEmail = deEmail;
	}
	public String getDeEnderecoCob() {
		return deEnderecoCob;
	}
	public void setDeEnderecoCob(String deEnderecoCob) {
		this.deEnderecoCob = deEnderecoCob;
	}
	public String getNmBairroCob() {
		return nmBairroCob;
	}
	public void setNmBairroCob(String nmBairroCob) {
		this.nmBairroCob = nmBairroCob;
	}
	public String getNmMunicipioCob() {
		return nmMunicipioCob;
	}
	public void setNmMunicipioCob(String nmMunicipioCob) {
		this.nmMunicipioCob = nmMunicipioCob;
	}
	public String getDeUfCob() {
		return deUfCob;
	}
	public void setDeUfCob(String deUfCob) {
		this.deUfCob = deUfCob;
	}
	public Integer getNuCepCob() {
		return nuCepCob;
	}
	public void setNuCepCob(Integer nuCepCob) {
		this.nuCepCob = nuCepCob;
	}
	public String getNmGestorConta() {
		return nmGestorConta;
	}
	public void setNmGestorConta(String nmGestorConta) {
		this.nmGestorConta = nmGestorConta;
	}
	public String getNuCpfGestorConta() {
		return nuCpfGestorConta;
	}
	public void setNuCpfGestorConta(String nuCpfGestorConta) {
		this.nuCpfGestorConta = nuCpfGestorConta;
	}
	public String getDeEmailGestorConta() {
		return deEmailGestorConta;
	}
	public void setDeEmailGestorConta(String deEmailGestorConta) {
		this.deEmailGestorConta = deEmailGestorConta;
	}
	public String getNuCepCobFmt() {
		return FormatadorUtil.formataCep(nuCepCob);
	}
	public void setNuCepCobFmt(String nuCepCobFmt) {
		if(!StringUtils.isEmpty(nuCepCobFmt)){
			setNuCepCob(FormatadorUtil.desformataCep(nuCepCobFmt));
		}
	}
	public String getNuCpfGestorContaFmt() {
		return FormatadorUtil.formataCpf(getNuCpfGestorConta());
	}
	public void setNuCpfGestorContaFmt(String nuCpfFmt) {
		if(!StringUtils.isEmpty(nuCpfFmt)){
			setNuCpfGestorConta(FormatadorUtil.desformataCpf(nuCpfFmt));
		}
	}
	@Override
	public Integer getId() {
		return getIdEmpresa();
	}
	public String getFlAceitaApenasFixo() {
		return flAceitaApenasFixo;
	}
	public void setFlAceitaApenasFixo(String flAceitaApenasFixo) {
		this.flAceitaApenasFixo = flAceitaApenasFixo;
	}
	public String getFlAceitaInternetMenor() {
		return flAceitaInternetMenor;
	}
	public void setFlAceitaInternetMenor(String flAceitaInternetMenor) {
		this.flAceitaInternetMenor = flAceitaInternetMenor;
	}
	
}
