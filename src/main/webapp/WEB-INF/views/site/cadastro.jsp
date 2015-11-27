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
		 		<div class="alert alert-warning" style="display: ${empty mensagemAviso ? 'none' : 'block'}">${mensagemAviso}</div>
			 	<h2>Cadastro de usuários</h2>
			 		<form:form action="addUsuario" commandName="usuario">
				 		<table width="800px">
				 			<tr>
				 				<td class="label">Nome*:</td>
				 				<td>
				 					<form:input path="nome" class="form-control" placeholder="E-mail" data-validation="required"/>
				 				</td>
				 			</tr>
				 			<tr>
				 				<td class="label">E-mail*:</td>
				 				<td>
				 					<form:input path="email" class="form-control" placeholder="nome@dominio.com" data-validation="email"/>
			 					</td>
				 			</tr>
				 			<tr>
				 				<td class="label">Senha*:</td>
				 				<td>
				 					<form:password path="senha" class="form-control" placeholder="" data-validation="required"/> 
				 				</td>
				 			</tr>
				 			<tr>
				 				<td class="label">Telefone*:</td>
				 				<td>
				 					<form:input path="ddd" class="form-control floatLeft" style="width: 50px;" placeholder="00" data-validation="number"/>
				 					<form:input path="telefone" class="form-control" style="width: 120px;" placeholder="0000-0000" data-validation="required"/>
				 				</td>
				 			</tr>
				 		</table>
						<br>
						<p class="alignCenter">
							<input type="submit"  class="btn btn-primary"  title="Confirmar Cadastro" />
						</p>
			 		</form:form>
			 	</div>
			 </div>
			<%@include file="rodape.jsp" %>  
		</div>
	</body>
	<script> $.validate({lang : 'pt'}); </script>
</html>
