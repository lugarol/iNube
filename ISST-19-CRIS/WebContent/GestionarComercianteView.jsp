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
							<a class="nav-link active luis" href="GestionarComercianteServlet">Gestiona tu cuenta</a>
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
					<h1 class="mb-3 mt-3">Gestiona tu cuenta</h1>
					
					<section class="col-md-12">
						<h3 class="mb-3">Mis comercios</h3>
						<table class="table table-bordered table-sm table-hover" border="1">
							<thead class="thead-light">
								<tr>
									<th class="text-center">MerchantId</th>
									<th class="text-center">Nombre del comercio</th>
									<th class="text-center">Sector</th>
									<th class="text-center">CP</th>
									<th class="text-center">Banco</th>
									<th class="text-center">Núm. ventas</th>
									<th class="text-center">Editar comercio</th>
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
											<form action="EditComercioServlet" method="get">
												<input type="hidden" name="merchantId" value="${comercio.merchantId}"/>
												<button class="btn btn-outline-primary btn-sm btn-block" type="submit">
													Editar comercio <span class="font-weight-bold">${comercio.nombreComercio}</span>
												</button>
											</form>
										</td>
									</tr>
								</c:forEach>	
							</tbody>						
						</table>
					</section>
					
					<hr>
					
					<section class="col-md-12">
						<h4 class="mb-3">Crear comercio</h4>
						<form action="CreateComercioServlet" method="post">
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="merchantId">MerchantId</label>
									<input class="form-control" type="text" name="merchantId" required />
								</div>
								<div class="col-md-6 mb-3">
									<label for="nombreComercio">Nombre del comercio</label>
									<input class="form-control" type="text" name="nombreComercio" required />
								</div>
								<div class="col-md-4 mb-3">
									<label for="sector">Sector</label>
									<input class="form-control" type="text" name="sector" required />
								</div>
								<div class="col-md-4 mb-3">
									<label for="cp">Código postal</label>
									<input class="form-control" type="number" name="cp" required />
								</div>
								<div class="col-md-4 mb-3">
									<label for="banco">Banco</label>
									<input class="form-control" type="text" name="banco" required />
								</div>
								<div class="col-md-12 mb-3">
									<input type="hidden" name="idComerciante" value="${idComerciante}" />
								</div>
								<div class="col-md-12 mb-3">
									<button class="btn btn-primary btn-lg btn-block" type="submit">Crear comercio</button>
								</div>
								
							</div>
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