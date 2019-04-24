package es.upm.dit.isst.inube.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.inube.dao.ClienteDAO;
import es.upm.dit.isst.inube.dao.ClienteDAOImplementation;
import es.upm.dit.isst.inube.dao.VentaDAO;
import es.upm.dit.isst.inube.dao.VentaDAOImplementation;
import es.upm.dit.isst.inube.model.Cliente;
import es.upm.dit.isst.inube.model.Venta;

@WebServlet("/DeleteClienteServlet")
public class DeleteClienteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" DeleteClienteServlet > doPost ");
		System.out.println(" ------------------------------------ ");
		
		// recoger datos formulario
		int clienteId = Integer.parseInt(req.getParameter("clienteId"));
		
		// primero borrar las ventas de este cliente
		VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
		Collection<Venta> ventasFromCliente = ventaDAO.readAllFromCliente(clienteId);
		if (ventasFromCliente != null && ventasFromCliente.size() > 0) {
			for (Venta v : ventasFromCliente) {
				ventaDAO.delete(v);
			}
		}
		
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		Cliente cliente = clienteDAO.read(clienteId);
		
		clienteDAO.delete(cliente);
		
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
		
	}
	
}

