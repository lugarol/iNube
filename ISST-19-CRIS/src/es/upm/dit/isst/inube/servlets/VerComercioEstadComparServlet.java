package es.upm.dit.isst.inube.servlets;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.inube.dao.ComercioDAO;
import es.upm.dit.isst.inube.dao.ComercioDAOImplementation;
import es.upm.dit.isst.inube.dao.VentaDAO;
import es.upm.dit.isst.inube.dao.VentaDAOImplementation;
import es.upm.dit.isst.inube.model.Comercio;
import es.upm.dit.isst.inube.model.Venta;

@WebServlet("/VerComercioEstadComparServlet")
public class VerComercioEstadComparServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public String getDiaSemanaStr(int diaSemana) {
		if (diaSemana == 1) {
			return "Domingo";
		} else if (diaSemana == 2) {
			return "Lunes";
		} else if (diaSemana == 3) {
			return "Martes";
		} else if (diaSemana == 4) {
			return "Miércoles";
		} else if (diaSemana == 5) {
			return "Jueves";
		} else if (diaSemana == 6) {
			return "Viernes";
		} else if (diaSemana == 7) {
			return "Sábado";
		}
		return "";
	}
	
	private String getRangoEdad(int edad) {
		String rango = "";
		if (edad >= 0 && edad <= 9) {
			rango = "0-9";
		} else if (edad >= 10 && edad <= 19) {
			rango = "10-19";
		} else if (edad >= 20 && edad <= 29) {
			rango = "20-29";
		} else if (edad >= 30 && edad <= 39) {
			rango = "30-39";
		} else if (edad >= 40 && edad <= 49) {
			rango = "40-49";
		} else if (edad >= 50 && edad <= 59) {
			rango = "50-59";
		} else if (edad >= 60 && edad <= 69) {
			rango = "60-69";
		} else if (edad >= 70 && edad <= 79) {
			rango = "70-79";
		} else if (edad >= 80 && edad <= 89) {
			rango = "80-89";
		} else if (edad >= 90 && edad <= 99) {
			rango = "90-99";
		}
		return rango;
	}
	
	private Map<Integer, double[]> inicializarPorHora() {
		Map<Integer, double[]> porHora = new HashMap<Integer, double[]>();
		for (int i = 0; i < 24; i++) {
			porHora.put(i, new double[2]);
		}
		return porHora;
	}
	
	private Map<Integer, double[]> inicializarNumVentasPorHoraMioYResto() {
		Map<Integer, double[]> numVentasPorHoraMiasYResto = inicializarPorHora();
		return numVentasPorHoraMiasYResto;
	}
	
	private Map<Integer, double[]> inicializarImportePorHoraMioYResto() {
		Map<Integer, double[]> importePorHoraMiasYResto = inicializarPorHora();
		return importePorHoraMiasYResto;
	}
	
	private Map<String, double[]> inicializarPorEdad() {
		Map<String, double[]> porEdad = new LinkedHashMap<String, double[]>(); // LinkedHashMap para que conserve el orden de inserción
		porEdad.put("0-9", new double[2]);
		porEdad.put("10-19", new double[2]);
		porEdad.put("20-29", new double[2]);
		porEdad.put("30-39", new double[2]);
		porEdad.put("40-49", new double[2]);
		porEdad.put("50-59", new double[2]);
		porEdad.put("60-69", new double[2]);
		porEdad.put("70-79", new double[2]);
		porEdad.put("80-89", new double[2]);
		porEdad.put("90-99", new double[2]);
		return porEdad;
	}
	
	private Map<String, double[]> inicializarNumVentasPorEdadMioYResto() {
		Map<String, double[]> numVentasPorEdadMioYResto = inicializarPorEdad();
		return numVentasPorEdadMioYResto;		
	}
	
	private Map<String, double[]> inicializarImportePorEdadMioYResto() {
		Map<String, double[]> importePorEdadMioYResto = inicializarPorEdad();
		return importePorEdadMioYResto;		
	}
	
	private Map<String, double[]> inicializarPorDiaSemana() {
		Map<String, double[]> porDiaSemana = new LinkedHashMap<String, double[]>(); // LinkedHashMap para que conserve el orden de inserción
		porDiaSemana.put("Lunes", new double[2]);
		porDiaSemana.put("Martes", new double[2]);
		porDiaSemana.put("Miércoles", new double[2]);
		porDiaSemana.put("Jueves", new double[2]);
		porDiaSemana.put("Viernes", new double[2]);
		porDiaSemana.put("Sábado", new double[2]);
		porDiaSemana.put("Domingo", new double[2]);
		return porDiaSemana;
	}
	
	private Map<String, double[]> inicializarNumVentasPorDiaSemanaMioYResto() {
		Map<String, double[]> numVentasPorDiaSemanaMioYResto = inicializarPorDiaSemana();
		return numVentasPorDiaSemanaMioYResto;
	}
	
	private Map<String, double[]> inicializarImportePorDiaSemanaMioYResto() {
		Map<String, double[]> importePorDiaSemanaMioYResto = inicializarPorDiaSemana();
		return importePorDiaSemanaMioYResto;
	}
	
	private double sacarMaximoPorHoraMioYResto(Map<Integer, double[]> porHoraMioYResto) {
		double maxPorHoraMioYResto = 0.0;
		for (int i = 0; i < 24; i++) {
			double[] arrayPorHoraMioYResto = porHoraMioYResto.get(i);
			if (arrayPorHoraMioYResto[0] > maxPorHoraMioYResto) {
				maxPorHoraMioYResto = arrayPorHoraMioYResto[0];
			}
			if (arrayPorHoraMioYResto[1] > maxPorHoraMioYResto) {
				maxPorHoraMioYResto = arrayPorHoraMioYResto[1];
			}				
		}
		return maxPorHoraMioYResto;
	}
	
	private double sacarMaximoPorEdadMioYResto(Map<String, double[]> porEdadMioYResto) {
		double maxPorEdadMioYResto = 0.0;
		for (Map.Entry<String, double[]> entry : porEdadMioYResto.entrySet()) {
			double[] arrayPorEdadMioYResto = entry.getValue();
			if (arrayPorEdadMioYResto[0] > maxPorEdadMioYResto) {
				maxPorEdadMioYResto = arrayPorEdadMioYResto[0];
			}
			if (arrayPorEdadMioYResto[1] > maxPorEdadMioYResto) {
				maxPorEdadMioYResto = arrayPorEdadMioYResto[1];
			}
		}
		return maxPorEdadMioYResto;
	}
	
	private double sacarMaximoPorDiaSemanaMioYResto(Map<String, double[]> porDiaSemanaMioYResto) {
		double maxPorDiaSemanaMioYResto = 0.0;
		for (Map.Entry<String, double[]> entry : porDiaSemanaMioYResto.entrySet()) {
			double[] arrayPorDiaSemanaMioYResto = entry.getValue();
			if (arrayPorDiaSemanaMioYResto[0] > maxPorDiaSemanaMioYResto) {
				maxPorDiaSemanaMioYResto = arrayPorDiaSemanaMioYResto[0];
			}
			if (arrayPorDiaSemanaMioYResto[1] > maxPorDiaSemanaMioYResto) {
				maxPorDiaSemanaMioYResto = arrayPorDiaSemanaMioYResto[1];
			}
		}
		return maxPorDiaSemanaMioYResto;
	}
	
	private double sacarMaximoPorCpMioYResto(Map<Integer, double[]> porCpMioYResto) {
		double maxPorCpMioYResto = 0.0;
		for (Map.Entry<Integer, double[]> entry : porCpMioYResto.entrySet()) {
			double[] arrayPorCpMioYResto = entry.getValue();
			if (arrayPorCpMioYResto[0] > maxPorCpMioYResto) {
				maxPorCpMioYResto = arrayPorCpMioYResto[0];
			}
			if (arrayPorCpMioYResto[1] > maxPorCpMioYResto) {
				maxPorCpMioYResto = arrayPorCpMioYResto[1];
			}
		}
		return maxPorCpMioYResto;
	}
	
	private void sumarUnaVentaAHora(Map<Integer, double[]> numVentasPorHoraMioYResto, int hora, int mioOResto) {
		double[] arrayNumVentasPorHoraMioYResto = numVentasPorHoraMioYResto.get(hora);
		arrayNumVentasPorHoraMioYResto[mioOResto] += 1;
		numVentasPorHoraMioYResto.put(hora, arrayNumVentasPorHoraMioYResto);
	}
	
	private void sumarImporteAHora(Map<Integer, double[]> importePorHoraMioYResto, int hora, double importe, int mioOResto) {
		double[] arrayImportePorHoraMioYResto = importePorHoraMioYResto.get(hora);
		arrayImportePorHoraMioYResto[mioOResto] += importe;
		importePorHoraMioYResto.put(hora, arrayImportePorHoraMioYResto);
	}
	
	private void sumarUnaVentaAEdad(Map<String, double[]> numVentasPorEdadMioYResto, String rangoEdadCliente, int mioOResto) {
		double[] arrayNumVentasPorEdadMioYResto = numVentasPorEdadMioYResto.get(rangoEdadCliente);
		arrayNumVentasPorEdadMioYResto[mioOResto] += 1;
		numVentasPorEdadMioYResto.put(rangoEdadCliente, arrayNumVentasPorEdadMioYResto);
	}
	
	private void sumarImporteAEdad(Map<String, double[]> importePorEdadMioYResto, String rangoEdadCliente, double importe, int mioOResto) {
		double[] arrayImportePorEdadMioYResto = importePorEdadMioYResto.get(rangoEdadCliente);
		arrayImportePorEdadMioYResto[mioOResto] += importe;
		importePorEdadMioYResto.put(rangoEdadCliente, arrayImportePorEdadMioYResto);
	}
	
	private void sumarUnaVentaADiaSemana(Map<String, double[]> numVentasPorDiaSemanaMioYResto, String diaSemanaStr, int mioOResto) {
		sumarUnaVentaAEdad(numVentasPorDiaSemanaMioYResto, diaSemanaStr, mioOResto);
	}

	private void sumarImporteADiaSemana(Map<String, double[]> importePorDiaSemanaMioYResto, String diaSemanaStr, double importe, int mioOResto) {
		sumarImporteAEdad(importePorDiaSemanaMioYResto, diaSemanaStr, importe, mioOResto);
	}
	
	private void sumarUnaVentaACp(Map<Integer, double[]> numVentasPorCpMioYResto, int cp, int mioOResto) {
		double[] arrayNumVentasPorCpMioYResto = new double[2];
		if (numVentasPorCpMioYResto.containsKey(cp)) {
			arrayNumVentasPorCpMioYResto = numVentasPorCpMioYResto.get(cp);
		}
		arrayNumVentasPorCpMioYResto[mioOResto] += 1;
		numVentasPorCpMioYResto.put(cp, arrayNumVentasPorCpMioYResto);
	}
	
	private void sumarImporteACp(Map<Integer, double[]> importePorCpMioYResto, int cp, double importe, int mioOResto) {
		double[] arrayImportePorCpMioYResto = new double[2];
		if (importePorCpMioYResto.containsKey(cp)) {
			arrayImportePorCpMioYResto = importePorCpMioYResto.get(cp);
		}
		arrayImportePorCpMioYResto[mioOResto] += importe;
		importePorCpMioYResto.put(cp, arrayImportePorCpMioYResto);
	}
	
	private void sumarUnaVentaACliente(Map<Integer, double[]> numVentasPorClienteMioYResto, int idCliente, int mioOResto) {
		sumarUnaVentaACp(numVentasPorClienteMioYResto, idCliente, mioOResto);
	}
	
	private void sumarImporteACliente(Map<Integer, double[]> importePorClienteMioYResto, int idCliente, double importe, int mioOResto) {
		sumarImporteACp(importePorClienteMioYResto, idCliente, importe, mioOResto);
	}
	
	private void sumarUnaVentaAClienteComercioExterno(Map<Integer, Double> numVentasPorClienteComercioExterno, int idCliente) {
		Double numVentasClienteComercioExterno = 0.0;
		if (numVentasPorClienteComercioExterno.containsKey(idCliente)) {
			numVentasClienteComercioExterno = numVentasPorClienteComercioExterno.get(idCliente);
		}
		numVentasClienteComercioExterno += 1.0;
		numVentasPorClienteComercioExterno.put(idCliente, numVentasClienteComercioExterno);
	}
		
	private void normalizarPorHoraMioYResto(Map<Integer, double[]> porHoraMioYResto, int numRestoComercios) {
		for (int i = 0; i < 24; i++) {
			double[] arrayPorHoraMioYResto = porHoraMioYResto.get(i);
			if (arrayPorHoraMioYResto[1] > 0.0) {
				arrayPorHoraMioYResto[1] = arrayPorHoraMioYResto[1] / (double) numRestoComercios;
				porHoraMioYResto.put(i, arrayPorHoraMioYResto);
			}				
		}
	}
	
	private void normalizarNumVentasPorHoraMioYResto(Map<Integer, double[]> numVentasPorHoraMioYResto, int numRestoComercios) {
		normalizarPorHoraMioYResto(numVentasPorHoraMioYResto, numRestoComercios);
	}
	
	private void normalizarImportePorHoraMioYResto(Map<Integer, double[]> importePorHoraMioYResto, int numRestoComercios) {
		normalizarPorHoraMioYResto(importePorHoraMioYResto, numRestoComercios);
	}
	
	private void normalizarPorEdadMioYResto(Map<String, double[]> porEdadMioYResto, int numRestoComercios) {
		for (Map.Entry<String, double[]> entry : porEdadMioYResto.entrySet()) {
			double[] arrayPorEdadMioYResto = entry.getValue();
			if (arrayPorEdadMioYResto[1] > 0.0) {
				arrayPorEdadMioYResto[1] = arrayPorEdadMioYResto[1] / numRestoComercios;
				porEdadMioYResto.put(entry.getKey(), arrayPorEdadMioYResto);
			}
		}
	}
	
	private void normalizarNumVentasPorEdadMioYResto(Map<String, double[]> numVentasPorEdadMioYResto, int numRestoComercios) {
		normalizarPorEdadMioYResto(numVentasPorEdadMioYResto, numRestoComercios);
	}
	
	private void normalizarImportePorEdadMioYResto(Map<String, double[]> importePorEdadMioYResto, int numRestoComercios) {
		normalizarPorEdadMioYResto(importePorEdadMioYResto, numRestoComercios);
	}
	
	private void normalizarPorDiaSemanaMioYResto(Map<String, double[]> porDiaSemanaMioYResto, int numRestoComercios) {
		for (Map.Entry<String, double[]> entry : porDiaSemanaMioYResto.entrySet()) {
			double[] arrayPorDiaSemanaMioYResto = entry.getValue();
			if (arrayPorDiaSemanaMioYResto[1] > 0.0) {
				arrayPorDiaSemanaMioYResto[1] = arrayPorDiaSemanaMioYResto[1] / numRestoComercios;
				porDiaSemanaMioYResto.put(entry.getKey(), arrayPorDiaSemanaMioYResto);
			}
		}
	}
	
	private void normalizarNumVentasPorDiaSemanaMioYResto(Map<String, double[]> numVentasPorDiaSemanaMioYResto, int numRestoComercios) {
		normalizarPorDiaSemanaMioYResto(numVentasPorDiaSemanaMioYResto, numRestoComercios);
	}
	
	private void normalizarImportePorDiaSemanaMioYResto(Map<String, double[]> importePorDiaSemanaMioYResto, int numRestoComercios) {
		normalizarPorDiaSemanaMioYResto(importePorDiaSemanaMioYResto, numRestoComercios);
	}
	
	private void normalizarPorCpMioYResto(Map<Integer, double[]> porCpMioYResto, int numRestoComercios) {
		for (Map.Entry<Integer, double[]> entry : porCpMioYResto.entrySet()) {
			double[] arrayPorCpMioYResto = entry.getValue();
			if (arrayPorCpMioYResto[1] > 0.0) {
				arrayPorCpMioYResto[1] = arrayPorCpMioYResto[1] / numRestoComercios;
				porCpMioYResto.put(entry.getKey(), arrayPorCpMioYResto);
			}
		}
	}
	
	private void normalizarNumVentasPorCpMioYResto(Map<Integer, double[]> numVentasPorCpMioYResto, int numRestoComercios) {
		normalizarPorCpMioYResto(numVentasPorCpMioYResto, numRestoComercios);
	}
	
	private void normalizarImportePorCpMioYResto(Map<Integer, double[]> importePorCpMioYResto, int numRestoComercios) {
		normalizarPorCpMioYResto(importePorCpMioYResto, numRestoComercios);
	}
	
	private void normalizarPorClienteMioYResto(Map<Integer, double[]> porClienteMioYResto, int numRestoComercios) {
		for (Map.Entry<Integer, double[]> entry : porClienteMioYResto.entrySet()) {
			double[] arrayPorClienteMioYResto = entry.getValue();
			if (arrayPorClienteMioYResto[1] > 0.0) {
				arrayPorClienteMioYResto[1] = arrayPorClienteMioYResto[1] / numRestoComercios;
				porClienteMioYResto.put(entry.getKey(), arrayPorClienteMioYResto);
			}
		}
	}
	
	private void normalizarNumVentasPorClienteMioYResto(Map<Integer, double[]> numVentasPorClienteMioYResto, int numRestoComercios) {
		normalizarPorClienteMioYResto(numVentasPorClienteMioYResto, numRestoComercios);
	}
	
	private void normalizarImportePorClienteMioYResto(Map<Integer, double[]> importePorClienteMioYResto, int numRestoComercios) {
		normalizarPorClienteMioYResto(importePorClienteMioYResto, numRestoComercios);
	}
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" VerComercioEstadComparServlet > doGet ");
		System.out.println(" ------------------------------------ ");
		
		// recoger parámetros
		String merchantId = req.getParameter("merchantId");
		
		// obtener comercio
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		Comercio comercio = comercioDAO.read(merchantId);
		
		Collection<Venta> misVentas = comercio.getVentas();
		int numMisVentas = misVentas.size();
		
		Set<Integer> idsMisClientesDistintos = new HashSet<Integer>();
		double miImporteTotal = 0.0;
		double miImporteMedio = 0.0;
		
		int numMisClientesUnaVez = 0;
		int numMisClientesDosVeces = 0;
		int numMisClientesTresOMasVeces = 0;
		double porcMisClientesUnaVez = 0.0;
		double porcMisClientesDosVeces = 0.0;
		double porcMisClientesTresOMasVeces = 0.0;
		
		int numMisClientesDistintos = 0;
		
		Calendar calendar = Calendar.getInstance();
		
		// variables para guardar datos mios y resto a la vez para las graficas
		// por hora
		Map<Integer, double[]> numVentasPorHoraMioYResto = inicializarNumVentasPorHoraMioYResto(); // double[0]: num ventas mio, double[1]: num ventas resto
		Map<Integer, double[]> importePorHoraMioYResto = inicializarImportePorHoraMioYResto(); // double[0]: importe mio, double[1]: importe resto
		// por sexo
		int numMisVentasHombres = 0;
		int numRestoVentasHombres = 0;
		int numMisVentasMujeres = 0;
		int numRestoVentasMujeres = 0;
		double miImporteHombres = 0.0;
		double restoImporteHombres = 0.0;
		double miImporteMujeres = 0.0;
		double restoImporteMujeres = 0.0;
		// por edad
		Map<String, double[]> numVentasPorEdadMioYResto = inicializarNumVentasPorEdadMioYResto(); // double[0]: num ventas mio, double[1]: num ventas resto
		Map<String, double[]> importePorEdadMioYResto = inicializarImportePorEdadMioYResto(); // double[0]: importe mio, double[1]: importe resto
		// por dia semana
		Map<String, double[]> numVentasPorDiaSemanaMioYResto = inicializarNumVentasPorDiaSemanaMioYResto(); // double[0]: num ventas mio, double[1]: num ventas resto
		Map<String, double[]> importePorDiaSemanaMioYResto = inicializarImportePorDiaSemanaMioYResto(); // double[0]: importe mio, double[1]: importe resto
		// por cp
		Map<Integer, double[]> numVentasPorCpMioYResto = new HashMap<Integer, double[]>(); // double[0]: num ventas mio, double[1]: num ventas resto
		Map<Integer, double[]> importePorCpMioYResto = new HashMap<Integer, double[]>(); // double[0]: importe mio, double[1]: importe resto
		// por cliente
		Map<Integer, double[]> numVentasPorClienteMioYResto = new HashMap<Integer, double[]>(); // double[0]: num ventas mio, double[1]: num ventas resto
		Map<Integer, double[]> importePorClienteMioYResto = new HashMap<Integer, double[]>(); // double[0]: importe mio, double[1]: importe resto
		
		
		// CALCULO ESTADISTICAS MIS VENTAS
		if (numMisVentas > 0) {
			for (Venta v : misVentas) {
				idsMisClientesDistintos.add(v.getPersona().getId());
				
				calendar.setTime(v.getFecha());				
				
				// ESTADISTICAS MIAS POR HORA
				int hora = calendar.get(Calendar.HOUR_OF_DAY);			
				sumarUnaVentaAHora(numVentasPorHoraMioYResto, hora, 0);
				sumarImporteAHora(importePorHoraMioYResto, hora, v.getImporte(), 0);
					
				// ESTADÍSTICAS MIAS POR SEXO
				// num ventas e importe
				if (v.getPersona().getSexo() == 0) { // hombre
					numMisVentasHombres++;
					miImporteHombres += v.getImporte();
				} else if (v.getPersona().getSexo() == 1) { // mujer
					numMisVentasMujeres++;
					miImporteMujeres += v.getImporte();
				}
				
				// ESTADISTICAS MIAS POR EDAD
				String rangoEdadCliente = getRangoEdad(v.getPersona().getEdad());			
				sumarUnaVentaAEdad(numVentasPorEdadMioYResto, rangoEdadCliente, 0);
				sumarImporteAEdad(importePorEdadMioYResto, rangoEdadCliente, v.getImporte(), 0);
					
				
				// ESTADISTICAS MIAS POR DIA SEMANA
				int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
				String diaSemanaStr = getDiaSemanaStr(diaSemana);
				sumarUnaVentaADiaSemana(numVentasPorDiaSemanaMioYResto, diaSemanaStr, 0);
				sumarImporteADiaSemana(importePorDiaSemanaMioYResto, diaSemanaStr, v.getImporte(), 0);
				
				
				// ESTADISTICAS MIAS POR CP
				int cpCliente = v.getPersona().getCp();
				sumarUnaVentaACp(numVentasPorCpMioYResto, cpCliente, 0);
				sumarImporteACp(importePorCpMioYResto, cpCliente, v.getImporte(), 0);
				
				// ESTADISTICAS MIAS FIDELIDAD
				int idCliente = v.getPersona().getId();
				sumarUnaVentaACliente(numVentasPorClienteMioYResto, idCliente, 0);
				sumarImporteACliente(importePorClienteMioYResto, idCliente, v.getImporte(), 0);
				
			}
			
			// obtener numMisClientesUnaVez, numMisClientesDosVeces y numMisClientesTresOMasVeces
			for (Map.Entry<Integer, double[]> entry : numVentasPorClienteMioYResto.entrySet()) {
				if (entry.getValue()[0] == 1) {
					numMisClientesUnaVez++;
				} else if (entry.getValue()[0] == 2) {
					numMisClientesDosVeces++;
				} else if (entry.getValue()[0] >= 3) {
					numMisClientesTresOMasVeces++;
				}
			}
			
			numMisClientesDistintos = idsMisClientesDistintos.size();
			
			porcMisClientesUnaVez = numMisClientesUnaVez / (double) numMisClientesDistintos * 100.0;
			porcMisClientesDosVeces = numMisClientesDosVeces / (double) numMisClientesDistintos * 100.0;
			porcMisClientesTresOMasVeces = numMisClientesTresOMasVeces / (double) numMisClientesDistintos * 100.0;
			
			miImporteTotal = miImporteHombres + miImporteMujeres;
			miImporteMedio = miImporteTotal / numMisVentas;
			
		}
		
		
		/* CALCULAR FIDELIDAD DEL RESTO DE COMERCIOS
		 * Se recorre el resto de comercios. Para cada uno se obtienen sus ventas.
		 * Para cada venta, se actualiza un Map con el id del cliente y el num ventas.
		 * Tras recorrer todas las ventas, se separan los clientes en 1, 2 o 3+ veces.
		 * Se calcula el % y se mete en el array de fidelidad
		 */
		// orden sql equivalente: SELECT * FROM comercio where merchantid!='15592835' and sector='centros de estetica' and cp=28001
		Collection<Comercio> restoComercios = comercioDAO.readAllButMe(merchantId, comercio.getSector(), comercio.getCp());
		int numRestoComercios = restoComercios.size();
		
		VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
		
		double[][] restoComerciosFidelidad = new double[numRestoComercios][3]; // por cada comercio -> [0]: 1 vez // [1]: 2 veces // [2]: 3+ veces
		int indice = 0;
		
		for (Comercio c : restoComercios) {
			Map<Integer, Double> numVentasPorClienteComercioExterno = new HashMap<Integer, Double>();
			int numClientesComercioExternoUnaVez = 0;
			int numClientesComercioExternoDosVeces = 0;
			int numClientesComercioExternoTresOMasVeces = 0;
			int numClientesComercioExternoDistintos = 0;
			double porcClientesComercioExternoUnaVez = 0.0;
			double porcClientesComercioExternoDosVeces = 0.0;
			double porcClientesComercioExternoTresOMasVeces = 0.0;
			
			Collection<Venta> ventasComercioExterno = ventaDAO.readAllForComercio(c.getMerchantId());
			for (Venta v : ventasComercioExterno) {
				sumarUnaVentaAClienteComercioExterno(numVentasPorClienteComercioExterno, v.getPersona().getId());
			}
			
			for (Map.Entry<Integer, Double> entry : numVentasPorClienteComercioExterno.entrySet()) {
				if (entry.getValue() == 1) {
					numClientesComercioExternoUnaVez++;
				} else if (entry.getValue() == 2) {
					numClientesComercioExternoDosVeces++;
				} else if (entry.getValue() == 3) {
					numClientesComercioExternoTresOMasVeces++;
				}
			}
			
			numClientesComercioExternoDistintos = numClientesComercioExternoUnaVez + numClientesComercioExternoDosVeces + numClientesComercioExternoTresOMasVeces;
			
			porcClientesComercioExternoUnaVez = (double) numClientesComercioExternoUnaVez / (double) numClientesComercioExternoDistintos * 100.0;
			porcClientesComercioExternoDosVeces = (double) numClientesComercioExternoDosVeces / (double) numClientesComercioExternoDistintos * 100.0;
			porcClientesComercioExternoTresOMasVeces = (double) numClientesComercioExternoTresOMasVeces / (double) numClientesComercioExternoDistintos * 100.0;
			
			restoComerciosFidelidad[indice][0] = porcClientesComercioExternoUnaVez;
			restoComerciosFidelidad[indice][1] = porcClientesComercioExternoDosVeces;
			restoComerciosFidelidad[indice][2] = porcClientesComercioExternoTresOMasVeces;
			indice++;;
			
		}
		
		double porcRestoClientesUnaVez = 0.0;
		double porcRestoClientesDosVeces = 0.0;
		double porcRestoClientesTresOMasVeces = 0.0;
		// hacer media de 1, 2, 3+ veces
		for (int i = 0; i < restoComerciosFidelidad.length; i++) {
			porcRestoClientesUnaVez += restoComerciosFidelidad[i][0];
			porcRestoClientesDosVeces += restoComerciosFidelidad[i][1];
			porcRestoClientesTresOMasVeces += restoComerciosFidelidad[i][2];
		}
		
		porcRestoClientesUnaVez /= numRestoComercios;
		porcRestoClientesDosVeces /= numRestoComercios;
		porcRestoClientesTresOMasVeces /= numRestoComercios;
		
		
		// CALCULO ESTADISTICAS RESTO VENTAS
		Collection<Venta> restoVentas = ventaDAO.readAllButMine(merchantId, comercio.getSector(), comercio.getCp());
		int numRestoVentas = restoVentas.size();
		
		Set<Integer> idsRestoClientesDistintos = new HashSet<Integer>();
		double restoImporteTotal = 0.0;
		double restoImporteMedio = 0.0;
		
		int numRestoClientesUnaVez = 0;
		int numRestoClientesDosVeces = 0;
		int numRestoClientesTresOMasVeces = 0;
		int numRestoClientesDistintos = 0;
		
		double maxNumVentasPorHoraMioYResto = 0.0;
		double maxImportePorHoraMioYResto = 0.0;
		double maxNumVentasPorEdadMioYResto = 0.0;
		double maxImportePorEdadMioYResto = 0.0;
		double maxNumVentasPorDiaSemanaMioYResto = 0.0;
		double maxImportePorDiaSemanaMioYResto = 0.0;
		double maxNumVentasPorCpMioYResto = 0.0;
		double maxImportePorCpMioYResto = 0.0;
		
		double porcRestoVentasHombres = 0.0;
		double porcRestoVentasMujeres = 0.0;
		double porcRestoImporteHombres = 0.0;
		double porcRestoImporteMujeres = 0.0;
		
		// CALCULO RESTO VENTAS
		if (numRestoVentas > 0) {
			for (Venta v : restoVentas) {
				idsRestoClientesDistintos.add(v.getPersona().getId());
				
				calendar.setTime(v.getFecha());
				
				// ESTADISTICAS COMPARADAS POR HORA
				int hora = calendar.get(Calendar.HOUR_OF_DAY);
				sumarUnaVentaAHora(numVentasPorHoraMioYResto, hora, 1);
				sumarImporteAHora(importePorHoraMioYResto, hora, v.getImporte(), 1);
				
				// ESTADÍSTICAS COMPARADAS POR SEXO
				if (v.getPersona().getSexo() == 0) {
					numRestoVentasHombres++;
					restoImporteHombres += v.getImporte();
				} else if (v.getPersona().getSexo() == 1) {
					numRestoVentasMujeres++;
					restoImporteMujeres += v.getImporte();
				}
				
				// ESTADISTICAS COMPARADAS POR EDAD
				String rangoEdadCliente = getRangoEdad(v.getPersona().getEdad());
				sumarUnaVentaAEdad(numVentasPorEdadMioYResto, rangoEdadCliente, 1);
				sumarImporteAEdad(importePorEdadMioYResto, rangoEdadCliente, v.getImporte(), 1);
				
				// ESTADISTICAS COMPARADAS POR DIA SEMANA
				int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
				String diaSemanaStr = getDiaSemanaStr(diaSemana);
				sumarUnaVentaADiaSemana(numVentasPorDiaSemanaMioYResto, diaSemanaStr, 1);
				sumarImporteADiaSemana(importePorDiaSemanaMioYResto, diaSemanaStr, v.getImporte(), 1);
				
				// ESTADISTICAS COMPARADAS POR CP
				int cpCliente = v.getPersona().getCp();
				sumarUnaVentaACp(numVentasPorCpMioYResto, cpCliente, 1);
				sumarImporteACp(importePorCpMioYResto, cpCliente, v.getImporte(), 1);
				
				// ESTADISTICAS COMPARADAS FIDELIDAD
				int idCliente = v.getPersona().getId();
				sumarUnaVentaACliente(numVentasPorClienteMioYResto, idCliente, 1);
				sumarImporteACliente(importePorClienteMioYResto, idCliente, v.getImporte(), 1);
				
			}
			
			// obtener numRestoClientesUnaVezResto, numRestoClientesDosVecesResto y numRestoClientesTresOMasVecesResto
			for (Map.Entry<Integer, double[]> entry : numVentasPorClienteMioYResto.entrySet()) {				
				if (entry.getValue()[1] == 1) {
					numRestoClientesUnaVez++;
				} else if (entry.getValue()[1] == 2) {
					numRestoClientesDosVeces++;
				} else if (entry.getValue()[1] >= 3) {
					numRestoClientesTresOMasVeces++;
				}
			}
			
			numRestoClientesDistintos = idsRestoClientesDistintos.size();
			
			porcRestoClientesUnaVez = numRestoClientesUnaVez / (double) numRestoClientesDistintos * 100.0;
			porcRestoClientesDosVeces = numRestoClientesDosVeces / (double) numRestoClientesDistintos * 100.0;
			porcRestoClientesTresOMasVeces = numRestoClientesTresOMasVeces / (double) numRestoClientesDistintos * 100.0;

			restoImporteTotal = restoImporteHombres + restoImporteMujeres;
			restoImporteMedio = restoImporteTotal / numRestoVentas;
			
			// normalizar			
			if (numRestoComercios > 1) {
				// por hora
				normalizarNumVentasPorHoraMioYResto(numVentasPorHoraMioYResto, numRestoComercios);
				normalizarImportePorHoraMioYResto(importePorHoraMioYResto, numRestoComercios);
				// por edad
				normalizarNumVentasPorEdadMioYResto(numVentasPorEdadMioYResto, numRestoComercios);
				normalizarImportePorEdadMioYResto(importePorEdadMioYResto, numRestoComercios);
				// por dia semana
				normalizarNumVentasPorDiaSemanaMioYResto(numVentasPorDiaSemanaMioYResto, numRestoComercios);
				normalizarImportePorDiaSemanaMioYResto(importePorDiaSemanaMioYResto, numRestoComercios);
				// por cp
				normalizarNumVentasPorCpMioYResto(numVentasPorCpMioYResto, numRestoComercios);
				normalizarImportePorCpMioYResto(importePorCpMioYResto, numRestoComercios);
				// por cliente (no se usa, se podria borrar)
				normalizarNumVentasPorClienteMioYResto(numVentasPorClienteMioYResto, numRestoComercios);
				normalizarImportePorClienteMioYResto(importePorClienteMioYResto, numRestoComercios);
			}
			
			// obtener maximos
			// por hora
			maxNumVentasPorHoraMioYResto = sacarMaximoPorHoraMioYResto(numVentasPorHoraMioYResto);
			maxImportePorHoraMioYResto = sacarMaximoPorHoraMioYResto(importePorHoraMioYResto);
			// por edad
			maxNumVentasPorEdadMioYResto = sacarMaximoPorEdadMioYResto(numVentasPorEdadMioYResto);
			maxImportePorEdadMioYResto = sacarMaximoPorEdadMioYResto(importePorEdadMioYResto);
			// por dia semana
			maxNumVentasPorDiaSemanaMioYResto = sacarMaximoPorDiaSemanaMioYResto(numVentasPorDiaSemanaMioYResto);
			maxImportePorDiaSemanaMioYResto = sacarMaximoPorDiaSemanaMioYResto(importePorDiaSemanaMioYResto);
			// por cp
			maxNumVentasPorCpMioYResto = sacarMaximoPorCpMioYResto(numVentasPorCpMioYResto);
			maxImportePorCpMioYResto = sacarMaximoPorCpMioYResto(importePorCpMioYResto);
				
			porcRestoVentasHombres = (double) numRestoVentasHombres / (double) numRestoVentas * 100.0;
			porcRestoVentasMujeres = (double) numRestoVentasMujeres / (double) numRestoVentas * 100.0;
			porcRestoImporteHombres = (double) restoImporteHombres / (double) restoImporteTotal * 100.0;
			porcRestoImporteMujeres = (double) restoImporteMujeres / (double) restoImporteTotal * 100.0;
				
		}
		
		// PARA VER VENTAS POR FECHA
		/*Collection<Venta> listaPorFechas = null;
		try {
			Date from = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("01-02-2019 00:00:00");
			Date to = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("28-02-2019 23:59:59");
			listaPorFechas = ventaDAO.readAllBetweenDates(from, to);
		} catch (Exception e) {
			
		}*/
		
		DecimalFormat df2Decimales = new DecimalFormat("#.##");
		df2Decimales.setRoundingMode(RoundingMode.CEILING);
		
		req.getSession().setAttribute("comercio", comercio);
		
		// num ventas mias
		req.getSession().setAttribute("numMisVentas", numMisVentas);
		
		// importe total y medio mios
		req.getSession().setAttribute("miImporteTotal", df2Decimales.format(miImporteTotal));
		req.getSession().setAttribute("miImporteMedio", df2Decimales.format(miImporteMedio));
		
		// estad indiv sexo
		req.getSession().setAttribute("numMisVentasHombres", numMisVentasHombres);
		req.getSession().setAttribute("miImporteHombres", miImporteHombres);
		req.getSession().setAttribute("numMisVentasMujeres", numMisVentasMujeres);
		req.getSession().setAttribute("miImporteMujeres", miImporteMujeres);
		
		// estad indiv fidelidad
		req.getSession().setAttribute("numMisClientesDistintos", numMisClientesDistintos);
		req.getSession().setAttribute("porcMisClientesUnaVez", porcMisClientesUnaVez);
		req.getSession().setAttribute("porcMisClientesDosVeces", porcMisClientesDosVeces);
		req.getSession().setAttribute("porcMisClientesTresOMasVeces", porcMisClientesTresOMasVeces);
		
		// resto importe total y medio
		req.getSession().setAttribute("restoImporteMedio", df2Decimales.format(restoImporteMedio));
		
		// estad comparadas hora
		req.getSession().setAttribute("numVentasPorHoraMioYResto", numVentasPorHoraMioYResto);
		req.getSession().setAttribute("maxNumVentasPorHoraMioYResto", maxNumVentasPorHoraMioYResto);
		req.getSession().setAttribute("importePorHoraMioYResto", importePorHoraMioYResto);
		req.getSession().setAttribute("maxImporteVentasPorHoraMioYResto", maxImportePorHoraMioYResto);
		
		// estad comparadas sexo		
		req.getSession().setAttribute("porcRestoVentasHombres", porcRestoVentasHombres);
		req.getSession().setAttribute("porcRestoImporteHombres", porcRestoImporteHombres);
		req.getSession().setAttribute("porcRestoVentasMujeres", porcRestoVentasMujeres);
		req.getSession().setAttribute("porcRestoImporteMujeres", porcRestoImporteMujeres);
		
		// estad comparadas edad
		req.getSession().setAttribute("numVentasPorEdadMioYResto", numVentasPorEdadMioYResto);
		req.getSession().setAttribute("maxNumVentasPorEdadMioYResto", maxNumVentasPorEdadMioYResto);
		req.getSession().setAttribute("importePorEdadMioYResto", importePorEdadMioYResto);
		req.getSession().setAttribute("maxImportePorEdadMioYResto", maxImportePorEdadMioYResto);
		
		// estad comparadas dia semana
		req.getSession().setAttribute("numVentasPorDiaSemanaMioYResto", numVentasPorDiaSemanaMioYResto);
		req.getSession().setAttribute("maxNumVentasPorDiaSemanaMioYResto", maxNumVentasPorDiaSemanaMioYResto);
		req.getSession().setAttribute("importePorDiaSemanaMioYResto", importePorDiaSemanaMioYResto);
		req.getSession().setAttribute("maxImportePorDiaSemanaMioYResto", maxImportePorDiaSemanaMioYResto);
		
		// estad comparadas cp
		req.getSession().setAttribute("numVentasPorCpMioYResto", numVentasPorCpMioYResto);
		req.getSession().setAttribute("maxNumVentasPorCpMioYResto", maxNumVentasPorCpMioYResto);
		req.getSession().setAttribute("importePorCpMioYResto", importePorCpMioYResto);
		req.getSession().setAttribute("maxImportePorCpMioYResto", maxImportePorCpMioYResto);
		
		// estad comparadas fidelidad
		req.getSession().setAttribute("numRestoClientesDistintos", numRestoClientesDistintos);
		req.getSession().setAttribute("porcRestoClientesUnaVez", porcRestoClientesUnaVez);
		req.getSession().setAttribute("porcRestoClientesDosVeces", porcRestoClientesDosVeces);
		req.getSession().setAttribute("porcRestoClientesTresOMasVeces", porcRestoClientesTresOMasVeces);
		
		getServletContext().getRequestDispatcher("/VerComercioEstadComparView.jsp").forward(req, resp);

	}
	
}