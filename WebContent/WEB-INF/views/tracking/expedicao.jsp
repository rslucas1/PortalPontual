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
		
			<div class="linha-detcli subPainel900">
				<div class="">
					<H2 class="">
						EXPEDIÇÃO</br>
					</H2>
					<form method="post" action="expedicaoCarregamento">
					
					</form>
					
						
				</div>
				

				<H4>${mensagemExpedicao1}</H4>
				<H4>${mensagemExpedicao2}</H4>
				<H4>${mensagemExpedicao3}</H4>
				
				<a href="roteiros" class="btn btn-warning btn-sm">Roteiros</a>
				
				<div class="table-responsive-sm" style="margin-top:10px;" >
				<table class="table table-bordered table-hover table-sm table-striped tabela-planCob-Vend"  style= "white-space: nowrap" >
					<tr>
						<th> DT ENTRADA </th>
						<th> PEDIDO </th>
						<th> CLIENTE </th>
						<th> VALOR </th>
						<th> NOTA  </th>
						<th> CARGA </th>
						<th> AGENDAMENTO</th>
								


					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.TrackingExpedicao" />
					<c:forEach var="detalheExpedicao"
						items="${detalheExpedicao}">
						<tr>
						
							<td class="alinhatexto"> ${detalheExpedicao.dtCadastro}</td>
							<td class="alinhatexto">${detalheExpedicao.nuPed}</td>
							<td>${detalheExpedicao.cliente}</td>
							<td class="alinhatexto">${detalheExpedicao.valorMoeda}</td>
							<td class="alinhatexto">${detalheExpedicao.notaFiscal}</td>
							<td><a target="_blank" class="linkTabela" href="romaneio?carga=${detalheExpedicao.carga}">${detalheExpedicao.carga}</a></td>
							
							<td><a  target="_blank" class="linkTabela" href="agendaEntrega?nuPed=${detalheExpedicao.nuPed}&amp;cliente=${detalheExpedicao.cliente}">&#9997;</a>${detalheExpedicao.ag_dt_Agedamento}</td>
								
						
							
						</tr>
					</c:forEach>
					
					
				</table>
				
				<h3>Pedidos Agendados</h3>
				
								<table class="table table-bordered table-hover table-sm table-striped tabela-planCob-Vend"  style= "white-space: nowrap" >
					<tr>
						<th> DT ENTRADA </th>
						<th> PEDIDO </th>
						<th> CLIENTE </th>
						<th> VALOR </th>
						<th> NOTA  </th>
						<th> CARGA </th>
						<th> AGENDAMENTO</th>
								


					</tr>
					<jsp:useBean id="dao2"
						class="br.com.lucasdev.modelo.relatorios.TrackingExpedicao" />
					<c:forEach var="pedidosAgendados"
						items="${pedidosAgendados}">
						<tr>
						
							<td class="alinhatexto"> ${pedidosAgendados.dtCadastro}</td>
							<td class="alinhatexto">${pedidosAgendados.nuPed}</td>
							<td>${pedidosAgendados.cliente}</td>
							<td class="alinhatexto">${pedidosAgendados.valorMoeda}</td>
							<td class="alinhatexto">${pedidosAgendados.notaFiscal}</td>
							<td><a target="_blank" class="linkTabela" href="romaneio?carga=${pedidosAgendados.carga}">${pedidosAgendados.carga}</a></td>
							
							<td><a  target="_blank" class="linkTabela" href="agendaEntrega?nuPed=${pedidosAgendados.nuPed}&amp;cliente=${pedidosAgendados.cliente}">&#9997;</a>${pedidosAgendados.ag_dt_Agedamento}</td>
								
						
							
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