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
			<div class="subPainel900">
				<H3 class="central-titulo" style="margin-bottom:20px;">DETALHE PEDIDOS</H3>
				<div style="margin-bottom:20px;">

				
				</div>
				
<!-- 				<div class="pedDiadio-totGeral"> -->
<%-- 					<div>Valor Total: ${totalPedidos}</div> --%>
<%-- 					<div>Quantidade: ${qtdPedidos}</div> --%>
<!-- 				</div> -->
				
				<div class="pedidosDiarioTabela">
					<table class="table table-bordered table-hover table-sm table-striped" style= "white-space: nowrap">
						<tr>
							<th>Empresa</th>
							<th>DT PEDIDO</th>
							<th>Pedido</th>
							<th>Cd Vend</th>
							
							
							<th>Cliente</th>
							<th>Valor</th>
						
						</tr>
						
						<jsp:useBean id="dao"
							class="br.com.lucasdev.modelo.relatorios.PedidosDiario" />
							<c:forEach var="detalhePedido" items="${detalhePedido}">
						
						<tr>
							<td>${detalhePedido.empresa}</td>
							<td>${detalhePedido.dataPedido}</td>
							<td>${detalhePedido.numPedido}</td>
							<td>${detalhePedido.cdVendedor}</td>
							
							
							<td>${detalhePedido.desc_cliente}</td>
							<td>${detalhePedido.valor}</td>
							
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