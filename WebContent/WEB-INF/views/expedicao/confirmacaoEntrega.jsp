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
					<H3 class="">
						CONFIRMAÇÃO DE ENTREGA</br>
					</H3>
					
						<div class="">
							<form method="post" action="confirmacaoEntrega">
								<input name="nu_romaneio" type="text" id="cnpj" value="${cargaAtual}" placeholder="Digite o nº da Carga" required/>
								<input class="btn btn-info btn-sm" type="submit" name="operacao" value="Buscar" /> 
								
							</form>
						</div>	
						
						<div class="">											
							<form method="post" action="confirmacaoEntrega">
							 
								 Data da ENTREGA dos pedidos selecionados: <input type="hidden" value="${cargaAtual}" name="carga" />
								 <input id="date" type="date" name="dataEntrega" value="" style="margin-top:30px;" required>
								 <input class="btn btn-success btn-sm" type="submit" name="operacao" value="Confirma Entrega" /> 
							 
								 <div class="table-responsive-sm" style="margin-top:10px;" >
									<table class="table-bordered table-hover table table-striped" style= "white-space: nowrap" >
										<tr>
											<th> CARGA </th>
											<th> NOTA </th>
											<th> PEDIDO </th>
											<th> DT_CARREGAMENTO</th>
											<th> SITUAÇÃO</th>
											<th> CONF.</th>
										</tr>
										
										<jsp:useBean id="dao" class="br.com.lucasdev.modelo.relatorios.ExpedicaoCarregamento" /> 
										<c:forEach var="expedicaoCarregamento" items="${expedicaoCarregamento}"> 
 										<tr> 		
							
											<td class="alinhatexto">${expedicaoCarregamento.nu_romaneio}</td> 
											<td class="alinhatexto" style="font-weight: bold;">${expedicaoCarregamento.nota}</td> 
					 						<td class="alinhatexto">${expedicaoCarregamento.nu_ped}</td>
											<td class="alinhatexto">${expedicaoCarregamento.dataCarregamento}</td>
											<td class="alinhatexto">${expedicaoCarregamento.situacao}</td>
											<td class="alinhatexto"><input type="checkbox" class="form-check-input" value="${expedicaoCarregamento.nota}" name="conf" checked></td>
										</tr> 
 										</c:forEach> 
									</table>
								</div>		
							</form>
						</div>
					</div>
				
				


				
				
			</div>
			
			
				
			
			
		</div>
	 </main>






	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>