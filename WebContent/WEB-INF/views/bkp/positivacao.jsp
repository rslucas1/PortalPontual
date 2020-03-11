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
    <title>Pontual | Positivação</title>

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
          	<div class="col-sm-12">
          		<H2 class="central-titulo">Consulta de Positivações</H2>
          	</div>
          </div>
          <div class="row">
          	<div class="col-sm-12">
          		<form action="posPositivados" method="post">
          			<select class="form-control form-control-sm select-mes inline" name="mesSelecionado">
  						<option value=1 selected>Janeiro</option>
  						<option value=2>Fevereiro</option>
  						<option value=3>Março</option>
  						<option value=4>Abril</option>
  						<option value=5>Maio</option>
  						<option value=6>Junho</option>
  						<option value=7>Julho</option>
  						<option value=8>Agosto</option>
  						<option value=9>Setembro</option>
  						<option value=10>Outubro</option>
  						<option value=11>Novembro</option>
  						<option value=12>Dezembro</option>
					</select>
					<select class="form-control form-control-sm select-ano inline" name="anoSelecionado">
  						<option value=2019>2019</option>
  						<option value=2020 selected>2020</option>
					</select>
					<div class="input-group mb-3 inline">
					 	<div class="input-group-prepend">
					 			<input class="btn btn-outline-secondary" type="submit" name="acionado" value="POSITIVADOS">
<!--				    		<button class="btn btn-outline-secondary" type="button">POSITIVADOS</button> -->
					    		<input class="btn btn-outline-secondary" type="submit" name="acionado" value="NÃO POSITIVADOS">
<!-- 					    	<button class="btn btn-outline-secondary" type="button">NÃO POSITIVADOS</button> -->
					 	</div>

					</div>

				</form>         		
          	</div>
          </div>
          
          
    		<div class="tabela table-responsive-sm">      
	          <table class="table table-bordered table-hover table-sm table-striped">
	          	<tr>
	          		<th>Cod</th>
	          		<th>Cliente</th>
	           	</tr>          
	          	<jsp:useBean id="dao" class="br.com.lucasdev.modelo.positivacao.Positivacao" />
	          	<c:forEach var="positivacao" items="${positivacao}">          	
	           	<tr>
	          		<td>${positivacao.cd_cliente}</td>
	          		<td>${positivacao.desc_cliente}</td>
	          		
	          	</tr>
	          	</c:forEach>	        
	          </table>
          	</div>
          
<!--           <div class="row"> -->
<!--           	<div class="col-sm-12 tabela-positivacao"  > -->
<!--        			<table class="table table-hover"> -->
<!-- 				  <thead> -->
<!-- 				    <tr> -->
<!-- 				      <th scope="col">#</th> -->
<!-- 				      <th scope="col">First</th> -->
<!-- 				      <th scope="col">Last</th> -->
<!-- 				      <th scope="col">Handle</th> -->
<!-- 				    </tr> -->
<!-- 				  </thead> -->
<!-- 				  <tbody> -->
<!-- 				    <tr> -->
<!-- 				      <th scope="row">1</th> -->
<!-- 				      <td>Mark</td> -->
<!-- 				      <td>Otto</td> -->
<!-- 				      <td>@mdo</td> -->
<!-- 				    </tr> -->
<!-- 				    <tr> -->
<!-- 				      <th scope="row">2</th> -->
<!-- 				      <td>Jacob</td> -->
<!-- 				      <td>Thornton</td> -->
<!-- 				      <td>@fat</td> -->
<!-- 				    </tr> -->
<!-- 				    <tr> -->
<!-- 				      <th scope="row">3</th> -->
<!-- 				      <td colspan="2">Larry the Bird</td> -->
<!-- 				      <td>@twitter</td> -->
<!-- 				    </tr> -->
<!-- 				  </tbody> -->
<!-- 				</table>          	 -->
<!--           	</div> -->
<!--           </div> -->
		</div>
      </div>    
    <c:import url="rodape.jsp" /> 
</body>
</html>