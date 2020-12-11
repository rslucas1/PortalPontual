<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

		<div class="botoes-pos">
			<H3 class="botoes-pos">RELATÓRIO CLIENTE X INDUSTRIA</H3>
		</div>

		<div class="positivacoes">

			<div class="botoes-pos">
				<form action="clienteXindustria" method="post">

					<div class="inline">

						<select class="form-control form-control-sm select-mes"
							name="mesSelecionado">
							<option value=1>Janeiro</option>
							<option value=2>Fevereiro</option>
							<option value=3>Março</option>
							<option value=4>Abril</option>
							<option value=5 selected>Maio</option>
							<option value=6>Junho</option>
							<option value=7>Julho</option>
							<option value=8>Agosto</option>
							<option value=9>Setembro</option>
							<option value=10>Outubro</option>
							<option value=11>Novembro</option>
							<option value=12>Dezembro</option>
						</select>
					</div>
					<div class="inline">
						<select class="form-control form-control-sm select-ano"
							name="anoSelecionado">
							<option value=2019>2019</option>
							<option value=2020 selected>2020</option>
						</select>
					</div>


					<div class="inline">
						<input class="btn btn-primary btn-sm" type="submit" value="PROCESSAR" />
						
						<a href="analiseRede"  class="btn btn-warning btn-sm" style="margin-left:30px;">ANALISE POR REDE</a>
<!-- 						<input class="btn btn-warning btn-sm" type="submit" name="operacao" value="ANALISE POR REDE" style="margin-left:30px;"/>  -->
						
					</div>


				</form>
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

						
						<th>ONTEX</th>
						<th>BIC</th>
						<th>CARTA FABRIL</th>
						<th>KIMBERLY</th>
						<th>BARUEL</th>
						<th>PHISALIA</th>
						<th>SKALA</th>
						<th>ALFAPARF</th>
						<th>EMBELLEZE</th>
						<th>BEAUTY COLOR</th>
						<th>HYPERA S/A</th>
						<th>STEVITA</th>
						<th>PAMPAM</th>
						<th>YPE</th>
						<th>APOLO</th>

						


					</tr>
					<jsp:useBean id="dao"
						class="br.com.lucasdev.modelo.relatorios.ColunasMesesBody" />
					<c:forEach var="clienteXindustrias"
						items="${clienteXindustrias}">
						<tr>

							<td>${clienteXindustrias.cd_cliente}</td>
							<td>${clienteXindustrias.desc_cliente}</td>
							<td>${clienteXindustrias.fantasia}</td>
							<td>${clienteXindustrias.cgc_cpf}</td>
							<td>${clienteXindustrias.telefone}</td>
							<td>${clienteXindustrias.grupoCli}</td>
							<td>${clienteXindustrias.segmento}</td>
							<td>${clienteXindustrias.area}</td>
							<td>${clienteXindustrias.cep}</td>
							<td>${clienteXindustrias.logradouro}</td>
							<td>${clienteXindustrias.numero}</td>
							<td>${clienteXindustrias.bairro}</td>
							<td>${clienteXindustrias.municipio}</td>
							<td>${clienteXindustrias.distrito}</td>
							<td>${clienteXindustrias.cdVendedor}</td>
							<td>${clienteXindustrias.vendedor}</td>
							<td>${clienteXindustrias.nomeGuerraVend}</td>
							<td>${clienteXindustrias.descGerencia}</td>
							<td>${clienteXindustrias.cdEquipe}</td>
							<td>${clienteXindustrias.descEquipe}</td>
							
							
							
							
							<td>${clienteXindustrias.coluna01}</td>
							<td>${clienteXindustrias.coluna02}</td>
							<td>${clienteXindustrias.coluna03}</td>
							<td>${clienteXindustrias.coluna04}</td>
							<td>${clienteXindustrias.coluna05}</td>
							<td>${clienteXindustrias.coluna06}</td>
							<td>${clienteXindustrias.coluna07}</td>
							<td>${clienteXindustrias.coluna08}</td>
							<td>${clienteXindustrias.coluna09}</td>
							<td>${clienteXindustrias.coluna10}</td>
							<td>${clienteXindustrias.coluna11}</td>
							<td>${clienteXindustrias.coluna12}</td>
							<td>${clienteXindustrias.coluna13}</td>
							<td>${clienteXindustrias.coluna14}</td>
							<td>${clienteXindustrias.coluna15}</td>
							
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