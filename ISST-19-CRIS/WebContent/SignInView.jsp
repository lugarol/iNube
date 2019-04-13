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
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	</head>
	
	<body>
	
		<nav class="navbar sticky-top navbar-expand navbar-light bg-light">
			<a class="navbar-brand" href="LoginServlet">
				<img src="img/logo.png" height="50"/>
			</a>
			
			<ul class="navbar-nav mr-auto">
				<shiro:hasRole name="admin">
					<li class="nav-item">
						<a class="nav-link" href="AdminServlet">Menú de admin</a>
					</li>
				</shiro:hasRole>
				
				<shiro:user>
					<shiro:lacksRole name="admin">
						<li class="nav-item">
							<a class="nav-link" href="LoginServlet">Home</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="MenuComercianteServlet">Menú</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="SeleccionarComercioEstadIndivServlet">Estadísticas individuales</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">Estadísticas comparadas</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="GestionarComercianteServlet">Gestiona tu cuenta</a>
						</li>
					</shiro:lacksRole>
				</shiro:user>
			</ul>
			
			<shiro:guest>
				<form class="form-inline my-2 my-lg-0" action="LoginServlet" method="get">
					<button class="btn btn-primary btn-lg" type="submit">Inicia sesión</button>
				</form>
			</shiro:guest>
			
		</nav>
		
		<div class="container">
			<shiro:guest>
				<form class="form-signup-comerciante" action="CreateComercianteServlet" method="post">
					<h1 class="h3 mb-3 font-weight-normal">Regístrate como comerciante</h1>
					<input type="text" class="form-control" name="usuario" placeholder="Usuario" required autofocus>
					<input type="password" class="form-control" name="password1" placeholder="Password" required>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Registrarse</button>
				</form>
			</shiro:guest>
		
			<shiro:user>
		    	¡Ya estás registrado, <shiro:principal />! Haz clic <a href="LogoutServlet">aquí</a> para cerrar sesión.
			</shiro:user>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
		
	</body>
</html>