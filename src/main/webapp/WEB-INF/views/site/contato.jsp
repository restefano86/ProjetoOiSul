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
			 		<%@include file="msgs.jsp" %>
			 		<h2>Contato</h2>
			 		<form:form action="enviarContato" commandName="contato">
				 		<table width="800px">
				 			<tr>
				 				<td class="label">Nome*:</td>
				 				<td>
				 					<form:input path="nmContato" class="form-control" placeholder="Ex: Paulo da Costa" data-validation="required"/>
				 				</td>
				 			</tr>
				 			<tr>
				 				<td class="label">E-mail*:</td>
				 				<td>
				 					<form:input path="deEmail" class="form-control" placeholder="nome@dominio.com" data-validation="email"/>
			 					</td>
				 			</tr>
				 			<tr>
				 				<td class="label">Telefone:</td>
				 				<td>
				 					<form:input path="deTelefone" class="form-control" placeholder="(00) 0000-0000"/>
			 					</td>
				 			</tr>
				 			<tr>
				 				<td class="label">Produto:</td>
				 				<td>
									<select id="deProduto" name="deProduto" class="form-control" style="width: 300px;">
									   <option value="Não Selecionado">-- Selecione --</option>
									   <option value="Produto 1">Produto 1</option>
									   <option value="Produto 1">Produto 2</option>
									   <option value="Produto 1">Produto 3</option>
									</select> 						 		
			 					</td>
				 			</tr>
				 			<tr>
				 				<td class="label">Mensagem*:</td>
				 				<td>
				 					<textarea class="form-control" rows="10" id="deMensagem" name="deMensagem" style="height: 200px;"
				 					data-validation="required">
				 					</textarea>
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
