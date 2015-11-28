package br.com.oisul.spring.utils;

public enum UrlsSite {
	HOME("site/index"),
	CADASTROUSUARIO("site/cadastro"),
	CADASTRONAOLOGADO("redirect:/cadastroNaoLogado"),
	AQUISICAO_PASSO_0("site/aquisicaoPasso0"),
	AQUISICAO_PASSO_1("site/aquisicaoPasso1"),
	AQUISICAO_PASSO_2("site/aquisicaoPasso2"),
	AQUISICAO_PASSO_3("site/aquisicaoPasso3"),
	AQUISICAO_PASSO_4("site/aquisicaoPasso4"),
	AQUISICAO_PASSO_5("site/aquisicaoPasso5");
	
	public String url;

	UrlsSite(String s) {
		url = s;
	}
}
