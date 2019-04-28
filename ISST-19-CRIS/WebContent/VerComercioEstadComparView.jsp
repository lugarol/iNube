<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Ver Comercio Estadísticas Comparadas View</title>
		<link rel="stylesheet" type="text/css" href="css/verComercioEstadIndiv_styles.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
		
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		
		<script type="text/javascript">
		    // Load the Visualization API and the piechart package.
		    google.load('visualization', '1.0', {'packages': ['corechart']});
		
		    // Set a callback to run when the Google Visualization API is loaded.		    
		    google.setOnLoadCallback(drawChart1_numVentasPorHoraMioYResto);
		    google.setOnLoadCallback(drawChart2_importePorHoraMioYResto);
		    google.setOnLoadCallback(drawChart5_numVentasPorEdadMioYResto);
		    google.setOnLoadCallback(drawChart6_importePorEdadMioYResto);
		    google.setOnLoadCallback(drawChart7_numVentasPorDiaSemanaMioYResto);
		    google.setOnLoadCallback(drawChart8_importePorDiaSemanaMioYResto);
		    google.setOnLoadCallback(drawChart9_numVentasPorCpMioYResto);
		    google.setOnLoadCallback(drawChart10_importePorCpMioYResto);
		    google.setOnLoadCallback(drawChart11_fidelidadClientesMioYResto);
		    
		    google.setOnLoadCallback(drawChart12_PieChartNumVentasHombres);
		    google.setOnLoadCallback(drawChart13_PieChartNumVentasMujeres);
		    google.setOnLoadCallback(drawChart14_PieChartImporteHombres);
		    google.setOnLoadCallback(drawChart15_PieChartImporteMujeres);
		    
		    function drawChart1_numVentasPorHoraMioYResto() {
		    	let data = google.visualization.arrayToDataTable([
		    		['Hora', 'Núm. mis ventas', 'Núm. resto ventas'],
		    		<c:forEach items="${numVentasPorHoraMioYResto}" var="horai">
			    		['${horai.key}h', ${horai.value[0]}/${numDiasDiferencia}, ${horai.value[1]}/${numDiasDiferencia}],
			    	</c:forEach>
		    	]);
		    	
		    	let options = {
	    			'title': 'Núm. ventas individuales y ventas totales por hora al día',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Núm. ventas mías'},
	    				1: {title: 'Núm. ventas resto'}
	    			},
	    			vAxis: {
	    				viewWindow: {
	    					min: 0,
	    					max: ${maxNumVentasPorHoraMioYResto}/${numDiasDiferencia}
	    				}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745'] // azul: #007bff, rojo: #dc3545, verde: #28a745
		    	};
		    	
		    	let chart = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_por_hora_mio_y_resto'));
		    	chart.draw(data, options);
		    }
		    
		    function drawChart2_importePorHoraMioYResto() {
		    	let data = google.visualization.arrayToDataTable([
		    		['Hora', 'Importe mis ventas', 'Importe resto ventas'],
		    		<c:forEach items="${importePorHoraMioYResto}" var="horai">
			    		['${horai.key}h', ${horai.value[0]}/${numDiasDiferencia}, ${horai.value[1]}/${numDiasDiferencia}],
			    	</c:forEach>
		    	]);
		    	
		    	let options = {
	    			'title': 'Importe ventas individuales y ventas totales por hora al día',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Importe mío'},
	    				1: {title: 'Importe resto'}
	    			},
	    			vAxis: {
	    				viewWindow: {
	    					min: 0,
	    					max: ${maxImporteVentasPorHoraMioYResto}/${numDiasDiferencia}
	    				}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745']
		    	};
		    	
		    	let chart = new google.visualization.ColumnChart(document.getElementById('col_chart_importe_por_hora_mio_y_resto'));
		    	chart.draw(data, options);
		    }
		    
		    function drawChart5_numVentasPorEdadMioYResto() {
		    	let data = google.visualization.arrayToDataTable([
		    		['Edad', 'Núm. mis ventas', 'Núm. resto ventas'],
		    		<c:forEach items="${numVentasPorEdadMioYResto}" var="edadi">
			    		['${edadi.key}', ${edadi.value[0]}/${numDiasDiferencia}, ${edadi.value[1]}/${numDiasDiferencia}],
			    	</c:forEach>
		    	]);
		    	
		    	let options = {
	    			'title': 'Núm. ventas individuales y ventas totales por edad al día',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Núm. ventas mías'},
	    				1: {title: 'Núm. ventas resto'}
	    			},
	    			vAxis: {
	    				viewWindow: {
	    					min: 0,
	    					max: ${maxNumVentasPorEdadMioYResto}/${numDiasDiferencia}
	    				}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745']
		    	};
		    	
		    	let chart = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_por_edad_mio_y_resto'));
		    	chart.draw(data, options);
		    }
		    
		    function drawChart6_importePorEdadMioYResto() {
		    	let data = google.visualization.arrayToDataTable([
		    		['Edad', 'Importe mis ventas', 'Importe resto ventas'],
		    		<c:forEach items="${importePorEdadMioYResto}" var="edadi">
			    		['${edadi.key}', ${edadi.value[0]}/${numDiasDiferencia}, ${edadi.value[1]}/${numDiasDiferencia}],
			    	</c:forEach>
		    	]);
		    	
		    	let options = {
	    			'title': 'Importe ventas individuales y ventas totales por edad al día',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Importe mío'},
	    				1: {title: 'Importe resto'}
	    			},
	    			vAxis: {
	    				viewWindow: {
	    					min: 0,
	    					max: ${maxImportePorEdadMioYResto}/${numDiasDiferencia}
	    				}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745']
		    	};
		    	
		    	let chart = new google.visualization.ColumnChart(document.getElementById('col_chart_importe_por_edad_mio_y_resto'));
		    	chart.draw(data, options);
		    }
		    
		    function drawChart7_numVentasPorDiaSemanaMioYResto() {
		    	let data = google.visualization.arrayToDataTable([
		    		['Día de la semana', 'Núm. mis ventas', 'Núm. resto ventas'],
		    		<c:forEach items="${numVentasPorDiaSemanaMioYResto}" var="diai">
			    		['${diai.key}', ${diai.value[0]}/${numDiasDiferencia}, ${diai.value[1]}/${numDiasDiferencia}],
			    	</c:forEach>
		    	]);
		    	
		    	let options = {
	    			'title': 'Núm. ventas individuales y ventas totales por día de la semana al día',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Núm. ventas mías'},
	    				1: {title: 'Núm. ventas resto'}
	    			},
	    			vAxis: {
	    				viewWindow: {
	    					min: 0,
	    					max: ${maxNumVentasPorDiaSemanaMioYResto}/${numDiasDiferencia}
	    				}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745']
		    	};
		    	
		    	let chart = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_por_dia_semana_mio_y_resto'));
		    	chart.draw(data, options);
		    }	    
		    
		    function drawChart8_importePorDiaSemanaMioYResto() {
		    	let data = google.visualization.arrayToDataTable([
		    		['Día de la semana', 'Importe mis ventas', 'Importe resto ventas'],
		    		<c:forEach items="${importePorDiaSemanaMioYResto}" var="diai">
			    		['${diai.key}', ${diai.value[0]}/${numDiasDiferencia}, ${diai.value[1]}/${numDiasDiferencia}],
			    	</c:forEach>
		    	]);
		    	
		    	let options = {
	    			'title': 'Importe ventas individuales y ventas totales por día de la semana al día',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Importe mío'},
	    				1: {title: 'Importe resto'}
	    			},
	    			vAxis: {
	    				viewWindow: {
	    					min: 0,
	    					max: ${maxImportePorDiaSemanaMioYResto}/${numDiasDiferencia}
	    				}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745']
		    	};
		    	
		    	let chart = new google.visualization.ColumnChart(document.getElementById('col_chart_importe_por_dia_semana_mio_y_resto'));
		    	chart.draw(data, options);
		    }
		    
		    function drawChart9_numVentasPorCpMioYResto() {
		    	let data = google.visualization.arrayToDataTable([
		    		['CP', 'Núm. mis ventas', 'Núm. resto ventas'],
		    		<c:forEach items="${numVentasPorCpMioYResto}" var="cpi">
			    		['${cpi.key}', ${cpi.value[0]}/${numDiasDiferencia}, ${cpi.value[1]}/${numDiasDiferencia}],
			    	</c:forEach>
		    	]);
		    	
		    	let options = {
	    			'title': 'Núm. ventas individuales y ventas totales por CP al día (tu CP es ${comercio.cp})',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Núm. ventas mías'},
	    				1: {title: 'Núm. ventas resto'}
	    			},
	    			vAxis: {
	    				viewWindow: {
	    					min: 0,
	    					max: ${maxNumVentasPorCpMioYResto}/${numDiasDiferencia}
	    				}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745']
		    	};
		    	
		    	let chart = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_por_cp_mio_y_resto'));
		    	chart.draw(data, options);
		    }
		    
		    function drawChart10_importePorCpMioYResto() {
		    	let data = google.visualization.arrayToDataTable([
		    		['CP', 'Importe mis ventas', 'Importe resto ventas'],
		    		<c:forEach items="${importePorCpMioYResto}" var="cpi">
			    		['${cpi.key}', ${cpi.value[0]}/${numDiasDiferencia}, ${cpi.value[1]}/${numDiasDiferencia}],
			    	</c:forEach>
		    	]);
		    	
		    	let options = {
	    			'title': 'Importe ventas individuales y ventas totales por CP al día (tu CP es ${comercio.cp})',
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Importe mío'},
	    				1: {title: 'Importe resto'}
	    			},
	    			vAxis: {
	    				viewWindow: {
	    					min: 0,
	    					max: ${maxImportePorCpMioYResto}/${numDiasDiferencia}
	    				}
	    			},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745']
		    	};
		    	
		    	let chart = new google.visualization.ColumnChart(document.getElementById('col_chart_importe_por_cp_mio_y_resto'));
		    	chart.draw(data, options);
		    }
		 	
		 	function drawChart11_fidelidadClientesMioYResto() {
		 		let data = google.visualization.arrayToDataTable([
		    		['Veces', '% mi comercio', '% resto de comercios'],
		    		['1 vez', ${porcMisClientesUnaVez}, ${porcRestoClientesUnaVez}],
		    		['2 veces', ${porcMisClientesDosVeces}, ${porcRestoClientesDosVeces}],
		    		['3+ veces', ${porcMisClientesTresOMasVeces}, ${porcRestoClientesTresOMasVeces}]
		    	]);
		    	
		 		let options = {
	    			'title': 'Fidelidad clientes (veces que han venido)',
	    			bar: {groupWidth: '90%'},
	    			vAxis: {
	    				format: '#\'%\''
	    			},
	    			legend: {position: 'top'},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745']
		    	};
		    	
		 		let chart = new google.visualization.ColumnChart(document.getElementById('col_chart_fidelidad_clientes_mio_y_resto'));
		    	chart.draw(data, options);
		    }
		 	
		 	function drawChart12_PieChartNumVentasHombres() {
		    	let data = google.visualization.arrayToDataTable([
		    		['Comercio', 'Núm. ventas por hombres (total)'],
		    		['Mi comercio', ${numMisVentasHombres}],
		    		['Resto', ${numRestoVentasHombres}]
		    	]);
		    	
		    	let options = {
		    		'title': 'Núm. ventas hombres',
		    		pieSliceText: 'none',
		    		tooltip :  {showColorCode: true},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745'] // azul: #007bff, rojo: #dc3545
		    	};
		    	
		    	let chart = new google.visualization.PieChart(document.getElementById('pie_chart_num_ventas_mias_por_sexo'));
		    	chart.draw(data, options);
		    }
		 	
		 	function drawChart13_PieChartNumVentasMujeres() {
		    	let data = google.visualization.arrayToDataTable([
		    		['Comercio', 'Núm. ventas por mujeres'],
		    		['Mi comercio', ${numMisVentasMujeres}],
		    		['Resto', ${numRestoVentasMujeres}]
		    	]);
		    	
		    	let options = {
		    		'title': 'Núm. ventas mujeres',
		    		pieSliceText: 'none',
		    		tooltip :  {showColorCode: true},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745'] // azul: #007bff, rojo: #dc3545
		    	};
		    	
		    	let chart = new google.visualization.PieChart(document.getElementById('pie_chart_num_ventas_resto_por_sexo'));
		    	chart.draw(data, options);
		    }
		    
		    function drawChart14_PieChartImporteHombres() {
		    	let data = google.visualization.arrayToDataTable([
		    		['Comercio', 'Importe ventas por hombres'],
		    		['Mi comercio', ${miImporteHombres}],
		    		['Resto', ${restoImporteHombres}]
		    	]);
		    	
		    	let options = {
		    		'title': 'Importe hombres',
		    		pieSliceText: 'none',
		    		tooltip :  {showColorCode: true},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745'] // azul: #007bff, rojo: #dc3545
		    	};
		    	
		    	let chart = new google.visualization.PieChart(document.getElementById('pie_chart_importe_mio_por_sexo'));
		    	chart.draw(data, options);
		    }
		    
		    function drawChart15_PieChartImporteMujeres() {
		    	let data = google.visualization.arrayToDataTable([
		    		['Comercio', 'Importe ventas por mujeres'],
		    		['Mi comercio', ${miImporteMujeres}],
		    		['Resto', ${restoImporteMujeres}]
		    	]);
		    	
		    	let options = {
		    		'title': 'Importe mujeres',
		    		pieSliceText: 'none',
		    		tooltip :  {showColorCode: true},
	    			fontName: 'Segoe UI Light',
	    			fontSize: 16,
	    			colors: ['#007bff', '#28a745'] // azul: #007bff, rojo: #dc3545
		    	};
		    	
		    	let chart = new google.visualization.PieChart(document.getElementById('pie_chart_importe_resto_por_sexo'));
		    	chart.draw(data, options);
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
						<li class="nav-item active">
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
					<form action="SeleccionarComercioEstadComparServlet" method="get">
						<button type="submit" class="mt-3 btn btn-outline-primary"><span class="fa fa-arrow-left"></span> Volver</button>
					</form>
					
					<h1 class="mb-3 mt-3">Vista de comercio</h1>
				
					<h3>Mi comercio</h3>
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
						<c:when test="${numMisVentas > 0}">
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
										<td class="align-middle text-center">${numMisVentas}</td>
										<td class="align-middle text-center">${miImporteTotal}€</td>
										<td class="align-middle text-center">${numMisClientesDistintos}</td>
										<td class="align-middle text-center">${miImporteMedio}€</td>
									</tr>
								</tbody>
							</table>
							
							<h5 class="text-center">Resto de comercios</h5>
							<div class="row">
								<div class="col-md-5"></div>
								<table class="table table-bordered table-sm col-md-2 text-center">
									<thead class="thead-light">
										<tr>
											<th class="text-center">Importe medio</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="align-middle text-center">${restoImporteMedio}€</td>
										</tr>
									</tbody>
								</table>
								<div class="col-md-5"></div>
							</div>
							
							<form action="VerComercioEstadComparServlet" method="get">
								<div class="row">
									<div class="col-md-4 mb-3">
										<label for="fechaInicial">Fecha inicio</label>
										<input class="form-control" type="datetime-local" name="fechaInicial" value="${fechaInicialStr}" required />
									</div>
									<div class="col-md-4 mb-3">
										<label for="fechaFinal">Fecha final</label>
										<input class="form-control" type="datetime-local" name="fechaFinal" value="${fechaFinalStr}" required />
									</div>
									<div class="col-md-4 mb-3">
										<label>&nbsp;</label>
										<input type="hidden" name="merchantId" value="${comercio.merchantId}" />
										<button type="submit" class="btn btn-info btn-block">Restringir fechas</button>
									</div>
									<!-- 
									<div class="col-md-3 mb-3">
										<label>&nbsp;</label>
										<div>
											${numDiasDiferencia}
											<c:if test="${numDiasDiferencia != 1}">días</c:if>
											<c:if test="${numDiasDiferencia == 1}">día</c:if>
											[${numDiasDiferencia2}]
										</div>
									</div>
									 -->
								</div>
							</form>
							
							<div class="col-md-12">
						        <div class="py-3" id="col_chart_num_ventas_por_hora_mio_y_resto"></div>
						    </div>
						    
						    <div class="col-md-12">
						        <div class="py-3" id="col_chart_importe_por_hora_mio_y_resto"></div>
						    </div>
						     
						    <div class="row">
							    <div class="col-md-6">
									<div class="py-3" id="col_stacked_chart_num_ventas_por_sexo_mio_y_resto"></div>
								</div>
								<div class="col-md-6">
									<div class="py-3" id="col_stacked_chart_importe_por_sexo_mio_y_resto"></div>
								</div>
						    </div>
						    
						    <div class="row">
								<div class="col-md-6">
									<div class="py-3" id="pie_chart_num_ventas_mias_por_sexo"></div>
								</div>
								
								<div class="col-md-6">
									<div class="py-3" id="pie_chart_num_ventas_resto_por_sexo"></div>
								</div>
						    </div>
						    
						    <div class="row">
						    	<div class="col-md-6">
									<div class="py-3" id="pie_chart_importe_mio_por_sexo"></div>
								</div>
								
								<div class="col-md-6">
									<div class="py-3" id="pie_chart_importe_resto_por_sexo"></div>
								</div>
						    </div>
						    
						    <div class="col-md-12">
						        <div class="py-3" id="col_chart_num_ventas_por_edad_mio_y_resto"></div>
						    </div>
						    
						    <div class="col-md-12">
						        <div class="py-3" id="col_chart_importe_por_edad_mio_y_resto"></div>
						    </div>
						    
						    <div class="col-md-12">
								<div class="py-3" id="col_chart_num_ventas_por_dia_semana_mio_y_resto"></div>
							</div>
							
							<div class="col-md-12">
								<div class="py-3" id="col_chart_importe_por_dia_semana_mio_y_resto"></div>
							</div>
							
							<div class="col-md-12">
								<div class="py-3" id="col_chart_num_ventas_por_cp_mio_y_resto"></div>
							</div>
							
							<div class="col-md-12">
								<div class="py-3" id="col_chart_importe_por_cp_mio_y_resto"></div>
							</div>
							
							<div class="col-md-12">
								<div class="py-3" id="col_chart_fidelidad_clientes_mio_y_resto"></div>
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