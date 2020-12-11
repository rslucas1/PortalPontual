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
		<div class=subPainel1200>
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
						    							
							
							<option value="PC35">MARCOS-LIT.SUL/V.RIB</option>
							<option value="PC40">VAGO - ZONA SUL</option>
							<option value="PC70">RAFAEL - Z.LESTE</option>
							<option value="PC90">EDSON - ZONA LESTE</option>
							<option value="PG45">SUP VAGO -OESTE</option>
							<option value="1200">MARCOS-ABC</option>
							<option value="1000">EDSON - AL-GUA	</option>
							<option value="1001">EDSON - ZN</option>
							<option value="RM02">RAFAEL KA</option>
					
						
						   </select>							
						</div>
						<div class="inline">
							<input class="btn btn-primary" type="submit" value="PROCESSAR" />
							
							<a href="teste" class="btn btn-warning btn">&DownArrowBar;</a>
							
							
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
						<th>CARTEIRA</th>
						<th>GERENTE</th>
						<th>INICIO</th>
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
							<td class="alinhatexto" style="color:red;">${planilhaDeSetores.qtdCliente}</td>
							<td>${planilhaDeSetores.nomeGerente}</td>
							<td>${planilhaDeSetores.dataInicio}</td>
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