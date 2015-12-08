<html >
	<head>
		<title>OiSul - Planos empresariais da operadora Oi</title>
		<%@include file="includes.jsp"%> 
    </head>
	<body>
		<div id="site">
			<%@include file="cabecalho.jsp" %>
 			<div id="conteudo">
			 	<div id="corpo">
		 		<%@include file="msgs.jsp" %>
			 	<h2>Cadastro de usuários</h2>
			 		<form:form action="addUsuario" commandName="usuario">
			 		
			 		
				 		<BR>
				 		
				 			a:  <form:input path="lista[0].nome"/>
				 		<br>			 		
			 		
			 		</form:form>
			 	</div>
			 </div>
			<%@include file="rodape.jsp" %>  
		</div>
	</body>
	<script> $.validate({lang : 'pt'}); </script>
</html>
