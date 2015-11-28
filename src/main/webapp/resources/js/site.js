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

var abreLink = function(url){
	location.href=url;
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

