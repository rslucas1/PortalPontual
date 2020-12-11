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
						OBJETIVO VENDEDOR <a href="objetivoView"><button type="button" class="btn btn-warning">Modo Visualização</button><a></a></br></br>
					</H3>
				
				<form action="objetivo" method="post">
						<fieldset class="dropdownTradeIndset">
							<legend style="padding-left:20px;">Periodo de Apuração:</legend>
								<span style="padding-left:20px;">
								    Inicio: <input id="date" type="date" name="dataInicial" value="${dataInicial}" required>					
									Fim:   <input id="date" type="date" name="dataFinal" value="${dataFinal}" required>
								</span>
								
															
								<input class="btn btn-success" type="submit" value="Processar" style="margin-left:15px;"/>	
								
						</fieldset>
				</form>
									
			</div>


			<div class="table-responsive-sm" style="margin-top:15px;">
				<table style= "white-space: nowrap" class="tabela-objetivo table-bordered table-hover table-sm table-striped ">
					<tr>
						<th>EQUIPE</th>
						<th>COD</th>
						<th>VENDEDOR</th>
						<th>VL. AB</th>
						<th>VL. FA</th>
						<th>TOTAL</th>
						<th>PROJEÇÃO</th>
						<th>% ATINGIDO</th>
						<th>QTDE CLIENTES</th>
						<th>QTDE POSITIVADOS</th>
						<th>% POSITIVADO</th>
						<th>BIC</th>
						<th>CARTA FABRIL</th>
						<th>KIMBERLY</th>
						<th>BARUEL</th>
						<th>PHISALIA</th>
						<th>SKALA</th>
						<th>ALFAPARF</th>
						<th>EMBELLEZE</th>
						<th>BEAUTY COLOR</th>
						<th>HYPERA S/A</th>
						<th>STEVITA</th>
						<th>YPE</th>
						<th>APOLO</th>
						<th>SU BELLO</th>
					

					</tr>
					
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.ObjetivoVendedor" />
					<c:forEach var="objetivoVendedor" items="${objetivoVendedor}">
						<tr>
						<form action="objetivo?cd_venda=${objetivoVendedor.cd_venda}" method="post">
						
								    <input id="date" type="hidden" name="dataInicial" value="${dataInicial}">					
									<input id="date" type="hidden" name="dataFinal" value="${dataFinal}">						
							<td>${objetivoVendedor.equipe}</td>
							<td>${objetivoVendedor.cd_venda}</td>
							<td>${objetivoVendedor.nome}</td>
							
							<td>${objetivoVendedor.vl_aberto}</td>
							<td>${objetivoVendedor.vl_faturado}</td>
							<td>${objetivoVendedor.vl_total}</td>
							<td><input name="projecaoValor" value="${objetivoVendedor.vlObjetivo}" type="text" size="6"/> <input type="submit" class="btn btn-link" value="&#10003;" /></td>
							<td class="alinhatexto percentual-objetivo">${objetivoVendedor.perc_fat}%</td>
							<td class="alinhatexto">${objetivoVendedor.qdadeCarteira}</td>
							<td class="alinhatexto">${objetivoVendedor.positivacao}</td>
							<td class="alinhatexto percentual-objetivo">${objetivoVendedor.perc_positivado}%</td>
							
							<td>${objetivoVendedor.industria1}</td>
							<td>${objetivoVendedor.industria2}</td>
							<td>${objetivoVendedor.industria3}</td>
							<td>${objetivoVendedor.industria4}</td>
							<td>${objetivoVendedor.industria5}</td>
							<td>${objetivoVendedor.industria6}</td>
							<td>${objetivoVendedor.industria7}</td>
							<td>${objetivoVendedor.industria8}</td>
							<td>${objetivoVendedor.industria9}</td>
							<td>${objetivoVendedor.industria10}</td>
							<td>${objetivoVendedor.industria11}</td>
							<td>${objetivoVendedor.industria12}</td>
							<td>${objetivoVendedor.industria13}</td>
							<td>${objetivoVendedor.industria14}</td>
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