<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Editar Comercio View</title>
		<link rel="stylesheet" type="text/css" href="css/editComercio_styles.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
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
			<shiro:user>
				<shiro:hasRole name="comerciante">
					<button onclick="history.back(-1)" class="mt-3 btn btn-outline-primary"><span class="fa fa-arrow-left"></span> Volver a Gestión de cuenta</button>
				
					<h1 class="mb-3 mt-3">Vista de comerciante</h1>
					
					<!-- meter datos del comercio -->
					
					<section class="col-md-12">
						<h4 class="mb-3">Editar comercio</h4>
						<form action="EditComercioServlet" method="post">
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="merchantId">MerchantId</label>
									<input class="form-control" type="text" value="${comercio.merchantId}" name="merchantId" required readonly />
								</div>
								<div class="col-md-6 mb-3">
									<label for="nombreComercio">Nombre del comercio</label>
									<input class="form-control" type="text" value="${comercio.nombreComercio}" name="nombreComercio" required />
								</div>
								<div class="col-md-4 mb-3">
									<label for="sector">Sector</label>
									<input class="form-control" type="text" value="${comercio.sector}" name="sector" required />
								</div>
								<div class="col-md-4 mb-3">
									<label for="cp">Código postal</label>
									<input class="form-control" type="number" value="${comercio.cp}" name="cp" required />
								</div>
								<div class="col-md-4 mb-3">
									<label for="banco">Banco</label>
									<input class="form-control" type="text" value="${comercio.banco}" name="banco" required />
								</div>
								<div class="col-md-12 mb-3">
									<input type="hidden" name="idComerciante" value="${comercio.comerciante.id}" />
								</div>
								<div class="col-md-12 mb-3">
									<button class="btn btn-primary btn-lg btn-block" type="submit">Guardar cambios</button>
								</div>
							</div>
						</form>
					</section>
					
					<hr>
					
					<section class="col-md-12">
						<!-- PTE HACER -->
						<h4 class="mb-3">Eliminar comercio</h4>
						<form action="" method="post">
							<input type="hidden" name="merchantId" value="${comercio.merchantId}" />
							<button class="btn btn-danger btn-lg" type="submit">Eliminar comercio (en construcción)</button>
						</form>
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