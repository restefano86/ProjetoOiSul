<style>
	#divQtAcessos .rotulo{
		margin: 8 10 0 10;
		float: left;
	} 
	#divQtAcessos .fmtNumber{
		width: 60px; 
		float: left;
	} 
	.divVendaItem{
		display: table;
		width: 100%;
	}
	.divVendaItem *{
		float: left;  
		display: table;
		margin-left: 10px;
	}
	.spanItem{
		width: 40px;
		margin-top: 6px;
		font-size: 12pt;
		text-align: center;
	}
	#cabecalhoProdutos td{
		text-align: center;
	}
</style>			 	


<%@include file="msgs.jsp" %>
<img alt="aquisicao-passo-0" src="/ProjetoOiSul/resources/images/topAquisicaoPasso1.jpg" class="topAquisicao" >
<h2 class="tituloAquisicao">Os contratos de fixo estão em fase de teste. Por favor, utilize apenas se for solicitado.</h2>
<h2 class="tituloAquisicao">Selecione os serviços contratados.</h2>

<form:form action="aquisicaoAdminPasso2" commandName="venda">
	<input type="hidden" id="qtItens" value="${venda.qtItens}" />
	<input type="hidden" id="qtItensPortabilidade" value="${venda.qtItensPortabilidade}" />

	<div style="margin: 0 auto; text-align: center; display: table;" id="divQtAcessos">
		<span class="rotulo">Quantidade de Linhas?</span>
		 
		<input type="text" name="qtAcessos" id="qtAcessos" class="form-control fmtNumber" maxlength="3" value="${venda.qtItens}"/>
			
		<span class="rotulo">Quantas serão portados?</span> 
		
		<input type="text" name="qtAcessosPortados" id="qtAcessosPortados" class="form-control fmtNumber" maxlength="2" value="${venda.qtItensPortabilidade}"/>
		
		<br><BR><BR> 
		<input type="button" class="btn btn-success" id="btQtAcessos" value="GERAR LINHAS" style="width: 250px;"/>
		<BR><BR>
	</div>
		
	<table width="100%" id="cabecalhoProdutos" style="display: none;">  
		<tr>
			<td width="60px;">&nbsp;</td>
			<td width="295px;">Plano</td> 
			<td width="80px;">DDD&nbsp;</td>
			<td width="113px">Portabilidade</td> 
			<td width="150px">Nº Portado</td>
			<td width="110px;">Doadora</td> 
			<td width="115px">Internet</td> 
			<td width="*">&nbsp;</td>
		</tr>
	</table>
	
	<div id="componentes" style="display: none;">
		<div class="divVendaItem" >
			<span id="matrizNuItem" class="nuItem spanItem"></span>
			
			<select id="matrizSelectPlano" class="form-control selectPlano" style="width: 290px;">
			   <option value="">-- Selecione --</option>
			   <option value="7">Oi Mais Fixo Controle (Clientes Novos)</option>
			   <option value="8">Oi Mais Fixo Básico</option>
			   <option value="9">Oi Mais Fixo Avançado</option>
			   <option value="10">Oi mais Fixo Top</option>
			   <option value="22">SOMENTE INTERNET</option>
			</select> 						 		
			
			<select id="matrizSelectDdd" class="form-control selectDdd" style="width: 70px;">
			   <option value="">--</option>
			   <option value="41">41</option>
			   <option value="42">42</option>
			   <option value="43">43</option>
			   <option value="44">44</option>
			   <option value="45">45</option>
			   <option value="46">46</option>
			   <option value="47">47</option>
			   <option value="48">48</option>
			   <option value="49">49</option>
			</select> 						 		
	
			<select id="matrizPortabilidade" class="form-control selectPortabilidade" style="width: 110px;" disabled="disabled">
			   <option value="">--</option>
			   <option value="S">Sim</option>
			   <option value="N">Não</option>
			</select> 						 		
			
			<input type="text" id="matrizNuPortabilidade" class="form-control" style="width: 140px;" placeholder="0000-0000" max="9"/>
	
			<select id="matrizOperadora" class="form-control selectOperadora" style="width: 100px;">
			   <option value="">--</option>
			   <option value="4">GVT</option>
			   <option value="5">Net</option>
			   <option value="1">Tim</option>
			   <option value="2">Claro</option>
			   <option value="3">Vivo</option>
			</select> 	
			
			<select id="matrizSelectPlanoBL" class="form-control selectPlanoBL" style="width: 100px;">
			   <option value="">NÃO</option>
			   <option value="12">300 KBPS</option>
			   <option value="13">600 KBPS</option>
			   <option value="14">1 MB</option>
			   <option value="15">2 MB</option>
			   <option value="16">5 MB</option>
			   <option value="17">10 MB</option>
			   <option value="18">15 MB</option>
			   <option value="19">20 MB</option>
			   <option value="20">25 MB</option>
			   <option value="21">35 MB</option>
			</select> 						 		

		</div>
	</div>
	
	<div id="divAcessos" style="width: 100%;" >
	</div>
		
	<div id="navegacaoEtapasContratacao">
		<img alt="Etapa Anterior" src="/ProjetoOiSul/resources/images/btEtapaAnterior.jpg" 
			onclick="etapaAnterior();" class="btEtapaAnterior">
		<img alt="Próxima Etapa" src="/ProjetoOiSul/resources/images/btProximaEtapa.jpg" 
			onclick="proximaEtapa();" class="btProximaEtapa">
	</div>
</form:form>

<script type="text/javascript">
var copiaPlanoDemais, geraLinhasEdicao, atualizaLinhaValoresEdicao;
$(function(){
	
	$("#btQtAcessos").click(function(){
		var jaPossuiAcessosGerados = $("#divAcessos select").length > 0;
		if(jaPossuiAcessosGerados && !confirm("Já existem acessos gerados. Ao gerar novamente os acessos existentes serão apagados. Deseja continuar?")){
			return;
		}
		var qtItens = $("#qtAcessos").val();
		var qtItensPortabilidade = $("#qtAcessosPortados").val();
		if(!qtItens || !qtItensPortabilidade){ 
			alert("É necessário preencher a quantidade de acessos e a quantidade de acessos portados");
			return;
		}
		geraLinhasItensVendas(qtItens, qtItensPortabilidade);
		$("#cabecalhoProdutos").show();
	});

	function geraLinhasItensVendas(qtItens, qtItensPortabilidade){
		$("#divAcessos").html("");
		for(var i=0; i < qtItens; i++){
			var isPortabilidade = false;
			if( i < qtItensPortabilidade){
				isPortabilidade = true;
			}
			geraLinhaVendaItem(i, isPortabilidade);
		}
	}

	function selecionouMaisQueUmAcesso(){
		return $("#qtAcessos").val() > 1 ;
	}

	function geraLinhaVendaItem(indice, isPortabilidade) {
		var htmlDiv = '<div id="vendaItem['+indice+']" class="divVendaItem"></div>';
		$("#divAcessos").append($(htmlDiv));

		var nuItem = $("#matrizNuItem").clone();
		nuItem.html(indice+1);
		$("#vendaItem\\["+indice+"\\]").append(nuItem);
		
		var selectPlano = $("#matrizSelectPlano").clone();
		selectPlano.attr("id", "itens["+indice+"].idProduto");
		selectPlano.attr("name", "itens["+indice+"].idProduto");
		if(indice == 0){
			selectPlano.attr("onchange","copiaPlanoDemais();");
		}
		$("#vendaItem\\["+indice+"\\]").append(selectPlano);
		
		var selectDdd = $("#matrizSelectDdd").clone();
		selectDdd.attr("id", "itens["+indice+"].nuDdd");
		selectDdd.attr("name", "itens["+indice+"].nuDdd");
		if(indice == 0){
			selectDdd.attr("onchange","copiaDddDemais();");
		}
		$("#vendaItem\\["+indice+"\\]").append(selectDdd);
		
		var selectPortabilidade = $("#matrizPortabilidade").clone();
		selectPortabilidade.attr("id", "itens["+indice+"].flPortabilidade");
		selectPortabilidade.attr("name", "itens["+indice+"].flPortabilidade");
		if(isPortabilidade){
			selectPortabilidade.val("S");
		} else {
			selectPortabilidade.val("N");
		}
		$("#vendaItem\\["+indice+"\\]").append(selectPortabilidade);
		
		var nuPortabilidade = $("#matrizNuPortabilidade").clone();
		nuPortabilidade.attr("id", "itens["+indice+"].nuPortabilidadeFmt");
		nuPortabilidade.attr("name", "itens["+indice+"].nuPortabilidadeFmt");
		$("#vendaItem\\["+indice+"\\]").append(nuPortabilidade);
		if(!isPortabilidade){
			nuPortabilidade.attr("disabled", "disabled")
		} 
		
		var selectOperadora = $("#matrizOperadora").clone();
		selectOperadora.attr("id", "itens["+indice+"].idOperadora");
		selectOperadora.attr("name", "itens["+indice+"].idOperadora");
		$("#vendaItem\\["+indice+"\\]").append(selectOperadora);
		if(!isPortabilidade){
			selectOperadora.attr("disabled", "disabled")
		} 

		var selectPlanoBL = $("#matrizSelectPlanoBL").clone();
		selectPlanoBL.attr("id", "itens["+indice+"].idProdutoBL");
		selectPlanoBL.attr("name", "itens["+indice+"].idProdutoBL");
		$("#vendaItem\\["+indice+"\\]").append(selectPlanoBL);
		
	}

	geraLinhasEdicao = function(qtItens, qtPortabilidade){
		var qtItens = $("#qtItens").val();
		var qtItensPortabilidade = $("#qtItensPortabilidade").val();
		if(qtItens == 0){
			return; 
		}
		$("#cabecalhoProdutos").show();
		geraLinhasItensVendas(qtItens, qtItensPortabilidade);
		
		var indiceAtualizar = 0;
		$('#hiddensEdicao input').each(function(i){ 
			var idProduto = $(this).attr("idProduto");
			var nuDdd = $(this).attr("nuDdd");
			var flTipoChip = $(this).attr("flTipoChip");
			var flPortabilidade = $(this).attr("flPortabilidade");
			var nuPortabilidadeFmt = $(this).attr("nuPortabilidadeFmt");
			var idOperadora = $(this).attr("idOperadora");
			$("#itens\\["+indiceAtualizar+"\\]\\.idProduto").val(idProduto);
			$("#itens\\["+indiceAtualizar+"\\]\\.nuDdd").val(nuDdd);
			$("#itens\\["+indiceAtualizar+"\\]\\.flTipoChip").val(flTipoChip);
			$("#itens\\["+indiceAtualizar+"\\]\\.flPortabilidade").val(flPortabilidade);
			$("#itens\\["+indiceAtualizar+"\\]\\.nuPortabilidadeFmt").val(nuPortabilidadeFmt);
			$("#itens\\["+indiceAtualizar+"\\]\\.idOperadora").val(idOperadora);
			indiceAtualizar++;
        }); 
	}
	
	copiaPlanoDemais = function(){
		if(selecionouMaisQueUmAcesso() && confirm("Deseja preencher os demais acessos com esse mesmo plano?")){
			$(".selectPlano").val($("#itens\\[0\\]\\.idProduto").val());
		}
	}
	
	copiaDddDemais = function(){
		if(selecionouMaisQueUmAcesso() && confirm("Deseja preencher os demais acessos com esse mesmo DDD?")){
			$(".selectDdd").val($("#itens\\[0\\]\\.nuDdd").val());
		}
	}
	
	copiaChipDemais = function(){
		if(selecionouMaisQueUmAcesso() && confirm("Deseja preencher os demais acessos com esse mesmo Tipo de CHIP?")){
			$(".selectTipoChip").val($("#itens\\[0\\]\\.flTipoChip").val());
		}
	}

	geraLinhasEdicao();
});
</script>

<div id="hiddensEdicao">
	<c:forEach items="${venda.itens}" var="i">
		<input type="hidden" idProduto="${i.idProduto}" nuDdd="${i.nuDdd}" flTipoChip="${i.flTipoChip}" flPortabilidade="${i.flPortabilidade}" nuPortabilidadeFmt="${i.nuPortabilidadeFmt}" idOperadora="${i.idOperadora}"/>
	</c:forEach>
</div>

