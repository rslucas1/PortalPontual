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
			<div class="subPainel1100">
			<H3 class="user-btn-titulo">Filtros: Indústria</H3>
			
				<form action="tradeIn" method="post">
						<fieldset class="dropdownTradeIndset">
							<legend>Indústia</legend>
							    <select class="form-control" id="exampleFormControlSelect1" name="industria"  style="width:200px;">

							    
							      <option value="todas" selected>TODAS</option>
							    <jsp:useBean id="dao" class="br.com.lucasdev.modelo.relatorios.Industria" />
							    <c:forEach var="industria" items="${industria}">
							      <option value="${industria.cod_industria}">${industria.desc_industria}</option>

								</c:forEach>
							    </select>
						</fieldset>
						
						<fieldset>
						<legend>Segmento</legend>
							<table class="tradeInCategoria">
								<jsp:useBean id="dao4" class="br.com.lucasdev.modelo.relatorios.Segmento" />
								<c:forEach var="segmentosJdbc" items="${segmentosJdbc}">
								<tr>
									<td><input type="checkbox" class="form-check-input" name="segmento" value="${segmentosJdbc.cod_segmento}">
							    		<label class="form-check-label">${segmentosJdbc.desc_segmento}</label>
							    	</td>
								</tr>
								</c:forEach>
							</table>
						</fieldset>
						
						<fieldset class="dropdownTradeIndset">
							<legend>Equipe</legend>
							    <select class="form-control inline" id="exampleFormControlSelect1" name="equipe" style="width:300px;">
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
						</fieldset>
						
						
						<fieldset class="dropdownTradeIndset">
							<legend>Período</legend>
							   Inicio: <input id="date" type="date" name="dataInicial" required>					
								Fim:   <input id="date" type="date" name="dataFinal" required>
						</fieldset>
				
						<input class="btn btn-success" type="submit" value="APLICAR" style="margin-top:15px;"/>						
				
					</form>
			</div>

		</div>
	
	 </main>






	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>