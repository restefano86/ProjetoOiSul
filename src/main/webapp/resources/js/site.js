$(function(){
	$('#slides').cycle({ 
		fx:         'scrollLeft', 
		timeout:     3000, 
		pager:      '#nav', 
		fastOnEvent: false 
	});
	
	$("#btLogin").click(function(){
		$(".usuarioForm").submit();
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

