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
	</head>
	
	<body>
		
		<shiro:hasRole name="comerciante">
			<p>¡Bienvenido, <shiro:principal />! Haz clic <a href="LogoutServlet">aquí</a> para cerrar sesión.</p>
			
			<hr>
			
			<p><a href="MenuComercianteServlet">&lt; Volver al menú</a></p>
		
			<h1>Vista de comerciante</h1>
			
			<section>
				<h3>Mis comercios</h3>
				<table border="1">
					<tr>
						<th>MerchantId</th>
						<th>Nombre del comercio</th>
						<th>Sector</th>
						<th>CP</th>
						<th>Banco</th>
						<th>Núm. ventas</th>
						<th>Editar comercio</th>
					</tr>
					<c:forEach items="${misComercios}" var="comercio">
						<tr>
							<td>${comercio.merchantId}</td>
							<td>${comercio.nombreComercio}</td>
							<td>${comercio.sector}</td>
							<td>${comercio.cp}</td>
							<td>${comercio.banco}</td>
							<td>${fn:length(comercio.ventas)}</td>
							<td>
								<form action="EditComercioServlet" method="get">
									<input type="hidden" name="merchantId" value="${comercio.merchantId}"/>
									<button type="submit">Editar comercio "${comercio.nombreComercio}"</button>
								</form>
							</td>
						</tr>
					</c:forEach>
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
					<p><button type="submit">Crear comercio</button></p>
				</form>
			</section>
			
		</shiro:hasRole>
		
		<shiro:lacksRole name="comerciante">
			<p>No eres comerciante. No tienes permiso para ver esta página.</p>
		</shiro:lacksRole>
		
		<shiro:guest>
			No has iniciado sesión. Haz clic <a href="LogoutServlet">aquí</a> para iniciar sesión.
		</shiro:guest>
		
	</body>
</html>