$(function(){
	$('#slides').cycle({ 
		fx:         'scrollLeft', 
		timeout:     3000, 
		pager:      '#nav', 
		fastOnEvent: false 
	});
	
	$("#btLogin").click(function(){
		var isEmailLoginPreenchido = $("#emailLogin").val(); 
		var isSenhaLoginPreenchido = $("#senhaLogin").val();
		if(isEmailLoginPreenchido && isSenhaLoginPreenchido){
			$(".usuarioForm").submit();
		} else {
			alert("Por favor, preencha login e senha.");
			$("#emailLogin").focus();
		}
	});
	
	$( "#areaRestrita input" ).keypress(function(e) {
		  console.log("aaa");
		  if(e.which == 13) {
			  $("#btLogin").trigger( "click" );
		  }
	});
	
});

function isNumber(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}

var abreLink = function(url){
	location.href=url;
}

function formataCNPJ (nuCNPJ){
	var parte1 = nuCNPJ.substring(0,2);
	var parte2 = nuCNPJ.substring(2,5);
	var parte3 = nuCNPJ.substring(5,8);
	var parte4 = nuCNPJ.substring(8,12);
	var parte5 = nuCNPJ.substring(12,14);
	return parte1+"."+parte2+"."+parte3+"/"+parte4+"-"+parte5;
}

function formataCPF (nuCPF){
	var parte1 = nuCPF.substring(0,3);
	var parte2 = nuCPF.substring(3,6);
	var parte3 = nuCPF.substring(6,9);
	var parte4 = nuCPF.substring(9,11);
	return parte1+"."+parte2+"."+parte3+"-"+parte4;
}

function formataCEP (nuCEP){
	var parte1 = nuCEP.substring(0,2);
	var parte2 = nuCEP.substring(2,5);
	var parte3 = nuCEP.substring(5,8);
	return parte1+"."+parte2+"-"+parte3;
}

function formataTelefone (nuTelefone){
	var parte1 = nuTelefone.substring(0,4);
	var parte2 = nuTelefone.substring(4,8);
	return parte1+"-"+parte2;
}


/*$("#slide1").click(function(){
	location.href="solucoesTI.php#servidores";
});
$("#slide2").click(function(){
	location.href="solucoesTI.php#pabx";
});
$("#slide3").click(function(){
	location.href="ensaMed1000B.php";
});*/

