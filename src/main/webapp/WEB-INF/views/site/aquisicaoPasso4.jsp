<html>
	<head>
		<title>OiSul - Planos empresariais da operadora Oi</title>
		<%@include file="includes.jsp"%> 
	</head>
	<body>
		<div id="site">
			<%@include file="cabecalho.jsp" %>
 			<div id="conteudo">
			 	<div id="corpo">

					<%@include file="../comum/includeAquisicaoPasso4.jsp" %>

			 	</div>
			 </div>
			<%@include file="rodape.jsp" %> 
		</div>
	</body>
</html>
<script type="text/javascript">
	function proximaEtapa(){
		abreLink('aquisicaoPasso5');
	}
	function etapaAnterior(){
		abreLink('aquisicaoPasso3');
	}
</script>