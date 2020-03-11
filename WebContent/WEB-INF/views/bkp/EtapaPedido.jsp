<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Pontual | Etapa Pedidos</title>

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
          <div class="row">
          
          	<div class="col-sm-6">
          		<H2 class="central-titulo ">Etapa de Pedidos</H2>
          	</div>
          	 <div class="col-sm-6">
          		<form action="consEtapaPed" method="post">
					<div class="input-group-prepend">
					 	<input class="btn btn-outline-secondary inline botao-Etapa" type="submit" name="acionado" value="Atualizar">
					</div>

				</form>         		
          	</div>
          </div>

          
    		<div class="tabela table-responsive-sm">      
	          <table class="table table-bordered table-hover table-sm table-striped">
	          	<tr>
	          		<th>Pedido</th>
	          		<th>Cliente</th>
	          		<th>Valor</th>
	          		<th>Fila</th>
	           	</tr>          
	          	<jsp:useBean id="dao" class="br.com.lucasdev.modelo.etapaPedidos.EtapaPedidoRelatorio" />
	          	<c:forEach var="relEtapaPedidos" items="${relEtapaPedidos}">          	
	           	<tr>
	          		<td>${relEtapaPedidos.pedido}</td>
					<td>${relEtapaPedidos.cliente}</td>
					<td>${relEtapaPedidos.valor}</td>
					<td>${relEtapaPedidos.fila}</td>
	          		
	          	</tr>
	          	</c:forEach>	        
	          </table>
          	</div>
          
		</div>
      </div>    
    <c:import url="rodape.jsp" /> 
</body>
</html>