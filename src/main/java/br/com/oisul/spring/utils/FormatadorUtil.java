package br.com.oisul.spring.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.apache.commons.lang.StringUtils;

public class FormatadorUtil {
	
	public static final String FORMATO_DATA_PADRAO = "dd/MM/yyyy";
	public static final String FORMATO_DATA_HORA_PADRAO = "dd/MM/yyyy HH:mm";

	public static String formataTelefone(Integer nuTelefone){
		if(nuTelefone != null && nuTelefone > 0){
			return format("####-####", completaZerosAEsquerda(nuTelefone, 8));
		}
		return "";
	}
	public static String formataCpf(String cpf){
		if(StringUtils.isNotEmpty(cpf)){
			return format("###.###.###-##", cpf);
		}
		return "";
	}
	public static String formataCep(Integer cep){
		if(cep != null && cep > 0){
		return format("##.###-###", completaZerosAEsquerda(cep, 8));
		} 
		return "";
		
	}
	public static String formataCnpj(String cnpj){
		if(cnpj != null){
			return format("##.###.###/####-##", cnpj);
		} 
		return "";
	}

	public static String desformataCpf(String cpfFmt){
		if (cpfFmt != null) {
			return cpfFmt.replaceAll("[^\\d]", "");
		}
		return null;
	}
	public static Integer desformataCep(String cepFmt){
		if (cepFmt != null) {
			return Integer.parseInt(cepFmt.replaceAll("[^\\d]", ""));
		}
		return null;
	}
	public static String desformataCnpj(String cnpjFmt){
		if (cnpjFmt != null) {
			return cnpjFmt.replaceAll("[^\\d]", "");
		}
		return null;
	}

	public static Integer desformataTelefone(String nuTelefoneFmt){
		if (nuTelefoneFmt != null) {
			return Integer.parseInt(nuTelefoneFmt.replaceAll("[^\\d]", ""));
		}
		return null;
	}

	private static String format(String pattern, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static String completaZerosAEsquerda(Integer numero, int qtdDigitos){
		return completaZerosAEsquerda(numero.longValue(), qtdDigitos);
	}

	public static String completaZerosAEsquerda(Long numero, int qtdDigitos){
		String numeroStr =  numero.toString();
		if(numeroStr.length() == qtdDigitos){
			return numeroStr;
		}
		Integer qtDigitosAtual  = numeroStr.length();
		while (qtDigitosAtual < qtdDigitos) {
			numeroStr = "0"+numeroStr;
			qtDigitosAtual  = numeroStr.length();
		}
		return numeroStr;
	}
	
	public static String formataMoeda(Double valor){
		DecimalFormat formato = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
		formato.setMinimumFractionDigits(2); 
		formato.setParseBigDecimal (true);
		return formato.format(valor);
	}
	
	public static String formataDoisDigitos(Integer valor){
		return completaZerosAEsquerda(valor, 2);
	}
	
	public static String formataData(Date data){
		if(data != null){
			SimpleDateFormat fmt = new SimpleDateFormat(FORMATO_DATA_PADRAO);
			return fmt.format(data);
		} else {
			return "";
		}
	}

	public static String formataDataHora(Date data){
		SimpleDateFormat fmt = new SimpleDateFormat(FORMATO_DATA_HORA_PADRAO);
		return fmt.format(data);
	}
	
	public static Date getDateByString(String dataStr) throws Exception{
		SimpleDateFormat fmt = new SimpleDateFormat(FORMATO_DATA_PADRAO);
		Date data = fmt.parse(dataStr);
		return data;
	}
	
}
