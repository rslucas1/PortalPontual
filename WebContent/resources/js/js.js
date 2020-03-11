$(document).ready(function() {
 var botao = $('.bt1');
 var dropDown = $('.ul-consultas');    

    botao.on('click', function(event){
        dropDown.stop(true,true).slideToggle();
        event.stopPropagation();
    });
});

$(document).ready(function() {
 var botao = $('.bt2');
 var dropDown = $('.ul-relatorios');    

    botao.on('click', function(event){
        dropDown.stop(true,true).slideToggle();
        event.stopPropagation();
    });
});


$(document).ready(function() {
	 var botao = $('.bt3');
	 var dropDown = $('.ul-config');    

	    botao.on('click', function(event){
	        dropDown.stop(true,true).slideToggle();
	        event.stopPropagation();
	    });
	});


$(document).ready(function(){	
	$("#cnpj").mask("99.999.999/9999-99");
});

