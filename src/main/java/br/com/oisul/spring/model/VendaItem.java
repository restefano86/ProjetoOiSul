package br.com.oisul.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import br.com.oisul.spring.utils.FormatadorUtil;

@Entity
@Table(name="vendaitem")
public class VendaItem implements ModelInterface, Cloneable {
	
	public static final String FL_PORTABILIDADE_SIM = "S";
	public static final String FL_PORTABILIDADE_NAO = "N";

	@Id 
    @Column(name="idvendaitem", unique=true, nullable=false)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer idVendaItem;
	
	private Integer idVenda;
	private Integer idProduto;
	private Integer nuDdd;
	private String flTipoChip;
	private String flPortabilidade;
	private Integer nuPortabilidade;
	private Integer idOperadora;
	private Integer idProdutoBL;
	
	
	public Integer getIdVendaItem() {
		return idVendaItem;
	}
	public void setIdVendaItem(Integer idVendaItem) {
		this.idVendaItem = idVendaItem;
	}
	public Integer getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getNuDdd() {
		return nuDdd;
	}
	public void setNuDdd(Integer nuDdd) {
		this.nuDdd = nuDdd;
	}
	public String getFlTipoChip() {
		return flTipoChip;
	}
	public void setFlTipoChip(String flTipoChip) {
		this.flTipoChip = flTipoChip;
	}
	public String getFlPortabilidade() {
		return flPortabilidade;
	}
	public void setFlPortabilidade(String flPortabilidade) {
		this.flPortabilidade = flPortabilidade;
	}
	public Integer getNuPortabilidade() {
		return nuPortabilidade;
	}
	public void setNuPortabilidade(Integer nuPortabilidade) {
		this.nuPortabilidade = nuPortabilidade;
	}
	public Integer getIdOperadora() {
		return idOperadora;
	}
	public void setIdOperadora(Integer idOperadora) {
		this.idOperadora = idOperadora;
	}
	@Override
	public Integer getId() {
		return getIdVendaItem();
	}
	
	public String getNuPortabilidadeFmt() {
		return FormatadorUtil.formataTelefone(getNuPortabilidade());
	}
	public void setNuPortabilidadeFmt(String nuPortabilidadeFmt) {
		if(!StringUtils.isEmpty(nuPortabilidadeFmt)){
			setNuPortabilidade(FormatadorUtil.desformataTelefone(nuPortabilidadeFmt));
		}
	}

    @Override  
    public VendaItem clone() throws CloneNotSupportedException {  
        VendaItem clonado = (VendaItem) super.clone();  
        return clonado;  
    }
	public Integer getIdProdutoBL() {
		return idProdutoBL;
	}
	public void setIdProdutoBL(Integer idProdutoBL) {
		this.idProdutoBL = idProdutoBL;
	}  

}
