<html>
	<head>
		<title>OiSul - Planos empresariais da operadora Oi</title>
		<%@include file="includes.jsp"%> 
	</head>
	<body>
		<div id="site">
			<%@include file="cabecalho.jsp" %>
 			<div id="conteudo">
			 	<div id="corpo">
			 	
			 		<%@include file="../comum/includeAquisicaoPasso2.jsp" %>
			 	
			 	</div>
			 </div>
			<%@include file="rodape.jsp" %> 
		</div>
	</body>
	<script>

		function salvarProximo(){
			$("#empresa").submit();
		}

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

		function proximaEtapa(){
			abreLink('aquisicaoPasso3');
		}
		function etapaAnterior(){
			abreLink('aquisicaoPasso1');
		}
	</script>
</html>
