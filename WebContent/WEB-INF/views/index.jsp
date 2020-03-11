<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/resources/img/index-logo.png" var="imagens"/>

<!doctype html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8"> 
    
    <title>PORTAL</title> 
    
    <link type="text/css" href="resources/css/portal.css" rel="stylesheet" />

  </head>

  <body class="index-body">
    <div class="index-geral">
      <div class="index-box-login">
        <div class="index-box-login-logo">
          <img class="index-logo" src=<c:url value="/resources/img/index-logo.png"/> width="200"/>  
        </div>

        <div class="index-box-login-formulario">
          <form name="loginform" method="post" action="home">

            <input class="index-box-login-formulario-email" type="email" placeholder="EMAIL" name="email" maxlength="40" value="" size="29">

            <input class="index-box-login-formulario-email" type="password" placeholder="SENHA" name="senha" maxlength="30" size="29">

            <input class="btn" type="submit" name="entrar" value="Entrar" >

            <div class ="textbox2">
              <a href="#" color:#ffffff>Esqueci minha senha</a>
            </div>
            
<!--             <div class ="textbox2"> -->
<!--               <a href="#" color:#ffffff>Cadastra-se</a> -->
<!--             </div>             -->

          </form>

        </div>  

      </div>

    </div>

  </body>
</html>