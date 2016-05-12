package br.com.oisul.spring.utils;

public enum UrlsAdmin {
	
	AQUISICAO_PASSO_0("admin/aquisicaoAdminPasso0"),
	AQUISICAO_PASSO_1("admin/aquisicaoAdminPasso1"),
	AQUISICAO_PASSO_1_FIXO("admin/aquisicaoAdminPasso1Fixo"),
	AQUISICAO_PASSO_2("admin/aquisicaoAdminPasso2"),
	AQUISICAO_PASSO_3("admin/aquisicaoAdminPasso3"),
	AQUISICAO_PASSO_4("admin/aquisicaoAdminPasso4"),
	AQUISICAO_PASSO_5("admin/aquisicaoAdminPasso5"),
	ABRIR_PEDIDOS_CONSULTOR("admin/conPedidoConsultor"),
	// CADASTRO DE USUÁRIOS
	CONSULTA_USUARIOS_ADMIN("admin/conUsuario"),
	CADASTRO_USUARIOS_ADMIN("admin/cadUsuario"),
	INFO_AQUISICAO("admin/infoAquisicao"),
	
	HOME("admin/index");

	public String url;

	UrlsAdmin(String s) {
		url = s;
	}
}
