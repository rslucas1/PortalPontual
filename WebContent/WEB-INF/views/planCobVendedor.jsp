<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
	<c:import url="head.jsp" />
<body>
		<c:import url="cabecalho.jsp" />
		<c:import url="menuLateral.jsp" />

	
	<main>
		<div class="Painel">
			<div class="col-sm-12">
				<div class="row">
					<H3 class="central-relatorio">
						PLANO DE COBERTURA</br>
					</H3>
					<h4 class="central-relatorio">
					RCA: ${logado.cd_target}</h4>

				</div>

			</div>


			<div class="table-responsive-sm">
				<table class="tabela-planCob-Vend table-bordered table-hover table-sm ">
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

							<td><a class="" href="planCobCliente?cd_cliente=${planoDeCoberturaVenCli.cd_cliente}">${planoDeCoberturaVenCli.cd_cliente}</a></td>
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
							<td class="ultimaColuna">${planoDeCoberturaVenCli.coluna12}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	 </main>






	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>