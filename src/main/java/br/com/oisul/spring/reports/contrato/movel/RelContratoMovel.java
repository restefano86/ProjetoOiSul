package br.com.oisul.spring.reports.contrato.movel;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.util.StringUtils;

import br.com.oisul.spring.model.PerfilVenda;
import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.reports.PDFBoxUtils;
import br.com.oisul.spring.utils.DatabaseUtils;
import br.com.oisul.spring.utils.FormatadorUtil;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class RelContratoMovel {
	private PDDocument _pdfDocument;
	
	public byte[] geraRelatorio(Venda vendaMovel) throws Exception{
		try {
			InputStream originalPdf = this.getClass().getResourceAsStream("/br/com/oisul/spring/reports/contrato/movel/ContratoMovelModelo.pdf");
			_pdfDocument = PDDocument.load(originalPdf);
			
			_pdfDocument.getNumberOfPages();
			//printFields();  //Uncomment to see the fields in this document in console
			
			PDFBoxUtils.setField("nmParceiro", "VALORANCE", _pdfDocument);
			PDFBoxUtils.setField("pdvParceiro",vendaMovel.getCodPDVByUF(), _pdfDocument);
			PDFBoxUtils.setField("deRazaoSocial", vendaMovel.getEmpresa().getDeRazaoSocial(), _pdfDocument);
			PDFBoxUtils.setField("nuCnpjFmt", vendaMovel.getEmpresa().getNuCnpjFmt(), _pdfDocument);
			
			//ENDEREÇO
			PDFBoxUtils.setField("deEndereco", vendaMovel.getEmpresa().getDeEndereco(), _pdfDocument);
			PDFBoxUtils.setField("nmBairro", vendaMovel.getEmpresa().getNmBairro(), _pdfDocument);
			PDFBoxUtils.setField("nuCepFmt", vendaMovel.getEmpresa().getNuCepFmt(), _pdfDocument);
			PDFBoxUtils.setField("nmMunicipio", vendaMovel.getEmpresa().getNmMunicipio(), _pdfDocument);
			PDFBoxUtils.setField("deUf", vendaMovel.getEmpresa().getDeUf(), _pdfDocument);

			boolean enderecoCobPreenchido = StringUtils.isEmpty(vendaMovel.getEmpresa().getDeEnderecoCob()) ? false : true;
			if(enderecoCobPreenchido){
				PDFBoxUtils.setField("cobDifReceita", "X", _pdfDocument);
				PDFBoxUtils.setField("deEnderecoCob", vendaMovel.getEmpresa().getDeEnderecoCob(), _pdfDocument);
				PDFBoxUtils.setField("nmBairroCob", vendaMovel.getEmpresa().getNmBairroCob(), _pdfDocument);
				PDFBoxUtils.setField("nuCepCobFmt", vendaMovel.getEmpresa().getNuCepCobFmt(), _pdfDocument);
				PDFBoxUtils.setField("nmMunicipioCob", vendaMovel.getEmpresa().getNmMunicipioCob(), _pdfDocument);
				PDFBoxUtils.setField("deUfCob", vendaMovel.getEmpresa().getDeUfCob(), _pdfDocument);
			} else {
				PDFBoxUtils.setField("cobMesmoReceita", "X", _pdfDocument);
			}
			
			PDFBoxUtils.setField("nmGestorConta", vendaMovel.getEmpresa().getNmGestorConta(), _pdfDocument);
			PDFBoxUtils.setField("nuCpfGestorConta", vendaMovel.getEmpresa().getNuCpfGestorContaFmt(), _pdfDocument);
			PDFBoxUtils.setField("deEmailGestorConta", vendaMovel.getEmpresa().getDeEmailGestorConta(), _pdfDocument);
			PDFBoxUtils.setField("nuTelFixoFmt", "("+vendaMovel.getEmpresa().getNuDddFixo()+")"+vendaMovel.getEmpresa().getNuTelFixoFmt(), _pdfDocument);
			PDFBoxUtils.setField("nuTelFixoFmt0", "("+vendaMovel.getEmpresa().getNuDddFixo()+")"+vendaMovel.getEmpresa().getNuTelFixoFmt(), _pdfDocument);
			PDFBoxUtils.setField("nuCelularFmt", "("+vendaMovel.getEmpresa().getNuDddCelular()+")"+vendaMovel.getEmpresa().getNuTelCelularFmt(), _pdfDocument);
			PDFBoxUtils.setField("nmRepLegal", vendaMovel.getEmpresa().getNmRepLegal(), _pdfDocument);
			PDFBoxUtils.setField("nuCpfRepLegalFmt", vendaMovel.getEmpresa().getNuCpfRepLegalFmt(), _pdfDocument);
			
			PDFBoxUtils.setField("X1", "X", _pdfDocument);
			PDFBoxUtils.setField("possuiMinimodelN", "X", _pdfDocument);
			PDFBoxUtils.setField("vencimento"+vendaMovel.getEmpresa().getNuDiaVencimento(), "X", _pdfDocument);
			
			Integer qtAcessos = 0;
			Integer qtAcessosPortados = vendaMovel.getQtItensPortabilidade();
			
			boolean possuiPortabilidade = false;
			int indice = 0;
			for (PerfilVenda perfil : vendaMovel.getPerfis()) {
				qtAcessos += perfil.getQtAcessos();
				
				PDFBoxUtils.setField("codigo_"+indice, perfil.getProduto().getCodigoOi().toString(), _pdfDocument);
				PDFBoxUtils.setField("ddd_"+indice, perfil.getNuDdd().toString(), _pdfDocument);
				PDFBoxUtils.setField("qtAcessos_"+indice, perfil.getQtAcessos().toString(), _pdfDocument);
				PDFBoxUtils.setField("vlAssinatura_"+indice, "R$"+FormatadorUtil.formataMoeda(perfil.getProduto().getVlplano()), _pdfDocument);
				
				String deTipoChip = "";
				if("C".equals(perfil.getFlTipoChip())){
					deTipoChip = "Comum";
				} else if("M".equals(perfil.getFlTipoChip())){
					deTipoChip = "Micro";
				} else if("N".equals(perfil.getFlTipoChip())){
					deTipoChip = "Nano";
				}
				
				PDFBoxUtils.setField("modeloChip_"+indice, deTipoChip, _pdfDocument);
				PDFBoxUtils.setField("vlChip_"+indice, "R$1,00", _pdfDocument);
				PDFBoxUtils.setField("formaPagto_"+indice, "À vista", _pdfDocument);
				
				indice++;
			}
			PDFBoxUtils.setField("X1", "X", _pdfDocument);
			if(vendaMovel.getQtItensPortabilidade() > 0){
				PDFBoxUtils.setField("possuiPortabilidadeS", "X", _pdfDocument);
			} else {
				PDFBoxUtils.setField("possuiPortabilidadeN", "X", _pdfDocument);
			}
			PDFBoxUtils.setField("qtAcessos", qtAcessos.toString(), _pdfDocument);
			PDFBoxUtils.setField("qtAcessosPortados", qtAcessosPortados.toString(), _pdfDocument);
			
			//DATA DA SOLICITAÇÃO
			Calendar calendar = new GregorianCalendar();
			PDFBoxUtils.setField("dtDia", FormatadorUtil.formataDoisDigitos(calendar.get(Calendar.DAY_OF_MONTH))+"", _pdfDocument);
			PDFBoxUtils.setField("dtMes", FormatadorUtil.formataDoisDigitos(calendar.get(Calendar.MONTH)+1)+"", _pdfDocument);
			PDFBoxUtils.setField("dtAno", FormatadorUtil.formataDoisDigitos(calendar.get(Calendar.YEAR))+"", _pdfDocument);
			PDFBoxUtils.setField("dtAno", FormatadorUtil.formataDoisDigitos(calendar.get(Calendar.YEAR))+"", _pdfDocument);
			PDFBoxUtils.setField("xConfirmaRecebimento", "X", _pdfDocument);
			PDFBoxUtils.setField("xDesejaReceberMensagemN", "X", _pdfDocument);
			PDFBoxUtils.setField("cpfRepLegalAssinatura", vendaMovel.getEmpresa().getNuCpfRepLegalFmt(), _pdfDocument);

			ByteArrayOutputStream bout = new ByteArrayOutputStream();

			_pdfDocument.save(bout);
			_pdfDocument.save("C:\\blah\\ContratoMovel_"+(new Date()).getTime()+".pdf");
			_pdfDocument.close();
			return bout.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		
	}

}
