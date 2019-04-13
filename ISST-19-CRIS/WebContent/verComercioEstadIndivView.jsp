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
		    		['Hora', 'Nº ventas', 'Importe ventas'],
		    		<c:forEach items="${numVentasEImportePorHora}" var="horai">
			    		['${horai.key}h', ${horai.value[0]}, ${horai.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options1 = {
	    			'title': 'Nº ventas e importe por hora',
	    			width: 1700,
	    			height: 500,
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Nº ventas'},
	    				1: {title: 'Importe ventas'}
	    			}
		    	};
		    	
		    	var chart1 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_importe_por_hora'));
		    	chart1.draw(data1, options1);
		    }
		    
		    function drawChart2_numVentasPorSexo() {
		    	var data2 = google.visualization.arrayToDataTable([
		    		['Sexo', 'Nº ventas'],
		    		['Hombres', ${numVentasHombres}],
		    		['Mujeres', ${numVentasMujeres}]
		    	]);
		    	
		    	var options2 = {
		    		'title': 'Nº ventas por sexo',
		    		pieSliceText: 'label',
		    		tooltip :  {showColorCode: true}
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
		    		'title': 'Importe ventas por sexo',
		    		pieSliceText: 'label',
		    		tooltip :  {showColorCode: true}
		    	};
		    	
		    	var chart3 = new google.visualization.PieChart(document.getElementById('pie_chart_importe_por_sexo'));
		    	chart3.draw(data3, options3);
		    }
		    
		    function drawChart4_numVentasEImportePorEdad() {
		    	var data4 = google.visualization.arrayToDataTable([
		    		['Edad', 'Nº ventas', 'Importe ventas'],
		    		<c:forEach items="${numVentasEImportePorEdad}" var="edadi">
			    		['${edadi.key}', ${edadi.value[0]}, ${edadi.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options4 = {
	    			'title': 'Nº ventas e importe por edad',
	    			width: 1700,
	    			height: 500,
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Nº ventas'},
	    				1: {title: 'Importe ventas'}
	    			}
		    	};
		    	
		    	var chart4 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_importe_por_edad'));
		    	chart4.draw(data4, options4);
		    }  
		    
		    function drawChart5_numVentasEImportePorDiaSemana() {
		    	var data5 = google.visualization.arrayToDataTable([
		    		['Día de la semana', 'Nº ventas', 'Importe ventas'],
		    		<c:forEach items="${numVentasEImportePorDiaSemana}" var="diai">
			    		['${diai.key}', ${diai.value[0]}, ${diai.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options5 = {
	    			'title': 'Nº ventas e importe por día semana',
	    			width: 1700,
	    			height: 500,
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Nº ventas'},
	    				1: {title: 'Importe ventas'}
	    			}
		    	};
		    	
		    	var chart5 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_importe_por_dia_semana'));
		    	chart5.draw(data5, options5);
		    }
		    
		    function drawChart6_numVentasEImportePorCP() {
		    	var data6 = google.visualization.arrayToDataTable([
		    		['CP', 'Nº ventas', 'Importe ventas'],
		    		<c:forEach items="${numVentasEImportePorCp}" var="cpi">
			    		['${cpi.key}', ${cpi.value[0]}, ${cpi.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options6 = {
	    			'title': 'Nº ventas e importe por CP',
	    			width: 1700,
	    			height: 500,
	    			series: {
	    				0: {targetAxisIndex: 0},
	    				1: {targetAxisIndex: 1}
	    			},
	    			vAxes: {
	    				0: {title: 'Nº ventas'},
	    				1: {title: 'Importe ventas'}
	    			}
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
	    			width: 500,
	    			height: 500,
	    			bar: {groupWidth: '90%'},
	    			vAxis: {
	    				format: '#\'%\''
	    			}
		    	};
		    	
		    	var chart7 = new google.visualization.ColumnChart(document.getElementById('col_chart_fidelidad_clientes'));
		    	chart7.draw(data7, options7);
		    }
		    
		</script>
	</head>
	
	<body>
		<shiro:user>
		    Welcome back, <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
		</shiro:user>
		
		<hr>
		
		<shiro:hasRole name="comerciante">
			<p><a href="SeleccionarComercioEstadIndivServlet">&lt; Volver a vista comerciante</a></p>
			
			<h2>Vista de comercio</h2>
		
			<h3>Comercio</h3>
			<table border="1">
				<thead>
					<tr>
						<th>MerchantID</th>
						<th>Nombre del comercio</th>
						<th>Sector</th>
						<th>CP</th>
						<th>Banco</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${comercio.merchantId}</td>
						<td>${comercio.nombreComercio}</td>
						<td>${comercio.sector}</td>
						<td>${comercio.cp}</td>
						<td>${comercio.banco}</td>
					</tr>
				</tbody>
			</table>
			<p>Nº ventas: ${numVentas} || Suma importe: ${importeTotal}€ || Nº clientes distintos: ${numClientesDistintos} || Importe medio: ${importeMedio}€ </p>
			
			<!-- Si no hay ventas no mostrar esto (variable num_ventas) -->
		    <div style="width: 500px;">
		        <div id="col_chart_num_ventas_importe_por_hora"></div>
		    </div>
		    <div style="width: 500px;">
				<div id="pie_chart_num_ventas_por_sexo"></div>
			</div>
			<div style="width: 500px;">
				<div id="pie_chart_importe_por_sexo"></div>
			</div>
			<div style="width: 500px;">
				<div id="col_chart_num_ventas_importe_por_edad"></div>
			</div>
			<div style="width: 500px;">
				<div id="col_chart_num_ventas_importe_por_dia_semana"></div>
			</div>
			<div style="width: 500px;">
				<div id="col_chart_num_ventas_importe_por_cp"></div>
			</div>
			<div style="width: 500px;">
				<div id="col_chart_fidelidad_clientes"></div>
			</div>
			
		</shiro:hasRole>
		
		<shiro:lacksRole name="comerciante">
			<p>No eres comerciante</p> 
		</shiro:lacksRole>
		
	</body>
</html>