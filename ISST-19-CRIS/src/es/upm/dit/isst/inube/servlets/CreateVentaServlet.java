package es.upm.dit.isst.inube.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.inube.dao.ClienteDAO;
import es.upm.dit.isst.inube.dao.ClienteDAOImplementation;
import es.upm.dit.isst.inube.dao.ComercioDAO;
import es.upm.dit.isst.inube.dao.ComercioDAOImplementation;
import es.upm.dit.isst.inube.dao.VentaDAO;
import es.upm.dit.isst.inube.dao.VentaDAOImplementation;
import es.upm.dit.isst.inube.model.Cliente;
import es.upm.dit.isst.inube.model.Comercio;
import es.upm.dit.isst.inube.model.Venta;

@WebServlet("/CreateVentaServlet")
public class CreateVentaServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" CreateVentaServlet > doPost ");
		System.out.println(" ------------------------------------ ");
		
		// recoger datos formulario
		String diaHora = req.getParameter("date");
		double importe = Double.parseDouble(req.getParameter("price"));
		String comercioMerchantId = req.getParameter("comercio");
		int compradorId = Integer.parseInt(req.getParameter("comprador"));
		
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		
		Date fecha = null;
		try {
			fecha = formatter.parse(diaHora);
		} catch (Exception e) {
			System.out.println(" --- CreateVentaServlet - Error parseando fecha o fecha vacía");
		}
		
		Comercio comercio = comercioDAO.read(comercioMerchantId);
		Cliente cliente = clienteDAO.read(compradorId);
		
		Venta venta = new Venta();
		venta.setImporte(importe);
		venta.setComercio(comercio);
		venta.setPersona(cliente);
		venta.setFecha(fecha);
		
		VentaDAO ventaDao = VentaDAOImplementation.getInstance();
		ventaDao.create(venta);
	
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
		
	}
	
}

