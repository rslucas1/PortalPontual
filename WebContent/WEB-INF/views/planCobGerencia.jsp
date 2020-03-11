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
			<div class="col-sm-12">
				<div class="row">
					<H3 class="central-relatorio">
						PLANO DE COBERTURA - GERENCIA</br>
					</H3>
<%-- 					<h4 class="central-relatorio">${equipe.cdEquipe} - --%>
<%-- 						${equipe.descEquipe} ${equipe.descGerencia}</h4> --%>
				</div>
			</div>


			<div class="tabela-planCob-equipe table-responsive-sm">
				<table
					class="table table-bordered table-hover table-sm table-striped">
					<tr>
						<th>COD. EQUIPE</th>
						<th>DESCRICAO</th>
						<th>GERENTE</th>
					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.Equipe" />
					<c:forEach var="gerenciaEquipe" items="${gerenciaEquipe}">
						<tr>
							<td><a href="planCobEquipe?cdEquipe=${gerenciaEquipe.cdEquipe}">${gerenciaEquipe.cdEquipe}</a></td>
							<td>${gerenciaEquipe.descEquipe}</td>
							<td>${gerenciaEquipe.descGerencia}</td>
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