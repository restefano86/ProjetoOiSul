package br.com.oisul.spring.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="venda")
public class Venda implements ModelInterface {
	
	public static final String TP_VENDA_FIXO = "F";
	public static final String TP_VENDA_MOVEL = "M";

	@Id 
    @Column(name="idvenda", unique=true, nullable=false)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer idVenda;
	
	private Integer idConsultor;
	private Integer idUsuario;
	private Integer idEmpresa;
	private Date dtCadastro;
	private Date dtContratoGerado;
	private Date dtContratoEnviado;
	private Integer idSituacao;
	private String tpVenda;
	
	@Transient
	private List<VendaDocumento> documentosGerados; 
	
	@Transient
	private List<VendaDocumento> documentosInseridos; 
	
	@Transient
	private List<PerfilVenda> perfis;
	
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="idVenda")
    private List<VendaItem> itens;
    
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "idConsultor", insertable = false, updatable = false)
    @Transient
	private Usuario consultor;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "idUsuario", insertable = false, updatable = false)
    @Transient
	private Usuario usuario;
//
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSituacao", insertable = false, updatable = false)
    @Transient
	private SituacaoVenda situacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEmpresa", insertable = false, updatable = false)
	private Empresa empresa;

	public Venda() {
		setItens(new ArrayList<VendaItem>());
//		setConsultor(new Usuario());
//		setUsuario(new Usuario());
		setSituacao(new SituacaoVenda());
		setEmpresa(new Empresa());
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}
	public Integer getIdConsultor() {
		return idConsultor;
	}
	public void setIdConsultor(Integer idConsultor) {
		this.idConsultor = idConsultor;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public Date getDtContratoGerado() {
		return dtContratoGerado;
	}
	public void setDtContratoGerado(Date dtContratoGerado) {
		this.dtContratoGerado = dtContratoGerado;
	}
	public Date getDtContratoEnviado() {
		return dtContratoEnviado;
	}
	public void setDtContratoEnviado(Date dtContratoEnviado) {
		this.dtContratoEnviado = dtContratoEnviado;
	}
	public String getTpVenda() {
		return tpVenda;
	}
	public void setTpVenda(String tpVenda) {
		this.tpVenda = tpVenda;
	}
	@Override
	public Integer getId() {
		return getIdVenda();
	}
	public List<VendaItem> getItens() {
		return itens;
	}
	public void setItens(List<VendaItem> itens) {
		this.itens = itens;
	}

	public Integer getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
	}

//	public Usuario getConsultor() {
//		return consultor;
//	}
//
//	public void setConsultor(Usuario consultor) {
//		this.consultor = consultor;
//	}
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//
//	public SituacaoVenda getSituacao() {
//		return situacao;
//	}
//
//	public void setSituacao(SituacaoVenda situacao) {
//		this.situacao = situacao;
//	}
	
	public Integer getQtItens() {
		return getItens().size();
	}
	public Integer getQtItensPortabilidade() {
		Integer qtItensPortabilidade = 0;
		for (VendaItem vendaItem : getItens()) {
			if("S".equals(vendaItem.getFlPortabilidade())){
				qtItensPortabilidade++;
			}
		}
		return qtItensPortabilidade;
	}

	public List<VendaDocumento> getDocumentosGerados() {
		return documentosGerados;
	}

	public void setDocumentosGerados(List<VendaDocumento> documentosGerados) {
		this.documentosGerados = documentosGerados;
	}

	public List<VendaDocumento> getDocumentosInseridos() {
		return documentosInseridos;
	}

	public void setDocumentosInseridos(List<VendaDocumento> documentosInseridos) {
		this.documentosInseridos = documentosInseridos;
	}

	public Usuario getConsultor() {
		return consultor;
	}

	public void setConsultor(Usuario consultor) {
		this.consultor = consultor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public SituacaoVenda getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoVenda situacao) {
		this.situacao = situacao;
	}
	
	public boolean getIsVendaFixo(){
		return TP_VENDA_FIXO.equals(getTpVenda());
	}

	public List<PerfilVenda> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<PerfilVenda> perfis) {
		this.perfis = perfis;
	}
	
	
	

}
