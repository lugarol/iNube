<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Comerciante View</title>
	</head>
	
	<body>
		<shiro:user>
		    Welcome back, <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
		</shiro:user>
		
		<hr>
		
		<shiro:hasRole name="comerciante">
			<h2>Vista de comerciante</h2>
		
			<h3>Crear comercio</h3>
			<form action="CreateComercioServlet" method="post">
				<p>MerchantId: <input type="text" name="merchantid"/><p>
				<p>Nombre del comercio: <input type="text" name="name"/><p>
				<p>Sector: <input type="text" name="sector"/><p>
				<p>Código postal: <input type="number" name="cp"/><p>
				<p>Banco: <input type="text" name="banco"/><p>
				<p><input type="hidden" name="comerciante_id" value="${comerciante_id}" /></p>
				<p><button type="submit">Crear comercio</button></p>
			</form>
			
			<h3>Lista de comercios</h3>
			<table border="1">
				<tr>
					<th>MerchantId</th>
					<th>Nombre comercio</th>
					<th>Sector</th>
					<th>CP</th>
					<th>Banco</th>
					<th>Comerciante</th>
					<th>Nº ventas</th>
					<th>Ver comercio</th>
				</tr>
				<c:forEach items="${my_comercios_list}" var="comercio">
					<tr>
						<td>${comercio.merchantId}</td>
						<td>${comercio.nombreComercio}</td>
						<td>${comercio.sector}</td>
						<td>${comercio.cp}</td>
						<td>${comercio.banco}</td>
						<td>${comercio.comerciante.usuario}</td>
						<td>${fn:length(comercio.ventas)}</td>
						<td>
							<form action="ComercioServlet" method="get">
								<input type="hidden" name="merchantid" value="${comercio.merchantId}"/>
								<button type="submit">Ver comercio ${comercio.nombreComercio}</button>
							</form>
						</td>
						<!-- Igual poner numero de ventas e importe? -->
					</tr>					
					
				</c:forEach>
			</table>
		</shiro:hasRole>
		
		<shiro:lacksRole name="comerciante">
			<p>No eres comerciante</p>
		</shiro:lacksRole>
		
	</body>
</html>