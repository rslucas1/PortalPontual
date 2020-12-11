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
			<div class="" style="padding:20px;">
			<H3 class="central-relatorio">Relatório Operacional</H3>
			
				<form action="formacaoCarga" method="post">

						
						<fieldset class="dropdownTradeIndset">
							<legend style="padding-left:20px;">Data de entrada do pedido:</legend>
								<span>
								    Inicio: <input id="date" type="date" name="dataInicial" value="${dataInicial}" required>					
									Fim:   <input id="date" type="date" name="dataFinal" value="${dataFinal}" required>
								</span>
								
<!-- 									Status Entrega:<select class="form-control inline" id="exampleFormControlSelect1" name="statusEntrega"  style="width:200px;"> -->
<!-- 									     <option value="todas" selected>TODAS</option> -->
<%-- 										    <jsp:useBean id="dao2" class="br.com.lucasdev.modelo.relatorios.CodDescricao" /> --%>
<%-- 										    <c:forEach var="statusEntrega" items="${statusEntrega}"> --%>
<%-- 									      <option value="${statusEntrega.codigo}">${statusEntrega.descricao}</option>	 --%>
<%-- 											</c:forEach> --%>
<!-- 								    </select> -->
								
								<input class="btn btn-success" type="submit" value="Buscar" style="margin-left:10px;"/>	
								
<!-- 								<input class="btn btn-warning" type="submit"  name="operacao" value="rel expedicao" style="margin-left:100px;" />	 -->
<!-- 								<input class="btn btn-warning" type="submit"  name="operacao" value="carregamento"/> -->
<!-- 								<input class="btn btn-warning" type="submit"  name="operacao" value="confirmacao"/> -->
								
								<a href="expedicaoRelatorio"  class="btn btn-warning" style="margin-left:100px;">Rel. Expedição</a>
								<a href="expedicaoCarregamento"  class="btn btn-warning">Carregamento</a>
								<a href="confirmacaoEntrega"  class="btn btn-warning">Confirmacao</a>
								
						</fieldset>
				
											
					
					
					</form>
					
					
					
			<div class="table-responsive-sm" style="margin-top:10px;" >
				<table class="tabela-planCob-Vend table-bordered table-hover table-sm " style= "white-space: nowrap" >
					<tr>
						<th>DT. PEDIDO</th>
						<th>INIC. SEPARAÇÃO</th>
						<th>Nº PEDIDO</th>
						<th>NOTA</th>
						<th>CARGA</th>
						<th>STATUS MOTORISTA</th>
						<th>MOTORISTA</th>
						<th>BAIXA ERP</th>
						<th>DISTRITO</th>
						<th>BAIRRO</th>
						<th>CARRO</th>
						<th>FRETE</th>
					
						


					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.PedidoProducao" />
					<c:forEach var="pedidoProducao"
						items="${pedidoProducao}">
						<tr>

							
							<td>${pedidoProducao.dt_entrada}</td>
							<td class="alinhatexto">${pedidoProducao.dt_inic_sepa}</td>
							<td>${pedidoProducao.nu_pedido}</td>
							<td>${pedidoProducao.nota}</td>
							<td class="alinhatexto">${pedidoProducao.roteiro}</td>
							<td>${pedidoProducao.status_entrega}</td>
							<td>${pedidoProducao.motorista}</td>
							<td>${pedidoProducao.dt_baixaErp}</td>
							<td>${pedidoProducao.distrito}</td>
							<td>${pedidoProducao.bairro}</td>
							<td>${pedidoProducao.carro}</td>
							<td>${pedidoProducao.frete}</td>
							
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