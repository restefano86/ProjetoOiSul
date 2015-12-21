<script src="/ProjetoOiSul/resources/js/bootstrap-filestyle.min.js" type="text/javascript"></script>
<%@include file="msgs.jsp" %>	
<img alt="aquisicao-passo-0" src="/ProjetoOiSul/resources/images/topAquisicaoPasso4.jpg" class="topAquisicao" >

<h2 class="tituloAquisicao">Envie o contrato assinado juntamente com a documentação necessária.</h2>


	<form action="adicionarVendaDocumentoAdmin" method="post" enctype="multipart/form-data">

	<div style="width: 600px; margin: 0 auto; text-align:center;">
		<input type="file" class="filestyle" id="file" name="file" data-buttonText="Selecionar Arquivo..." >
	</div>
	<div style="margin: 0 auto; text-align:center;">
		<br>
		<input type="submit" id="btGerarContratos" class="btn btn-primary" title="Enviar Arquivo" value="Enviar Arquivo" /> 
		<br>

		<br>Arquivos anexados ao pedido:<br>
		<div style="font-size: 14pt"> 
		    <c:forEach items="${venda.documentosInseridos}" var="docInserido">
		    	<a href="visualizarDocumentoVenda?idVendaDocumento=${docInserido.idVendaDocumento}" target="_blank">${docInserido.nmDocumento}</a><BR>
	    	</c:forEach>
    	</div>
    	
    	<BR><BR>
    	<br>Quando não houver mais arquivos para anexar, finalize a venda para dar encaminhamento no pedido.<br>
		<br>
		<input type="button" id="btFinalizarVenda" class="btn btn-primary" title="Finalizar Venda" value="Finalizar Venda" onclick="abreLink('finalizarVendaAdmin');" /> 
    </div> 
		
	</form>

<div id="navegacaoEtapasContratacao">
	<img alt="Etapa Anterior" src="/ProjetoOiSul/resources/images/btEtapaAnterior.jpg" 
		onclick="etapaAnterior();" class="btEtapaAnterior">
</div>

