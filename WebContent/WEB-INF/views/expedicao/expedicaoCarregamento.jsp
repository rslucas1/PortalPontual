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
						Carregamento</br>
					</H2>
					<form method="post" action="expedicaoCarregamento">
					
						<input name="nu_romaneio" type="text" id="cnpj" value="${cargaAtual}" placeholder="Digite o nº da Carga" required/>
						
						<input class="btn btn-info" type="submit" name="operacao" value="Buscar" /> 
<!-- 						<button type="submit" class="btn btn-info">Buscar</button> -->
					</form>
					
					
					<H4>${mensagem}</H4>
					
					<form method="post" action="expedicaoCarregamento">
					 Data de saída da carga ${cargaAtual}: <input type="hidden" value="${cargaAtual}" name="carga" />
					 <input id="date" type="date" name="saidaCarga" value="" style="margin-top:30px;" required>
<!-- 					<button type="button" class="btn btn-success btn-sm">Efetiva</button> -->
						<input class="btn btn-success btn-sm" type="submit" name="operacao" value="Efetiva" /> 
					</form>
				</div>
				

				
				<H4>${mensagem3}</H4>
				<H4>${mensagem4}</H4>
				
				<div class="table-responsive-sm" style="margin-top:10px;" >
				<table class="table-bordered table-hover table table-striped" style= "white-space: nowrap" >
					<tr>
						<th> NOTA FISCAL </th>
						<th> PEDIDO </th>
						<th> CLIENTE </th>
						<th> VL NOTA </th>
						<th> SITUAÇÃO</th>
								


					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.ExpedicaoCarregamento" />
					<c:forEach var="expedicaoCarregamento"
						items="${expedicaoCarregamento}">
						<tr>

							
							
							<td class="alinhatexto"  style="font-weight: bold;">${expedicaoCarregamento.nota}</td>
							<td class="alinhatexto">${expedicaoCarregamento.nu_ped}</td>
							<td>${expedicaoCarregamento.cliente}</td>
							<td class="alinhatexto">${expedicaoCarregamento.vl_nota}</td>
							<td class="alinhatexto">${expedicaoCarregamento.situacao}</td>
						
							
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