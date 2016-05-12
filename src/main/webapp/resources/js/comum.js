var abreLink = function(url){
	location.href=url;
}

$(function(){
	$(".fmtNumber").keyup(function(){
		this.value = this.value.replace(/\D/g,'')
	});
	
	$(".fmtCnpj").keyup(function(){
		if(this.value.length == 14 && isNumber(this.value)){
			this.value = formataCNPJ(this.value);
		}
	});
	
	$(".fmtCpf").keyup(function(){
		if(this.value.length == 11 && isNumber(this.value)){
			this.value = formataCPF(this.value);
		}
	});
	
	$(".fmtCep").keyup(function(){
		if(this.value.length == 8 && isNumber(this.value)){
			this.value = formataCEP(this.value);
		}
	});
	
	$(".fmtTelefone").keyup(function(){
		if(this.value.length == 8 && isNumber(this.value)){
			this.value = formataTelefone(this.value);
		}
	});
});


function isNumber(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
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