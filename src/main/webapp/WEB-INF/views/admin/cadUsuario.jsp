<%@include file="preConteudo.jsp" %>
<%@include file="msgs.jsp" %>
<h2>:: Cadastro de usuários</h2>
<BR>
	<form:form action="salvarUsuarioAdmin" commandName="usuario">
		<table width="800px" class="tableCadastro">
			<tr>
				<td class="rotulo">Codigo*:</td>
				<td>
					<form:input path="idUsuario" readonly="true" style="width: 100px;" class="form-control floatLeft"/>
					<span class="rotulo floatLeft" style="margin-left: 100px;">Ativo:</span>
					<form:checkbox path="isAtivado" class="form-control floatLeft" style="width:20px; height:30px;" 
						value="S"/>
				</td> 
			</tr>
			<tr>
				<td class="rotulo">Nome*:</td>
				<td>
					<form:input path="nome" class="form-control" placeholder="Ex: Paulo da Costa" data-validation="required"/>
				</td>
			</tr>
			<tr>
				<td class="rotulo">E-mail*:</td>
				<td>
					<form:input path="email" class="form-control" placeholder="nome@dominio.com" data-validation="email"/>
				</td>
			</tr>
			<tr>
				<td class="rotulo">Senha*:</td>
				<td>
					<form:input path="senha" class="form-control" placeholder="" data-validation="required"/> 
				</td>
			</tr>
			<tr>
				<td class="rotulo">Telefone*:</td>
				<td>
					<form:input path="ddd" class="form-control floatLeft" style="width: 50px;" placeholder="00" data-validation="number"/>
					<form:input path="telefone" class="form-control" style="width: 120px;" placeholder="0000-0000" data-validation="required"/>
				</td>
			</tr>
			<tr>
				<td class="rotulo">CPF*:</td>
				<td>
			 		<form:input path="nuCpfFmt" class="form-control fmtCpf" placeholder="Ex: 000.000.000-00 ou 00000000000" 
	 					data-validation="custom" data-validation-regexp="^\d{3}.\d{3}.\d{3}-\d{2}$" style="width: 350px;"/>
				</td>
			</tr>
			<tr>
				<td class="rotulo">Tipo de usuário*:</td>
				<td>
					<form:select path="tpUsuario"class="form-control" style="width: 290px;" data-validation="required">
						<form:option value="">-- Selecione --</form:option>
						<form:option value="C">Consultor</form:option>
						<form:option value="A">Administrador</form:option>
						<form:option value="B">Basico</form:option>
					</form:select>
				</td>
			</tr>
		</table>
	<br>
	<p class="alignCenter">
		<input type="submit"  class="btn btn-primary"  title="Confirmar Cadastro" value="Confirmar Cadastro" />
	</p>
	</form:form>
<%@include file="posConteudo.jsp" %>	

<script type="text/javascript">
	$(function(){
		$.validate({lang : 'pt'}); 
	});
</script>		 	

