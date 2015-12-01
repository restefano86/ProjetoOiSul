<%@include file="preConteudo.jsp" %>
<h2>:: Olá ${sessionScope.usuario.nome},</h2>
<BR>
<p>Segue a situação de suas vendas nos últimos 30 dias:</p>

<table class="table table-striped">
	<tr>
		<th width="20%">Quantidade</th>
		<th>Situação</th> 
	</tr>
	<tr>
		<td>5</td>
		<td>Pendentes</td>
	</tr>
	<tr>
		<td>3</td>
		<td>Confirmadas</td>
	</tr>
	<tr>
		<td>1</td>
		<td>Aprovadas</td>
	</tr>
	<tr>
		<td>1</td>
		<td>Abandonadas</td>
	</tr>
</table>
<%@include file="posConteudo.jsp" %>			 	

