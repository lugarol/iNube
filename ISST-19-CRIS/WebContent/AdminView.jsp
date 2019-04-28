<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Admin View</title>
		<link rel="stylesheet" type="text/css" href="css/admin_styles.css">
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
						<li class="nav-item active">
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
				<shiro:hasRole name="admin">
					<h1 class="mb-3 mt-3">Menú de administración</h1>
				
					<section class="col-md-12 comerciantes">
						<h3 class="mb-3">Crear comerciante</h3>
						<form action="CreateComercianteServlet" method="post">
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="usuario">Usuario</label>
									<input type="text" class="form-control" name="usuario" />
								</div>
								<div class="col-md-6 mb-3">
									<label for="password">Password</label>
									<input type="password" class="form-control" name="password1" />
								</div>
								<div class="col-md-12 mb-3">
									<input type="hidden" name="comingFromAdmin" value="yes" >
									<button class="btn btn-primary btn-lg btn-block" type="submit">Crear comerciante</button>
								</div>
							</div>
						</form>
						
						<h3 class="mb-3">Lista de comerciantes</h3>
						<table class="table table-bordered table-sm table-hover">
							<thead class="thead-light">
								<tr>
									<th class="text-center">Id</th>
									<th class="text-center">Usuario</th>
									<th class="text-center">Nº comercios</th>
									<th class="text-center">Borrar</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allComerciantes}" var="comerciantei">
									<tr>
										<td class="align-middle text-center">${comerciantei.id}</td>
										<td class="align-middle text-center">${comerciantei.usuario}</td>
										<td class="align-middle text-center">${fn:length(comerciantei.comercios)}</td>
										<td class="align-middle text-center">
											<form action="DeleteComercianteServlet" method="post">
												<input type="hidden" name="comercianteId" value="${comerciantei.id}" />
												<button class="btn btn-danger btn-sm btn-block" type="submit"><span class="fa fa-trash-alt"></span></button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</section>
					
					<hr>
					
					<section class="col-md-12 ventas">						
						<h3 class="mb-3">Insertar venta</h3>
						<form action="CreateVentaServlet" method="post">
							<div class="row">
								<div class="col-md-4 mb-3">
									<label for="date">Día y hora</label>
									<input class="form-control" type="datetime-local" name="date" required />
								</div>
								<div class="col-md-4 mb-3">
									<label for="price">Importe</label>
									<input class="form-control" type="number" name="price" step=".01" required />
								</div>
								<div class="col-md-4 mb-3">
									<label for="comprador">Comprador</label>
									<select name="comprador" class="custom-select w-100 d-block" required>
										<option value="" disabled selected>Elegir comprador</option>
										<c:forEach items="${allClientes}" var="clientei">
											<option value="${clientei.id}">
												CP: ${clientei.cp} / Sexo: <c:if test="${clientei.sexo == 0}">H</c:if><c:if test="${clientei.sexo == 1}">M</c:if> / Edad: ${clientei.edad}
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-12 mb-3">
									<label for="comercio">Comercio</label>
									<select name="comercio" class="custom-select w-100 d-block" required>
										<option value="" disabled selected>Elegir comercio</option>
										<c:forEach items="${allComercios}" var="comercioi">
											<option value="${comercioi.merchantId}">
												Nombre: ${comercioi.nombreComercio} / Sector: ${comercioi.sector} / CP: ${comercioi.cp} / Comerciante: ${comercioi.comerciante.usuario}
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-12 mb-3">
									<button class="btn btn-primary btn-lg btn-block" type="submit">Insertar venta</button>
								</div>
							</div>
						</form>
						
						<h3 class="mb-3">Lista de ventas</h3>
						<table class="table table-bordered table-sm table-hover">
							<thead class="thead-light">
								<tr>
									<th class="text-center">Id</th>
									<th class="text-center">Fecha</th>
									<th class="text-center">Importe</th>
									<th class="text-center">Nombre comercio</th>
									<th class="text-center">Id persona</th>
									<th class="text-center">Borrar</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allVentas}" var="ventai">
									<tr>
										<td class="align-middle text-center">${ventai.id}</td>
										<!-- https://www.tutorialspoint.com/jsp/jstl_format_formatdate_tag.htm -->
										<td class="align-middle text-center"><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${ventai.fecha}" /></td>
										<td class="align-middle text-center">${ventai.importe}</td>
										<td class="align-middle text-center">${ventai.comercio.nombreComercio}</td>
										<td class="align-middle text-center">${ventai.persona.id}</td>
										<td class="align-middle text-center">
											<form action="DeleteVentaServlet" method="post">
												<input type="hidden" name="ventaId" value="${ventai.id}" />
												<button class="btn btn-danger btn-sm btn-block" type="submit"><span class="fa fa-trash-alt"></span></button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</section>
					
					<hr>
		
					<section class="col-md-12 clientes">
						<h3 class="mb-3">Insertar cliente (comprador)</h3>
						<form action="CreateClienteServlet" method="post">
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="id">Id cliente</label>
									<input class="form-control" type="number" name="id" required />
								</div>
								<div class="col-md-6 mb-3">
									<label for="cp">Código postal</label>
									<input class="form-control" type="number" name="cp" required />
								</div>
								<div class="col-md-6 mb-3">
									<label for="sex">Sexo</label>
									<div class="col-md-12">
										<div class="row d-flex justify-content-center">
											<div class="col-md-4 custom-control-inline custom-radio">
												<input id="man" name="sex" type="radio" value="0" class="custom-control-input">
												<label class="custom-control-label" for="man">Hombre</label>
											</div>
											<div class="col-md-4 custom-control-inline custom-radio">
												<input id="woman" name="sex" type="radio" value="1" class="custom-control-input">
												<label class="custom-control-label" for="woman">Mujer</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 mb-3">
									<label for="age">Edad</label>
									<input class="form-control" type="number" name="age" required />
								</div>
								<div class="col-md-12 mb-3">
									<button class="btn btn-primary btn-lg btn-block" type="submit">Insertar cliente (comprador)</button>
								</div>
							</div>
						</form>
						
						<h3 class="mb-3">Lista de clientes</h3>
						<table class="table table-bordered table-sm table-hover">
							<thead class="thead-light">
								<tr>
									<th class="text-center">Id</th>
									<th class="text-center">CP</th>
									<th class="text-center">Edad</th>
									<th class="text-center">Sexo</th>
									<th class="text-center">Borrar</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allClientes}" var="clientei">
									<tr>
										<td class="align-middle text-center">${clientei.id}</td>
										<td class="align-middle text-center">${clientei.cp}</td>
										<td class="align-middle text-center">${clientei.edad}</td>
										<td class="align-middle text-center">
											<c:if test="${clientei.sexo == 0}">Hombre</c:if>
											<c:if test="${clientei.sexo == 1}">Mujer</c:if>
										</td>
										<td class="align-middle text-center">
											<form action="DeleteClienteServlet" method="post">
												<input type="hidden" name="clienteId" value="${clientei.id}" />
												<button class="btn btn-danger btn-sm btn-block" type="submit"><span class="fa fa-trash-alt"></span></button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</section>
					
				</shiro:hasRole>
				
				<shiro:lacksRole name="admin">
					<h1 class="mb-3 mt-3"><shiro:principal />, no tienes permisos para ver esta página</h1>
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