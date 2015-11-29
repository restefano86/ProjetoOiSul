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
			 	
				 	<img alt="aquisicao-passo-0" src="/ProjetoOiSul/resources/images/topAquisicaoPasso2.jpg" class="topAquisicao" >
				 	
				 	<h2 class="tituloAquisicao">Preencha o cadastro com os dados da empresa.</h2>
				 	
				 	
				 	
				 	
				 	
				 	
				 	<div id="navegacaoEtapasContratacao">
						<img alt="Etapa Anterior" src="/ProjetoOiSul/resources/images/btEtapaAnterior.jpg" 
							onclick="abreLink('aquisicaoPasso1');" class="btEtapaAnterior">
						<img alt="Próxima Etapa" src="/ProjetoOiSul/resources/images/btProximaEtapa.jpg" 
							onclick="abreLink('aquisicaoPasso3');" class="btProximaEtapa">
					</div>
			 	</div>
			 </div>
			<%@include file="rodape.jsp" %> 
		</div>
	</body>
</html>
