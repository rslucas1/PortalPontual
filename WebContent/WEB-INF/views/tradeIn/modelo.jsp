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
			<div class="subPainel1100">
			<H3 class="user-btn-titulo">Filtro</H3>
			
				<form action="indFocoParConfig" method="post">
						<fieldset class="dropdownTradeIndset">
							<legend>Indústia</legend>
							    <select class="form-control" id="exampleFormControlSelect1" name="industria"  style="width:200px;">
							      <option value="todas" selected>TODAS</option>
							      <option value="">ONTEX GLOBAL</option>
							      <option value="">KIMBERLY</option>
							      <option value="">BIC</option>
							      <option value="">HYPERA S/A</option>
							      <option value="">EMBELLEZE</option>
							      <option value="">HARIBO</option>
							      <option value="">PAMPAM</option>
							      <option value="">SU BELLO</option>
							      <option value="">YPE</option>
							      <option value="">CARTA FABRIL</option>
							      <option value="">FLORA</option>
							      <option value="">FLORA PERFUMARIA</option>
							      <option value="">PHISALIA</option>
							      <option value="">LILLO</option>
							      <option value="">ALFAPARF</option>
							      <option value="">APOLO</option>
							      <option value="">BARUEL</option>
							      <option value="">SKALA</option>
							      <option value="">BEAUTY COLOR</option>
						
							    </select>
						</fieldset>
						
						<fieldset class="dropdownTradeIndset">
							<legend>Equipe</legend>
							    <select class="form-control inline" id="exampleFormControlSelect1" name="filtro" style="width:300px;">
								   	<option value="GERAL">GERAL</option>
								    <option value="PC98">GERENCIA - RAFAEL ALVES</option>
								    <option value="PC02">GERENCIA - VAGNER SOUZA</option>
									
									
									<option value="PC35">EQUIPE - MARCOS-LIT.SUL/V.RIB</option>
									<option value="PC90">EQUIPE - SLAV- Z.LESTE(F)</option>
									<option value="PC70">EQUIPE - RAFAEL - Z. LESTE</option>
									<option value="PC40">EQUIPE - SOUZA - Z. OESTE</option>
									<option value="PG45">EQUIPE - VIDAL - ZONA SUL</option>
									<option value="1000">EQUIPE - EDSON-AL-GUA-ZN-ZL-V</option>
								  </select>
						</fieldset>
						
						
						<fieldset class="dropdownTradeIndset">
							<legend>Período</legend>
							   Inicio: <input id="date" type="date">					
								Fim:   <input id="date" type="date">
						</fieldset>
						
						<fieldset>
							<legend>Ramo</legend>
								<div class="form-check">
							    	<input type="checkbox" class="form-check-input" name="ramo" value="FARMA">
							    	<label class="form-check-label checkTradeIn">farma</label>
							    	
							    	<input type="checkbox" class="form-check-input" name="ramo" value="MERCADO">
							    	<label class="form-check-label checkTradeIn">farma</label>
							    	
							    								    	
							  </div>
						</fieldset>
						
						
						
						<fieldset>
							<legend>Segmento</legend>
								<div class="form-check">
							    	<input type="checkbox" class="form-check-input" name="segmento" value="ADULTO">
							    	<label class="form-check-label checkTradeIn">SEG ADULTO</label>
							    	
							    	<input type="checkbox" class="form-check-input" name="segmento" value="INFANTIL">
							    	<label class="form-check-label checkTradeIn">SEG INFANTIL</label>
							    	
							    	<input type="checkbox" class="form-check-input" name="segmento" value="LIMPEZA">
							    	<label class="form-check-label checkTradeIn">SEG LIMPEZA</label>
							    	
							    	<input type="checkbox" class="form-check-input" name="segmento" value="HIGIENE">
							    	<label class="form-check-label checkTradeIn">SEG HIGIENE</label>	
							    	
							       	<input type="checkbox" class="form-check-input" name="segmento" value="PERFUMARIA">
							    	<label class="form-check-label checkTradeIn">SEG PERFUMARIA</label>
							    	
							    	<input type="checkbox" class="form-check-input" name="segmento" value="ALIMENTOS">
							    	<label class="form-check-label checkTradeIn" >SEG ALIMENTOS</label>								    	
							  </div>
						</fieldset>
						<fieldset class="fild">
							<legend>Categoria</legend>
									<table class="tableTradeIn">
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C1">
							    				<label class="form-check-label">ABS ADULTO</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C2">
							    				<label class="form-check-label" >ABSORVENTE</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C3">
							    				<label class="form-check-label" >ADOCANTE</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C4">
							    				<label class="form-check-label" >AEROSOL</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C5">
							    				<label class="form-check-label" >AGUA SANITARIA / ALVEJ</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C6">
							    				<label class="form-check-label">ALGODÃO / CURATIVO</label>
							    			</td>
										</tr>
										
										
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C7">
							    				<label class="form-check-label">AMACIANTE </label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C8">
							    				<label class="form-check-label" >BARBEADOR</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C9">
							    				<label class="form-check-label" >BEBIDA</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C10">
							    				<label class="form-check-label" >BLOQUEADOR DE ODORES</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C11">
							    				<label class="form-check-label" >COLONIA INF</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C12">
							    				<label class="form-check-label">COLORACAO</label>
							    			</td>
										</tr>
										
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C13">
							    				<label class="form-check-label">CONDICIONADOR</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C14">
							    				<label class="form-check-label" >CONDICIONADOR INF</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C15">
							    				<label class="form-check-label" >CREME / ESPUMA BARBEAR</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C16">
							    				<label class="form-check-label" >CREME ASSADURA INF</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C17">
							    				<label class="form-check-label" >CREME DE PENTEAR</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C18">
							    				<label class="form-check-label">CREME DE PENTEAR INF</label>
							    			</td>
										</tr>
										
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C19">
							    				<label class="form-check-label">CREME DENTAL</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C20">
							    				<label class="form-check-label" >CREME PE / TRAT</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C21">
							    				<label class="form-check-label" >DEPILADOR</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C22">
							    				<label class="form-check-label" >DESCOLORAÇÃO</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C23">
							    				<label class="form-check-label" >DESINFETANTE</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C24">
							    				<label class="form-check-label">DESODORANTE</label>
							    			</td>
										</tr>
										
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C25">
							    				<label class="form-check-label">DETERGENTE LIQ</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C26">
							    				<label class="form-check-label" >DOCE</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C27">
							    				<label class="form-check-label" >ESMALTE</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C28">
							    				<label class="form-check-label" >ESPONJA </label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C29">
							    				<label class="form-check-label" >FINALIZADORES INF</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C30">
							    				<label class="form-check-label">FRALDA ADULTO</label>
							    			</td>
										</tr>
										
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C31">
							    				<label class="form-check-label">FRALDA INFANTIL</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C32">
							    				<label class="form-check-label" >GUARDANAPO</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C33">
							    				<label class="form-check-label" >HASTES FLEXIVEIS</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C34">
							    				<label class="form-check-label" >INSETICIDA</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C35">
							    				<label class="form-check-label" >JATO SECO</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C36">
							    				<label class="form-check-label">KIT SHAMPOO + </label>
							    			</td>
										</tr>
										
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C37">
							    				<label class="form-check-label">KIT SHAMPOO + INF</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C38">
							    				<label class="form-check-label" >LAVA ROUPA LIQ</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C39">
							    				<label class="form-check-label" >LAVA ROUPA PO</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C40">
							    				<label class="form-check-label" >LENÇO</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C41">
							    				<label class="form-check-label" >LILLO</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C42">
							    				<label class="form-check-label">LIMPADOR PERFUMADO</label>
							    			</td>
										</tr>
										
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C43">
							    				<label class="form-check-label">LOCAO HIDRATANTE</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C44">
							    				<label class="form-check-label" >LUSTRA MOVEIS</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C45">
							    				<label class="form-check-label" >MODELADOR</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C46">
							    				<label class="form-check-label" >MULTIUSO</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C47">
							    				<label class="form-check-label" >PANO DE LIMPEZA</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C48">
							    				<label class="form-check-label">PAPEL HIGIENICO</label>
							    			</td>
										</tr>
										
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C49">
							    				<label class="form-check-label">PASTILHA SANITARIA</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C50">
							    				<label class="form-check-label" >PROTETOR DIARIO</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C51">
							    				<label class="form-check-label" >REPELENTE </label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C52">
							    				<label class="form-check-label" >SAB INTIMO</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C53">
							    				<label class="form-check-label" >SABAO BARRA</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C54">
							    				<label class="form-check-label">SABAO PASTA</label>
							    			</td>
										</tr>
										
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C55">
							    				<label class="form-check-label">SABONETE BARRA</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C56">
							    				<label class="form-check-label" >SABONETE BARRA INF</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C57">
							    				<label class="form-check-label" >SABONETE LIQUIDO</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C58">
							    				<label class="form-check-label" >SABONETE LIQUIDO INF</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C59">
							    				<label class="form-check-label" >SAPONACEO</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C60">
							    				<label class="form-check-label">SHAMPOO</label>
							    			</td>
										</tr>
										
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C61">
							    				<label class="form-check-label">SHAMPOO INF</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C62">
							    				<label class="form-check-label" >TALCO EM PO</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C63">
							    				<label class="form-check-label" >TALCO INF</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C64">
							    				<label class="form-check-label" >TIRA MANCHAS</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C65">
							    				<label class="form-check-label" >TOALHA DE PAPEL</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C66">
							    				<label class="form-check-label">TOALHA/LENÇO UMED ADUL</label>
							    			</td>
										</tr>
										
										<tr>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C67">
							    				<label class="form-check-label">TOALHA/LENÇO UMED INF</label>
							    			</td>
											<td><input type="checkbox" class="form-check-input" name="segmento" value="C68">
							    				<label class="form-check-label" >TRANSFORMACAO</label>
							    			</td>
							    			<td><input type="checkbox" class="form-check-input" name="segmento" value="C69">
							    				<label class="form-check-label" >TRATAMENTO CAPILAR</label>
							    			</td>
<!-- 							    			<td><input type="checkbox" class="form-check-input" name="segmento" value=""> -->
<!-- 							    				<label class="form-check-label" ></label> -->
<!-- 							    			</td> -->
<!-- 							    			<td><input type="checkbox" class="form-check-input" name="segmento" value=""> -->
<!-- 							    				<label class="form-check-label" ></label> -->
<!-- 							    			</td> -->
<!-- 											<td><input type="checkbox" class="form-check-input" name="segmento" value=""> -->
<!-- 							    				<label class="form-check-label"></label> -->
<!-- 							    			</td> -->
										</tr>
										
									
									</table>
						</fieldset>
						
						<fieldset class="fild">
							<legend>Marca</legend>
								<table class="tableTradeIn">
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M1">
							    			<label class="form-check-label">ADOCYL</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M2">
							    			<label class="form-check-label" >ALBANY</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M3">
							    			<label class="form-check-label" >ALFAPARF</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M4">
							    			<label class="form-check-label" >AMOR DE MAE</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M5">
							    			<label class="form-check-label" >APOLO</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M6">
							    			<label class="form-check-label">ARGAN&COCO</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M7">
							    			<label class="form-check-label">ASSIM</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M8">
							    			<label class="form-check-label" >ASSOLAN</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M9">
							    			<label class="form-check-label" >BARUEL</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M10">
							    			<label class="form-check-label" >BC BARBEADOR</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M11">
							    			<label class="form-check-label" >BC BASES</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M12">
							    			<label class="form-check-label">BC CINTILANTES</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M13">
							    			<label class="form-check-label">BC COND</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M14">
							    			<label class="form-check-label" >BC CREME PENTEAR</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M15">
							    			<label class="form-check-label" >BC CREMOSOS</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M16">
							    			<label class="form-check-label" >BC DEPILADOR</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M17">
							    			<label class="form-check-label" >BC GLITTER</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M18">
							    			<label class="form-check-label">BC HIPOALERGICOS</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M19">
							    			<label class="form-check-label">BC METALICOS</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M20">
							    			<label class="form-check-label" >BC NEON</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M21">
							    			<label class="form-check-label" >BC OLEO ESPECIAL</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M22">
							    			<label class="form-check-label" >BC REMOVEDOR DE ESMALTE</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M23">
							    			<label class="form-check-label" >BC TRANSPARENTES</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M24">
							    			<label class="form-check-label">BC VERNIZES</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M25">
							    			<label class="form-check-label">BELA&COR</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="26M">
							    			<label class="form-check-label" >BEM ESTAR</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M27">
							    			<label class="form-check-label" >BIGFRAL</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M28">
							    			<label class="form-check-label" >BIGMAXI</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M29">
							    			<label class="form-check-label" >CLEAN</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M30">
							    			<label class="form-check-label">COLO DE MÃE</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M31">
							    			<label class="form-check-label">COLOR INSPIRE</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M32">
							    			<label class="form-check-label" >COLOR XPLODE</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M33">
							    			<label class="form-check-label" >COQUETEL</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M34">
							    			<label class="form-check-label" >COTTON</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M35">
							    			<label class="form-check-label" >CREME ASSADURA</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M36">
							    			<label class="form-check-label">CREME DE BARBEAR</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M37">
							    			<label class="form-check-label">CREME PENTEAR</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M38">
							    			<label class="form-check-label" >CREMER</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M39">
							    			<label class="form-check-label" >DELUXE</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M40">
							    			<label class="form-check-label" >DESINFETANTE</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M41">
							    			<label class="form-check-label" >DETERGENTE</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M42">
							    			<label class="form-check-label">DIANA</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M43">
							    			<label class="form-check-label">DURAMAX</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M44">
							    			<label class="form-check-label" >ELIXIR SUPREME</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M45">
							    			<label class="form-check-label" >EMB COND</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M46">
							    			<label class="form-check-label" >EMB TRANSFORMACAO</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M47">
							    			<label class="form-check-label" >EMB TRATAMENTO</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M48">
							    			<label class="form-check-label">ESPUMA DE BARBEAR</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M49">
							    			<label class="form-check-label">EVE</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M50">
							    			<label class="form-check-label" >FINN</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M51">
							    			<label class="form-check-label" >FL COND</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M52">
							    			<label class="form-check-label" >FL CREME PENTEAR</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M53">
							    			<label class="form-check-label" >FL MODELADOR</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M54">
							    			<label class="form-check-label">FL TRATAMENTO</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M55">
							    			<label class="form-check-label">FOR MEN</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M56">
							    			<label class="form-check-label" >FORT</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M57">
							    			<label class="form-check-label" >FRANCIS</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M58">
							    			<label class="form-check-label" >FRANCIS PROTEGE</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M59">
							    			<label class="form-check-label" >FROZEN</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M60">
							    			<label class="form-check-label">GELATINA CAPILAR</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M61">
							    			<label class="form-check-label">GRAN FINALE</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M62">
							    			<label class="form-check-label" >HARIBO</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M63">
							    			<label class="form-check-label" >HASTES FLEXIVEIS</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M64">
							    			<label class="form-check-label" >HUGGIES</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M65">
							    			<label class="form-check-label" >HYDRATTA</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M66">
							    			<label class="form-check-label">INTIMUS</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M67">
							    			<label class="form-check-label">INTIMUS DAYS</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M68">
							    			<label class="form-check-label" >INTIMUS INTERNO</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M69">
							    			<label class="form-check-label" >INTIMUS SPORT</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M70">
							    			<label class="form-check-label" >KARINA</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M71">
							    			<label class="form-check-label" >KIT BELA&COR</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M72">
							    			<label class="form-check-label">KIT DESAMARELADOR</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M73">
							    			<label class="form-check-label">KLEENEX</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M74">
							    			<label class="form-check-label" >LEBLON</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M74">
							    			<label class="form-check-label" >LILLO</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M76">
							    			<label class="form-check-label" >LIMP MULTIUSO</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M77">
							    			<label class="form-check-label" >LIMP PERFUMADO</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M78">
							    			<label class="form-check-label">LIMPA LIMO</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M79">
							    			<label class="form-check-label">LOONEY TUNES</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M80">
							    			<label class="form-check-label" >LUKINHA</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M81">
							    			<label class="form-check-label" >LUMIAN</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M82">
							    			<label class="form-check-label" >LUMIAN SAFIRA</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M83">
							    			<label class="form-check-label" >MAT INSET</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M84">
							    			<label class="form-check-label">MAXTON</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M85">
							    			<label class="form-check-label">MINUANO</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M86">
							    			<label class="form-check-label" >MONICA</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M87">
							    			<label class="form-check-label" >NATUCOR</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M88">
							    			<label class="form-check-label" >NEUTROX</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M89">
							    			<label class="form-check-label" >NEVE</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M90">
							    			<label class="form-check-label">NO INSET</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M91">
							    			<label class="form-check-label">NOVEX</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M92">
							    			<label class="form-check-label" >OX</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M93">
							    			<label class="form-check-label" >PAMPAM</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M94">
							    			<label class="form-check-label" >PERFEX</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M95">
							    			<label class="form-check-label" >PH COND</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M96">
							    			<label class="form-check-label">PH CR PENT</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M97">
							    			<label class="form-check-label">PH DESODORANTES</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M98">
							    			<label class="form-check-label" >PLENITUD</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M99">
							    			<label class="form-check-label" >POMPOM</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M100">
							    			<label class="form-check-label" >PREMIUM</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M101">
							    			<label class="form-check-label" >PRINCESAS</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M102">
							    			<label class="form-check-label">PROTEC</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M103">
							    			<label class="form-check-label">PURISSI</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M104">
							    			<label class="form-check-label" >ROUPINHA</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M105">
							    			<label class="form-check-label" >SAPEKA</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M106">
							    			<label class="form-check-label" >SCOTT</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M107">
							    			<label class="form-check-label" >SHAMPOO BC</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M108">
							    			<label class="form-check-label">SHAMPOO BEM</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M109">
							    			<label class="form-check-label">SHAMPOO FL</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M110">
							    			<label class="form-check-label" >SHAMPOO PH</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M111">
							    			<label class="form-check-label" >SHAMPOO SK</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M112">
							    			<label class="form-check-label" >SILHOUETTE</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M113">
							    			<label class="form-check-label" >SK COND</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M114">
							    			<label class="form-check-label">SK CREME PENTEAR</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M115">
							    			<label class="form-check-label">SK DESODORANTE</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M116">
							    			<label class="form-check-label" >SK HIDRATANTE</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M117">
							    			<label class="form-check-label" >SK TRATAMENTO</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M118">
							    			<label class="form-check-label" >SKALA</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M119">
							    			<label class="form-check-label" >SOUL POWER</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M120">
							    			<label class="form-check-label">SU BELLO</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M121">
							    			<label class="form-check-label">SUPREME CARE</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M122">
							    			<label class="form-check-label" >TENYS PE</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M123">
							    			<label class="form-check-label" >TIXAN</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M124">
							    			<label class="form-check-label" >TRA LA LA BABY</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M125">
							    			<label class="form-check-label" >TRA LA LA KIDS</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M126">
							    			<label class="form-check-label">TRANSFORMES</label>
							    		</td>
									</tr>
									
									<tr>									
										<td><input type="checkbox" class="form-check-input" name="marca" value="M127">
							    			<label class="form-check-label">TRIPLA PROTEÇÃO</label>
							    		</td>
										<td><input type="checkbox" class="form-check-input" name="marca" value="M128">
							    			<label class="form-check-label" >VITAY</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M129">
							    			<label class="form-check-label" >XUXINHA</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M130">
							    			<label class="form-check-label" >YPE</label>
							    		</td>
							    		<td><input type="checkbox" class="form-check-input" name="marca" value="M131">
							    			<label class="form-check-label" >ZERO CAL</label>
							    		</td>
<!-- 										<td><input type="checkbox" class="form-check-input" name="marca" value=""> -->
<!-- 							    			<label class="form-check-label"></label> -->
<!-- 							    		</td> -->
									</tr>
									
								</table>
						</fieldset>						
												
						<fieldset class="fild">
							<legend>Familia</legend>
							
							<table class="tableTradeIn">
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F1">
							   		<label class="form-check-label">100 DIAS</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F2">
							    	<label class="form-check-label" >ABS DIANA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F3">
							    	<label class="form-check-label" >ABS INTERNO</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F4">
							    	<label class="form-check-label" >ABS NOTURNO</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F5">
							    	<label class="form-check-label" >ABS PLENITUD</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F6">
							    	<label class="form-check-label">ABS SPORT</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F7">
							   		<label class="form-check-label">ABS TRIPLA</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F8">
							    	<label class="form-check-label" >ABS ULTRA FINO</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F9">
							    	<label class="form-check-label" >ABS ÚNICA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F10">
							    	<label class="form-check-label" >ABSORVENTE SEIOS</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F11">
							    	<label class="form-check-label" >ACQUA3</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F12">
							    	<label class="form-check-label">ADOCYL</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F13">
							   		<label class="form-check-label">AEROSOL</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F14">
							    	<label class="form-check-label" >AGUA DE COCO</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F15">
							    	<label class="form-check-label" >AGUA OXIGENADA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F16">
							    	<label class="form-check-label" >AGUA SANITARIA</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F17">
							    	<label class="form-check-label" >ALGODAO</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F18">
							    	<label class="form-check-label">ALVEJANTE</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F19">
							   		<label class="form-check-label">AMACIANTE</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F20">
							    	<label class="form-check-label" >AMACIANTE CONC</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F21">
							    	<label class="form-check-label" >AMAMENTACAO</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F22">
							    	<label class="form-check-label" >AMPOLA</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F23">
							    	<label class="form-check-label" >ASPIRADOR</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F24">
							    	<label class="form-check-label">ASSIM</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F25">
							   		<label class="form-check-label">ASSOLAN</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F26">
							    	<label class="form-check-label" >ATOL</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F27">
							    	<label class="form-check-label" >BABY WIPES</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F28">
							    	<label class="form-check-label" >BAK</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F29">
							    	<label class="form-check-label" >BALA</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F30">
							    	<label class="form-check-label">BARUEL</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F31">
							   		<label class="form-check-label">BC BASE</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F32">
							    	<label class="form-check-label" >BC BASES</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F33">
							    	<label class="form-check-label" >BC CINTILANTES</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F34">
							    	<label class="form-check-label" >BC CREMOSOS</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F35">
							    	<label class="form-check-label" >BC GLITTER</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F36">
							    	<label class="form-check-label">BC HIPOALERGICOS</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F37">
							   		<label class="form-check-label">BC METALICOS</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F38">
							    	<label class="form-check-label" >BC NEON</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F39">
							    	<label class="form-check-label" >BC OLEO CAPILAR</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F40">
							    	<label class="form-check-label" >BC OLEO ESPECIAL</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F41">
							    	<label class="form-check-label" >BC REESTRUTURAÇÃO</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F42">
							    	<label class="form-check-label">BC REMOVEDOR DE ESMALTE</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F43">
							   		<label class="form-check-label">BC TRANSPARENTES</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F44">
							    	<label class="form-check-label" >BC TRATAMENTO</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F45">
							    	<label class="form-check-label" >BC VERNIZES</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F46">
							    	<label class="form-check-label" >BEM ESTAR COMF</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F47">
							    	<label class="form-check-label" >BICO</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F48">
							    	<label class="form-check-label">BIGFRAL</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F49">
							   		<label class="form-check-label">BIGFRAL CONFORT</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F50">
							    	<label class="form-check-label" >BIGFRAL NOTUR</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F51">
							    	<label class="form-check-label" >BIGFRAL PANTS</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F52">
							    	<label class="form-check-label" >BIGFRAL PANTS</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F53">
							    	<label class="form-check-label" >BIGMAXI</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F54">
							    	<label class="form-check-label">CHUPETA</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F55">
							   		<label class="form-check-label">CITRU FLORAL</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F56">
							    	<label class="form-check-label" >CLASSIC</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F57">
							    	<label class="form-check-label" >CLEAN</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F58">
							    	<label class="form-check-label" >COLOR XPLODE</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F59">
							    	<label class="form-check-label" >COLORACAO</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F60">
							    	<label class="form-check-label">COMF2</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F61">
							   		<label class="form-check-label">COMF3</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F62">
							    	<label class="form-check-label" >COND SOU LINDA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F63">
							    	<label class="form-check-label" >CONDICIONADOR</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F64">
							    	<label class="form-check-label" >COPO</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F65">
							    	<label class="form-check-label" >COQUETEL</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F66">
							    	<label class="form-check-label">CORTADOR DE UNHAS</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F67">
							   		<label class="form-check-label">COTTON</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F68">
							    	<label class="form-check-label" >CR PREVENTIVO</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F69">
							    	<label class="form-check-label" >CREME DE BARBEAR</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F70">
							    	<label class="form-check-label" >CREME DE TRATAMENTO</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F71">
							    	<label class="form-check-label" >CREME DESODORANTE</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F72">
							    	<label class="form-check-label">CREMER</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F73">
							   		<label class="form-check-label">CURATIVOS</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F74">
							    	<label class="form-check-label" >DELUXE</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F75">
							    	<label class="form-check-label" >DERMA CARE</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F76">
							    	<label class="form-check-label" >DURAMAX</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F77">
							    	<label class="form-check-label" >ECONOMICA</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F78">
							    	<label class="form-check-label">ESCOVA DENTAL</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F79">
							   		<label class="form-check-label">ESCOVA E PENTE</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F80">
							    	<label class="form-check-label" >ESPUMA DE BARBEAR</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F81">
							    	<label class="form-check-label" >FINN</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F82">
							    	<label class="form-check-label" >FL SABONETE BARRA</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F83">
							    	<label class="form-check-label" >FL SABONETE LIQUIDO</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F84">
							    	<label class="form-check-label">FLEX3</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F85">
							   		<label class="form-check-label">FLUSS</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F86">
							    	<label class="form-check-label" >FORT</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F87">
							    	<label class="form-check-label" >FROZEN</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F88">
							    	<label class="form-check-label" >GEL LIQUIDO</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F89">
							    	<label class="form-check-label" >GELATINA CAPILAR</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F90">
							    	<label class="form-check-label">GRAN FINALE</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F91">
							   		<label class="form-check-label">HAIR LIFE</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F92">
							    	<label class="form-check-label" >HASTES FLEXIVEIS</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F93">
							    	<label class="form-check-label" >HIPER</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F94">
							    	<label class="form-check-label" >HUGGIES</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F95">
							    	<label class="form-check-label" >JATO SECO</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F96">
							    	<label class="form-check-label">JUMBINHO</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F97">
							   		<label class="form-check-label">JUMBO</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F98">
							    	<label class="form-check-label" >KARINA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F99">
							    	<label class="form-check-label" >KIT HIGIENE</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F100">
							    	<label class="form-check-label" >KIT MANICURE</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F101">
							    	<label class="form-check-label" >KIT SH+COND</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F102">
							    	<label class="form-check-label">KLEENEX</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F103">
							   		<label class="form-check-label">KOLENE</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F104">
							    	<label class="form-check-label" >LAMINA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F105">
							    	<label class="form-check-label" >LANCHEIRA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F106">
							    	<label class="form-check-label" >LAVANDA</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F107">
							    	<label class="form-check-label" >LEAVE IN</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F108">
							    	<label class="form-check-label">LEBLON</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F109">
							   		<label class="form-check-label">LENÇO UMED INTIMUS</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F110">
							    	<label class="form-check-label" >LILLO</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F111">
							    	<label class="form-check-label" >LOCAO CAPILAR</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F112">
							    	<label class="form-check-label" >LUKINHA</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F113">
							    	<label class="form-check-label" >LUSTRA MOVEIS</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F114">
							    	<label class="form-check-label">MAGIC CARE</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F115">
							   		<label class="form-check-label">MAMADEIRA</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F116">
							    	<label class="form-check-label" >MARSHMALLOW</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F117">
							    	<label class="form-check-label" >MAT INSET</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F118">
							    	<label class="form-check-label" >MEGA</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F119">
							    	<label class="form-check-label" >MEUS CACHOS</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F120">
							    	<label class="form-check-label">MINUANO</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F121">
							   		<label class="form-check-label">MODELADOR</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F122">
							    	<label class="form-check-label" >MONICA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F123">
							    	<label class="form-check-label" >MORDEDOR</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F124">
							    	<label class="form-check-label" >MOVIMENT</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F126">
							    	<label class="form-check-label" >NEUTROX</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F126">
							    	<label class="form-check-label">NO INSET</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F127">
							   		<label class="form-check-label">NOVEX</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F128">
							    	<label class="form-check-label" >OX</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F129">
							    	<label class="form-check-label" >PANTS</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F130">
							    	<label class="form-check-label" >PELUCIA</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F131">
							    	<label class="form-check-label" >PERFEX</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F132">
							    	<label class="form-check-label">PINHO</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F133">
							   		<label class="form-check-label">PLENITUD</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F134">
							    	<label class="form-check-label" >PO DESCOLORANTE</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F135">
							    	<label class="form-check-label" >POMPOM</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F136">
							    	<label class="form-check-label" >PRATICA</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F137">
							    	<label class="form-check-label" >PREMIUM</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F138">
							    	<label class="form-check-label">PRENDEDOR CHUP</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F139">
							   		<label class="form-check-label">PRINCESAS</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F140">
							    	<label class="form-check-label" >PROT DIAR ANTIBAC</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F141">
							    	<label class="form-check-label" >PROT DIAR DAYS</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F142">
							    	<label class="form-check-label" >PROT DIAR SPORT</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F143">
							    	<label class="form-check-label" >REGULAR</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F144">
							    	<label class="form-check-label">RENA</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F145">
							   		<label class="form-check-label">ROLL ON</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F146">
							    	<label class="form-check-label" >SAB INT EVE</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F147">
							    	<label class="form-check-label" >SAB INT FRESH</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F148">
							    	<label class="form-check-label" >SABONETE FLOR DE YPE</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F149">
							    	<label class="form-check-label" >SAPONACEO</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F150">
							    	<label class="form-check-label">SCOTT</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F151">
							   		<label class="form-check-label">SH COLOR</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F152">
							    	<label class="form-check-label" >SH KIDS</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F153">
							    	<label class="form-check-label" >SH MEN</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F154">
							    	<label class="form-check-label" >SH MEUS CACHOS</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F155">
							    	<label class="form-check-label" >SH NEUTROX</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F156">
							    	<label class="form-check-label">SH OX</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F157">
							   		<label class="form-check-label">SH SECO KARINA</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F158">
							    	<label class="form-check-label" >SH SOU LINDA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F159">
							    	<label class="form-check-label" >SH TRA LA LA BABY</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F160">
							    	<label class="form-check-label" >SH TRA LA LA KIDS</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F161">
							    	<label class="form-check-label" >SH TRANSFORMES</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F162">
							    	<label class="form-check-label">SH VITAY</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F163">
							   		<label class="form-check-label">SHAMPOO SK</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F164">
							    	<label class="form-check-label" >SHAMPOO SK MEN</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F165">
							    	<label class="form-check-label" >SHAVER</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F166">
							    	<label class="form-check-label" >SKALA</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F167">
							    	<label class="form-check-label" >SOLEIL</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F168">
							    	<label class="form-check-label">SOU LINDA</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F169">
							   		<label class="form-check-label">SOUL POWER</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F170">
							    	<label class="form-check-label" >STICKS</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F171">
							    	<label class="form-check-label" >SUCOS</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F172">
							    	<label class="form-check-label" >SUPLEMENTO ALIMENTAR</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F173">
							    	<label class="form-check-label" >SUPREME</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F174">
							    	<label class="form-check-label">SUPREME CARE</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F175">
							   		<label class="form-check-label">TALCO EM PO</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F176">
							    	<label class="form-check-label" >TESOURA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F177">
							    	<label class="form-check-label" >TINT PERMANENTE</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F178">
							    	<label class="form-check-label" >TINTURA</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F179">
							    	<label class="form-check-label" >TINTURA S/AMONIA</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F180">
							    	<label class="form-check-label">TIXAN</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F181">
							   		<label class="form-check-label">TOA UMED</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F182">
							    	<label class="form-check-label" >TONALIZ MASC S/ AMONIA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F183">
							    	<label class="form-check-label" >TONALIZANTE BC</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F184">
							    	<label class="form-check-label" >TRA LA LA BABY</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F185">
							    	<label class="form-check-label" >TRA LA LA KIDS</label>
							    </td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F186">
							    	<label class="form-check-label">TRANSFORMES</label>
							   	</td>
							</tr>
							
							<tr>									
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F187">
							   		<label class="form-check-label">UMIDIFICADOR</label>
							  	</td>
								<td>
									<input type="checkbox" class="form-check-input" name="familia" value="F188">
							    	<label class="form-check-label" >UTENSILIOS</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F189">
							    	<label class="form-check-label" >XUXINHA</label>
							    </td>
							    <td>
							    	<input type="checkbox" class="form-check-input" name="familia" value="F190">
							    	<label class="form-check-label" >YPE</label>
							    </td>
							    <td><input type="checkbox" class="form-check-input" name="familia" value="F191">
							    	<label class="form-check-label" >ZERO CAL</label>
							    </td>
<!-- 								<td> -->
<!-- 									<input type="checkbox" class="form-check-input" name="familia" value=""> -->
<!-- 							    	<label class="form-check-label"></label> -->
<!-- 							   	</td> -->
							</tr>
							
							
							
							</table>
							
						</fieldset>				
				
				</form>
			
			
			
			
			
			
			
			
			
<!-- 				<form action="indFocoParConfig" method="post"> -->
					 
<!-- 					  <div class="form-group inline"> -->
<!-- 					    <div> -->
<!-- 					    	<label for="exampleFormControlSelect1">Filtro</label> -->
<!-- 					    </div> -->
<!-- 					    	<div id="cxSelIndFoco" class="inline">	 -->
<!-- 							    <select class="form-control inline" id="exampleFormControlSelect1" name="industria"> -->
<!-- 							      <option value="null">GERAL</option> -->
<!-- 							      <option value="kimberly">RCA</option> -->
<!-- 							      <option value="ONTEX">RCA - ONTEX</option> -->
<!-- 							      <option value="kimberly">REGIÕES - KC</option> -->
<!-- 								  <option value="kimberly">***</option> -->
<!-- 								  <option value="kimberly">***</option> -->
<!-- 								  <option value="kimberly">***</option> -->
<!-- 								  <option value="kimberly">***</option> -->
<!-- 								  <option value="kimberly">***</option> -->
<!-- 								  <option value="kimberly">***</option> -->
<!-- 							    </select> -->
<!-- 							 </div> -->
<!-- 							 <span> -->
<!-- 							     <input class="btn" type="submit" value="LISTAR" /> -->
<!-- 					 	 	</span> -->
<!-- 						</div> -->
						
<!-- 						 <div class="form-group inline"> -->
<!-- 					    <div> -->
<!-- 					    	<label for="exampleFormControlSelect1">INDÚSTRIA</label> -->
<!-- 					    </div> -->
<!-- 					    	<div id="cxSelIndFoco" class="inline">	 -->
<!-- 							    <select class="form-control inline" id="exampleFormControlSelect1" name="industria"> -->
<!-- 								    <option value="TODAS">TODAS INDÚSTRIAS</option> -->
<!-- 								    <option value="36511">ONTEX</option> -->
<!-- 									<option value="KC">KIMBERLY</option> -->
<!-- 									<option value="CARTA">CARTA FABRIL</option> -->
<!-- 									<option value="LM">BIC</option> -->
<!-- 									<option value="PH">PHISÁLIA</option> -->
<!-- 									<option value="YPE">YPE</option> -->
<!-- 									<option value="1.10">PAM PAM</option> -->
<!-- 									<option value="1.12">SU BELLO</option> -->
<!-- 									<option value="12499">BARUEL</option>	 -->
<!-- 							    </select> -->
<!-- 							 </div> -->
<!-- 							 <span> -->
<!-- 							     <input class="btn btn-primary" type="submit" value="PROCESSAR" /> -->
<!-- 					 	 	</span> -->
<!-- 						</div> -->
<!-- 				 </form> -->
			
								
				
					
			</div>

		</div>
	
	 </main>






	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>