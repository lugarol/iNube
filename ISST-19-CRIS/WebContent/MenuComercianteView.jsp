<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Menu Comerciante View</title>
		<link rel="stylesheet" type="text/css" href="css/menuComerciante_styles.css">
	</head>
	
	<body>
		
		<shiro:user>
	    	¡Bienvenido, <shiro:principal />! Haz clic <a href="LogoutServlet">aquí</a> para cerrar sesión.
	    	
	    	<hr>
	    	
	    	<h3>Consultar estadísticas individuales</h3>
	    	<form action="SeleccionarComercioEstadIndivServlet" method="get">
	    		<button>Estadísticas individuales</button>
	    	</form>
	    	
	    	<h3>Consultar estadísticas comparadas</h3>
	    	<form action="" method="get">
				<button>En construcción</button>	
	    	</form>
	    	
	    	<h3>Gestionar tu cuenta (añadir, modificar o eliminar comercios)</h3>
	    	<form action="GestionarComercianteServlet" method="get">
	    		<button type="submit">Gestionar</button>
	    	</form>
	    	
		</shiro:user>
		
		<shiro:guest>
			No has iniciado sesión. Haz clic <a href="LogoutServlet">aquí</a> para iniciar sesión.
		</shiro:guest>
		
	</body>
</html>