<%@include file="preConteudo.jsp" %>
<%@include file="../comum/msgs.jsp" %>

<h2>:: Pedidos por consultor</h2>
<BR>
<p>Por padrão, aqui estão listadas suas vendas dos últimos 30 dias. Para visualizar vendas anteriores altere o campo "Data" </p>
<BR>

<form:form action="consultarVendasConsultor" commandName="venda">
	<table class="table table-striped gridTextoMenor" >
		<tr>
			<th width="50px">Cód.</th>
			<th width="*">Razão Social</th> 
			<th width="100px">Situação</th> 
			<th width="130px">Data <span style="font-size: 10px;">(a partir de)</span></th> 
			<th width="50px;"></th>
		</tr> 
		<tr>
			<td><form:input path="idVenda" class="form-control"/></td>
			<td><form:input path="empresa.deRazaoSocial" class="form-control"/></td>
			<td><form:input path="situacao.deSituacao" class="form-control"/></td>
			<td><form:input path="dtContratoGeradoFmt" class="form-control" placeholder="00/00/0000"/></td>
			<td style="text-align: center"><img src="/ProjetoOiSul/resources/images/icoPesquisa.gif" class="icoPesquisa" onclick="forms[0].submit();"/></td>
		</tr>
		<c:forEach items="${listaVendas}" var="venda">
			<tr>
				<td class="tdTextoPequeno">${venda.idVenda}</td>
				<td class="tdTextoPequeno">${venda.empresa.deRazaoSocial}</td>
				<td class="tdTextoPequeno">${venda.situacao.nmSituacao}</td> 
				<td class="tdTextoPequeno">${venda.dtHrContratoGeradoFmt}</td>  
				<td class="tdTextoPequeno"><a href="/ProjetoOiSul/editarAquisicaoAdminProvisorio?idVenda=${venda.idVenda}">Editar</a></td>
			</tr> 
		 </c:forEach>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr> 
	</table>
	<c:if test="${empty listaVendas}">
		<center>Não há vendas para o período selecionado</center>
		<BR><BR><BR><BR>
	</c:if>
</form:form>

<%@include file="posConteudo.jsp" %>			 	

