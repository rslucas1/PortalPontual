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
					<H3 class="central-relatorio">PLANO DE COBERTURA</br></H3>
					<h4 class="central-relatorio"> ${equipe.cdEquipe} - ${equipe.descEquipe} ${equipe.descGerencia}</h4>

				</div>

			</div>


			<div class="tabela table-responsive-sm">
				<table
					class="table table-bordered table-hover table-sm table-striped">
					<tr>
						<th>COD. VENDEDOR</th>
						<th>NOME COMPLETO</th>
						<th>COD. GUERRA</th>
					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.Vendedor" />
					<c:forEach var="vendedoresEquipe" items="${vendedoresEquipe}">
						<tr>
							<td>
								<a href="${vendedoresEquipe.cd_venda}" >${vendedoresEquipe.cd_venda}</a>
							</td>
							<td>${vendedoresEquipe.nome}</td>
							<td>${vendedoresEquipe.cd_guerra}</td>


						</tr>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>
	<c:import url="rodape.jsp" />
</body>
</html>