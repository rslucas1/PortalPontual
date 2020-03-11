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
			<div class="linha-detcli">
				<div class="">
					<H3 class="">
						Consulta de Cliente</br>
					</H3>
					<form method="post" action="consCliente">
						<input name="cnpj" type="text" id="cnpj" placeholder="Digite o CNPJ"/>
						
						<button type="submit" class="btn btn-info">Buscar</button>
					</form>

				</div>
			</div>
				
			<div class="linha-detcli">
				<div class="inline">
					<div>Situação</div>
					<div>
						<input class="form-control form-control-sm " type="text" value="${clienteDetalhado.situacao}" readonly size="12">
					</div>
				</div>
					
				<div class="inline">
					<div>Vendedor</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value="${clienteDetalhado.vendedor}"readonly size="40">
					</div>
				</div>
				
				<div class="inline">
					<div>data da ultima compra</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value="${clienteDetalhado.dtUltimaCompra}"readonly size="19">
					</div>
				</div>
				
			</div>	
				
			<div class="linha-detcli">
				<div class="inline">
					<div>Código</div>
					<div>
						<input class="form-control form-control-sm " type="text" value="${clienteDetalhado.cd_cliente}" readonly size="5">
					</div>
				</div>
					
				<div class="inline">
					<div>Razão Social</div>
					<div class="inline">
						<input class="form-control form-control-sm " type="text" value="${clienteDetalhado.desc_cliente}"readonly size="75">
					</div>
				</div>
			</div>
				
			<div class="linha-detcli">
				<div class="inline">
					<div>Fantasia</div>
					<div class="inline">
						<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.fantasia}" readonly size="35">
					</div>
				</div>
					
				<div class="inline">
					<div>CNPJ/CGC</div>
					<div class="inline">
						<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.cgc_cpf}" readonly size="15">
					</div>
				</div>
					
				<div class="inline">	
				<div>Grupo</div>
				<div class="inline">
					<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.grupoCli}" readonly size="21">
				</div>
			</div>
		</div>
				
				
		<div class="linha-detcli">
			<div class="inline">
				<div>Logradouro</div>
				<div class="inline">
					<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.logradouro}" readonly size="52">
				</div>
			</div>
					
			<div class="inline">
				<div>Numero</div>
				<div class="inline">
					<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.numero}" readonly size="4">
				</div>
			</div>
					
			<div class="inline">	
				<div>CEP</div>
				<div class="inline">
					<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.cep}" readonly size="15">
				</div>
			</div>
		</div>	
				
			<div class="linha-detcli">
				<div class="inline">
					<div>Segmento</div>
					<div class="inline">
						<input class="form-control form-control-sm" type="text" " value="${clienteDetalhado.segmento}" readonly size="22">
					</div>
				</div>
					
				<div class="inline">
					<div>Area</div>
					<div class="inline">
						<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.area}" readonly size="22">
					</div>
				</div>
					
				<div class="inline">	
					<div>Bairro</div>
					<div class="inline">
						<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.bairro}" readonly size="27">
					</div>
				</div>
			</div>
		</div>
	 </main>






	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>