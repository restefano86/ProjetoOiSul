package br.com.oisul.spring.utils;

public enum UrlsAdmin {
	HOME("admin/index"),
	AQUISICAO_PASSO_0("admin/aquisicaoAdminPasso0"),
	AQUISICAO_PASSO_1("admin/aquisicaoAdminPasso1"),
	AQUISICAO_PASSO_2("admin/aquisicaoAdminPasso2"),
	AQUISICAO_PASSO_3("admin/aquisicaoAdminPasso3"),
	AQUISICAO_PASSO_4("admin/aquisicaoAdminPasso4"),
	AQUISICAO_PASSO_5("admin/aquisicaoAdminPasso5"),
	ABRIR_PEDIDOS_CONSULTOR("admin/conPedidoConsultor");
	
	public String url;

	UrlsAdmin(String s) {
		url = s;
	}
}
