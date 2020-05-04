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

		<div class="botoes-pos">
			<H3 class="botoes-pos">POSITIVAÇÕES</H3>
		</div>

		<div class="positivacoes">

			<div class="botoes-pos">
				<form action="posPositivados" method="post">

					<div class="inline">

						<select class="form-control form-control-sm select-mes"
							name="mesSelecionado">
							<option value=1>Janeiro</option>
							<option value=2>Fevereiro</option>
							<option value=3>Março</option>
							<option value=4 selected>Abril</option>
							<option value=5>Maio</option>
							<option value=6>Junho</option>
							<option value=7>Julho</option>
							<option value=8>Agosto</option>
							<option value=9>Setembro</option>
							<option value=10>Outubro</option>
							<option value=11>Novembro</option>
							<option value=12>Dezembro</option>
						</select>
					</div>
					<div class="inline">
						<select class="form-control form-control-sm select-ano"
							name="anoSelecionado">
							<option value=2019>2019</option>
							<option value=2020 selected>2020</option>
						</select>
					</div>


					<div class="inline">
						<input class="btn btn-primary" type="submit" name="acionado"
							value="POSITIVADOS" /> <input class="btn btn-danger"
							type="submit" name="acionado" value="NÃO POSITIVADOS" />
					</div>


				</form>
			</div>

			<div class="subPainel900">
				<table
					class="table table-bordered table-hover table-sm table-striped tabela-pos">
					<tr>
						<th>Cod</th>
						<th>Cliente</th>
					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.positivacao.Positivacao" />
					<c:forEach var="positivacao" items="${positivacao}">
						<tr>
							<td>${positivacao.cd_cliente}</td>
							<td>${positivacao.desc_cliente}</td>

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