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
			 		<h2>Confirma��o de usu�rio</h2>
			 		
			 		<h3 style="margin-top: 50px; text-align: center;">Seu usu�rio foi confirmado com sucesso.</h3>
			 		<h3 style="margin-top: 50px; text-align: center;">Agora voc� j� pode contratar os planos empresariais da Oi.</h3>
			 		
			 		<div style="width: 100%; text-align: center; margin-top: 50px; margin-bottom: 100px;">
				 		<input type="button" class="btn btn-primary btn-danger" title="Planos de Telefonia M�vel" value="Planos de Telefonia M�vel" onclick='abreLink("./solucoesMovel")' /> 
				 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 		<input type="button" class="btn btn-primary btn-danger" title="Planos de Telefonia Fixa" value="Planos de Telefonia Fixa" onclick='abreLink("./solucoesFixo")'/> 
			 		</div>
			 		
			 	</div>
			 </div>
			<%@include file="rodape.jsp" %> 
		</div>
	</body>
</html>
