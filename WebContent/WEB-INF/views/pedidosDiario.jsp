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
				<H3 class="central-titulo" style="margin-bottom:20px;">ENTRADA DE PEDIDOS DIÁRIO</H3>
				<div style="margin-bottom:20px;">
					<form method="post" action="pedDiario">
					
						
						<span class="inline">
							<select class="form-control" id="exampleFormControlSelect1" name="estado">
								<option value="ENTRADA" ${ES}>ENTRADA</option>
						   		<option value="FATURADO" ${FS}>FATURADO</option>
						   		
						   	</select>
						
						
						</span>
						<span class="inline">
						  	<span class="inline">  De: <input  class="form-control" id="date" type="date" name="dataInicial" value="${dataInicial}" required>		</span>			
							<span class="inline">	Até:   <input  class="form-control" id="date" type="date" name="dataFinal" value="${dataFinal}" required> </span>
							
								
							
							<input type="checkbox" class="form-check-input" value="checked" name="statusFaturado" ${checkFaturado} style="margin-left:20px;">
							<label>Faturado</label>
								
							<input type="checkbox" class="form-check-input inline" value="checked" name="statusAberto" ${checkAberto}>								
							<label>Aberto</label>
							
							<input class="btn btn-success" type="submit" name="operacao" value="Buscar" style="margin-left:10px;"/>
						</span>	
						
					
					</form>
				
				</div>
				
				<div class="pedDiadio-totGeral">
					<div>Valor Total: ${totalPedidos}</div>
					<div>Quantidade: ${qtdPedidos}</div>
				</div>
				
				<div class="pedidosDiarioTabela">
					<table class="table table-bordered table-hover table-sm table-striped tabela-planCob-Vend" style= "white-space: nowrap">
						<tr>
							<th>Empresa</th>
							<th>DT PEDIDO</th>
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
							<td>${pedidosdiario.dataPedido}</td>
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