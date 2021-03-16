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
			<div class="col-sm-12">
				<div class="row">
					<H3 class="central-relatorio">
						CONFRONTO DE ESTOQUE</br>
					</H3>
				<div class="central-relatorio">	
					<form action="confrontoEstoque" method="post">	
						<div class="inline">
						  	<label for="exampleFormControlSelect1">ESTOQUE: </label>
						</div>	
						<div id="cxSelIndFoco" class="inline">
						  <select class="form-control inline" id="exampleFormControlSelect1" name="industria">
						    <option value="TODAS">CENTRAL</option>
						    <option value="FALCON">BLOQUEADO</option>
						    <option value="2">SHELF LIFE</option>
						   </select>							
						</div>
						<div class="inline">
							<input class="btn btn-primary" type="submit" value="PROCESSAR" />
						</div>
						<span>
							<input type="checkbox" class="form-check-input" value="checked" name="divertente" style="margin-left:20px;">
							<label>Somente Divergentes</label>
						</span>
					</form>
				</div>
			</div>


			<div class="table-responsive-sm">
				<table style= "white-space: nowrap" class="tabela-planCob-Vend table-bordered table-hover table-sm table-striped ">
					<tr>
						<th>CD_PROD</th>
						<th>DESCRIÇÃO</th>
						<th>FABRICANTE</th>
						<th>UNID.</th>
						<th>SPBROKER</th>
						<th>PONTUAL</th>
						<th> TOTAL</th>
						<th>EAN</th>
						<th>UNID.</th>
						<th>EST. WMS</th>
						<th>DIVERGENCIA</th>


					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.ConfrontoEstoque" />
					<c:forEach var="estoque"
						items="${estoque}">
						<tr>

							<td class="">${estoque.erpCodProd}</td>
							<td class="">${estoque.erpDescProd}</td>
							<td class="">${estoque.erpDescFacricante}</td>
							<td class="">${estoque.erpUnidEstoq}</td>
							<td class="">${estoque.erpEmpSpBroker}</td>
							<td class="">${estoque.erpEmpPontual}</td>
							<td class="colunaErp">${estoque.erpEmpTotal}</td>
							<td class="">${estoque.wmsEan}</td>
							<td class="">${estoque.wmsUn}</td>
							<td class="colunaWMS">${estoque.wmsTotal}</td>
							<td class="colunaDivergencia">${estoque.divergencia}</td>
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