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
	</head>
	
	<body>
		<shiro:user>
		    Welcome back, <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
		</shiro:user>
		
		<hr>
		
		<shiro:hasRole name="admin">
			<h2>Vista de administrador</h2>
		
			<section class="comerciantes">
				<h3>Crear comerciante</h3>
				<form action="CreateComercianteServlet" method="post">
					<p>Usuario: <input type="text" name="usuario"/><p>
					<p>Password: <input type="password" name="password"/><p>
					<p><button type="submit">Crear comerciante</button></p>
				</form>
				
				<h3>Lista de comerciantes</h3>
				<table border="1">
					<tr>
						<th>Id</th>
						<th>Usuario</th>
						<th>Nº comercios</th>
					</tr>
					<c:forEach items="${allComerciantes}" var="comerciantei">
						<tr>
							<td>${comerciantei.id}</td>
							<td>${comerciantei.usuario}</td>
							<td>${fn:length(comerciantei.comercios)}</td>
						</tr>
					</c:forEach>
				</table>
			</section>
			
			<hr>
			
			<section class="ventas">
				<h3>Insertar venta</h3>
				<form action="CreateVentaServlet" method="post">
					<p>Día y hora: <input type="datetime-local" name="date"/></p>
					<p>Importe: <input type="number" name="price" step=".01"/></p>
					<p>
						Comercio:
						<select name="comercio">
							<option value="" disabled selected>Elegir comercio</option>
							<c:forEach items="${allComercios}" var="comercioi">
								<option value="${comercioi.merchantId}">
									Nombre: ${comercioi.nombreComercio} / sector: ${comercioi.sector} / CP: ${comercioi.cp} / comerciante: ${comercioi.comerciante.usuario}
								</option>
							</c:forEach>
						</select> 
					</p>
					<p>
						Comprador:
						<select name="comprador">
							<option value="" disabled selected>Elegir comprador</option>
							<c:forEach items="${allClientes}" var="clientei">
								<option value="${clientei.id}">
									cp: ${clientei.cp} / sexo <c:if test="${clientei.sexo == 0}">H</c:if><c:if test="${clientei.sexo == 1}">M</c:if> / edad ${clientei.edad}
								</option>
							</c:forEach>
						</select>
					</p>
					<p><button type="submit">Insertar venta</button></p>
				</form>
				
				<h3>Lista de ventas</h3>
				<table border="1">
					<tr>
						<th>Id</th>
						<th>Fecha</th>
						<th>Importe</th>
						<th>Nombre comercio</th>
						<th>Id persona</th>
					</tr>
					<c:forEach items="${allVentas}" var="ventai">
						<tr>
							<td>${ventai.id}</td>
							<!-- <td>${ventai.fecha}</td> -->
							<!-- <td><fmt:formatDate type="both" value="${ventai.fecha}" /></td> -->
							<!-- https://www.tutorialspoint.com/jsp/jstl_format_formatdate_tag.htm -->
							<td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${ventai.fecha}" /></td>
							<td>${ventai.importe}</td>
							<td>${ventai.comercio.nombreComercio}</td>
							<td>${ventai.persona.id}</td>
						</tr>
					</c:forEach>
				</table>
			</section>
			
			<hr>

			<section class="clientes">
				<h3>Insertar cliente (comprador)</h3>
				<form action="CreateClienteServlet" method="post">
					<p>CP: <input type="number" name="cp"/></p>
					<p>
						Sexo:
						Hombre <input type="radio" name="sex" value="0" />
						Mujer <input type="radio" name="sex" value="1" />
					</p>
					<p>Edad: <input type="number" name="age"/></p>
					<p><button type="submit">Insertar cliente (comprador)</button></p>
				</form>
				
				<h3>Lista de clientes</h3>
				<table border="1">
					<thead>
						<tr>
							<th>Id</th>
							<th>CP</th>
							<th>Edad</th>
							<th>Sexo</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allClientes}" var="clientei">
							<tr>
								<td>${clientei.id}</td>
								<td>${clientei.cp}</td>
								<td>${clientei.edad}</td>
								<td>
									<c:if test="${clientei.sexo == 0}">Hombre</c:if>
									<c:if test="${clientei.sexo == 1}">Mujer</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
			
		</shiro:hasRole>
		
		<shiro:lacksRole name="admin">
			<p>No tienes permisos para ver esta página</p>
		</shiro:lacksRole>
		
	</body>
</html>