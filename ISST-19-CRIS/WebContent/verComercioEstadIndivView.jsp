<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Ver Comercio Estad Indiv View</title>
		<link rel="stylesheet" type="text/css" href="css/verComercioEstadIndiv_styles.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
		
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		
		<script type="text/javascript">
		    // Load the Visualization API and the piechart package.
		    google.load('visualization', '1.0', {'packages': ['corechart']});
		
		    // Set a callback to run when the Google Visualization API is loaded.
		    google.setOnLoadCallback(drawChart1_numVentasEImportePorHora);
		    google.setOnLoadCallback(drawChart2_numVentasPorSexo);
		    google.setOnLoadCallback(drawChart3_importeVentasPorSexo);
		    google.setOnLoadCallback(drawChart4_numVentasEImportePorEdad);
		    google.setOnLoadCallback(drawChart5_numVentasEImportePorDiaSemana);
		    google.setOnLoadCallback(drawChart6_numVentasEImportePorCP);
		    google.setOnLoadCallback(drawChart7_fidelidadClientes);
		    
		    function drawChart1_numVentasEImportePorHora() {
		    	var data1 = google.visualization.arrayToDataTable([
		    		['Hora', 'Núm. ventas', 'Importe ventas'],
		    		<c:forEach items="${numVentasEImportePorHora}" var="horai">
			    		['${horai.key}h', ${horai.value[0]}, ${horai.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options1 = {
	    			'title': 'Núm. ventas e importe por hora',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Núm. ventas'},
	    				1: {title: 'Importe ventas'}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16
		    	};
		    	
		    	var chart1 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_importe_por_hora'));
		    	chart1.draw(data1, options1);
		    }
		    
		    function drawChart2_numVentasPorSexo() {
		    	var data2 = google.visualization.arrayToDataTable([
		    		['Sexo', 'Núm. ventas'],
		    		['Hombres', ${numVentasHombres}],
		    		['Mujeres', ${numVentasMujeres}]
		    	]);
		    	
		    	var options2 = {
		    		'title': 'Núm. ventas por sexo',
		    		pieSliceText: 'label',
		    		tooltip :  {showColorCode: true},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16
		    	};
		    	
		    	var chart2 = new google.visualization.PieChart(document.getElementById('pie_chart_num_ventas_por_sexo'));
		    	chart2.draw(data2, options2);
		    }
		    
		    function drawChart3_importeVentasPorSexo() {
		    	var data3 = google.visualization.arrayToDataTable([
		    		['Sexo', 'Importe'],
		    		['Hombres', ${importeHombres}],
		    		['Mujeres', ${importeMujeres}]
		    	]);
		    	
		    	var options3 = {
		    		'title': 'Importe por sexo',
		    		pieSliceText: 'label',
		    		tooltip :  {showColorCode: true},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16
		    	};
		    	
		    	var chart3 = new google.visualization.PieChart(document.getElementById('pie_chart_importe_por_sexo'));
		    	chart3.draw(data3, options3);
		    }
		    
		    function drawChart4_numVentasEImportePorEdad() {
		    	var data4 = google.visualization.arrayToDataTable([
		    		['Edad', 'Núm. ventas', 'Importe ventas'],
		    		<c:forEach items="${numVentasEImportePorEdad}" var="edadi">
			    		['${edadi.key}', ${edadi.value[0]}, ${edadi.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options4 = {
	    			'title': 'Núm. ventas e importe por edad',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Núm. ventas'},
	    				1: {title: 'Importe ventas'}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16
		    	};
		    	
		    	var chart4 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_importe_por_edad'));
		    	chart4.draw(data4, options4);
		    }  
		    
		    function drawChart5_numVentasEImportePorDiaSemana() {
		    	var data5 = google.visualization.arrayToDataTable([
		    		['Día de la semana', 'Núm. ventas', 'Importe ventas'],
		    		<c:forEach items="${numVentasEImportePorDiaSemana}" var="diai">
			    		['${diai.key}', ${diai.value[0]}, ${diai.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options5 = {
	    			'title': 'Núm. ventas e importe por día de la semana',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Núm. ventas'},
	    				1: {title: 'Importe ventas'}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16
		    	};
		    	
		    	var chart5 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_importe_por_dia_semana'));
		    	chart5.draw(data5, options5);
		    }
		    
		    function drawChart6_numVentasEImportePorCP() {
		    	var data6 = google.visualization.arrayToDataTable([
		    		['CP', 'Núm. ventas', 'Importe ventas'],
		    		<c:forEach items="${numVentasEImportePorCp}" var="cpi">
			    		['${cpi.key}', ${cpi.value[0]}, ${cpi.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options6 = {
	    			'title': 'Núm. ventas e importe por CP (tu CP es ${comercio.cp})',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Núm. ventas'},
	    				1: {title: 'Importe ventas'}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16
		    	};
		    	
		    	var chart6 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_importe_por_cp'));
		    	chart6.draw(data6, options6);
		    }
		    
		    function drawChart7_fidelidadClientes() {
		    	var data7 = google.visualization.arrayToDataTable([
		    		['Veces', '%'],
		    		['1 vez', ${porcClientesUnaVez}],
		    		['2 veces', ${porcClientesDosVeces}],
		    		['3+ veces', ${porcClientesTresOMasVeces}]
		    	]);
		    	
		    	var options7 = {
	    			'title': 'Fidelidad clientes (veces que han venido)',
	    			bar: {groupWidth: '90%'},
	    			vAxis: {
	    				format: '#\'%\''
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16
		    	};
		    	
		    	var chart7 = new google.visualization.ColumnChart(document.getElementById('col_chart_fidelidad_clientes'));
		    	chart7.draw(data7, options7);
		    }
		    
		</script>
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
			
			<shiro:guest>
				<form class="form-inline my-2 my-lg-0" action="CreateComercianteServlet" method="get">
					<button class="btn btn-primary btn-lg" type="submit">Regístrate</button>
				</form>
			</shiro:guest>
			
		</nav>
		
		<div class="container">	
			<shiro:user>
				<shiro:hasRole name="comerciante">
					<button onclick="history.back(-1)" class="mt-3 btn btn-outline-primary"><span class="fa fa-arrow-left"></span> Volver</button>
					
					<h1 class="mb-3 mt-3">Vista de comercio</h1>
				
					<h3>Comercio</h3>
					<table class="table table-bordered table-sm">
						<thead class="thead-light">
							<tr>
								<th class="text-center">MerchantID</th>
								<th class="text-center">Nombre del comercio</th>
								<th class="text-center">Sector</th>
								<th class="text-center">CP</th>
								<th class="text-center">Banco</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="align-middle text-center">${comercio.merchantId}</td>
								<td class="align-middle text-center">${comercio.nombreComercio}</td>
								<td class="align-middle text-center">${comercio.sector}</td>
								<td class="align-middle text-center">${comercio.cp}</td>
								<td class="align-middle text-center">${comercio.banco}</td>
							</tr>
						</tbody>
					</table>
					
					<c:choose>
						<c:when test="${numVentas > 0}">
							<table class="table table-bordered table-sm">
								<thead class="thead-light">
									<tr>
										<th class="text-center">Núm. ventas</th>
										<th class="text-center">Importe total</th>
										<th class="text-center">Núm. clientes distintos</th>
										<th class="text-center">Importe medio</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="align-middle text-center">${numVentas}</td>
										<td class="align-middle text-center">${importeTotal}€</td>
										<td class="align-middle text-center">${numClientesDistintos}</td>
										<td class="align-middle text-center">${importeMedio}€</td>
									</tr>
								</tbody>
							</table>
							
							<div class="col-md-12">
						        <div class="py-3" id="col_chart_num_ventas_importe_por_hora"></div>
						    </div>
						    
						    <div class="row">
							    <div class="col-md-6">
									<div class="py-3" id="pie_chart_num_ventas_por_sexo"></div>
								</div>
								<div class="col-md-6">
									<div class="py-3" id="pie_chart_importe_por_sexo"></div>
								</div>
						    </div>
						    
							<div class="col-md-12">
								<div class="py-3" id="col_chart_num_ventas_importe_por_edad"></div>
							</div>
							
							<div class="col-md-12">
								<div class="py-3" id="col_chart_num_ventas_importe_por_dia_semana"></div>
							</div>
							
							<div class="col-md-12">
								<div class="py-3" id="col_chart_num_ventas_importe_por_cp"></div>
							</div>
							
							<div class="row">
								<div class="col-md-2">
									
								</div>
								<div class="col-md-8">
									<div class="py-3" id="col_chart_fidelidad_clientes"></div>
								</div>
								<div class="col-md-2">
									
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<h5>No hay ventas para este comercio</h5>
						</c:otherwise>
					</c:choose>
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