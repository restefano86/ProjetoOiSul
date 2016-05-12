<%@include file="preConteudo.jsp" %>
<%@include file="../comum/msgs.jsp" %>
<h2>:: Consulta de usuários</h2>
<BR>
<form:form action="consultarUsuarios" commandName="usuario">
	
	<div class="btsConsulta">
		<input type="button" id="btGerarContratos" class="btn btn-primary" title="Limpar Consulta" value="Limpar Consulta" onclick="abreLink('abrirConUsuarios');"/>
		<input type="button" id="btGerarContratos" class="btn btn-success" title="Cadastrar Usuário" value="Cadastrar Usuário" onclick="abreLink('abrirCadUsuario');"/>
	</div>

	<table class="table table-striped">
		<tr>
			<th width="300px">Nome</th>
			<th width="100px">Email</th> 
			<th width="150px">Telefone</th> 
			<th width="150px">Perfil</th>  
			<th width="30px"></th>  
		</tr>
		<tr>
			<td>
				<form:input path="nome" class="form-control" placeholder="Ex: Nome" data-validation=""/>
			</td>
			<td>
				<form:input path="email" class="form-control" placeholder="Ex: usuario@dominio.com" data-validation=""/>
			</td>
			<td>
				<form:input path="telefone" class="form-control" placeholder="Ex: 99999999" data-validation=""/>
			</td>
			<td>
				<form:select path="tpUsuario" class="form-control" >
					<form:option value="">Selecione</form:option>
					<form:option value="A">Administrador</form:option>
					<form:option value="C">Consultor</form:option>
					<form:option value="B">Comum</form:option>
				</form:select>
			</td>
			<td style="text-align: center"><img src="/ProjetoOiSul/resources/images/icoPesquisa.gif" class="icoPesquisa" onclick="forms[0].submit();"/></td>
		</tr>
		<c:forEach items="${listaUsuarios}" var="usuario">
			<tr>
				<td>${usuario.nome}</td>
				<td>${usuario.email}</td>
				<td>(${usuario.ddd})${usuario.telefone}</td>
				<td>${usuario.tpUsuarioFmt}</td>
				<td style="text-align: center"><img src="/ProjetoOiSul/resources/images/icoEdicao.gif" class="icoEdicao" onclick="abreLink('editarUsuarioAdmin?idUsuario=${usuario.idUsuario}')"/></td></td>
			</tr>
		</c:forEach>
	</table>
</form:form>



<%@include file="posConteudo.jsp" %>			 	

