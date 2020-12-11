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
				<form action="indFocoParConfig" method="post">
					 
					  <div class="form-group inline">
					    <div>
					    	<label for="exampleFormControlSelect1">Filtro</label>
					    </div>
					    	<div id="cxSelIndFoco" class="inline">	
							    <select class="form-control inline" id="exampleFormControlSelect1" name="industria">
							      <option value="null">GERAL</option>
							      <option value="kimberly">RCA</option>
							      <option value="ONTEX">RCA - ONTEX</option>
							      <option value="kimberly">REGIÕES - KC</option>
								  <option value="kimberly">***</option>
								  <option value="kimberly">***</option>
								  <option value="kimberly">***</option>
								  <option value="kimberly">***</option>
								  <option value="kimberly">***</option>
								  <option value="kimberly">***</option>
							    </select>
							 </div>
							 <span>
							     <input class="btn" type="submit" value="LISTAR" />
					 	 	</span>
						</div>
						
						 <div class="form-group inline">
					    <div>
					    	<label for="exampleFormControlSelect1">INDÚSTRIA</label>
					    </div>
					    	<div id="cxSelIndFoco" class="inline">	
							    <select class="form-control inline" id="exampleFormControlSelect1" name="industria">
								    <option value="TODAS">TODAS INDÚSTRIAS</option>
								    <option value="36511">ONTEX</option>
									<option value="KC">KIMBERLY</option>
									<option value="CARTA">CARTA FABRIL</option>
									<option value="LM">BIC</option>
									<option value="PH">PHISÁLIA</option>
									<option value="YPE">YPE</option>
									<option value="1.10">PAM PAM</option>
									<option value="1.12">SU BELLO</option>
									<option value="12499">BARUEL</option>	
							    </select>
							 </div>
							 <span>
							     <input class="btn btn-primary" type="submit" value="PROCESSAR" />
					 	 	</span>
						</div>
				 </form>
			<H3 class="user-btn-titulo">Vendedores Foco industria: ${industria}</H3>
								
				
				<div class="user-tabela">
					<table class="table table-bordered table-hover table-sm table-striped">
						<tr>
							<th>Cod</th>
							<th>Nome</th>
							<th>Equipe</th>
							<th>Foco</th>
							<th></th>

						</tr>
						
					<jsp:useBean id="dao"
					class="br.com.lucasdev.modelo.relatorios.IndFocoParVendedor" />
						<c:forEach var="vendedoresAtivos" items="${vendedoresAtivos}">
						
							<tr>
							<form action="indFocoParConfig?cd_venda=${vendedoresAtivos.cd_venda}" method="post">
								<td>${vendedoresAtivos.cd_venda}</td>
								<td>${vendedoresAtivos.nome}</td>
								<td>${vendedoresAtivos.equipe}</td>
								<td>
									<div class="custom-control custom-checkbox">
   										 <input type="checkbox" class="custom-control-input" value="true"c name="box">
    
									</div>
								</td>
								<td>
									
										<input type="submit" class="btn btn-outline-danger" name="operacao" value="editar" />
									
								</td>
							</form>
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