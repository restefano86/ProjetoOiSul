package br.com.oisul.spring.utils;

public enum UrlsAdmin {
	HOME("admin/index"),
	AQUISICAO_PASSO_0("admin/aquisicaoAdminPasso0"),
	AQUISICAO_PASSO_1("admin/aquisicaoPasso1"),
	AQUISICAO_PASSO_2("admin/aquisicaoPasso2"),
	AQUISICAO_PASSO_3("admin/aquisicaoPasso3"),
	AQUISICAO_PASSO_4("admin/aquisicaoPasso4"),
	AQUISICAO_PASSO_5("admin/aquisicaoPasso5");
	
	public String url;

	UrlsAdmin(String s) {
		url = s;
	}
}
