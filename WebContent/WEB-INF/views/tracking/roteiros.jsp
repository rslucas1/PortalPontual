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
						Roteiros</br>
					</H3>
				</div>
			</div>

				
				<div class="pedidosDiarioTabela">
					<table class="table table-bordered table-hover table-sm table-striped tabela-planCob-Vend" style= "white-space: nowrap">
						<tr>
							<th>Carga</th>
							<th>Regiao</th>
							<th>Vl. Nota</th>
							<th>Qtde Notas</th>
							<th>Veiculo</th>
							<th>Motorista</th>
							
						
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