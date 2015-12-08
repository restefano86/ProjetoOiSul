<%@include file="preConteudo.jsp" %>
	<%@include file="../comum/includeAquisicaoPasso1.jsp" %> 
<%@include file="posConteudo.jsp" %>	
<script type="text/javascript">
	function proximaEtapa(){
		$("input").removeAttr("disabled");
		$("select").removeAttr("disabled");
		$("#venda").submit();
		//abreLink('aquisicaoAdminPasso2');
	}
	function etapaAnterior(){
		abreLink('aquisicaoAdminPasso0');
	}
</script>