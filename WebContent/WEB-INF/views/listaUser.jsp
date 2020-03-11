<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<H3 class="user-btn-titulo">Lista Usuário</H3>
				
				<form  method="post" action="cadastraUsuario">
				<div class="linha-user">
					<div class="inline">
						<div>
							Código 
						</div>
						<div class="inline">
							<input name="codigo" class="form-control" type="text" size="2" readonly>
						</div>
					</div>
					<div class="inline">
						<div>
							Nome 
						</div>
						<div class="inline">
							<input name="nome" class="form-control" type="text" size="65" readonly>
						</div>
					</div>
					
					<div class="inline">
						<div>
							Codigo Target 
						</div>
						<div class="inline">
							<input name="cd_target" class="form-control" type="text" size="15">
						</div>
					</div>
					
				</div>
				
				<div class="linha-user">
					<div class="inline">
						<div>
							Email 
						</div>
						<div class="inline">
							<input name="email" class="form-control" type="text" size="65">
						</div>
					</div>
					
					
				</div>
				
				<div class="linha-user">
					<div class="inline">
						<div>
							Perfil 
						</div>
						<div class="inline">
							<input name="perfil" class="form-control" type="text" size="45">
						</div>
					</div>
					<div class="inline">
						<div>
							Data Cadastro
						</div>
						<div class="inline">
							<input name="dt_cadastro" class="form-control" type="text" size="24" readonly>
						</div>
					</div>
					
				</div>
				
				<div class="linha-user">
					<div class="inline">
						<div>
							Ativo 
						</div>
						<div class="inline">
							<input name="ativo" class="form-check-input form-control-lg" type="checkbox" id="inlineCheckbox1" value="option1">
						</div>
					</div>
					<div class="inline">
						<div>
							Prim. Acesso
						</div>
						<div class="inline">
							<input name="prim_acesso" class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">
						</div>
					</div>
					
					<div class="inline">
						<div class="inline">
							<button type="submit" class="btn btn-primary">Cadastrar</button>
						</div>
					</div>
					
				</div>
				</form>
				
			</div>
		</div>
	</main>






	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>