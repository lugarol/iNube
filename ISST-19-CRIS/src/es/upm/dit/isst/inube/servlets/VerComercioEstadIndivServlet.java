package es.upm.dit.isst.inube.servlets;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.inube.dao.ComercioDAO;
import es.upm.dit.isst.inube.dao.ComercioDAOImplementation;
import es.upm.dit.isst.inube.model.*;

@WebServlet("/VerComercioEstadIndivServlet")
public class VerComercioEstadIndivServlet extends HttpServlet {
	
	public void printMapIntegerInteger(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Hora: " + entry.getKey() + " | Nº ventas: " + entry.getValue());
        }
    }
	
	public void printMapIntegerDoubleArr(Map<Integer, Double[]> map) {
        for (Map.Entry<Integer, Double[]> entry : map.entrySet()) {
        	Integer hora_kk = entry.getKey();
        	Double[] array_kk = entry.getValue();
        	System.out.println("\nlen: " + array_kk.length);
        	System.out.println("Hora: " + hora_kk + " | array_kk: " + array_kk);
        	System.out.println("Hora: " + hora_kk + " | [nº ventas, importe]: " + Arrays.toString(array_kk));
        	System.out.println("Hora: " + hora_kk + " | nº ventas: " + array_kk[0]);
        	System.out.println("Hora: " + hora_kk + " | importe: " + array_kk[1]);
        }
    }
	
	public void printMapStringdoubleArr(Map<String, double[]> map) {
        for (Map.Entry<String, double[]> entry : map.entrySet()) {
        	String rangoEdad = entry.getKey();
        	double[] array_kk = entry.getValue();
        	System.out.println("Edad: " + rangoEdad + " | [nº ventas, importe]: " + Arrays.toString(array_kk));
        }
    }
	
	public void printMapIntegerdoubleArr(Map<Integer, double[]> map) {
        for (Map.Entry<Integer, double[]> entry : map.entrySet()) {
        	int idCliente = entry.getKey();
        	double[] array_kk = entry.getValue();
        	System.out.println("idCliente: " + idCliente + " | [nº ventas, importe]: " + Arrays.toString(array_kk));
        }
    }
	
	public void printMapStringDoubleArr(Map<String, Double[]> map) {
        for (Map.Entry<String, Double[]> entry : map.entrySet()) {
        	String rangoEdad = entry.getKey();
        	Double[] array_kk = entry.getValue();
        	System.out.println("Día semana: " + rangoEdad + " | [nº ventas, importe]: " + Arrays.toString(array_kk));
        }
    }
	
	public String getDiaDeLaSemanaStr(int diaSemana) {
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
	
	private Map<Integer, double[]> inicializarNumVentasEImportePorHora() {
		Map<Integer, double[]> numVentasEImportePorHora = new HashMap<Integer, double[]>();
		
		for (int i = 0; i < 24; i++) {
			numVentasEImportePorHora.put(i, new double[2]);
		}
		
		return numVentasEImportePorHora;
	}
	
	private Map<String, double[]> inicializarNumVentasEImportePorEdad() {
		Map<String, double[]> numVentasEImportePorEdad = new LinkedHashMap<String, double[]>();
		
		numVentasEImportePorEdad.put("0-9", new double[2]);
		numVentasEImportePorEdad.put("10-19", new double[2]);
		numVentasEImportePorEdad.put("20-29", new double[2]);
		numVentasEImportePorEdad.put("30-39", new double[2]);
		numVentasEImportePorEdad.put("40-49", new double[2]);
		numVentasEImportePorEdad.put("50-59", new double[2]);
		numVentasEImportePorEdad.put("60-69", new double[2]);
		numVentasEImportePorEdad.put("70-79", new double[2]);
		numVentasEImportePorEdad.put("80-89", new double[2]);
		numVentasEImportePorEdad.put("90-99", new double[2]);
		
		return numVentasEImportePorEdad;
	}
	
	private Map<String, double[]> inicializarNumVentasEImportePorDiaSemana() {
		Map<String, double[]> numVentasEImportePorDiaSemana = new LinkedHashMap<String, double[]>();
		
		numVentasEImportePorDiaSemana.put("Lunes", new double[2]);
		numVentasEImportePorDiaSemana.put("Martes", new double[2]);
		numVentasEImportePorDiaSemana.put("Miércoles", new double[2]);
		numVentasEImportePorDiaSemana.put("Jueves", new double[2]);
		numVentasEImportePorDiaSemana.put("Viernes", new double[2]);
		numVentasEImportePorDiaSemana.put("Sábado", new double[2]);
		numVentasEImportePorDiaSemana.put("Domingo", new double[2]);
		
		return numVentasEImportePorDiaSemana;
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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" VerComercioEstadIndivServlet > doGet ");
		System.out.println(" ------------------------------------ ");
		
		// recoger parámetros
		String merchantId = req.getParameter("merchantId");
		
		// obtener comercio
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		Comercio comercio = comercioDAO.read(merchantId);
		
		Collection<Venta> misVentas = comercio.getVentas();
		int numVentas = misVentas.size();
		
		Set<Integer> idsClientesDistintos = new HashSet<Integer>();
		double importeTotal = 0.0;
		double importeMedio = 0.0;
		
		Map<Integer, double[]> numVentasEImportePorHora = inicializarNumVentasEImportePorHora();
		Map<String, double[]> numVentasEImportePorEdad = inicializarNumVentasEImportePorEdad();
		Map<String, double[]> numVentasEImportePorDiaSemana = inicializarNumVentasEImportePorDiaSemana();
		Map<Integer, double[]> numVentasEImportePorCp = new HashMap<Integer, double[]>(); // si no queda ordenado, probar un TreeMap o LinkedHashMap
		Map<Integer, double[]> numVentasEImportePorCliente = new HashMap<Integer, double[]>(); // si no queda ordenado, probar un TreeMap o LinkedHashMap (clientesYSusVentas)
		
		int numVentasHombres = 0;
		int numVentasMujeres = 0;
		double importeHombres = 0.0;
		double importeMujeres = 0.0;
		
		int numClientesUnaVez = 0;
		int numClientesDosVeces = 0;
		int numClientesTresOMasVeces = 0;
		double porcClientesUnaVez = 0.0;
		double porcClientesDosVeces = 0.0;
		double porcClientesTresOMasVeces = 0.0;
		
		int numClientesDistintos = 0;
		
		Calendar calendar = Calendar.getInstance();
		
		if (numVentas > 0) {
			for (Venta v : misVentas) {
				idsClientesDistintos.add(v.getPersona().getId());
				
				calendar.setTime(v.getFecha());
				
				// num ventas e importe por hora
				int hora = calendar.get(Calendar.HOUR_OF_DAY);
				double[] arrayNumVentasEImportePorHora = numVentasEImportePorHora.get(hora);
				arrayNumVentasEImportePorHora[0] += + 1;
				arrayNumVentasEImportePorHora[1] += v.getImporte();
				numVentasEImportePorHora.put(hora, arrayNumVentasEImportePorHora);
				
				// num ventas e importe por sexo
				if (v.getPersona().getSexo() == 0) { // hombre
					numVentasHombres++;
					importeHombres += v.getImporte();
				} else if (v.getPersona().getSexo() == 1) { // mujer
					numVentasMujeres++;
					importeMujeres += v.getImporte();
				}
				
				// num ventas e importe por edad
				String rangoEdadCliente = getRangoEdad(v.getPersona().getEdad());
				double[] arrayNumVentasEImportePorEdad = numVentasEImportePorEdad.get(rangoEdadCliente);
				arrayNumVentasEImportePorEdad[0] += + 1;
				arrayNumVentasEImportePorEdad[1] += v.getImporte();
				numVentasEImportePorEdad.put(rangoEdadCliente, arrayNumVentasEImportePorEdad);
				
				// num ventas e importe por dia semana
				int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
				String diaSemanaStr = getDiaSemanaStr(diaSemana);
				double[] arrayNumVentasEImportePorDiaSemana = numVentasEImportePorDiaSemana.get(diaSemanaStr);
				arrayNumVentasEImportePorDiaSemana[0] += 1;
				arrayNumVentasEImportePorDiaSemana[1] += v.getImporte();
				numVentasEImportePorDiaSemana.put(diaSemanaStr, arrayNumVentasEImportePorDiaSemana);
				
				// num ventas e importe por cp
				int cpCliente = v.getPersona().getCp();
				double[] arrayNumVentasEImportePorCp = new double[2];
				if (numVentasEImportePorCp.containsKey(cpCliente)) {
					arrayNumVentasEImportePorCp = numVentasEImportePorCp.get(cpCliente);
				}
				arrayNumVentasEImportePorCp[0] += 1;
				arrayNumVentasEImportePorCp[1] += v.getImporte();
				numVentasEImportePorCp.put(cpCliente, arrayNumVentasEImportePorCp);
				
				// fidelidad clientes
				int idCliente = v.getPersona().getId();
				double[] arrayNumVentasEImportePorCliente = new double[2];
				if (numVentasEImportePorCliente.containsKey(idCliente)) {
					arrayNumVentasEImportePorCliente = numVentasEImportePorCliente.get(idCliente);
				}
				arrayNumVentasEImportePorCliente[0] += 1;
				arrayNumVentasEImportePorCliente[1] += v.getImporte();
				numVentasEImportePorCliente.put(idCliente, arrayNumVentasEImportePorCliente);
				
			}
			
			// obtener numClientesUnaVez, numClientesDosVeces y numClientesTresOMasVeces
			for (Map.Entry<Integer, double[]> entry : numVentasEImportePorCliente.entrySet()) {
				if (entry.getValue()[0] == 1) {
					numClientesUnaVez++;
				} else if (entry.getValue()[0] == 2) {
					numClientesDosVeces++;
				} else if (entry.getValue()[0] >= 3) {
					numClientesTresOMasVeces++;
				}
			}
			
			numClientesDistintos = numVentasEImportePorCliente.keySet().size();
			
			porcClientesUnaVez = numClientesUnaVez / (double) numClientesDistintos * 100.0;
			porcClientesDosVeces = numClientesDosVeces / (double) numClientesDistintos * 100.0;
			porcClientesTresOMasVeces = numClientesTresOMasVeces / (double) numClientesDistintos * 100.0;
			
			importeTotal = importeHombres + importeMujeres;
			importeMedio = importeTotal / numVentas;
			
		}
		
		DecimalFormat df2Decimales = new DecimalFormat("#.##");
		df2Decimales.setRoundingMode(RoundingMode.CEILING);
		
		req.getSession().setAttribute("comercio", comercio);
		req.getSession().setAttribute("numVentas", numVentas);
		req.getSession().setAttribute("importeTotal", df2Decimales.format(importeTotal));
		req.getSession().setAttribute("importeMedio", df2Decimales.format(importeMedio));
		req.getSession().setAttribute("numVentasEImportePorHora", numVentasEImportePorHora);
		req.getSession().setAttribute("numVentasHombres", numVentasHombres);
		req.getSession().setAttribute("importeHombres", importeHombres);
		req.getSession().setAttribute("numVentasMujeres", numVentasMujeres);
		req.getSession().setAttribute("importeMujeres", importeMujeres);
		req.getSession().setAttribute("numVentasEImportePorEdad", numVentasEImportePorEdad);
		req.getSession().setAttribute("numVentasEImportePorDiaSemana", numVentasEImportePorDiaSemana);
		req.getSession().setAttribute("numVentasEImportePorCp", numVentasEImportePorCp);
		
		req.getSession().setAttribute("numClientesDistintos", numClientesDistintos);
		req.getSession().setAttribute("porcClientesUnaVez", porcClientesUnaVez);
		req.getSession().setAttribute("porcClientesDosVeces", porcClientesDosVeces);
		req.getSession().setAttribute("porcClientesTresOMasVeces", porcClientesTresOMasVeces);
		
		getServletContext().getRequestDispatcher("/VerComercioEstadIndivView.jsp").forward(req, resp);

	}
}

