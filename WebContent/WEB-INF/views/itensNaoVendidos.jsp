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
				<div class="">
					<H3 class="">
						Relat�rio ultimas sa�das de produtos</br>
					</H3>
					<form method="post" action="analiseProdutos">
						
						PRODUTOS N�O VENDIDOS H� <input name="quantidade" type="text" size="1"/> DIAS <button id="buscarItensNvendidos"type="submit" class="btn btn-info">Buscar</button>
						<div>
							
						</div>
					</form>

				</div>
				
				<div>
				<table class="table table-bordered table-hover table-sm table-striped" id="tabelaItensNvendidos">
					<tr>
						<th>Cod</th>
						<th>Descri��o</th>
						<th>Fabricante</th>
						<th>N� ult. Pedido</th>
						<th>dt ult. Pedido</th>
						<th>Estoque</th>
<!-- 						<th>qtd. dias s/ venda</th> -->
						
					</tr>
					
					
					<jsp:useBean id="dao"
							class="br.com.lucasdev.modelo.relatorios.ProdNaoVendidos" />
							<c:forEach var="itensNaoVendidos" items="${itensNaoVendidos}">
						
						<tr>
							<td>${itensNaoVendidos.cdProd}</td>
							<td>${itensNaoVendidos.descProd}</td>
							<td>${itensNaoVendidos.fabricante}</td>
							<td>${itensNaoVendidos.nUltimoPed}</td>
							<td>${itensNaoVendidos.dtUltPed}</td>
							<td>${itensNaoVendidos.estoque}</td>
<%-- 							<td>${itensNaoVendidos.qtdDiasSemVenda}</td> --%>
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