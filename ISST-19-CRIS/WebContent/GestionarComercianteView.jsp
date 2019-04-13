<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Gestionar Comerciante View</title>
		<link rel="stylesheet" type="text/css" href="css/gestionarComerciante_styles.css">
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
			
			<shiro:user>
				<form class="form-inline my-2 my-lg-0" action="LogoutServlet" method="get">
					<button class="btn btn-primary btn-lg" type="submit">Cerrar sesión</button>
				</form>
			</shiro:user>
			
		</nav>
	
		<div class="container">
			<shiro:hasRole name="comerciante">
			
				<h1 class="mb-4">Vista de comerciante</h1>
				
				<section>
					<h3>Mis comercios</h3>
					<table class="table table-striped table-bordered table-sm" border="1">
						<thead class="thead-light">
							<tr>
								<th>MerchantId</th>
								<th>Nombre del comercio</th>
								<th>Sector</th>
								<th>CP</th>
								<th>Banco</th>
								<th>Núm. ventas</th>
								<th>Editar comercio</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${misComercios}" var="comercio">
								<tr>
									<td class="align-middle">${comercio.merchantId}</td>
									<td class="align-middle">${comercio.nombreComercio}</td>
									<td class="align-middle">${comercio.sector}</td>
									<td class="align-middle">${comercio.cp}</td>
									<td class="align-middle">${comercio.banco}</td>
									<td class="align-middle">${fn:length(comercio.ventas)}</td>
									<td class="align-middle">
										<form action="EditComercioServlet" method="get">
											<input type="hidden" name="merchantId" value="${comercio.merchantId}"/>
											<button class="btn btn-outline-primary" type="submit">Editar comercio "${comercio.nombreComercio}"</button>
										</form>
									</td>
								</tr>
							</c:forEach>	
						</tbody>						
					</table>
				</section>
				
				<section>
					<h3>Crear comercio</h3>
					<form action="CreateComercioServlet" method="post">
						<p>MerchantId: <input type="text" name="merchantId"/><p>
						<p>Nombre del comercio: <input type="text" name="nombreComercio"/><p>
						<p>Sector: <input type="text" name="sector"/><p>
						<p>Código postal: <input type="number" name="cp"/><p>
						<p>Banco: <input type="text" name="banco"/><p>
						<p><input type="hidden" name="idComerciante" value="${idComerciante}" /></p>
						<p><button class="btn btn-outline-primary" type="submit">Crear comercio</button></p>
					</form>
				</section>
				
			</shiro:hasRole>
			
			<shiro:lacksRole name="comerciante">
				<h1><shiro:principal />, no eres comerciante. No tienes permisos para ver esta página.</h1>
			</shiro:lacksRole>
			
			<shiro:guest>
				<h1>No has iniciado sesión. Haz clic <a href="LogoutServlet">aquí</a> para iniciar sesión.</h1>
			</shiro:guest>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		
	</body>
</html>