<%@include file="preConteudo.jsp" %>
<%@include file="../comum/msgs.jsp" %>

<h2>:: Pedidos por consultor</h2>
<BR>
<p>Segue a situação de suas vendas nos últimos 30 dias:</p>




<table class="table table-striped">
	<tr>
		<th width="50px">Código</th>
		<th>Razão Social</th> 
		<th width="100px">Situação</th> 
		<th width="200px">Data</th> 
		<th width="50px;"></th>
	</tr> 
	<tr>
		<td><input type="text" class="form-control"/></td>
		<td><input type="text" class="form-control"/></td>
		<td><input type="text" class="form-control"/></td>
		<td><input type="text" class="form-control"/></td>
		<td style="text-align: center"><img src="/ProjetoOiSul/resources/images/icoPesquisa.gif"/></td>
	</tr> 
	 <c:forEach items="${listaVendas}" var="venda">
		<tr>
			<td>${venda.idVenda}</td>
			<td>${venda.empresa.deRazaoSocial}</td>
			<td>${venda.situacao.nmSituacao}</td> 
			<td>${venda.dtContratoGerado}</td> 
			<td><a href="/ProjetoOiSul/editarAquisicaoAdmin?idVenda=${venda.idVenda}">Editar</a></td>
		</tr> 
	 </c:forEach>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr> 
</table>
<%@include file="posConteudo.jsp" %>			 	

