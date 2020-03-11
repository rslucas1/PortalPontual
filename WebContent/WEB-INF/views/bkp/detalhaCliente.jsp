<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Pontual | Plano de Cobertura</title>

<!-- Bootstrap -->
<link type="text/css" href="resources/css/bootstrap.min.css"
	rel="stylesheet">
<link type="text/css" href="resources/css/estilo.css" rel="stylesheet" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<c:import url="cabecalho.jsp" />

	<div class="container">
		<div class="row painel-navegacao">
			<form>
				<div class="row">
					<fieldset border=1>
						<div class="col-sm-1"></div>
						<div class="col-sm-1">
							<label>Código</label>
							<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.cd_cliente}" readonly>
						</div>
						<div class="col-sm-7">
							<label>Razão Social</label>
							<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.desc_cliente}"readonly>
						</div>
						<div class="col-sm-1">
							<label>TP Cliente</label>
							<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.tp_Cli}" readonly>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-1"></div>
						<div class="col-sm-3">
							<label>Fantasia</label>
							<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.fantasia}" readonly>
						</div>
						<div class="col-sm-3">
							<label>CNPJ/CGC</label>
							<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.cgc_cpf}" readonly>
						</div>
						<div class="col-sm-3">
							<label>Grupo</label>
							<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.grupoCli}" readonly>
						</div>
					</fieldset>
				</div>
				
				<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-6">
						<label>Logradouro</label>
						<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.logradouro}" readonly>
					</div>
					<div class="col-sm-1">
						<label>Numero</label>
						<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.numero}" readonly>
					</div>
					<div class="col-sm-2">
						<label>CEP</label>
						<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.cep}" readonly>
					</div>

				</div>
				
				<div class="row">
					<div class="col-sm-1"></div>

					<div class="col-sm-3">
						<label>Segmento</label>
						<input class="form-control form-control-sm" type="text" " value="${clienteDetalhado.segmento}" readonly>
					</div>
					<div class="col-sm-2">
						<label>Area</label>
						<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.area}" readonly>
					</div>
					

					<div class="col-sm-2">
						<label>bairro</label>
						<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.bairro}" readonly>
					</div>
				</div>
				

				
				<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-3">
						<label>Municipio</label>
						<input class="form-control form-control-sm" type="text" value="${clienteDetalhado.municipio}" readonly>
					</div>
					<div class="col-sm-3">
						<label>Distrito</label>
						<input class="form-control form-control-sm" type="text"value=" ${clienteDetalhado.distrito}"readonly>
					</div>

				</div>
				
				
			</form>
		</div>
	</div>


	<c:import url="rodape.jsp" />
</body>
</html>