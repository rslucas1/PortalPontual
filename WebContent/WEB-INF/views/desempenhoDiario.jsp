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
							<option value=4>Abril</option>
							<option value=5>Maio</option>
							<option value=6 selected>Junho</option>
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
						<th>ABERTO</th>
						<th>FATURADO</th>

					
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
						class="br.com.lucasdev.modelo.relatorios.DesempenhoDiario" />
					<c:forEach var="desempenhoDiario"
						items="${desempenhoDiario}">
						<tr>

							<td>${desempenhoDiario.codigoVendedor}</td>
							<td>${desempenhoDiario.nomeVendedor}</td>
							<td>${desempenhoDiario.totalAberto}</td>
							<td>${desempenhoDiario.valorTotal}</td>
							
							<td>${desempenhoDiario.dia_1}</td>
							<td>${desempenhoDiario.dia_2}</td>
							<td>${desempenhoDiario.dia_3}</td>
							<td>${desempenhoDiario.dia_4}</td>
							<td>${desempenhoDiario.dia_5}</td>
							<td>${desempenhoDiario.dia_6}</td>
							<td>${desempenhoDiario.dia_7}</td>
							<td>${desempenhoDiario.dia_8}</td>
							<td>${desempenhoDiario.dia_9}</td>
							<td>${desempenhoDiario.dia_10}</td>
							<td>${desempenhoDiario.dia_11}</td>
							<td>${desempenhoDiario.dia_12}</td>
							<td>${desempenhoDiario.dia_13}</td>
							<td>${desempenhoDiario.dia_14}</td>
							<td>${desempenhoDiario.dia_15}</td>
							<td>${desempenhoDiario.dia_16}</td>
							<td>${desempenhoDiario.dia_17}</td>
							<td>${desempenhoDiario.dia_18}</td>
							<td>${desempenhoDiario.dia_19}</td>
							<td>${desempenhoDiario.dia_20}</td>
							<td>${desempenhoDiario.dia_21}</td>
							<td>${desempenhoDiario.dia_22}</td>
							<td>${desempenhoDiario.dia_23}</td>
							<td>${desempenhoDiario.dia_24}</td>
							<td>${desempenhoDiario.dia_25}</td>
							<td>${desempenhoDiario.dia_26}</td>
							<td>${desempenhoDiario.dia_27}</td>
							<td>${desempenhoDiario.dia_28}</td>
							<td>${desempenhoDiario.dia_29}</td>
							<td>${desempenhoDiario.dia_30}</td>
							<td>${desempenhoDiario.dia_31}</td>
											
					</c:forEach>
				</table>
			</div>
		</div>
	 </main>






	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>