package br.com.oisul.spring.reports.contrato.fixo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.springframework.util.StringUtils;

import br.com.oisul.spring.model.PerfilVenda;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.model.VendaItem;
import br.com.oisul.spring.reports.PDFBoxUtils;
import br.com.oisul.spring.utils.FormatadorUtil;

public class RelContratoFixo {
	
	private PDDocument _pdfDocument;
	
	public byte[] geraRelatorio(Venda vendaFixo) throws Exception{
		try {
			InputStream originalPdf = this.getClass().getResourceAsStream("/br/com/oisul/spring/reports/contrato/fixo/ContratoFixoBandaLarga.pdf");
			_pdfDocument = PDDocument.load(originalPdf);
			
			_pdfDocument.getNumberOfPages();
			//printFields();  //Uncomment to see the fields in this document in console
			
			boolean enderecoCobPreenchido = StringUtils.isEmpty(vendaFixo.getEmpresa().getDeEnderecoCob()) ? false : true;
			
			PDFBoxUtils.setField("nmParceiro", "VALORANCE", _pdfDocument);
			PDFBoxUtils.setField("pdvParceiro", vendaFixo.getCodPDVByUF(), _pdfDocument);
			PDFBoxUtils.setField("deRazaoSocial", vendaFixo.getEmpresa().getDeRazaoSocial(), _pdfDocument);
			PDFBoxUtils.setField("nuCnpjFmt", vendaFixo.getEmpresa().getNuCnpjFmt(), _pdfDocument);
			PDFBoxUtils.setField("enderecoRFSim", !enderecoCobPreenchido ? "X" : "", _pdfDocument);
			PDFBoxUtils.setField("enderecoRFNao", enderecoCobPreenchido ? "X" : "", _pdfDocument);
			if(enderecoCobPreenchido){
				PDFBoxUtils.setField("deEnderecoCob", vendaFixo.getEmpresa().getDeEnderecoCob(), _pdfDocument);
				PDFBoxUtils.setField("nmBairroCob", vendaFixo.getEmpresa().getNmBairroCob(), _pdfDocument);
				PDFBoxUtils.setField("nuCepCobFmt", vendaFixo.getEmpresa().getNuCepCobFmt(), _pdfDocument);
				PDFBoxUtils.setField("nmMunicipioCob", vendaFixo.getEmpresa().getNmMunicipioCob(), _pdfDocument);
				PDFBoxUtils.setField("deUfCob", vendaFixo.getEmpresa().getDeUfCob(), _pdfDocument);
			}
			PDFBoxUtils.setField("nmGestorConta", vendaFixo.getEmpresa().getNmGestorConta(), _pdfDocument);
			PDFBoxUtils.setField("deEmailGestorConta", vendaFixo.getEmpresa().getDeEmailGestorConta(), _pdfDocument);
			PDFBoxUtils.setField("nuTelFixoFmt", vendaFixo.getEmpresa().getNuTelFixoFmt(), _pdfDocument);
			PDFBoxUtils.setField("nuDddFixo", vendaFixo.getEmpresa().getNuDddFixo().toString(), _pdfDocument);
			PDFBoxUtils.setField("nuDddCelular", vendaFixo.getEmpresa().getNuDddCelular().toString(), _pdfDocument);
			PDFBoxUtils.setField("nuTelCelularFmt", vendaFixo.getEmpresa().getNuTelCelularFmt(), _pdfDocument);
			PDFBoxUtils.setField("v1a5", "", _pdfDocument);
			PDFBoxUtils.setField("v6a10", "", _pdfDocument);
			PDFBoxUtils.setField("v11a15", "X", _pdfDocument);
			PDFBoxUtils.setField("v16a20", "", _pdfDocument);
			PDFBoxUtils.setField("v20a25", "", _pdfDocument);
			PDFBoxUtils.setField("v26a31", "", _pdfDocument);
			PDFBoxUtils.setField("nmRepLegal", vendaFixo.getEmpresa().getNmRepLegal(), _pdfDocument);
			PDFBoxUtils.setField("nuCpfRepLegalFmt", vendaFixo.getEmpresa().getNuCpfRepLegalFmt(), _pdfDocument);
			
			int indice = 1;
			Double vlTotalTaxa = 0d;
			Double vlTotalAssinatura= 0d;
			
			for (PerfilVenda perfil : vendaFixo.getPerfis()) {
				String produto = "";
				if(!perfil.getIdProduto().equals(22) && perfil.getIdProdutoBL() != null){
					produto = "Fixo + Velox";
				} else if(perfil.getIdProduto().equals(22) && perfil.getIdProdutoBL() != null){
					produto = "Velox";
				} else {
					produto = "Fixo";
				}
				
				vlTotalTaxa += 99.90 * perfil.getQtAcessos();
				vlTotalAssinatura += perfil.getProduto().getVlplano() * perfil.getQtAcessos();
				if(perfil.getIdProdutoBL() != null){	
					vlTotalAssinatura += perfil.getProdutoBL().getVlplano() * perfil.getQtAcessos();
				}
				String lCodigo = perfil.getProduto().getCodigoOi().toString();
				String qtdAcessos = perfil.getQtAcessos().toString();
				String lTAD = "10x R$9,90";
				
				String vlAssinatura;
				if(perfil.getIdProdutoBL() != null){
					String vl1 = FormatadorUtil.formataMoeda(perfil.getProduto().getVlplano());
					String vl2 = FormatadorUtil.formataMoeda(perfil.getProdutoBL().getVlplano());
					vlAssinatura = vl1+" / "+vl2;
				} else {
					vlAssinatura = FormatadorUtil.formataMoeda(perfil.getProduto().getVlplano());
				}
				
				String nuDdd = perfil.getNuDdd().toString();
				String telefone = "";
				int indiceLista = indice-1;
				if(vendaFixo.getItens().get(indiceLista).getNuPortabilidade() != null){
					telefone = vendaFixo.getItens().get(indiceLista).getNuPortabilidade().toString();
				}
				String lPortabilidade = perfil.getFlPortabilidade().equals(PerfilVenda.FL_PORTABILIDADE_S) ? "X" : "";
				String lVelox = perfil.getIdProdutoBL() != null ? "X" : "";
				String lOperadora = "";
				if(vendaFixo.getItens().get(indiceLista) != null && vendaFixo.getItens().get(indiceLista).getIdOperadora() != null){
					lOperadora = vendaFixo.getItens().get(indiceLista).getOperadora().getNmOperadora();
				}

				PDFBoxUtils.setField("l"+indice+"Produto", produto, _pdfDocument);
				PDFBoxUtils.setField("l"+indice+"Codigo", lCodigo, _pdfDocument);
				PDFBoxUtils.setField("l"+indice+"QtdAcessos", qtdAcessos, _pdfDocument);
				PDFBoxUtils.setField("l"+indice+"TAD", lTAD, _pdfDocument);
				PDFBoxUtils.setField("l"+indice+"VlAssinatura", vlAssinatura, _pdfDocument);
				PDFBoxUtils.setField("l"+indice+"Ddd", telefone.isEmpty()? "" : nuDdd, _pdfDocument);
				PDFBoxUtils.setField("l"+indice+"Telefone", telefone, _pdfDocument);
				PDFBoxUtils.setField("l"+indice+"Velox", lVelox, _pdfDocument);
				PDFBoxUtils.setField("l"+indice+"Portabilidade", lPortabilidade, _pdfDocument);
				PDFBoxUtils.setField("l"+indice+"Operadora", lOperadora, _pdfDocument);
				indice++;
			}
			PDFBoxUtils.setField("vlTotalTaxas", FormatadorUtil.formataMoeda(vlTotalTaxa), _pdfDocument);
			PDFBoxUtils.setField("vlTotalAssinatura", FormatadorUtil.formataMoeda(vlTotalAssinatura), _pdfDocument);
			
			//ENDEREÇO DE INSTALAÇÃO
			PDFBoxUtils.setField("aceitaApenasFixoS", vendaFixo.getEmpresa().getFlAceitaApenasFixo().equals("X") ? "X" : "", _pdfDocument);
			PDFBoxUtils.setField("aceitaApenasFixoN", vendaFixo.getEmpresa().getFlAceitaApenasFixo().equals("X") ? "" : "X", _pdfDocument);
			PDFBoxUtils.setField("aceitaVelocidadeMenorS", vendaFixo.getEmpresa().getFlAceitaInternetMenor().isEmpty() ? "" : "X", _pdfDocument);
			PDFBoxUtils.setField("aceitaVelocidadeMenorN", vendaFixo.getEmpresa().getFlAceitaInternetMenor().isEmpty() ? "X" : "", _pdfDocument);
			PDFBoxUtils.setField("qtVelocidadeMenor", vendaFixo.getEmpresa().getFlAceitaInternetMenor(), _pdfDocument);
			
			PDFBoxUtils.setField("qtBandaLarga", vendaFixo.getQtItens().toString(), _pdfDocument);
			PDFBoxUtils.setField("velBandaLarga", getVelocidadebandaLarga(vendaFixo), _pdfDocument);
			PDFBoxUtils.setField("deEndereco", vendaFixo.getEmpresa().getDeEndereco(), _pdfDocument);
			PDFBoxUtils.setField("nmBairro", vendaFixo.getEmpresa().getNmBairro(), _pdfDocument);
			PDFBoxUtils.setField("nuCepFmt", vendaFixo.getEmpresa().getNuCepFmt(), _pdfDocument);
			PDFBoxUtils.setField("nmMunicipio", vendaFixo.getEmpresa().getNmMunicipio(), _pdfDocument);
			PDFBoxUtils.setField("deUf", vendaFixo.getEmpresa().getDeUf(), _pdfDocument);
			
			//DATA DA SOLICITAÇÃO
			Calendar calendar = new GregorianCalendar();
			PDFBoxUtils.setField("dtDia", FormatadorUtil.formataDoisDigitos(calendar.get(Calendar.DAY_OF_MONTH))+"", _pdfDocument);
			PDFBoxUtils.setField("dtMes", FormatadorUtil.formataDoisDigitos(calendar.get(Calendar.MONTH)+1)+"", _pdfDocument);
			PDFBoxUtils.setField("dtAno", FormatadorUtil.formataDoisDigitos(calendar.get(Calendar.YEAR))+"", _pdfDocument);
			PDFBoxUtils.setField("periodoIndiferente", "X", _pdfDocument);
			
			ByteArrayOutputStream bout = new ByteArrayOutputStream();

			_pdfDocument.save(bout);
//			_pdfDocument.save("C:\\blah\\ContratoFixoBandaLarga"+(new Date()).getTime()+".pdf");
			_pdfDocument.close();
			return bout.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		
	}

	private String getQtBandaLarga(Venda vendaFixo) {
//		Integer qtBandaLarga = 0;
//		for (VendaItem item : vendaFixo.getItens()) {
//			qtBandaLarga++;
//		}
		return null;
	}
	
	private String getVelocidadebandaLarga(Venda vendaFixo) {
		for (PerfilVenda perfilVenda : vendaFixo.getPerfis()) {
			if(perfilVenda.getIdProdutoBL() != null){
				return perfilVenda.getProdutoBL().getDePlano();
			}
		}
		return "";
	}

	
//	private void populateAndCopy(InputStream originalPdf, String targetPdf) throws IOException, COSVisitorException {
//		_pdfDocument = PDDocument.load(originalPdf);
//		
//		_pdfDocument.getNumberOfPages();
//		//printFields();  //Uncomment to see the fields in this document in console
//		
//		PDFBoxUtils.setField("parceiro", "Nome do Parceiro", _pdfDocument);
//		PDStream pdStream = new PDStream(_pdfDocument);
//		byte[] byteArray = pdStream.getByteArray();
//		_pdfDocument.save(targetPdf);
//		_pdfDocument.close();
//	}

}
