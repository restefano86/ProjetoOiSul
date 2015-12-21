<%@include file="preConteudo.jsp" %>
<%@include file="msgs.jsp" %>	 
	<h2>:: Alteração de Senha</h2>
	<BR>
	<form:form action="alterarSenha" commandName="usuario">
				 		<table width="800px">
				 			<tr>
				 				<td style="text-align: right" >Senha antiga*:</td>
				 				<td>
				 					<input type="password"class="form-control" data-validation="required"/>
				 				</td>
				 			</tr>
				 			<tr>
				 				<td style="text-align: right" >Nova Senha*:</td>
				 				<td>
				 					<input type="password" class="form-control" data-validation="required"
				 						name="senha" id="senha"/>
				 				</td>
				 			</tr>
				 			<tr>
				 				<td style="text-align: right" >Confirmar Senha*:</td>
				 				<td>
				 					<input type="password"  class="form-control" data-validation="required"/>
				 				</td>
				 			</tr>
				 		</table>
						<br>
						<div style="text-align: center;">
							<p class="alignCenter">
								<input type="submit"  class="btn btn-primary"  title="Alterar Senha" value="Confirmar Alteração" />
							</p>
						</div> 
		</form:form>
<script> $.validate({lang : 'pt'}); </script>
<%@include file="posConteudo.jsp" %>	