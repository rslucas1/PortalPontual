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
				<H3 class="user-btn-titulo">CONFIGURAÇÃO DE FORNECEDOR</H3>
								
				<div class="inline user-btn-novo">
					<a class="" href="despesas/config"><img class="img_addusr" src=<c:url value="/resources/img/fornecedor.png"/>/></a>		
				</div>
												
				<div class="inline">
					<h6>Editar Fornecedor</h6>
				</div>
				
				<div class="user-tabela">
					<table class="table table-bordered table-hover table-sm table-striped">
						<tr>
							<th>Cod Target</th>
							<th>Fornecedor</th>
							<th>Estrutura</th>
							<th>Responsavel PG</th>
							<th>Vencimento</th>

						</tr>
						
					<jsp:useBean id="dao"
					class="br.com.lucasdev.modelo.relatorios.RelatorioDespesa" />
						<c:forEach var="fornecedores" items="${fornecedores}">
						
							<tr>
								
								<td>${fornecedores.cd_forn}</td>
								<td></td>
								<td>${fornecedores.estrutura}</td>
								<td>${fornecedores.responsavelPgtp}</td>
								<td>${fornecedores.vencimento}</td>
								
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