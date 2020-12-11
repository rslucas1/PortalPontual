<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<c:import url="head.jsp" />

<body>

	
	<c:import url="cabecalho.jsp" />
	<c:import url="menuLateral.jsp" />
	
	<head><meta http-equiv="refresh" content="300"></head>

	
	<main>
		<div class="Painel">
			<div class="linha-detcli">
				<div class="">
					<H3 class="">
						TRACKING 
<!-- 						<a href="tracking"><button type="button" class="btn btn-warning">Atualizar</button><a></a> -->
						</br></br>
					</H3>
					
				</div>
			</div>
			
			<form method="post" action="tracking">
				
			<div class="linha-detcli">
				<div class="inline">
					<div class="btn-etapa-pedidos"><b> PED. ELETRÔNICOS</b><hr>${pendEle.qtdPedido} PEDIDOS<br> ${pendEle.valorMoeda} <br>${pendEle.ultPed}
						<input class="btn-etapa-pedidos-input" type="submit" name="operacao" value=" "/>
					</div>
				</div>
				
				<div class="inline">
					<div class="btn-etapa-pedidos"><b> ADM. COMERCIAL</b><hr>${administracao.qtdPedido} PEDIDOS<br>${administracao.valorMoeda}<br>${administracao.ultPed}
						<input class="btn-etapa-pedidos-input" type="submit" name="operacao" value=" "/>
					</div>
				</div>
				
				<div class="inline">
					<div class="btn-etapa-pedidos"><b> CRÉDITO</b><hr>${credito.qtdPedido} PEDIDOS<br> ${credito.valorMoeda} <br>${credito.ultPed}
						<input class="btn-etapa-pedidos-input" type="submit" name="operacao" value=" "/>
					</div>
				</div>
				
				
				<div class="inline">
					<div class="btn-etapa-pedidos"><b> ROTEIRIZAÇÃO</b><hr>${roteirizacao.qtdPedido} PEDIDOS<br> ${roteirizacao.valorMoeda} <br>${roteirizacao.ultPed}
						<input class="btn-etapa-pedidos-input" type="submit" name="operacao" value=" "/>
					</div>
				</div>
				
				<div class="inline">
					<div class="btn-etapa-pedidos"><b> RESERVA</b><hr>${reserva.qtdPedido} PEDIDOS<br> ${reserva.valorMoeda} <br>${reserva.ultPed}
						<input class="btn-etapa-pedidos-input" type="submit" name="operacao" value=" "/>
					</div>
				</div>
					
			
				
			</div>	
			
			
						<div class="linha-detcli">
				<div class="inline">
					<div class="btn-etapa-pedidos"><b> SEPARAÇÃO</b><hr>${separacao.qtdPedido} PEDIDOS<br> ${separacao.valorMoeda} <br>${separacao.ultPed}
						<input class="btn-etapa-pedidos-input" type="submit" name="operacao" value=" "/>
					</div>
				</div>
				
				<div class="inline">
					<div class="btn-etapa-pedidos"><b> FATURAMENTO</b><hr>${faturamento.qtdPedido} PEDIDOS<br> ${faturamento.valorMoeda} <br>${faturamento.ultPed}
						<input class="btn-etapa-pedidos-input" type="submit" name="operacao" value=" "/>
					</div>
				</div>
				
				<div class="inline">
					<div class="btn-etapa-pedidos"><b> EXPEDIÇÃO</b><hr>${expedicao.qtdPedido} PEDIDOS<br> ${expedicao.valorMoeda} <br>${expedicao.ultPed}
						<input class="btn-etapa-pedidos-input" type="submit" name="operacao" value="op_expedicao"/>
					</div>
				</div>
				
				<div class="inline">
					<div class="btn-etapa-pedidos"><b> ENTREGA</b><hr>em desenvolvimento<br> em desenvolvimento <br>em desenvolvimento
						<input class="btn-etapa-pedidos-input" type="submit" name="operacao" value=" "/>
					</div>
				</div>
					
			</form>
				
			</div>	
				
			
						
		</div>
	 </main>






	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>