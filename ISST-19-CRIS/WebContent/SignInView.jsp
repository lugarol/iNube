<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Sign in View</title>
		<link rel="stylesheet" type="text/css" href="css/signIn_styles.css">
	</head>
	
	<body>
	
		<!-- <img src="img/beagle.jpg"> -->
				
		<shiro:guest>
			<h2>Registrarse como comerciante</h2>
			<form action="CreateComercianteServlet" method="post">
				User: <input type="text" name="usuario" placeholder="Email" />
				Contraseña: <input type="password" name="password1" placeholder="Contraseña" />
				<button type="submit">Registrarse</button>
			</form>
		</shiro:guest>
	
		<shiro:user>
	    	¡Ya estás registrado, <shiro:principal />! Haz clic <a href="LogoutServlet">aquí</a> para cerrar sesión.
		</shiro:user>
		
	</body>
</html>