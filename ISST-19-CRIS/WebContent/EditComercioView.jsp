<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Comercio View</title>
		<link rel="stylesheet" type="text/css" href="css/editComercio_styles.css">
	</head>
	
	<body>
		
		<shiro:hasRole name="comerciante">
			<p>¡Bienvenido, <shiro:principal />! Haz clic <a href="LogoutServlet">aquí</a> para cerrar sesión.</p>
			
			<hr>
			
			<p><a href="GestionarComercianteServlet">&lt; Volver a Gestionar comerciante</a></p>
		
			<h1>Vista de comerciante</h1>
			
			<section>
				<h3>Editar comercio</h3>
				<form action="EditComercioServlet" method="post">
					<p>MerchantId (no se puede cambiar): <input type="text" name="merchantId" value="${comercio.merchantId}" readonly/></p>
					<p>Nombre del comercio: <input type="text" name="nombreComercio" value="${comercio.nombreComercio}"/><p>
					<p>Sector: <input type="text" name="sector" value="${comercio.sector}"/><p>
					<p>Código postal: <input type="number" name="cp" value="${comercio.cp}"/><p>
					<p>Banco: <input type="text" name="banco" value="${comercio.banco}"/><p>
					<p><input type="hidden" name="idComerciante" value="${comercio.comerciante.id}" /></p>
					<p><button type="submit">Actualizar comercio</button></p>
				</form>
			</section>
			
			<section>
				<!-- PTE HACER -->
				<h3>Eliminar comercio</h3>
				<form action="" method="post">
					<p><input type="hidden" name="merchantId" value="${comercio.merchantId}" /></p>
					<p><button type="submit">Eliminar comercio (en construcción)</button></p>
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