<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Pontual | Plano de Cobertura</title>

<!-- Bootstrap -->
<link type="text/css" href="resources/css/bootstrap.min.css"
	rel="stylesheet">
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

			<div class="col-sm-12">
				<div class="row">
					<H3 class="central-relatorio">
						PLANO DE COBERTURA</br>
					</H3>
					<h4 class="central-relatorio">${vendedor.cd_venda} - ${vendedor.nome}
						${equipe.descEquipe} ${equipe.descGerencia}</h4>

				</div>

			</div>


			<div class="table-responsive-sm">
				<table class="tabPlanCobCli table-bordered table-hover table-sm ">
					<tr>
						<th>COD.</th>
						<th>DESCRIÇÃO</th>
						<th>${colunaMes.coluna01}</th>
						<th>${colunaMes.coluna02}</th>
						<th>${colunaMes.coluna03}</th>
						<th>${colunaMes.coluna04}</th>
						<th>${colunaMes.coluna05}</th>
						<th>${colunaMes.coluna06}</th>
						<th>${colunaMes.coluna07}</th>
						<th>${colunaMes.coluna08}</th>
						<th>${colunaMes.coluna09}</th>
						<th>${colunaMes.coluna10}</th>
						<th>${colunaMes.coluna11}</th>
						<th>${colunaMes.coluna12}</th>

					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.ColunasMesesBody" />
					<c:forEach var="planoDeCoberturaVenCli"
						items="${planoDeCoberturaVenCli}">
						<tr>

							<td><a href="detalhaCliente?cd_cliente=${planoDeCoberturaVenCli.cd_cliente}">${planoDeCoberturaVenCli.cd_cliente}</a></td>
							<td>${planoDeCoberturaVenCli.desc_cliente}</td>
							<td>${planoDeCoberturaVenCli.coluna01}</td>
							<td>${planoDeCoberturaVenCli.coluna02}</td>
							<td>${planoDeCoberturaVenCli.coluna03}</td>
							<td>${planoDeCoberturaVenCli.coluna04}</td>
							<td>${planoDeCoberturaVenCli.coluna05}</td>
							<td>${planoDeCoberturaVenCli.coluna06}</td>
							<td>${planoDeCoberturaVenCli.coluna07}</td>
							<td>${planoDeCoberturaVenCli.coluna08}</td>
							<td>${planoDeCoberturaVenCli.coluna09}</td>
							<td>${planoDeCoberturaVenCli.coluna10}</td>
							<td>${planoDeCoberturaVenCli.coluna11}</td>
							<td>${planoDeCoberturaVenCli.coluna12}</td>

						</tr>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>
	<c:import url="rodape.jsp" />
</body>
</html>