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
			 	
				 	<img alt="aquisicao-passo-0" src="/ProjetoOiSul/resources/images/topAquisicaoPasso1.jpg" class="topAquisicao" >
				 	
				 	<h2 class="tituloAquisicao">Selecione quais servi�os deseja contratar</h2>
				 	
				 	<BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>
				 	<BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>
				 	
				 	<div id="navegacaoEtapasContratacao">
						<img alt="Etapa Anterior" src="/ProjetoOiSul/resources/images/btEtapaAnterior.jpg" 
							onclick="abreLink('aquisicaoPasso0');" class="btEtapaAnterior">
						<img alt="Pr�xima Etapa" src="/ProjetoOiSul/resources/images/btProximaEtapa.jpg" 
							onclick="abreLink('aquisicaoPasso2');" class="btProximaEtapa">
					</div>
			 	</div>
			 </div>
			<%@include file="rodape.jsp" %> 
		</div>
	</body>
</html>
