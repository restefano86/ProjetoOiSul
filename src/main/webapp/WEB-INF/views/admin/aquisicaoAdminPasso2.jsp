<style>
	td.rotulo{
		width: 250px;
	}
</style>


<%@include file="preConteudo.jsp" %>
	<%@include file="../comum/includeAquisicaoPasso2.jsp" %> 
<%@include file="posConteudo.jsp" %>

<script type="text/javascript">
function proximaEtapa(){
	jQuery("#empresa").submit();
}
function etapaAnterior(){
	abreLink('aquisicaoAdminPasso1');
}
</script>	