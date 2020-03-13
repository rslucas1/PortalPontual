<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

		<div class="botoes-pos">
			<H3 class="botoes-pos">Descontos Financeiros</H3>
		</div>

		<div class="positivacoes">

			<div class="botoes-pos">
				<form action="descFinProcessado" method="post">

					<div class="inline">

						<select class="form-control form-control-sm select-mes"
							name="mesSelecionado">
							<option value=1>Janeiro</option>
							<option value=2>Fevereiro</option>
							<option value=3 selected>Março</option>
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
					</div>
					<div class="inline">
						<select class="form-control form-control-sm select-ano"
							name="anoSelecionado">
							<option value=2019>2019</option>
							<option value=2020 selected>2020</option>
						</select>
					</div>


					<div class="inline">
						<input class="btn btn-success" type="submit" name="BUSCA" value="busca" />
					</div>


				</form>
			</div>

			<div class="subPainel900">
				<h4>Total de desconto concedido:${descTotal}</h4>
			
				<table
					class="table table-bordered table-hover table-sm table-striped tabela-pos">
					<tr>
						<th>Pedido</th>
						<th>$ Nota</th>
						<th>$ desc</th>
						<th>% Desc</th>
						<th>Nu. Nota</th>
						<th>Emissão</th>
						<th>Cliente</th>
					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.DescontoFinanceiro" />
					<c:forEach var="descDetalhado" items="${descDetalhado}">
						<tr>
							<td>${descDetalhado.numPedido}</td>
							<td>${descDetalhado.vlTotalNota}</td>
							<td>${descDetalhado.vlDescFin}</td>
							<td>${descDetalhado.percDescFinan}</td>
							<td>${descDetalhado.numNota}</td>
							<td>${descDetalhado.emissaoNota}</td>
							<td>${descDetalhado.nomeCliente}</td>
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