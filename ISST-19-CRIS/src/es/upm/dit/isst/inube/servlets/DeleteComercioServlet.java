package es.upm.dit.isst.inube.servlets;

import java.io.IOException;
import java.util.Collection;

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

@WebServlet("/DeleteComercioServlet")
public class DeleteComercioServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" DeleteComercioServlet > doPost ");
		System.out.println(" ------------------------------------ ");
		
		// recoger datos formulario
		String merchantId = req.getParameter("merchantId");
		
		// primero borrar las ventas de este comercio
		VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
		Collection<Venta> ventasFromComercio = ventaDAO.readAllForComercio(merchantId);
		if (ventasFromComercio != null && ventasFromComercio.size() > 0) {
			for (Venta v : ventasFromComercio) {
				ventaDAO.delete(v);
			}
		}
		
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		Comercio comercio = comercioDAO.read(merchantId);
		
		comercioDAO.delete(comercio);
	
		resp.sendRedirect(req.getContextPath() + "/GestionarComercianteServlet");
		
	}
	
}

