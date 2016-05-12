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
	#cabecalhoProdutos{
		display: none;
	}
	#cabecalhoProdutos td{
		text-align: center;
	}
</style>			 	


<%@include file="msgs.jsp" %>
<img alt="aquisicao-passo-0" src="/ProjetoOiSul/resources/images/topAquisicaoPasso1.jpg" class="topAquisicao" >

<h2 class="tituloAquisicao">Selecione os serviços contratados.</h2>

<form:form action="aquisicaoAdminPasso2" commandName="venda">
	<input type="hidden" id="qtItens" value="${venda.qtItens}" />
	<input type="hidden" id="qtItensPortabilidade" value="${venda.qtItensPortabilidade}" />

	<div style="margin: 0 auto; text-align: center; display: table;" id="divQtAcessos">
		<span class="rotulo">Quantidade de Acessos?</span>
		
		<input type="text" name="qtAcessos" id="qtAcessos" class="form-control fmtNumber" maxlength="3" value="${venda.qtItens}"/>
			
		<span class="rotulo">Quantos serão portados?</span>
		
		<input type="text" name="qtAcessosPortados" id="qtAcessosPortados" class="form-control fmtNumber" maxlength="2" value="${venda.qtItensPortabilidade}"/>
		
		<br><BR><BR> 
		<input type="button" class="btn btn-success" id="btQtAcessos" value="GERAR ACESSOS" style="width: 250px;"/>
		<BR><BR>
	</div>
		
	<div id="componentes" style="display: none;">
		<div class="divVendaItem" >
			<span id="matrizNuItem" class="nuItem spanItem"></span>
			
			<select id="matrizSelectPlano" class="form-control selectPlano" style="width: 290px;">
			   <option value="">-- Selecione --</option>
			   <option value="1">Oi Mais Celular Controle</option>
			   <option value="2">Oi Mais Celular Controle Avançado</option>
			   <option value="23">Oi Mais Celular Controle Top</option>
			   <option value="3">Oi Mais Celular</option>
			   <option value="4">Oi Mais Celular Avançado</option>
			   <option value="5">Oi Mais Celular Top</option>
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
	
			<select id="matrizTipoChip" class="form-control selectTipoChip" style="width: 100px;">
			   <option value="">--</option>
			   <option value="C">Comum</option>
			   <option value="M">Micro</option>
			   <option value="N">Nano</option>
			</select> 						 		
			
			<select id="matrizPortabilidade" class="form-control selectPortabilidade" style="width: 110px;" disabled="disabled">
			   <option value="">--</option>
			   <option value="S">Sim</option>
			   <option value="N">Não</option>
			</select> 						 		
			
			<input type="text" id="matrizNuPortabilidade" class="form-control" style="width: 140px;" placeholder="0000-0000" max="9"/>
	
			<select id="matrizOperadora" class="form-control selectOperadora" style="width: 100px;">
			   <option value="">--</option>
			   <option value="1">Tim</option>
			   <option value="2">Claro</option>
			   <option value="3">Vivo</option>
			</select> 						 		
		</div>
	</div>
	
	<table width="100%" id="cabecalhoProdutos">  
		<tr>
			<td width="60px;">&nbsp;</td>
			<td width="290px;">Oferta</td> 
			<td width="80px;">DDD</td>
			<td width="115px">Chip</td> 
			<td width="118px">Portabilidade</td> 
			<td width="150px">Nº Portabilidade</td>
			<td width="110px;">Operadora</td> 
			<td width="*">&nbsp;</td>
		</tr>
	</table>				 		
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
		
		var selectTipoChip = $("#matrizTipoChip").clone();
		selectTipoChip.attr("id", "itens["+indice+"].flTipoChip");
		selectTipoChip.attr("name", "itens["+indice+"].flTipoChip");
		if(indice == 0){
			selectTipoChip.attr("onchange","copiaChipDemais();");
		}
		$("#vendaItem\\["+indice+"\\]").append(selectTipoChip);
		
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
		if(confirm("Deseja preencher os demais acessos com esse mesmo plano?")){
			$(".selectPlano").val($("#itens\\[0\\]\\.idProduto").val());
		}
	}
	
	copiaDddDemais = function(){
		if(confirm("Deseja preencher os demais acessos com esse mesmo DDD?")){
			$(".selectDdd").val($("#itens\\[0\\]\\.nuDdd").val());
		}
	}
	
	copiaChipDemais = function(){
		if(confirm("Deseja preencher os demais acessos com esse mesmo Tipo de CHIP?")){
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

