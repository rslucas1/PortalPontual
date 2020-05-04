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
		<div class=subPainel900>
			<div class="col-sm-12">
				<div class="row">
					<H3 class="central-relatorio">
						PLANILHA DE SETORES</br></br>
					</H3>
				<div class="central-relatorio">	
					<form action="planilhaDeSetores" method="post">	
						<div class="inline">
						  	<label for="exampleFormControlSelect1">FILTRO: </label>
						</div>	
						<div id="cxSelIndFoco" class="inline">
						  <select class="form-control inline" id="exampleFormControlSelect1" name="filtro">
						   	<option value="GERAL">GERAL</option>
						    <option value="PC98">GERENCIA - RAFAEL ALVES</option>
						    <option value="PC02">GERENCIA - VAGNER SOUZA</option>
							
							
							<option value="PC35">EQUIPE - MARCOS-LIT.SUL/V.RIB</option>
							<option value="PC90">EQUIPE - SLAV- Z.LESTE(F)</option>
							<option value="PC70">EQUIPE - RAFAEL - Z. LESTE</option>
							<option value="PC40">EQUIPE - SOUZA - Z. OESTE</option>
							<option value="PG45">EQUIPE - VIDAL - ZONA SUL</option>
							<option value="1000">EQUIPE - EDSON-AL-GUA-ZN-ZL-V</option>
						   </select>							
						</div>
						<div class="inline">
							<input class="btn btn-primary" type="submit" value="PROCESSAR" />
						</div>
					</form>
				</div>
			</div>


			<div class="table-responsive-sm">
				<table style= "white-space: nowrap" class="tabela-planCob-Vend table-bordered table-hover table-sm table-striped ">
					<tr>
						<th>SUP</th>
						<th>EQUIPE</th>
						<th>COD</th>
						<th>TARGET</th>
						<th>NOME VENDEDOR</th>
						<th>INICIO</th>
						<th>GERENTE</th>
						<th>TELEFONE</th>
						<th>CPF</th>
						<th>ATIVO</th>
		
						


					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.PlanilhaDeSetores" />
					<c:forEach var="planilhaDeSetores"
						items="${planilhaDeSetores}">
						<tr>

							<td>${planilhaDeSetores.codigoSup}</td>
							<td>${planilhaDeSetores.nomeSupervisor}</td>
							<td>${planilhaDeSetores.cd_guerra}</td>
							<td>${planilhaDeSetores.cd_venda}</td>
							<td>${planilhaDeSetores.nome}</td>
							<td>${planilhaDeSetores.dataInicio}</td>
							<td>${planilhaDeSetores.nomeGerente}</td>
							<td>${planilhaDeSetores.telefone}</td>
							<td>${planilhaDeSetores.cpf}</td>
							<td>${planilhaDeSetores.ativo}</td>
							
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