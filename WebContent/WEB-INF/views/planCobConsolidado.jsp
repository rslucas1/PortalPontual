<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	
	<c:import url="head.jsp" />
<body>
		<c:import url="cabecalho.jsp" />
		

	
	<main>
		<div class="Painel">
			<div class="col-sm-12">
				<div class="row">
					<H3 class="central-relatorio">
						PLANO DE COBERTURA CONSOLIDADO</br>
					</H3>
				<div class="central-relatorio">	
					<form action="planCobConsolidado" method="post">	
						<div class="inline">
						  	<label for="exampleFormControlSelect1">INDÚSTRIA: </label>
						</div>	
						<div id="cxSelIndFoco" class="inline">
						  <select class="form-control inline" id="exampleFormControlSelect1" name="industria">
						    <option value="TODAS">TODAS INDÚSTRIAS</option>
						    <option value="FALCON">ONTEX</option>
						    <option value="2">HYPERA S/A</option>
							<option value="36511">KIMBERLY</option>
							<option value="CARTA">CARTA FABRIL</option>
							<option value="LM">BIC</option>
							<option value="PH">PHISÁLIA</option>
							<option value="YPE">YPE</option>
							<option value="1.10">PAM PAM</option>
							<option value="1.12">SU BELLO</option>
							<option value="12499">BARUEL</option>
							<option value="EMB">EMBELLEZE</option>
							<option value="1.04">ALTA MODA</option>
							<option value="SKALA">SKALA</option>
						   </select>							
						</div>
						<div class="inline">
							<input class="btn btn-primary" type="submit" value="PROCESSAR" />
						</div>
					</form>
				</div>
			</div>


			<div class="table-responsive-sm">
				<table style= "white-space: nowrap" class="tabela-planCob-Vend table-bordered table-hover table-sm table-striped ">
					<tr>
						<th>COD.</th>
						<th>RAZÃO SOCIAL</th>
						<th>FANTASIA</th>
						<th>CNPJ/CPF</th>
						<th>TELEFONE</th>
						<th>GRUPO</th>
						<th>SEGMENTO</th>
						<th>AREA</th>
						<th>CEP</th>
						<th>LOGRADOURO</th>
						<th>Nº</th>
						<th>BAIRRO</th>
						<th>MUNICIPIO</th>
						<th>DISTRITO</th>
						<th>COD VEND.</th>
						<th>NOME VENDEDOR</th>
						<th>GUERRA</th>
						<th>GERENTE</th>
						<th>COD. EQUIPE</th>
						<th>EQUIPE</th>

						
						<th>${colunaMes.coluna01}</th>
						<th>${colunaMes.coluna02}</th>
						<th>${colunaMes.coluna03}</th>
						<th>${colunaMes.coluna04}</th>
						<th>${colunaMes.coluna05}</th>
						<th>${colunaMes.coluna06}</th>
						<th>${colunaMes.coluna07}</th>
						<th>${colunaMes.coluna08}</th>
						<th>${colunaMes.coluna09}</th>
						<th>${colunaMes.coluna10}</th>
						<th>${colunaMes.coluna11}</th>
						<th>${colunaMes.coluna12}</th>

					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.ColunasMesesBody" />
					<c:forEach var="planoDeCoberturaVenCli"
						items="${planoDeCoberturaVenCli}">
						<tr>

							<td>${planoDeCoberturaVenCli.cd_cliente}</td>
							<td>${planoDeCoberturaVenCli.desc_cliente}</td>
							<td>${planoDeCoberturaVenCli.fantasia}</td>
							<td>${planoDeCoberturaVenCli.cgc_cpf}</td>
							<td>${planoDeCoberturaVenCli.telefone}</td>
							<td>${planoDeCoberturaVenCli.grupoCli}</td>
							<td>${planoDeCoberturaVenCli.segmento}</td>
							<td>${planoDeCoberturaVenCli.area}</td>
							<td>${planoDeCoberturaVenCli.cep}</td>
							<td>${planoDeCoberturaVenCli.logradouro}</td>
							<td>${planoDeCoberturaVenCli.numero}</td>
							<td>${planoDeCoberturaVenCli.bairro}</td>
							<td>${planoDeCoberturaVenCli.municipio}</td>
							<td>${planoDeCoberturaVenCli.distrito}</td>
							<td>${planoDeCoberturaVenCli.cdVendedor}</td>
							<td>${planoDeCoberturaVenCli.vendedor}</td>
							<td>${planoDeCoberturaVenCli.nomeGuerraVend}</td>
							<td>${planoDeCoberturaVenCli.descGerencia}</td>
							<td>${planoDeCoberturaVenCli.cdEquipe}</td>
							<td>${planoDeCoberturaVenCli.descEquipe}</td>
							
							
							
							
							
							<td>${planoDeCoberturaVenCli.coluna01}</td>
							<td>${planoDeCoberturaVenCli.coluna02}</td>
							<td>${planoDeCoberturaVenCli.coluna03}</td>
							<td>${planoDeCoberturaVenCli.coluna04}</td>
							<td>${planoDeCoberturaVenCli.coluna05}</td>
							<td>${planoDeCoberturaVenCli.coluna06}</td>
							<td>${planoDeCoberturaVenCli.coluna07}</td>
							<td>${planoDeCoberturaVenCli.coluna08}</td>
							<td>${planoDeCoberturaVenCli.coluna09}</td>
							<td>${planoDeCoberturaVenCli.coluna10}</td>
							<td>${planoDeCoberturaVenCli.coluna11}</td>
							<td class="ultimaColuna">${planoDeCoberturaVenCli.coluna12}</td>
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