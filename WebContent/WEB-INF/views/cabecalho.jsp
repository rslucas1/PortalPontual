<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <div class="cabecalho "> -->
<!-- 	<div class="logoCabecalho inline"> -->
<!-- 		<img class="logo_cabecalho" src=<c:url value="/resources/img/index-logo.png"/> /> -->
<!-- 	</div> -->
	
<%-- 	<div class="descUsuario">${logado.cd_target} - ${logado.nome}</div> --%>
	
<!-- </div> -->

    <div class="cabecalho">
    
    
      <div class="logoCabecalho inline">
     	 <img class="img_logo_cabecalho" src=<c:url value="/resources/img/logo-ascendente.png"/> />
      </div>
      
     <div class="descUsuario inline">
     	${logado.cd_target} - ${logado.nome}
     
     </div>
	 
      
      
      
      
      

     
      
    </div>
