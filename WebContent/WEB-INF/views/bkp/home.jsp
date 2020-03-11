<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Pontual | Home</title>

    <!-- Bootstrap -->
   		<link type="text/css" href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" href="resources/css/estilo.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<c:import url="cabecalho.jsp" />

  
      <div class="container">


        <div class="row painel-navegacao">

          <div class="row botoes-g1">
            <div class="col-sm-4 campo-botoes">
              <button type="button" class="btn btn-primary btn-lg botaocss">CONSULTA <br /> DE PEDIDOS</button>
            </div>
            <div class="col-sm-4 campo-botoes">
              <a href="consPositivacao">
               	<button type="button" class="btn btn-primary btn-lg botaocss">POSITIVAÇÕES</button>
              </a>
            </div>

            <div class="col-sm-4 campo-botoes">
              <a href="consEtapaPed">		
  				<button type="button" class="btn btn-primary btn-lg botaocss">ETAPA <br />DE PEDIDOS</button>
              </a>
            </div>        

          </div>

          <div class="row botoes-g2">
            <div class="col-sm-4 campo-botoes">
            <a href="relatorios">
              <button type="button" class="btn btn-primary btn-lg botaocss">RELATÓRIOS</button>
            </div>
            <div class="col-sm-4 campo-botoes">
              <button type="button" class="btn btn-primary btn-lg botaocss">DOWNLOADS</button>
            </div>

            <div class="col-sm-4 campo-botoes">
              <button type="button" class="btn btn-primary btn-lg botaocss">CONFIGURAÇÃO</button>
            </div>        

          </div>
        </div>
 

    </div>



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resources/js/bootstrap.min.js"></script>

   <c:import url="rodape.jsp" /> 
  </body>
</html>