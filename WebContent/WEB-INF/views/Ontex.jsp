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
						Relatório ultimas saídas de produtos</br>
					</H3>
					<form method="post" action="analiseProdutos">
						
						PRODUTOS NÃO VENDIDOS HÁ <input name="quantidade" type="text" size="1"/> DIAS <button id="buscarItensNvendidos"type="submit" class="btn btn-info">Buscar</button>
						<div>
							
						</div>
					</form>

				</div>
				
				<div>
				<table class="table table-bordered table-hover table-sm table-striped" id="tabelaItensNvendidos">
					<tr>
						<th>Cod</th>
						<th>Descrição</th>
						<th>Fabricante</th>
						<th>Nº ult. Pedido</th>
						<th>dt ult. Pedido</th>
						<th>Estoque</th>
						<th>qtd. dias s/ venda</th>
						
					</tr>
					
					
					<jsp:useBean id="dao"
							class="br.com.ontex.Relatorio" />
							<c:forEach var="relatorio" items="${relatorio}">
						
						<tr>
							<td>${relatorio.cod}</td>
							<td>${relatorio.razao}</td>
							<td>${relatorio.cnpj}</td>
							<td>${relatorio.limite}</td>
							<td>${relatorio.recebimento}</td>
							<td>${relatorio.vlaberto}</td>
							<td>${relatorio.vlvencido}</td>
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