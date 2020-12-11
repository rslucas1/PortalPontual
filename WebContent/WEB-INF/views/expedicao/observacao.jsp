<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<c:import url="../head.jsp" />

<body>
	<c:import url="../cabecalho.jsp" />
<%-- 	<c:import url="../menuLateral.jsp" /> --%>

	
	<main>
		<div class="Painel">
			<div class="subPainel900">
				<H3 class="user-btn-titulo">Observacao Pedido ${pedido}</H3>
				
				<form  method="post" action="editaObservacaoExpedicao?nu_ped=${expedicaoCarregamento.nu_ped}">
					<textarea rows="10" cols="120" maxlength="500" name="texto">${texto}</textarea>
					<input style="margin-top:20px;" class="btn btn-success" type="submit" name="operacao" value="gravar" />
					
					<input type="button" style="margin-top:20px;" class="btn btn-danger" onclick="window.close()" value="Fechar" />
					
					<script>
						document.getElementById("#botaoOuLink").onclick = function()
						{
						    window.close();
						}
						
					</script>
				</form>
				
			</div>

		</div>
	
	 </main>



	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>