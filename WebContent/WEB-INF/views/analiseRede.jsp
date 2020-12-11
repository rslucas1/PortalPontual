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
<!-- 		/<div class=subPainel900> -->
			<div class="col-sm-12">
				<div class="row">
					<H3 class="central-relatorio">
						ANALISE FATURAMENTO DE REDE </br></br>
					</H3>
				
				<form action="analiseRede" method="post">
						<fieldset class="dropdownTradeIndset">
							<legend style="padding-left:20px;">Periodo de Apuração:</legend>
								<span style="padding-left:20px;">
								    Inicio: <input id="date" type="date" name="dataInicial" value="${dataInicial}" required>					
									Fim:   <input id="date" type="date" name="dataFinal" value="${hoje}" required>
								</span>
								
															
								<input class="btn btn-success" type="submit" name="operacao" value="Analise por Indústria" style="margin-left:15px;"/>	
								
						</fieldset>
				</form>
									
			</div>


			<div class="table-responsive-sm" style="margin-top:15px;">
				<table style= "white-space: nowrap" class="tabela-objetivo table-bordered table-hover table-sm table-striped ">
					<tr>
						<th>EQUIPE</th>
						<th>COD</th>
						<th>VENDEDOR</th>
						<th>REDE</th>
						<th>TD LOJA</th>
						<th>VL FATURADO</th>
						<th>VL ABERTO</th>
						<th>TOTAL</th>
						<th>KIMBERLY</th>
						<th>BIC</th>
						<th>CARTA FABRIL</th>
						<th>BARUEL</th>
						<th>PHISALIA</th>
						<th>SKALA</th>
						<th>ALFAPARF</th>
						<th>EMBELLEZE</th>
						<th>BEAUTY COLOR</th>
						<th>HYPERA S/A</th>
						<th>STEVITA</th>
						<th>PAMPAM</th>
						<th>YPE</th>
						<th>APOLO</th>
					

					</tr>
					
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.ColunasMesesBody" />
					<c:forEach var="redeVendedorFaturamento" items="${redeVendedorFaturamento}">
						<tr>
						<form action="objetivo?cd_venda=${objetivoVendedor.cd_venda}" method="post">
							
							<td>${redeVendedorFaturamento.descEquipe}</td>
							<td>${redeVendedorFaturamento.cdVendedor}</td>
							<td>${redeVendedorFaturamento.vendedor}</td>
							<td>${redeVendedorFaturamento.grupoCli}</td>
							<td>${redeVendedorFaturamento.numero}</td>
							<td class="alinhatexto" style="font-weight: bold;">${redeVendedorFaturamento.vlFaturado}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.vlAberto}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.vlTotal}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna01}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna02}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna03}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna04}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna05}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna06}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna07}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna08}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna09}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna10}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna11}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna12}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna13}</td>
							<td class="alinhatexto">${redeVendedorFaturamento.coluna14}</td>
						</form>
						</tr>
					</c:forEach>
					
				</table>
			</div>
		</div>
<!-- 		//</div> -->
	 </main>






	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>