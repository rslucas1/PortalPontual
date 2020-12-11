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
			<div class="subPainel1100">
				<H3 class="central-titulo">PEDIDOS DIÁRIO</H3>
				<div class="pedDiadio-totGeral">
					<div>Valor Total: ${totalPedidos}</div>
					<div>Quantidade: ${qtdPedidos}</div>
				</div>
				
				<div class="pedidosDiarioTabela">
					<table class="table table-bordered table-hover table-sm table-striped">
						<tr>
							<th>Empresa</th>
							<th>Pedido</th>
							<th>Cd Vend</th>
							<th>Vendedor</th>
							<th>Cd Cli</th>
							<th>Cliente</th>
							<th>Valor</th>
							<th>Situação</th>
						</tr>
						
						<jsp:useBean id="dao"
							class="br.com.lucasdev.modelo.relatorios.PedidosDiario" />
							<c:forEach var="pedidosdiario" items="${pedidosdiario}">
						
						<tr>
							<td>${pedidosdiario.empresa}</td>
							<td>${pedidosdiario.numPedido}</td>
							<td>${pedidosdiario.cdVendedor}</td>
							<td>${pedidosdiario.nomeVendedor}</td>
							<td>${pedidosdiario.cd_cliente}</td>
							<td>${pedidosdiario.desc_cliente}</td>
							<td>${pedidosdiario.valor}</td>
							<td>${pedidosdiario.situacao}</td>
						</tr>
							</c:forEach>
					</table>
				
				</div>
			
			</div>

		</div>
	
	 </main>


	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>