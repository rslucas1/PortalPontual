<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<c:import url="../head.jsp" />

<body>

	
	<c:import url="../cabecalho.jsp" />
	<c:import url="../menuLateral.jsp" />

	
	<main>
		<div class="Painel">
		<div class="subPainel900">
		
			<div class="linha-detcli">
				<div class="">
					<H3 class="">
						Detalhe do Romaneio</br>
					</H3>
<!-- 					<form method="post" action="consCliente"> -->
<!-- 						<input name="cnpj" type="text" id="cnpj" placeholder="Digite o CNPJ"/> -->
						
<!-- 						<button type="submit" class="btn btn-info">Buscar</button> -->
<!-- 					</form> -->

				</div>
			</div>
				
			<div class="linha-detcli">
				<div class="inline">
					<div>Romaneio</div>
					<div>
						<input class="form-control form-control-sm " type="text" value="${romaneio.romaneio}" readonly size="12">
					</div>
				</div>
					
				<div class="inline">
					<div>Região</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value="${romaneio.regiao}"readonly size="40">
					</div>
				</div>
				
				<div class="inline">
					<div>data da montagem</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value="${romaneio.dt_montagem}"readonly size="19">
					</div>
				</div>
				
			</div>	
				
			<div class="linha-detcli">
				<div class="inline">
					<div>Motorista</div>
					<div>
						<input class="form-control form-control-sm " type="text" value="${romaneio.motorista}" readonly size="28">
					</div>
				</div>
					
				<div class="inline">
					<div>Veiculo</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value="${romaneio.veiculo}"readonly size="25">
					</div>
				</div>
				
				<div class="inline">
					<div>Capac. Kg</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value=""readonly size="5">
					</div>
				</div>
				
				<div class="inline">
					<div>Capac. M3</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value=""readonly size="5">
					</div>
				</div>
			</div>
				
			<div class="linha-detcli">
				<div class="inline">
					<div>Vl. Total Notas</div>
					<div>
						<input class="form-control form-control-sm " type="text" value="${romaneio.vlTotalNotasFormat}" readonly size="10">
					</div>
				</div>
					
				<div class="inline">
					<div>Qtdade Notas</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value="${romaneio.qtdeNotas}"readonly size="10">
					</div>
				</div>
				
				<div class="inline">
					<div>Peso</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value=""readonly size="5">
					</div>
				</div>
				
				<div class="inline">
					<div>Cubagem</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value=""readonly size="5">
					</div>
				</div>
				
				<button type="submit" class="btn btn-warning">Buscar Veículos Compatíveis</button>
				
			</div>
			
			<div class="linha-detcli">
				
				
				<div class="inline">
					<div>% Peso</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value=""readonly size="5">
					</div>
				</div>
				
				<div class="inline">
					<div>% Cubagem</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value=""readonly size="5">
					</div>
				</div>
				
				
				
			</div>
				
				<div class="pedidosDiarioTabela">
					<table class="table table-bordered table-hover table-sm table-striped tabela-planCob-Vend" style= "white-space: nowrap">
						<tr>
							<th>Pedido</th>
							<th>Dt_Pedido</th>
							<th>Cliente</th>
							<th>Empresa</th>
							<th>Nota</th>
							<th>Valor Nota</th>
						
						</tr>
						
						<jsp:useBean id="dao"
							class="br.com.lucasdev.modelo.relatorios.PedidosDiario" />
							<c:forEach var="itemRomaneio" items="${itemRomaneio}">
						
						<tr>
							<td>${itemRomaneio.numPedido}</td>
							<td>${itemRomaneio.dataPedido}</td>
							<td>${itemRomaneio.desc_cliente}</td>
							<td>${itemRomaneio.empresa}</td>
							<td>${itemRomaneio.nota}</td>
							<td>${itemRomaneio.vlMoeda}</td>
							
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