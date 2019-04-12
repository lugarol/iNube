<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login View</title>
		<link rel="stylesheet" type="text/css" href="css/login_styles.css">
		
	</head>
	
	<body>
	
		<!-- <img src="img/beagle.jpg"> -->
		
		<h1>iNube</h1>
		
		<shiro:guest>
			<h3>Registrarse como comerciante</h3>
			<form action="CreateComercianteServlet" method="get">
				<button type="submit">Registrarse</button>
			</form>
			
			<h3>Login</h3>
			<form action="LoginServlet" method="post">
				Email: <input type="text" name="usuario" placeholder="Email" />
				Password: <input type="password" name="password" placeholder="Password" />
				<button type="submit">Login</button>
			</form>
		</shiro:guest>
	
		<shiro:user>
	    	Welcome back, <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
		</shiro:user>
		
	</body>
</html>