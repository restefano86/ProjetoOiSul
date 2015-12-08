var abreLink = function(url){
	location.href=url;
}

$(function(){
	$(".fmtNumber").keyup(function(){
		this.value = this.value.replace(/\D/g,'')
	});
})

