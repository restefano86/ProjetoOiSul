package br.com.oisul.spring.reports.contrato.fixo;

import java.io.InputStream;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.springframework.util.StringUtils;

import br.com.oisul.spring.model.Venda;
import br.com.oisul.spring.reports.PDFBoxUtils;

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
			PDFBoxUtils.setField("pdvParceiro", "1033292/1480", _pdfDocument);
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
			
			
			
			
			PDStream pdStream = new PDStream(_pdfDocument);
			byte[] byteArray = pdStream.getByteArray();
			
			_pdfDocument.save("C:\\blah\\ContratoFixoBandaLarga"+(new Date()).getTime()+".pdf");
			_pdfDocument.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		
		return null;
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
