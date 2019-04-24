package es.upm.dit.isst.inube.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.inube.dao.ComercianteDAO;
import es.upm.dit.isst.inube.dao.ComercianteDAOImplementation;
import es.upm.dit.isst.inube.dao.ComercioDAO;
import es.upm.dit.isst.inube.dao.ComercioDAOImplementation;
import es.upm.dit.isst.inube.dao.VentaDAO;
import es.upm.dit.isst.inube.dao.VentaDAOImplementation;
import es.upm.dit.isst.inube.model.Comerciante;
import es.upm.dit.isst.inube.model.Comercio;
import es.upm.dit.isst.inube.model.Venta;

@WebServlet("/DeleteComercianteServlet")
public class DeleteComercianteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" DeleteComercianteServlet > doPost ");
		System.out.println(" ------------------------------------ ");
		
		// recoger datos formulario
		int comercianteId = Integer.parseInt(req.getParameter("comercianteId"));
		
		// primero borrar los comercios de este comerciante, primero borrar las ventas de sus comercios
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		Collection<Comercio> comerciosFromComerciante = comercioDAO.readAllFromComerciante(comercianteId);
		if (comerciosFromComerciante != null && comerciosFromComerciante.size() > 0) {
			VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
			for (Comercio comercio : comerciosFromComerciante) {
				Collection<Venta> ventasFromComercio = ventaDAO.readAllForComercio(comercio.getMerchantId());
				if (ventasFromComercio != null && ventasFromComercio.size() > 0) {
					for (Venta v : ventasFromComercio) {
						ventaDAO.delete(v);
					}
				}
				comercioDAO.delete(comercio);
			}
		}
		
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		Comerciante comerciante = comercianteDAO.read(comercianteId);
		
		comercianteDAO.delete(comerciante);
	
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
		
	}
	
}

