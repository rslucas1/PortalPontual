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
	<div class="row tituloEtapa">
		<div class="col-sm-1"></div>
		<div class="col-sm-5">
			<H2 class="central-titulo ">Etapa de Pedidos</H2>
		</div>
		<div class="col-sm-5">
			<form action="consEtapaPed" method="post">
				<div class="input-group-prepend">
					<input class="btn btn-outline-secondary inline botao-Etapa"
						type="submit" name="acionado" value="Atualizar">
				</div>

			</form>
		</div>
	</div>


	<div class="tabelaEtapa table-responsive-sm">
		<table class="table table-bordered table-hover table-sm table-striped">
			<tr>
				<th>Pedido</th>
				<th>Data Pedido</th>
				<th>Cliente</th>
				<th>Valor</th>
				<th>Fila</th>
			</tr>
			<jsp:useBean id="dao"
				class="br.com.lucasdev.modelo.etapaPedidos.EtapaPedidoRelatorio" />
			<c:forEach var="relEtapaPedidos" items="${relEtapaPedidos}">
				<tr>
					<td>${relEtapaPedidos.pedido}</td>
					<td>${relEtapaPedidos.dtPedido}</td>
					<td>${relEtapaPedidos.cliente}</td>
					<td>${relEtapaPedidos.valor}</td>
					<td>${relEtapaPedidos.fila}</td>

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