<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Comercio View</title>
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		<script type="text/javascript">
		    // Load the Visualization API and the piechart package.
		    google.load('visualization', '1.0', {'packages': ['corechart']});
		
		    // Set a callback to run when the Google Visualization API is loaded.
		    google.setOnLoadCallback(drawChart_numVentasEImportePorHora);
		    google.setOnLoadCallback(drawChart_importeVentasPorSexo);
		    google.setOnLoadCallback(drawChart_numVentasPorSexo);
		    google.setOnLoadCallback(drawChart_numVentasEImportePorEdad);
		    google.setOnLoadCallback(drawChart_numVentasEImportePorDiaSemana);
		    google.setOnLoadCallback(drawChart_numVentasEImportePorCP);
		    google.setOnLoadCallback(drawChart_fidelidadClientes);
		    //google.setOnLoadCallback(drawChart2);
		    //google.setOnLoadCallback(drawChart4);
		    
		    function drawChart_numVentasEImportePorHora() {
		    	var data3 = google.visualization.arrayToDataTable([
		    		['Hora', 'Nº ventas', 'Importe ventas'],
		    		<c:forEach items="${num_ventas_e_importes_hora}" var="horai">
			    		['${horai.key}h', ${horai.value[0]}, ${horai.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options3 = {
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
		    	
		    	var chart3 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_importe_por_hora'));
		    	chart3.draw(data3, options3);
		    }
		    
		    function drawChart_importeVentasPorSexo() {
		    	var data5 = google.visualization.arrayToDataTable([
		    		['Sexo', 'Importe'],
		    		['Hombres', ${importe_hombres}],
		    		['Mujeres', ${importe_mujeres}]
		    	]);
		    	
		    	var options5 = {
		    		'title': 'Importe ventas por sexo',
		    		pieSliceText: 'label',
		    		tooltip :  {showColorCode: true}
		    	};
		    	
		    	var chart5 = new google.visualization.PieChart(document.getElementById('pie_chart_importe_por_sexo'));
		    	chart5.draw(data5, options5);
		    }
		    
		    function drawChart_numVentasPorSexo() {
		    	var data5 = google.visualization.arrayToDataTable([
		    		['Sexo', 'Nº ventas'],
		    		['Hombres', ${num_ventas_hombres}],
		    		['Mujeres', ${num_ventas_mujeres}]
		    	]);
		    	
		    	var options5 = {
		    		'title': 'Nº ventas por sexo',
		    		pieSliceText: 'label',
		    		tooltip :  {showColorCode: true}
		    	};
		    	
		    	var chart5 = new google.visualization.PieChart(document.getElementById('pie_chart_num_ventas_por_sexo'));
		    	chart5.draw(data5, options5);
		    }
		    
		    function drawChart_numVentasEImportePorEdad() {
		    	var data3 = google.visualization.arrayToDataTable([
		    		['Edad', 'Nº ventas', 'Importe ventas'],
		    		<c:forEach items="${num_ventas_e_importes_edad}" var="edadi">
			    		['${edadi.key}', ${edadi.value[0]}, ${edadi.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options3 = {
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
		    	
		    	var chart3 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_importe_por_edad'));
		    	chart3.draw(data3, options3);
		    }  
		    
		    function drawChart_numVentasEImportePorDiaSemana() {
		    	var data6 = google.visualization.arrayToDataTable([
		    		['Día de la semana', 'Nº ventas', 'Importe ventas'],
		    		<c:forEach items="${num_ventas_e_importes_dia_semana_str}" var="diai">
			    		['${diai.key}', ${diai.value[0]}, ${diai.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options6 = {
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
		    	
		    	var chart6 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_importe_por_dia_semana'));
		    	chart6.draw(data6, options6);
		    }
		    
		    function drawChart_numVentasEImportePorCP() {
		    	var data7 = google.visualization.arrayToDataTable([
		    		['CP', 'Nº ventas', 'Importe ventas'],
		    		<c:forEach items="${num_ventas_e_importes_cp}" var="cpi">
			    		['${cpi.key}', ${cpi.value[0]}, ${cpi.value[1]}],
			    	</c:forEach>
		    	]);
		    	
		    	var options7 = {
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
		    	
		    	var chart7 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas_importe_por_cp'));
		    	chart7.draw(data7, options7);
		    }
		    
		    function drawChart_fidelidadClientes() {
		    	var data8 = google.visualization.arrayToDataTable([
		    		['Veces', '%'],
		    		['1 vez', ${porc_clientes_una_vez}],
		    		['2 veces', ${porc_clientes_dos_veces}],
		    		['3+ veces', ${porc_clientes_tres_o_mas_veces}]
		    	]);
		    	
		    	var options8 = {
	    			'title': 'Fidelidad clientes (veces que han venido)',
	    			width: 500,
	    			height: 500,
	    			bar: {groupWidth: '90%'},
	    			vAxis: {
	    				format: '#\'%\''
	    			}
		    	};
		    	
		    	var chart8 = new google.visualization.ColumnChart(document.getElementById('col_chart_fidelidad_clientes'));
		    	chart8.draw(data8, options8);
		    }
		    
		    
		    

		    function drawChart2() {
		    	var data2 = google.visualization.arrayToDataTable([
		    		['Hora', 'Nº ventas'],
		    		<c:forEach items="${horas_venta}" var="horai">
		    			['${horai.key}', ${horai.value}],
		    		</c:forEach>
		    	]);
		    	
		    	var options2 = {
	    			'title': 'Nº ventas por hora',
	    			width: 1700,
	    			height: 500,
	    			bar: {groupWidth: '90%'}
		    	}
		    	
		    	var chart2 = new google.visualization.ColumnChart(document.getElementById('col_chart_num_ventas'));
		    	chart2.draw(data2, options2);
		    }
		    
		    function drawChart4() {
		    	var data4 = google.visualization.arrayToDataTable([
		    		['Hora', 'Importe', {role: 'style'}],
		    		<c:forEach items="${importe_ventas_hora}" var="horai">
		    			['${horai.key}', ${horai.value}, 'red'],
		    		</c:forEach>
		    	]);
		    	
		    	var options4 = {
	    			'title': 'Importe por hora',
	    			width: 1700,
	    			height: 500,
	    			bar: {groupWidth: '90%'}
		    	}
		    	
		    	var chart4 = new google.visualization.ColumnChart(document.getElementById('col_chart_importe'));
		    	chart4.draw(data4, options4);
		    }
		    
		</script>
	</head>
	
	<body>
		<shiro:user>
		    Welcome back, <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
		</shiro:user>
		
		<hr>
		
		<shiro:hasRole name="comerciante">
			<p><a href="ComercianteServlet">&lt; Volver a vista comerciante</a></p>
			
			<h2>Vista de comercio</h2>
		
			<h3>Comercio</h3>
			<table border="1">
				<thead>
					<tr>
						<th>MerchantID</th>
						<th>Nombre comercio</th>
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
			<p>Nº ventas: ${num_ventas} || Suma importe: ${suma_importe} || Importe medio: ${importe_medio} </p>
			<p>Nº clientes distintos: ${num_clientes_distintos} || Importe hombres: ${importe_hombres}€ || Importe mujeres: ${importe_mujeres}€</p>
			
			<!-- <p>Un ${porc_clientes_una_vez_str}% de los clientes (${num_clientes_una_vez}/${num_clientes_distintos}) ha venido una vez.</p> -->
			<!-- <p>Un ${porc_clientes_dos_veces_str}% de los clientes (${num_clientes_dos_veces}/${num_clientes_distintos}) ha venido dos veces.</p> -->
			<!-- <p>Un ${porc_clientes_tres_o_mas_veces_str}% de los clientes (${num_clientes_tres_o_mas_veces}/${num_clientes_distintos}) ha venido tres o más veces.</p> -->
			
			<!-- Si no hay ventas no mostrar esto (variable num_ventas) -->
			<div style="width: 500px;">
		        <div id="col_chart_fidelidad_clientes"></div>
		    </div>
		    <div style="width: 500px;">
		        <div id="col_chart_num_ventas_importe_por_hora"></div>
		    </div>
		    <div style="width: 500px;">
		        <div id="pie_chart_importe_por_sexo"></div>
		    </div>
		    <div style="width: 500px;">
		        <div id="pie_chart_num_ventas_por_sexo"></div>
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
		        <div id="col_chart_num_ventas"></div>
		    </div>
		    <div style="width: 500px;">
		        <div id="col_chart_importe"></div>
		    </div>		    
			
			<h3>Ventas</h3>
			<table border="1">
				<thead>
					<tr>
						<th>Id</th>
						<th>Fecha</th>
						<th>Importe</th>
						<th>CP cliente</th>
						<th>Sexo cliente</th>
						<th>Edad cliente</th>
						<th>Id cliente</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${comercio.ventas}" var="venta">
						<tr>
							<td>${venta.id}</td>
							<td>${venta.fecha}</td>
							<td>${venta.importe}</td>
							<td>${venta.persona.cp}</td>
							<td>
								<c:if test="${venta.persona.sexo == 0}">Hombre</c:if>
								<c:if test="${venta.persona.sexo == 1}">Mujer</c:if>
							</td>
							<td>${venta.persona.edad}</td>
							<td>${venta.persona.id}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<h4>Distribución por horas</h4>
			<table border="1">
				<thead>
					<tr>
						<th>Hora</th>
						<th>Nº ventas</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${horas_venta}" var="horai">
						<tr>
							<td>${horai.key}</td>
							<td>${horai.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<h4>Importe por horas</h4>
			<table border="1">
				<thead>
					<tr>
						<th>Hora</th>
						<th>Importe ventas</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${importe_ventas_hora}" var="horai">
						<tr>
							<td>${horai.key}</td>
							<td>${horai.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<h4>Nº ventas e importe por horas</h4>
			<table border="1">
				<thead>
					<tr>
						<th>Hora</th>
						<th>Nº ventas</th>
						<th>Importe ventas</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${num_ventas_e_importes_hora}" var="horai">
						<tr>
							<td>${horai.key}</td>
							<td>${horai.value[0]}</td>
							<td>${horai.value[1]}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</shiro:hasRole>
		
		<shiro:lacksRole name="comerciante">
			<p>No eres comerciante</p> 
		</shiro:lacksRole>
		
	</body>
</html>