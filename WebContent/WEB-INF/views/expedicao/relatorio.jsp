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
			<div class="" style="padding:20px;">
			<H2 class="central-relatorio">RELATORIO EXPEDIÇÃO CONSOLIDADO</H2>
			
				<form action="expedicaoRelatorio" method="post">

						
						<fieldset class="dropdownTradeIndset">
							<legend style="padding-left:20px;">Data de entrada do pedido:</legend>
								<span>
								    Inicio: <input id="date" type="date" name="dataInicial" value="${dataInicial}" required>					
									Fim:   <input id="date" type="date" name="dataFinal" value="${dataFinal}" required>
								</span>
								
								<input class="btn btn-success" type="submit" name="operacao" value="Buscar" style="margin-left:10px;"/>	
								<input class="btn btn-danger" type="submit" name="operacao" value="Atrasados" style="margin-left:10px;"/>
								
								<input type="checkbox" class="form-check-input" value="pendente" name="statusPendente" ${checkPendente} style="margin-left:20px;">
								<label>Pendentes</label>
								
								<input type="checkbox" class="form-check-input inline" value="OK" name="statusConcluido" ${checkConcluido}>								
								<label>Concluídos</label>
					
								
								<label style="margin-left:20px;">Ordenação</label>
							<div id="cxSelIndFoco" class="inline">	
							    <select class="form-control inline" id="exampleFormControlSelect1" name="ordenacao">
							      <option value="${codOrdenacao}" selected>${descOrdenacao}</option>
							      <option value="1">PEDIDO</option>
							      <option value="26">DISTRITO</option>
							      <option value="27">BAIRRO</option>
							      <option value="25">CEP</option>
							    </select>
							 </div>
								

								
						</fieldset>
				
											
					
					
					</form>
					
					
					
			<div class="table-responsive-sm" style="margin-top:10px;" >
				<table class="tabela-planCob-Vend table-bordered table-hover table-sm " style= "white-space: nowrap" >
					<tr>
						<th>PEDIDO</th>
						<th>CLIENTE</th>
						<th>TIPO DE PEDIDO</th>
						<th>DT PEDIDO</th>
						<th>INT</th>
						<th>ENTRADA WMS</th>
						<th>INT</th>
						<th>DT FATURAMENTO</th>
						<th>INT</th>
						<th>DT CARREGAMENTO</th>
						<th>INT</th>
						<th>DT ENTREGA</th>
						<th>CONCLUSAO</th>
						<th>NOTA</th>
						<th>CARGA</th>
						<th>COND PGTO</th>
						<th>DT VENC.</th>
						<th>DIAS A VENCER</th>
						<th>TOTAL</th>
						<th>VL NOTA</th>
						<th>SIMULADOR<br>CARGA</th>
						<th>CEP</th>
						<th>DISTRITO</th>
						<th>BAIRRO</th>
						<th>OBSERVACAO</th>
												


					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.ExpedicaoCarregamento" />
					<c:forEach var="expedicaoCarregamento"
						items="${expedicaoCarregamento}">
						<tr>
								<td>${expedicaoCarregamento.nu_ped}</td>
								<td>${expedicaoCarregamento.cliente}</td>
								<td>${expedicaoCarregamento.tipoPedido}</td>
								<td>${expedicaoCarregamento.dataCadastro}</td>
								<td>${expedicaoCarregamento.intervalo1}</td>
								<td>${expedicaoCarregamento.dataEntradaWms}</td>
								<td>${expedicaoCarregamento.intervalo2}</td>
								<td>${expedicaoCarregamento.dataFaturamento}</td>
								<td>${expedicaoCarregamento.intervalo3}</td>
								<td>${expedicaoCarregamento.dataCarregamento}</td>
								<td>${expedicaoCarregamento.intervalo4}</td>
								<td>${expedicaoCarregamento.dataEntrega}</td>
								<td>${expedicaoCarregamento.conclusao}</td>
								<td>${expedicaoCarregamento.nota}</td>
								<td>${expedicaoCarregamento.nu_romaneio}</td>
								<td>${expedicaoCarregamento.condPgto}</td>
								<td>${expedicaoCarregamento.diasVencer}</td>
								<td>${expedicaoCarregamento.dataVencimento}</td>
								<td>${expedicaoCarregamento.total}</td>
								<td>${expedicaoCarregamento.vl_nota}</td>
								<td><input type="checkbox" class="form-check-input inline" value="" name="simula">	</td>
								<td>${expedicaoCarregamento.cep}</td>
								<td>${expedicaoCarregamento.distrito}</td>
								<td>${expedicaoCarregamento.bairro}</td>
								<td><a  target="_blank" class="linkTabela" href="editaObservacaoExpedicao?nu_ped=${expedicaoCarregamento.nu_ped}">&#9997;</a>${expedicaoCarregamento.observacao}</td>
								
						
						</tr>
					</c:forEach>
				</table>
			</div>
			
			</form>
			</div>

		</div>
	
	 </main>






	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>