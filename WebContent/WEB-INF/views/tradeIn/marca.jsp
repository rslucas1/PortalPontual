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
			<H3 class="user-btn-titulo">Filtros: Marca</H3>
			
				<form action="tradeIn" method="post">
						<fieldset class="dropdownTradeIndset">
							<legend>Indústia</legend>
							    <select class="form-control" id="exampleFormControlSelect1" name="industria"  style="width:200px;">

							    
							      
							    <jsp:useBean id="dao" class="br.com.lucasdev.modelo.relatorios.Industria" />
							    <c:forEach var="industria" items="${industria}">
							      
							      <option value="${industria.cod_industria}">${industria.desc_industria}</option>
							      <option value="todas">TODAS</option>
								
								</c:forEach>
							    </select>
						</fieldset>
					
						<fieldset>
						<legend>Segmento</legend>
							<table class="tradeInCategoria">
								<jsp:useBean id="dao4" class="br.com.lucasdev.modelo.relatorios.Segmento" />
								<c:forEach var="segmentosJdbc" items="${segmentosJdbc}">
								<tr>
									<td><input type="checkbox" class="form-check-input" name="segmento" value="true"${segmentosJdbc.ativo ? 'checked' : 'unchecked'} >
							    		<label class="form-check-label">${segmentosJdbc.desc_segmento}</label>
							    	</td>
								</tr>
								</c:forEach>
							</table>
						</fieldset>
						
						<fieldset class="dropdownTradeIndset">
							<legend>Equipe</legend>
							    <select class="form-control inline" id="exampleFormControlSelect1" name="equipe" style="width:300px;" >
								   	<option value="${equipe.cdEquipe}">${equipe.descEquipe}</option>
								
								  </select>
						</fieldset>
						
						
						<fieldset class="dropdownTradeIndset">
							<legend>Período</legend>
							    Inicio: <input id="date" type="date" name="dataInicial" value="${dataInicial}" >					
								Fim:   <input id="date" type="date" name="dataFinal" value="${dataFinal}" >
						</fieldset>
				
											
						<fieldset class="dropdownTradeIndset">
							<legend>Categoria</legend>
								<table class="tradeInCategoria">
									<jsp:useBean id="dao2" class="br.com.lucasdev.modelo.relatorios.Categoria" />
									<c:forEach var="getJdbccategorias" items="${getJdbccategorias}">
										<tr>
											<td><input type="checkbox" class="form-check-input" value="true"${getJdbccategorias.ativo ? 'checked' : 'unchecked'} >
									    		<label class="form-check-label">${getJdbccategorias.desc_categoria}</label>
									    	</td>
										</tr>
								
								
									</c:forEach>
								</table>
				
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