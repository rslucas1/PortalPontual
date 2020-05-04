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
			<H3 class="user-btn-titulo">Filtros: Categoria</H3>
			
				<form action="tradeIn" method="post">
						<fieldset class="dropdownTradeIndset">
							<legend>Indústia</legend>
							    <select class="form-control" id="exampleFormControlSelect1" name="industria"  style="width:200px;" disabled="">

							    
							      
							    <jsp:useBean id="dao" class="br.com.lucasdev.modelo.relatorios.Industria" />
							    <c:forEach var="industria" items="${industria}">
							      <option value="${industria.cod_industria}">${industria.desc_industria}</option>
								<option value="todas">TODAS</option>
								</c:forEach>
							    </select>
						</fieldset>
					
							<legend>Segmento</legend>
								<div class="form-check">
							    	<input type="checkbox" class="form-check-input" name="segmento" value="ADULTO">
							    	<label class="form-check-label checkTradeIn">SEG ADULTO</label>
							    	
							    	<input type="checkbox" class="form-check-input" name="segmento" value="INFANTIL">
							    	<label class="form-check-label checkTradeIn">SEG INFANTIL</label>
							    	
							    	<input type="checkbox" class="form-check-input" name="segmento" value="LIMPEZA">
							    	<label class="form-check-label checkTradeIn">SEG LIMPEZA</label>
							    	
							    	<input type="checkbox" class="form-check-input" name="segmento" value="HIGIENE">
							    	<label class="form-check-label checkTradeIn">SEG HIGIENE</label>	
							    	
							       	<input type="checkbox" class="form-check-input" name="segmento" value="PERFUMARIA">
							    	<label class="form-check-label checkTradeIn">SEG PERFUMARIA</label>
							    	
							    	<input type="checkbox" class="form-check-input" name="segmento" value="ALIMENTOS">
							    	<label class="form-check-label checkTradeIn" >SEG ALIMENTOS</label>								    	
							  </div>
						</fieldset>
						
						<fieldset class="dropdownTradeIndset">
							<legend>Equipe</legend>
							    <select class="form-control inline" id="exampleFormControlSelect1" name="equipe" style="width:300px;" disabled="">
								   	<option value="${equipe.cdEquipe}">${equipe.descEquipe}</option>
								
								  </select>
						</fieldset>
						
						
						<fieldset class="dropdownTradeIndset">
							<legend>Período</legend>
							    Inicio: <input id="date" type="date" name="dataInicial" value="${dataInicial}" disabled="">					
								Fim:   <input id="date" type="date" name="dataFinal" value="${dataFinal}" disabled="">
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