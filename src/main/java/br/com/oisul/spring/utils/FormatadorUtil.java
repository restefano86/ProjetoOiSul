package br.com.oisul.spring.utils;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class FormatadorUtil {
	

	public static String formataTelefone(Integer nuTelefone){
		if(nuTelefone != null && nuTelefone > 0){
			return format("####-####", completaZerosAEsquerda(nuTelefone, 8));
		}
		return "";
	}
	public static String formataCpf(Long cpf){
		if(cpf != null && cpf > 0){
			return format("###.###.###-##", completaZerosAEsquerda(cpf, 11));
		}
		return "";
	}
	public static String formataCep(Integer cep){
		if(cep != null && cep > 0){
		return format("##.###-###", completaZerosAEsquerda(cep, 8));
		} 
		return "";
		
	}
	public static String formataCnpj(Long cnpj){
		if(cnpj != null && cnpj > 0){
			return format("##.###.###/####-##", completaZerosAEsquerda(cnpj, 14));
		} 
		return "";
	}

	public static Long desformataCpf(String cpfFmt){
		if (cpfFmt != null) {
			return Long.parseLong(cpfFmt.replaceAll("[^\\d]", ""));
		}
		return null;
	}
	public static Integer desformataCep(String cepFmt){
		if (cepFmt != null) {
			return Integer.parseInt(cepFmt.replaceAll("[^\\d]", ""));
		}
		return null;
	}
	public static Long desformataCnpj(String cnpjFmt){
		if (cnpjFmt != null) {
			return Long.parseLong(cnpjFmt.replaceAll("[^\\d]", ""));
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
	

}
