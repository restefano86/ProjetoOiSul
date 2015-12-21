<%@include file="msgs.jsp" %>	 	
<img alt="aquisicao-passo-0" src="/ProjetoOiSul/resources/images/topAquisicaoPasso3.jpg" class="topAquisicao" >

<h2 class="tituloAquisicao">Gere o contrato preenchido para assinatura.</h2>

<BR>
<div style="text-align: center; width: 100%">
	<p style="text-align: center;">Agora que voc� j� preencheu todos os dados necess�rios, voc� deve gerar os contratos para que os mesmo sejam assinados e retornados para o sistema na p�xima etapa.</p>
	<BR><BR>
	<input type="button" id="btGerarContratos" class="btn btn-primary" title="Gerar Contrato" value="Gerar Contrato" /> 
	<BR><br><br>
		
		<div style="font-size: 14pt">
			<c:if test="${not empty venda.documentosGerados}" >
				<span style="font-size: 12pt">
					A Venda foi gerada com sucesso. <br>
					Agora voc� deve fazer o download de todos os documentos abaixo, imprimi-los e assin�-los.<br>
					Ap�s ter feito isso, prossiga para a pr�xima etapa e siga as instru��es.<BR><BR>
				</span>
			</c:if>
		    <c:forEach items="${venda.documentosGerados}" var="docGerado">
		    	<a href="visualizarDocumentoVenda?idVendaDocumento=${docGerado.idVendaDocumento}" target="_blank">${docGerado.nmDocumento}</a><BR>
	    	</c:forEach>
	    	<c:if test="${not empty venda.documentosGerados}">
	    		<a href="/ProjetoOiSul/resources/files/termoPortabilidade.pdf"  target="_blank">termoPortabilidade.pdf</a>
	    	</c:if>
    	</div>
    	
    	
	<BR>
</div>

<BR><BR><BR>
<div id="navegacaoEtapasContratacao">
	<img alt="Etapa Anterior" src="/ProjetoOiSul/resources/images/btEtapaAnterior.jpg" 
		onclick="etapaAnterior();" class="btEtapaAnterior">
	<img alt="Pr�xima Etapa" src="/ProjetoOiSul/resources/images/btProximaEtapa.jpg" 
		onclick="proximaEtapa();" class="btProximaEtapa">
</div>

<script type="text/javascript">
$(function(){

	$("#btGerarContratos").click(function(){
		abreLink('geraContratoAdmin');
	});
	
})
</script>
