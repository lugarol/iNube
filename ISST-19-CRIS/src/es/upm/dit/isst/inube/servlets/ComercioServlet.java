package es.upm.dit.isst.inube.servlets;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.inube.dao.ClienteDAO;
import es.upm.dit.isst.inube.dao.ClienteDAOImplementation;
import es.upm.dit.isst.inube.dao.ComercianteDAO;
import es.upm.dit.isst.inube.dao.ComercianteDAOImplementation;
import es.upm.dit.isst.inube.dao.ComercioDAO;
import es.upm.dit.isst.inube.dao.ComercioDAOImplementation;
import es.upm.dit.isst.inube.dao.VentaDAO;
import es.upm.dit.isst.inube.dao.VentaDAOImplementation;
import es.upm.dit.isst.inube.model.*;

@WebServlet("/ComercioServlet")
public class ComercioServlet extends HttpServlet {
	
	public String getIdsClientesDistintos(Set<Integer> idClientesDistintos) {
		String ids = "";
		for (Integer i : idClientesDistintos) {
			ids += i + ", ";
		}
		return ids;
	}
	
	public void printMap(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Hora: " + entry.getKey() + " | Nº ventas: " + entry.getValue());
        }
    }
	
	public void printMap2(Map<Integer, Double[]> map) {
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
	
	public void printMap3(Map<String, double[]> map) {
        for (Map.Entry<String, double[]> entry : map.entrySet()) {
        	String rangoEdad = entry.getKey();
        	double[] array_kk = entry.getValue();
        	System.out.println("Edad: " + rangoEdad + " | [nº ventas, importe]: " + Arrays.toString(array_kk));
        }
    }
	
	public void printMap4(Map<Integer, double[]> map) {
        for (Map.Entry<Integer, double[]> entry : map.entrySet()) {
        	int idCliente = entry.getKey();
        	double[] array_kk = entry.getValue();
        	System.out.println("idCliente: " + idCliente + " | [nº ventas, importe]: " + Arrays.toString(array_kk));
        }
    }
	
	public void printMap5(Map<String, Double[]> map) {
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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" ComercioServlet > doGet ");
		System.out.println(" ------------------------------------ ");
		
		String merchantId = req.getParameter("merchantid");
		
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		Comercio comercio = comercioDAO.read(merchantId);
		
		
		/*VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
		Collection<Venta> ventas = ventaDAO.readAllFromClienteForComercio(8, merchantId);
		System.out.println(" --- readAllFromClienteForComercio(8, merchantId).size(): " + ventas.size());
		
		Collection<Venta> ventas2 = ventaDAO.readAllForComercio(merchantId);
		System.out.println(" --- readAllForComercio(merchantId).size(): " + ventas2.size());
		*/
		
		int numVentas = comercio.getVentas().size();
		
		Set<Integer> idClientesDistintos = new HashSet<Integer>();
		double sumaImporte = 0.0;
		double importeMedio = 0.0;
		Map<Integer, Integer> horasVentaAux = new HashMap<Integer, Integer>();
		Map<Integer, Integer> diasSemanaVentaAux = new HashMap<Integer, Integer>();
		Map<Integer, Double> importeVentasHoraAux = new HashMap<Integer, Double>();
		Map<Integer, Double> importeVentasDiaSemanaAux = new HashMap<Integer, Double>();
		Map<Integer, Integer> numVentasCPAux = new HashMap<Integer, Integer>();
		Map<Integer, Double> importeVentasCPAux = new HashMap<Integer, Double>();
		
		Map<Integer, double[]> clientesYSusVentas = new HashMap<Integer, double[]>();

		Map<Integer, Double[]> ventasImportesPorHora = new HashMap<Integer, Double[]>();
		Double[] arrayAux = {0.0, 0.0};
		for (int i = 0; i < 24; i++) {
			horasVentaAux.put(i, 0);
			importeVentasHoraAux.put(i, 0.0);
			ventasImportesPorHora.put(i, arrayAux);
		}
		
		Map<Integer, Double[]> ventasImportesPorDiaSemana = new HashMap<Integer, Double[]>();
		for (int i = 1; i <= 7; i++) {
			diasSemanaVentaAux.put(i, 0);
			importeVentasDiaSemanaAux.put(i, 0.0);
			ventasImportesPorDiaSemana.put(i, arrayAux);
		}
		
		Map<String, Integer> diasSemanaVentaAuxStr = new HashMap<String, Integer>();
		Map<String, Double> importeVentasDiaSemanaAuxStr = new HashMap<String, Double>();
		Map<String, Double[]> ventasImportesPorDiaSemanaStr = new HashMap<String, Double[]>();
		for (int i = 2; i <= 7; i++) {
			diasSemanaVentaAuxStr.put(getDiaDeLaSemanaStr(i), 0);
			importeVentasDiaSemanaAuxStr.put(getDiaDeLaSemanaStr(i), 0.0);
			ventasImportesPorDiaSemanaStr.put(getDiaDeLaSemanaStr(i), arrayAux);
		}
		diasSemanaVentaAuxStr.put(getDiaDeLaSemanaStr(1), 0);
		importeVentasDiaSemanaAuxStr.put(getDiaDeLaSemanaStr(1), 0.0);
		ventasImportesPorDiaSemanaStr.put(getDiaDeLaSemanaStr(1), arrayAux);
		
		Map<Integer, Double[]> ventasImportesPorCP = new HashMap<Integer, Double[]>();
		
		Map<String, double[]> ventasImportesPorEdadAux = new HashMap<String, double[]>();
		ventasImportesPorEdadAux.put("0-9", new double[2]);
		ventasImportesPorEdadAux.put("10-19", new double[2]);
		ventasImportesPorEdadAux.put("20-29", new double[2]);
		ventasImportesPorEdadAux.put("30-39", new double[2]);
		ventasImportesPorEdadAux.put("40-49", new double[2]);
		ventasImportesPorEdadAux.put("50-59", new double[2]);
		ventasImportesPorEdadAux.put("60-69", new double[2]);
		ventasImportesPorEdadAux.put("70-79", new double[2]);
		ventasImportesPorEdadAux.put("80-89", new double[2]);
		ventasImportesPorEdadAux.put("90-99", new double[2]);
		
		int numVentasHombres = 0;
		int numVentasMujeres = 0;
		double importeHombres = 0.0;
		double importeMujeres = 0.0;
		
		Calendar calendar = Calendar.getInstance();
		
		if (numVentas > 0) {
			int xa = 1;
			for (Venta v : comercio.getVentas()) {
				System.out.println(" --- venta " + xa++);
				sumaImporte += v.getImporte(); // acumular importe
				
				idClientesDistintos.add(v.getPersona().getId()); // añadir id del cliente si no esta todavia
				
				calendar.setTime(v.getFecha());
				int hora = calendar.get(Calendar.HOUR_OF_DAY); // hora de la venta
				int diaSemana = calendar.get(Calendar.DAY_OF_WEEK); // dia de la semana de la venta (1 domingo, 2 lunes...)
				String diaSemanaStr = getDiaDeLaSemanaStr(diaSemana);
				
				int numVentasEnEsaHora = 0;
				double importeEnEsaHora = 0.0;
				
				int numVentasEnEseDia = 0;
				double importeEnEseDia = 0.0;
				
				// actualizar nº ventas en esa hora
				if (horasVentaAux.containsKey(hora)) {
					numVentasEnEsaHora = horasVentaAux.get(hora);
					horasVentaAux.put(hora, numVentasEnEsaHora + 1);
				} else {
					horasVentaAux.put(hora, 1);
				}
				
				// actualizar importe ventas en esa hora
				if (importeVentasHoraAux.containsKey(hora)) {
					importeEnEsaHora = importeVentasHoraAux.get(hora);
					importeVentasHoraAux.put(hora, importeEnEsaHora + v.getImporte());
				} else {
					importeVentasHoraAux.put(hora, v.getImporte());
				}
				
				// actualizar nº ventas en ese dia
				if (diasSemanaVentaAux.containsKey(diaSemana)) {
					numVentasEnEseDia = diasSemanaVentaAux.get(diaSemana);
					diasSemanaVentaAux.put(diaSemana, numVentasEnEseDia + 1);
				} else {
					diasSemanaVentaAux.put(diaSemana, 1);
				}
				
				// actualizar importe ventas en ese dia
				if (importeVentasDiaSemanaAux.containsKey(diaSemana)) {
					importeEnEseDia = importeVentasDiaSemanaAux.get(diaSemana);
					importeVentasDiaSemanaAux.put(diaSemana, importeEnEseDia + v.getImporte());
				} else {
					importeVentasDiaSemanaAux.put(diaSemana, v.getImporte());
				}
				
				
				
				// diasSemanaVentaAuxStr importeVentasDiaSemanaAuxStr ventasImportesPorDiaSemanaStr
				
				// actualizar nº ventas en ese dia
				if (diasSemanaVentaAuxStr.containsKey(diaSemanaStr)) {
					numVentasEnEseDia = diasSemanaVentaAuxStr.get(diaSemanaStr);
					diasSemanaVentaAuxStr.put(diaSemanaStr, numVentasEnEseDia + 1);
				} else {
					diasSemanaVentaAuxStr.put(diaSemanaStr, 1);
				}
				
				// actualizar importe ventas en ese dia
				if (importeVentasDiaSemanaAuxStr.containsKey(diaSemanaStr)) {
					importeEnEseDia = importeVentasDiaSemanaAuxStr.get(diaSemanaStr);
					importeVentasDiaSemanaAuxStr.put(diaSemanaStr, importeEnEseDia + v.getImporte());
				} else {
					importeVentasDiaSemanaAuxStr.put(diaSemanaStr, v.getImporte());
				}
				
				
				
				
				
				int cpCliente = v.getPersona().getCp();
				int numVentasEseCP = 0;
				double importeVentasEseCP = 0.0;
				
				// actualizar nº ventas por CP
				if (numVentasCPAux.containsKey(cpCliente)) {
					numVentasEseCP = numVentasCPAux.get(cpCliente);
					numVentasCPAux.put(cpCliente, numVentasEseCP + 1);
				} else {
					numVentasCPAux.put(cpCliente, 1);
				}
				
				// actualizar importe ventas en ese CP
				if (importeVentasCPAux.containsKey(cpCliente)) {
					importeVentasEseCP = importeVentasCPAux.get(cpCliente);
					importeVentasCPAux.put(cpCliente, importeVentasEseCP + v.getImporte());	
				} else {
					importeVentasCPAux.put(cpCliente, v.getImporte());
				}
				
				int sexo = v.getPersona().getSexo(); // 0 hombre, 1 mujer
				if (sexo == 0) {
					importeHombres += v.getImporte();
					numVentasHombres++;
				} else if (sexo == 1) {
					importeMujeres += v.getImporte();
					numVentasMujeres++;
				}
				
				double[] arrayVentasImportesEnEsaEdad = {0.0, 0.0};		
				switch(v.getPersona().getEdad()) {
					case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
						//System.out.println(" --- 0-9");
						arrayVentasImportesEnEsaEdad = ventasImportesPorEdadAux.get("0-9");
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						arrayVentasImportesEnEsaEdad[0] = arrayVentasImportesEnEsaEdad[0] + 1;
						arrayVentasImportesEnEsaEdad[1] = arrayVentasImportesEnEsaEdad[1] + v.getImporte();
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						ventasImportesPorEdadAux.put("0-9", arrayVentasImportesEnEsaEdad);
						break;
					case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
						//System.out.println(" --- 10-19");
						arrayVentasImportesEnEsaEdad = ventasImportesPorEdadAux.get("10-19");
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						arrayVentasImportesEnEsaEdad[0] = arrayVentasImportesEnEsaEdad[0] + 1;
						arrayVentasImportesEnEsaEdad[1] = arrayVentasImportesEnEsaEdad[1] + v.getImporte();
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						ventasImportesPorEdadAux.put("10-19", arrayVentasImportesEnEsaEdad);
						break;
					case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29:
						//System.out.println(" --- 20-29");
						arrayVentasImportesEnEsaEdad = ventasImportesPorEdadAux.get("20-29");
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						arrayVentasImportesEnEsaEdad[0] = arrayVentasImportesEnEsaEdad[0] + 1;
						arrayVentasImportesEnEsaEdad[1] = arrayVentasImportesEnEsaEdad[1] + v.getImporte();
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						ventasImportesPorEdadAux.put("20-29", arrayVentasImportesEnEsaEdad);
						break;
					case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39:
						//System.out.println(" --- 30-39");
						arrayVentasImportesEnEsaEdad = ventasImportesPorEdadAux.get("30-39");
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						arrayVentasImportesEnEsaEdad[0] = arrayVentasImportesEnEsaEdad[0] + 1;
						arrayVentasImportesEnEsaEdad[1] = arrayVentasImportesEnEsaEdad[1] + v.getImporte();
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						ventasImportesPorEdadAux.put("30-39", arrayVentasImportesEnEsaEdad);
						break;
					case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 49:
						//System.out.println(" --- 40-49");
						arrayVentasImportesEnEsaEdad = ventasImportesPorEdadAux.get("40-49");
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						arrayVentasImportesEnEsaEdad[0] = arrayVentasImportesEnEsaEdad[0] + 1;
						arrayVentasImportesEnEsaEdad[1] = arrayVentasImportesEnEsaEdad[1] + v.getImporte();
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						ventasImportesPorEdadAux.put("40-49", arrayVentasImportesEnEsaEdad);
						break;
					case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 59:
						//System.out.println(" --- 50-59");
						arrayVentasImportesEnEsaEdad = ventasImportesPorEdadAux.get("50-59");
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						arrayVentasImportesEnEsaEdad[0] = arrayVentasImportesEnEsaEdad[0] + 1;
						arrayVentasImportesEnEsaEdad[1] = arrayVentasImportesEnEsaEdad[1] + v.getImporte();
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						ventasImportesPorEdadAux.put("50-59", arrayVentasImportesEnEsaEdad);
						break;
					case 60: case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 68: case 69:
						//System.out.println(" --- 60-69");
						arrayVentasImportesEnEsaEdad = ventasImportesPorEdadAux.get("60-69");
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						arrayVentasImportesEnEsaEdad[0] = arrayVentasImportesEnEsaEdad[0] + 1;
						arrayVentasImportesEnEsaEdad[1] = arrayVentasImportesEnEsaEdad[1] + v.getImporte();
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						ventasImportesPorEdadAux.put("60-69", arrayVentasImportesEnEsaEdad);
						break;
					case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79:
						//System.out.println(" --- 70-79");
						arrayVentasImportesEnEsaEdad = ventasImportesPorEdadAux.get("70-79");
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						arrayVentasImportesEnEsaEdad[0] = arrayVentasImportesEnEsaEdad[0] + 1;
						arrayVentasImportesEnEsaEdad[1] = arrayVentasImportesEnEsaEdad[1] + v.getImporte();
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						ventasImportesPorEdadAux.put("70-79", arrayVentasImportesEnEsaEdad);
						break;
					case 80: case 81: case 82: case 83: case 84: case 85: case 86: case 87: case 88: case 89:
						//System.out.println(" --- 80-89");
						arrayVentasImportesEnEsaEdad = ventasImportesPorEdadAux.get("80-89");
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						arrayVentasImportesEnEsaEdad[0] = arrayVentasImportesEnEsaEdad[0] + 1;
						arrayVentasImportesEnEsaEdad[1] = arrayVentasImportesEnEsaEdad[1] + v.getImporte();
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						ventasImportesPorEdadAux.put("80-89", arrayVentasImportesEnEsaEdad);
						break;
					case 90: case 91: case 92: case 93: case 94: case 95: case 96: case 97: case 98: case 99:
						//System.out.println(" --- 90-99");
						arrayVentasImportesEnEsaEdad = ventasImportesPorEdadAux.get("90-99");
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						arrayVentasImportesEnEsaEdad[0] = arrayVentasImportesEnEsaEdad[0] + 1;
						arrayVentasImportesEnEsaEdad[1] = arrayVentasImportesEnEsaEdad[1] + v.getImporte();
						//System.out.println(Arrays.toString(arrayVentasImportesEnEsaEdad));
						ventasImportesPorEdadAux.put("90-99", arrayVentasImportesEnEsaEdad);
						break;
					default:
						break;
				}
				
				//printMap3(ventasImportesPorEdadAux);
				
				int idCliente = v.getPersona().getId();
				if (clientesYSusVentas.containsKey(idCliente)) {
					double[] arrayVentasImportesDeEseCliente = clientesYSusVentas.get(idCliente);
					//System.out.println(" --- cargando clientesYSusVentas: cliente existente: " + idCliente + ". [Nº ventas, importe]: " + Arrays.toString(arrayVentasImportesDeEseCliente));
					arrayVentasImportesDeEseCliente[0] = arrayVentasImportesDeEseCliente[0] + 1;
					arrayVentasImportesDeEseCliente[1] = arrayVentasImportesDeEseCliente[1] + v.getImporte();
					//System.out.println(" --- cargando clientesYSusVentas: venta " + v.getId() + ". [Nº ventas, importe]: " + Arrays.toString(arrayVentasImportesDeEseCliente));
					clientesYSusVentas.put(idCliente, arrayVentasImportesDeEseCliente);
				} else {
					//System.out.println(" --- cargando clientesYSusVentas: cliente nuevo: " + idCliente);
					double[] arrayAPoner = {1.0, v.getImporte()};
					clientesYSusVentas.put(idCliente, arrayAPoner);
					//double[] arrayVentasImportesDeEseCliente = clientesYSusVentas.get(idCliente);
					//System.out.println(" --- cargando clientesYSusVentas: recién actualizado (" + idCliente + "). [Nº ventas, importe]: " + Arrays.toString(arrayVentasImportesDeEseCliente));
				}
				
			}
			
			importeMedio = (double) (sumaImporte / (double) numVentas);
			
		}
		
		printMap4(clientesYSusVentas);
		
		int numClientesUnaVez = 0;
		int numClientesDosVeces = 0;
		int numClientesTresOMasVeces = 0;
		
		for (Map.Entry<Integer, double[]> entry : clientesYSusVentas.entrySet()) {
			if (entry.getValue()[0] == 1) {
				numClientesUnaVez++;
			} else if (entry.getValue()[0] == 2) {
				numClientesDosVeces++;
			} else if (entry.getValue()[0] >= 3) {
				numClientesTresOMasVeces++;
			}
		}
		
		for (Map.Entry<Integer, Integer> entry : horasVentaAux.entrySet()) {
			//System.out.println("Hora: " + entry.getKey() + " | Nº ventas: " + entry.getValue());
			int hora = entry.getKey();
			double numeroDeVentasEnHora = (double) entry.getValue();
			Double[] arrayAMeter = {numeroDeVentasEnHora, 0.0};
			ventasImportesPorHora.put(hora, arrayAMeter);
		}
		
		for (Map.Entry<Integer, Double> entry : importeVentasHoraAux.entrySet()) {
			//System.out.println("Hora: " + entry.getKey() + " | Importe ventas: " + entry.getValue());
			int hora = entry.getKey();
			double importeDeVentasEnHora = entry.getValue();
			Double[] arrayAMeter = ventasImportesPorHora.get(hora);
			arrayAMeter[1] = importeDeVentasEnHora;
			ventasImportesPorHora.put(hora, arrayAMeter);
		}
		
		
		
		for (Map.Entry<Integer, Integer> entry : diasSemanaVentaAux.entrySet()) {
			int dia = entry.getKey();
			double numeroDeVentasEnDiaSemana = (double) entry.getValue();
			Double[] arrayAMeter = {numeroDeVentasEnDiaSemana, 0.0};
			ventasImportesPorDiaSemana.put(dia, arrayAMeter);
		}
		
		for (Map.Entry<Integer, Double> entry : importeVentasDiaSemanaAux.entrySet()) {
			int dia = entry.getKey();
			double importeDeVentasEnDiaSemana = (double) entry.getValue();
			Double[] arrayAMeter = ventasImportesPorDiaSemana.get(dia);
			arrayAMeter[1] = importeDeVentasEnDiaSemana;
			ventasImportesPorDiaSemana.put(dia, arrayAMeter);
		}
		
		
		
		
		
		// diasSemanaVentaAuxStr importeVentasDiaSemanaAuxStr ventasImportesPorDiaSemanaStr
		for (Map.Entry<String, Integer> entry : diasSemanaVentaAuxStr.entrySet()) {
			String diaStr = entry.getKey();
			double numeroDeVentasEnDiaSemana = (double) entry.getValue();
			Double[] arrayAMeter = {numeroDeVentasEnDiaSemana, 0.0};
			ventasImportesPorDiaSemanaStr.put(diaStr, arrayAMeter);
		}
		
		for (Map.Entry<String, Double> entry : importeVentasDiaSemanaAuxStr.entrySet()) {
			String diaStr = entry.getKey();
			double importeDeVentasEnDiaSemana = (double) entry.getValue();
			Double[] arrayAMeter = ventasImportesPorDiaSemanaStr.get(diaStr);
			arrayAMeter[1] = importeDeVentasEnDiaSemana;
			ventasImportesPorDiaSemanaStr.put(diaStr, arrayAMeter);
		}
		
		// ordenar ventasImportesPorDiaSemanaStr
		Map<String, Double[]> ventasImportesPorDiaSemanaStrOrdenado = new LinkedHashMap<String, Double[]>();
		
		Double[] arrayKK = new Double[2];
		
		String dia = "";
		// Lunes
		dia = "Lunes";
		arrayKK = ventasImportesPorDiaSemanaStr.get(dia);
		ventasImportesPorDiaSemanaStrOrdenado.put(dia, arrayKK);
		
		// Martes
		dia = "Martes";
		arrayKK = ventasImportesPorDiaSemanaStr.get(dia);
		ventasImportesPorDiaSemanaStrOrdenado.put(dia, arrayKK);
		
		// Miércoles
		dia = "Miércoles";
		arrayKK = ventasImportesPorDiaSemanaStr.get(dia);
		ventasImportesPorDiaSemanaStrOrdenado.put(dia, arrayKK);
		
		// Jueves
		dia = "Jueves";
		arrayKK = ventasImportesPorDiaSemanaStr.get(dia);
		ventasImportesPorDiaSemanaStrOrdenado.put(dia, arrayKK);
		
		// Viernes
		dia = "Viernes";
		arrayKK = ventasImportesPorDiaSemanaStr.get(dia);
		ventasImportesPorDiaSemanaStrOrdenado.put(dia, arrayKK);
		
		// Sábado
		dia = "Sábado";
		arrayKK = ventasImportesPorDiaSemanaStr.get(dia);
		ventasImportesPorDiaSemanaStrOrdenado.put(dia, arrayKK);
		
		// Domingo
		dia = "Domingo";
		arrayKK = ventasImportesPorDiaSemanaStr.get(dia);
		ventasImportesPorDiaSemanaStrOrdenado.put(dia, arrayKK);
		
		
		
		for (Map.Entry<Integer, Integer> entry : numVentasCPAux.entrySet()) {
			int cpCliente = entry.getKey();
			double numeroDeVentasEnCP = (double) entry.getValue();
			Double[] arrayAMeter = {numeroDeVentasEnCP, 0.0};
			ventasImportesPorCP.put(cpCliente, arrayAMeter);
		}
		
		for (Map.Entry<Integer, Double> entry : importeVentasCPAux.entrySet()) {
			int cpCliente = entry.getKey();
			double importeDeVentasEnCP = entry.getValue();
			Double[] arrayAMeter = ventasImportesPorCP.get(cpCliente);
			arrayAMeter[1] = importeDeVentasEnCP;
			ventasImportesPorCP.put(cpCliente, arrayAMeter);
		}
		
		Map<Integer, Integer> horasVenta = new TreeMap<Integer, Integer>(horasVentaAux); // TreeMap es ordenado, así ordena por horas
		Map<Integer, Double> importeVentasHora = new TreeMap<Integer, Double>(importeVentasHoraAux); // TreeMap es ordenado, así ordena por horas
		Map<String, double[]> ventasImportesPorEdad = new TreeMap<String, double[]>(ventasImportesPorEdadAux); // TreeMap es ordenado, así ordena por edad
		
		DecimalFormat df2Decimales = new DecimalFormat("#.##");
		df2Decimales.setRoundingMode(RoundingMode.CEILING);
		
		DecimalFormat df1Decimal = new DecimalFormat("#.#");
		df1Decimal.setRoundingMode(RoundingMode.CEILING);
		
		String sumaImporteStr = df2Decimales.format(sumaImporte);
		String importeMedioStr = df2Decimales.format(importeMedio);
		String importeHombresStr = df2Decimales.format(importeHombres);
		String importeMujeresStr = df2Decimales.format(importeMujeres);
		int numClientesDistintos = idClientesDistintos.size();
		
		System.out.println("numClientesDistintos: " + numClientesDistintos);
		System.out.println("numClientesUnaVez: " + numClientesUnaVez);
		System.out.println("numClientesDosVeces: " + numClientesDosVeces);
		System.out.println("numClientesTresOMasVeces: " + numClientesTresOMasVeces);
		
		double porcClientesUnaVez = (numClientesUnaVez / (double) numClientesDistintos) * 100.0;
		double porcClientesDosVeces = (numClientesDosVeces / (double) numClientesDistintos) * 100.0;
		double porcClientesTresOMasVeces = (numClientesTresOMasVeces / (double) numClientesDistintos) * 100.0;
		
		System.out.println("porcClientesUnaVez: " + porcClientesUnaVez);
		System.out.println("porcClientesDosVeces: " + porcClientesDosVeces);
		System.out.println("porcClientesTresOMasVeces: " + porcClientesTresOMasVeces);
		
		String porcClientesUnaVezStr = df1Decimal.format(porcClientesUnaVez);
		String porcClientesDosVecesStr = df1Decimal.format(porcClientesDosVeces);
		String porcClientesTresOMasVecesStr = df1Decimal.format(porcClientesTresOMasVeces);
		
		System.out.println("porcClientesUnaVezStr: " + porcClientesUnaVezStr);
		System.out.println("porcClientesDosVecesStr: " + porcClientesDosVecesStr);
		System.out.println("porcClientesTresOMasVecesStr: " + porcClientesTresOMasVecesStr);
		
		
		
		req.getSession().setAttribute("comercio", comercio);
		req.getSession().setAttribute("num_ventas", numVentas);
		req.getSession().setAttribute("suma_importe", sumaImporteStr);
		req.getSession().setAttribute("importe_medio", importeMedioStr);
		req.getSession().setAttribute("num_clientes_distintos", numClientesDistintos);
		req.getSession().setAttribute("horas_venta", horasVenta);
		req.getSession().setAttribute("importe_ventas_hora", importeVentasHora);
		req.getSession().setAttribute("num_ventas_e_importes_hora", ventasImportesPorHora); // nº ventas e importes por hora
		req.getSession().setAttribute("importe_hombres", importeHombresStr);
		req.getSession().setAttribute("importe_mujeres", importeMujeresStr);
		req.getSession().setAttribute("num_ventas_hombres", numVentasHombres);
		req.getSession().setAttribute("num_ventas_mujeres", numVentasMujeres);
		req.getSession().setAttribute("num_ventas_e_importes_edad", ventasImportesPorEdad);  // nº ventas e importes por edad
		req.getSession().setAttribute("num_ventas_e_importes_dia_semana", ventasImportesPorDiaSemana);  // nº ventas e importes por dia de la semana
		req.getSession().setAttribute("num_ventas_e_importes_cp", ventasImportesPorCP); // nº ventas e importes por cp
		req.getSession().setAttribute("num_ventas_e_importes_dia_semana_str", ventasImportesPorDiaSemanaStrOrdenado);  // nº ventas e importes por dia de la semana strings
		
		req.getSession().setAttribute("num_clientes_una_vez", numClientesUnaVez); 
		req.getSession().setAttribute("num_clientes_dos_veces", numClientesDosVeces);
		req.getSession().setAttribute("num_clientes_tres_o_mas_veces", numClientesTresOMasVeces);
		
		req.getSession().setAttribute("porc_clientes_una_vez", porcClientesUnaVez); 
		req.getSession().setAttribute("porc_clientes_dos_veces", porcClientesDosVeces);
		req.getSession().setAttribute("porc_clientes_tres_o_mas_veces", porcClientesTresOMasVeces);
		
		req.getSession().setAttribute("porc_clientes_una_vez_str", porcClientesUnaVezStr); 
		req.getSession().setAttribute("porc_clientes_dos_veces_str", porcClientesDosVecesStr);
		req.getSession().setAttribute("porc_clientes_tres_o_mas_veces_str", porcClientesTresOMasVecesStr);
		
		getServletContext().getRequestDispatcher("/ComercioView.jsp").forward(req, resp);		

	}
}

