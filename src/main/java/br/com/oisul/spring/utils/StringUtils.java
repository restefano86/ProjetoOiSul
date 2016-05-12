package br.com.oisul.spring.utils;

public class StringUtils {
	
	public static boolean isNotNulltOrBlank(String string){
		return (string != null && !"".equals(string));
	}

}
