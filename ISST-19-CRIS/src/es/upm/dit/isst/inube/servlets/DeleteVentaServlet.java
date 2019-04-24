package es.upm.dit.isst.inube.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.inube.dao.VentaDAO;
import es.upm.dit.isst.inube.dao.VentaDAOImplementation;
import es.upm.dit.isst.inube.model.Venta;

@WebServlet("/DeleteVentaServlet")
public class DeleteVentaServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" DeleteVentaServlet > doPost ");
		System.out.println(" ------------------------------------ ");
		
		// recoger datos formulario
		int ventaId = Integer.parseInt(req.getParameter("ventaId"));
		
		VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
		Venta venta = ventaDAO.read(ventaId);
		
		ventaDAO.delete(venta);
	
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
		
	}
	
}

