			 	
<img alt="aquisicao-passo-0" src="/ProjetoOiSul/resources/images/topAquisicaoPasso2.jpg" class="topAquisicao" >

<h2 class="tituloAquisicao">Preencha o cadastro com os dados da empresa.</h2>

<form:form action="aquisicaoAdminPasso3" commandName="empresa">

<form:hidden path="idEmpresa"/>

	<table width="800px">
		<tr>
			<td class="rotulo" width="200px">Razão social*:</td> 
			<td>
	 		<form:input path="deRazaoSocial" class="form-control" placeholder="Ex: Xyz Alimentos LTDA" data-validation="required length" data-validation-length="max80"/>
			</td>
		</tr>
		<tr>
			<td class="rotulo">CNPJ*:</td>
			<td>
	 		<form:input path="nuCnpjFmt" class="form-control fmtCnpj" placeholder="Ex: 00.000.000/0000-00 ou 00000000000000" 
	 			data-validation="custom" data-validation-regexp="^\d{2}.\d{3}.\d{3}\/\d{4}-\d{2}$" style="width: 350px;"/>
			</td>
		</tr>
		<tr>
			<td class="rotulo">Representante legal*:</td>
			<td>
	 		<form:input path="nmRepLegal" class="form-control" placeholder="Ex: Paulo Barbosa" data-validation="required length" data-validation-length="max80"/>
			</td>
		</tr> 
		<tr>
			<td class="rotulo">CPF do representante legal*:</td>
			<td>
	 		<form:input path="nuCpfRepLegalFmt" class="form-control fmtCpf" placeholder="Ex: 000.000.000-00 ou 00000000000" 
	 			data-validation="custom" data-validation-regexp="^\d{3}.\d{3}.\d{3}-\d{2}$" style="width: 350px;"/>
			</td> 
		</tr>
		<tr>
			<td class="rotulo">Telefone Fixo*:</td>
			<td>
	 		<form:input path="nuDddFixo" class="form-control" placeholder="00" maxlength="2"
	 			data-validation="required number length" style="width: 50px; float: left;" data-validation-length="min2"/>
	 		<form:input path="nuTelFixoFmt" class="form-control fmtTelefone" placeholder="Ex: 0000-0000" maxlength="9"
	 			data-validation="custom" style="width: 300px;" data-validation-regexp="^\d{4}-\d{4}$"/>
			</td> 
		</tr>
		<tr>
			<td class="rotulo">Telefone Celular*:</td>
			<td>
	 		<form:input path="nuDddCelular" class="form-control" placeholder="00" maxlength="2"
	 			data-validation="required number length" style="width: 50px; float: left;" data-validation-length="min2"/>
	 		<form:input path="nuTelCelularFmt" class="form-control fmtTelefone" placeholder="Ex: 0000-0000" maxlength="9"
	 			data-validation="custom" style="width: 300px;" data-validation-regexp="^\d{4}-\d{4}$"/>
			</td> 
		</tr>
		<tr>
			<td class="rotulo">E-mail*:</td> 
			<td>
	 		<form:input path="deEmail" class="form-control" placeholder="Ex: nome@dominio.com" data-validation="email" />
			</td>
		</tr>
		<tr>
			<td class="rotulo">Gestor da conta*:</td>
			<td>
	 		<form:input path="nmGestorConta" class="form-control" placeholder="Ex: Paulo Barbosa" data-validation="required length" data-validation-length="max80"/>
			</td>
		</tr> 
		<tr>
			<td class="rotulo">CPF do gestor da conta*:</td>
			<td>
	 		<form:input path="nuCpfGestorContaFmt" class="form-control fmtCpf" placeholder="Ex: 000.000.000-00 ou 00000000000" 
	 			data-validation="custom" data-validation-regexp="^\d{3}.\d{3}.\d{3}-\d{2}$" style="width: 350px;"/>
			</td> 
		</tr>
		<tr>
			<td class="rotulo">E-mail do gestor da conta*:</td> 
			<td>
	 		<form:input path="deEmailGestorConta" class="form-control" placeholder="Ex: nome@dominio.com" data-validation="email" />
			</td>
		</tr>

	</table>
	
	<br>
	<h2 style="float: left;">Endereço da Empresa</h2><span class="destaque" style="padding-top: 22px; padding-left: 5px; float: left; font-style: italic;">(Deve ser o mesmo registrado na Receita Federal)</span>
	
	<table width="800px">
		<tr>
			<td class="rotulo" style="width: 200px;">Endereço(Rua/nº)*:</td>
			<td colspan="3">
	 		<form:input path="deEndereco" class="form-control" placeholder="Ex: Av. Atlântica, 255" data-validation="required length" data-validation-length="max80"/>
			</td> 
		</tr>
		<tr>
			<td class="rotulo" style="width: 200px;">Bairro*:</td>
			<td colspan="3">
	 		<form:input path="nmBairro" class="form-control" placeholder="Ex: Centro" data-validation="required length" data-validation-length="max80"/>
			</td>
		</tr>
		<tr>
			<td class="rotulo" style="width: 200px;">Municipio*:</td>
			<td>
	 		<form:input path="nmMunicipio" class="form-control" placeholder="Ex: Florianópolis" data-validation="required length" data-validation-length="max80"/>
			</td>
			<td class="rotulo" style="width: 100px;">UF*:</td>
			<td>
			<select id="deUf" name="deUf" class="form-control" style="width: 100%;">
			   <option value="SC">SC</option>
			   <option value="PR">PR</option>
			</select> 						 		
			</td> 
		</tr>
		<tr>
			<td class="rotulo">CEP*:</td>
			<td>
	 		<form:input path="nuCepFmt" class="form-control fmtCep" placeholder="Ex: 00.000-000 ou 00000000" 
	 			data-validation="custom" data-validation-regexp="^\d{2}.\d{3}-\d{3}$" style="width: 350px;"/>
			</td> 
		</tr>
	</table>	
		
	<br>
	<h2>Dados para Cobrança</h2>
	<table width="800px" id="dadosCobranca">
		<tr>
			<td class="rotulo" style="width: 200px;">
				<input type="checkbox" id="dadosCobrancaMesmoEmpresa" onchange="preecheDadosCobranca(this);"/>
			</td>
			<td colspan="3" style="padding: 8 0 10 0; text-decoration: underline;">
				<span style="font-size: 10pt; padding: 10px;">Preencher os dados de cobrança com os mesmos dados da empresa.</span>
			</td> 
		</tr>
		<tr>
			<td class="rotulo" style="width: 200px;">Endereço(Rua/nº)*:</td>
			<td colspan="3">
	 		<form:input path="deEnderecoCob" class="form-control" placeholder="Ex: Av. Atlântica, 255" data-validation="required length" data-validation-length="max80"/>
			</td> 
		</tr>
		<tr>
			<td class="rotulo" style="width: 200px;">Bairro*:</td>
			<td colspan="3">
	 		<form:input path="nmBairroCob" class="form-control" placeholder="Ex: Centro" data-validation="required length" data-validation-length="max80"/>
			</td>
		</tr>
		<tr>
			<td class="rotulo" style="width: 200px;">Municipio*:</td>
			<td>
	 		<form:input path="nmMunicipioCob" class="form-control" placeholder="Ex: Florianópolis" data-validation="required length" data-validation-length="max80"/>
			</td>
			<td class="rotulo" style="width: 100px;">UF*:</td>
			<td>
			<select id="deUf" name="deUfCob" class="form-control" style="width: 100%;">
			   <option value="SC">SC</option>
			   <option value="PR">PR</option>
			</select> 						 		
			</td> 
		</tr>
		<tr>
			<td class="rotulo">CEP*:</td>
			<td>
	 		<form:input path="nuCepCobFmt" class="form-control fmtCep" placeholder="Ex: 00.000-000 ou 00000000" 
	 			data-validation="custom" data-validation-regexp="^\d{2}.\d{3}-\d{3}$" style="width: 350px;"/>
			</td> 
		</tr>
		<tr>
			<td class="rotulo" width="200px">Dia do Vencimento da Fatura:</td>
			<td>
			<select id="nuDiaVencimento" name="nuDiaVencimento" class="form-control" style="width: 150px;" data-validation="required">
				   <option value="">-- Selecione --</option>
				   <option value="1">1</option>
				   <option value="4">4</option>
				   <option value="10">10</option>
				   <option value="14">14</option>
				</select> 						 		
				</td>
			</tr>
	</table>			
	
	</form:form> 

<BR><BR>
	
	<div id="navegacaoEtapasContratacao">
	<img alt="Etapa Anterior" src="/ProjetoOiSul/resources/images/btEtapaAnterior.jpg" 
		onclick="etapaAnterior();" class="btEtapaAnterior">
<img alt="Próxima Etapa" src="/ProjetoOiSul/resources/images/btProximaEtapa.jpg" 
	onclick="proximaEtapa();" class="btProximaEtapa">
</div>

<script type="text/javascript">
	function preecheDadosCobranca(_this){
		if($("#dadosCobrancaMesmoEmpresa:checked" ).length > 0){
			$("#dadosCobranca input").attr("disabled", "disabled");
			$("#dadosCobranca select").attr("disabled", "disabled");
		} else {
			$("#dadosCobranca input").removeAttr("disabled");
			$("#dadosCobranca select").removeAttr("disabled");
		}
		$("#dadosCobrancaMesmoEmpresa").removeAttr("disabled");
	}

	function salvarProximo(){
		$("#empresa").submit();
	}

$(function(){
	$.validate({lang : 'pt'}); 

	$(".fmtCnpj").keypress(function(){
		if(this.value.length == 14 && isNumber(this.value)){
			this.value = formataCNPJ(this.value);
		}
	});

	$(".fmtCpf").keypress(function(){
		if(this.value.length == 11 && isNumber(this.value)){
			this.value = formataCPF(this.value);
		}
	});

	$(".fmtCep").keypress(function(){
		if(this.value.length == 8 && isNumber(this.value)){
			this.value = formataCEP(this.value);
		}
	});

	$(".fmtTelefone").keypress(function(){
		if(this.value.length == 8 && isNumber(this.value)){
			this.value = formataTelefone(this.value);
		}
	});
});
</script>
