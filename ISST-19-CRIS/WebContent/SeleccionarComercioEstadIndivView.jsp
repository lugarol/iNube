<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Seleccionar Comercio Estadísticas Individuales View</title>
		<link rel="stylesheet" type="text/css" href="css/seleccionarComercioEstadIndiv_styles.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	</head>
	
	<body>
	
		<nav class="navbar sticky-top navbar-expand navbar-light bg-light">
			<a class="navbar-brand" href="LoginServlet">
				<img src="img/logo.png" height="50"/>
			</a>
			
			<ul class="navbar-nav mr-auto">
				<shiro:user>
					<shiro:hasRole name="admin">
						<li class="nav-item">
							<a class="nav-link" href="AdminServlet">Menú de admin</a>
						</li>
					</shiro:hasRole>
				
					<shiro:lacksRole name="admin">
						<li class="nav-item">
							<a class="nav-link" href="LoginServlet">Home</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="MenuComercianteServlet">Menú</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="SeleccionarComercioEstadIndivServlet">Estadísticas individuales</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="SeleccionarComercioEstadComparServlet">Estadísticas comparadas</a>
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
			
			<shiro:guest>
				<form class="form-inline my-2 my-lg-0" action="CreateComercianteServlet" method="get">
					<button class="btn btn-primary btn-lg" type="submit">Regístrate</button>
				</form>
			</shiro:guest>
			
		</nav>
		
		<div class="container">
			<shiro:user>
				<shiro:hasRole name="comerciante">			
					<h1 class="mb-3 mt-3">Vista de comerciante</h1>
					
					<section class="col-md-12">
						<h3 class="mb-3">Mis comercios</h3>
						<table class="table table-bordered table-sm table-hover">
							<thead class="thead-light">
								<tr>
									<th class="text-center">MerchantId</th>
									<th class="text-center">Nombre del comercio</th>
									<th class="text-center">Sector</th>
									<th class="text-center">CP</th>
									<th class="text-center">Banco</th>
									<th class="text-center">Núm. ventas</th>
									<th class="text-center">Ver estadísticas individuales</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${misComercios}" var="comercio">
									<tr>
										<td class="align-middle text-center">${comercio.merchantId}</td>
										<td class="align-middle text-center">${comercio.nombreComercio}</td>
										<td class="align-middle text-center">${comercio.sector}</td>
										<td class="align-middle text-center">${comercio.cp}</td>
										<td class="align-middle text-center">${comercio.banco}</td>
										<td class="align-middle text-center">${fn:length(comercio.ventas)}</td>
										<td class="align-middle text-center">
											<form action="VerComercioEstadIndivServlet" method="get">
												<input type="hidden" name="merchantId" value="${comercio.merchantId}"/>
												<button class="btn btn-outline-primary btn-sm btn-block" type="submit">
													Ver comercio <span class="font-weight-bold">${comercio.nombreComercio}</span> 
												</button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<c:if test="${empty misComercios}">
							<h5 class="mb-3">No tienes comercios</h5>
						</c:if>
					</section>
				</shiro:hasRole>
				
				<shiro:lacksRole name="comerciante">
					<h1 class="mb-3 mt-3"><shiro:principal />, no eres comerciante. No tienes permisos para ver esta página.</h1>
				</shiro:lacksRole>
			</shiro:user>
			
			<shiro:guest>
				<h1 class="mb-3 mt-3">No has iniciado sesión. Haz clic <a href="LogoutServlet">aquí</a> para iniciar sesión.</h1>
			</shiro:guest>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		
	</body>
</html>