<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>TESTE B</title>

<!-- Bootstrap -->
<link type="text/css" href="resources/css/bootstrap.min.css"
	rel="stylesheet">
<link type="text/css" href="resources/css/estiloB.css" rel="stylesheet">
<link type="text/css" href="resources/css/estilo.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/js.js"></script>

<script>
	function validarSenha() {
		var senha1 = document.f1.senha1.value
		var senha2 = document.f1.senha2.value

		
		if(senha1.length<6 ||senha1.length>10){
			alert("sua nova senha deverá ter entre 6 a 10 caracteres !")
			return false;
		}else{
			if (senha1 == senha2) {
				alert("OK, entre no sistema com a NOVA senha")
			} else {
				alert("VOCÊ DIGITOU SENHAS DIFERENTES")
				return false;
			}
			
		}
		
	}
</script>
</head>
<body>
	<h3 class="novaSenha-campos">Crie um nova senha !</h3>

	<form action="alteraSenha" method="post" name="f1">
		<div class="inline central-painel">
			<input class="form-control novaSenha-campos" name="senha1"
				type="password" placeholder="Digite a nova senha" size="25">
			<input class="form-control novaSenha-campos" name="senha2"
				type="password" placeholder="Repita a senha" size="25">
			<button type="submit" class="btn btn-primary novaSenha-campos"
				onClick="validarSenha()">OK</button>
		</div>
	</form>


</body>
</html>