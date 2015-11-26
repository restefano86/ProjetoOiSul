package br.com.oisul.spring.utils;

public enum UrlsSite {
	HOME("site/home"),
	AQUISICAO_PASSO_1("site/aquisicaoPasso1");
	
	public String url;

	UrlsSite(String s) {
		url = s;
	}
}
