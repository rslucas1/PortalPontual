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
				<H3 class="user-btn-titulo">Usuários</H3>
								
				<div class="inline user-btn-novo">
					<a class="" href="newUser"><img class="img_addusr" src=<c:url value="/resources/img/addusr.png"/>/></a>		
				</div>
				
				<div class="inline">
					<input class="form-control user-input-buscar" type="text" placeholder="Pesquisa por nome" size="29">
				</div>
				
				<div class="inline">
					<button type="button" class="btn btn-secondary user-btn-novo">Buscar</button>
				</div>
				
				<div class="user-tabela">
					<table class="table table-bordered table-hover table-sm table-striped">
						<tr>
							<th>Cod</th>
							<th>Nome</th>
							<th>Email</th>
							<th>Cod. Target</th>
							<th>Perfil</th>
							<th>Ativo</th>
							<th>Acesso</th>
						</tr>
						
					<jsp:useBean id="dao"
					class="br.com.lucasdev.modelo.usuario.Usuario" />
						<c:forEach var="usuarios" items="${usuarios}">
						
							<tr>
								<td>
								<a href="listaUser?codigo=${usuarios.codigo}">
									${usuarios.codigo}</a>
								</td>
								<td>${usuarios.nome}</td>
								<td>${usuarios.email}</td>
								<td>${usuarios.cd_target}</td>
								<td>${usuarios.perfil}</td>
								<td>${usuarios.ativo}</td>
								<td>${usuarios.prim_acesso}</td>
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