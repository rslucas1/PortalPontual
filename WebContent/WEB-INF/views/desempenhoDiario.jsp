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
			<div class="col-sm-12">
				<div class="row">
					<H3 class="central-relatorio">
						Timeline - Desempenho Diário</br>
					</H3>
				<div class="central-relatorio">	
					<form action="desempenhoDiario" method="post">	
											<div class="inline">

						<select class="form-control form-control-sm select-mes"
							name="mesSelecionado">
							<option value=1>Janeiro</option>
							<option value=2>Fevereiro</option>
							<option value=3>Março</option>
							<option value=4 selected>Abril</option>
							<option value=5>Maio</option>
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
							<input class="btn btn-primary" type="submit" value="PROCESSAR" />
						</div>
					</form>
				</div>
			</div>


			<div class="table-responsive-sm">
				<table style= "white-space: nowrap" class="tabela-planCob-Vend table-bordered table-hover table-sm table-striped ">
					<tr>
						<th>Cod</th>
						<th>Nome</th>

					
						<th>1</th>
						<th>2</th>
						<th>3</th>
						<th>4</th>
						<th>5</th>
						<th>6</th>
						<th>7</th>
						<th>8</th>
						<th>9</th>
						<th>10</th>
						<th>11</th>
						<th>12</th>
						<th>13</th>
						<th>14</th>
						<th>15</th>
						<th>16</th>
						<th>16</th>
						<th>17</th>
						<th>18</th>
						<th>19</th>
						<th>20</th>
						<th>21</th>
						<th>22</th>
						<th>23</th>
						<th>24</th>
						<th>25</th>
						<th>26</th>
						<th>27</th>
						<th>28</th>
						<th>29</th>
						<th>30</th>
						<th>31</th>
						
			
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