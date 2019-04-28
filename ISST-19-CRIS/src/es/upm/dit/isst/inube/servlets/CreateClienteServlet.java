package es.upm.dit.isst.inube.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.inube.dao.ClienteDAO;
import es.upm.dit.isst.inube.dao.ClienteDAOImplementation;
import es.upm.dit.isst.inube.model.Cliente;

@WebServlet("/CreateClienteServlet")
public class CreateClienteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// recoger datos formulario (cp, sex, age)
		int id = Integer.parseInt(req.getParameter("id"));
		int cp = Integer.parseInt(req.getParameter("cp"));
		int sexo = Integer.parseInt(req.getParameter("sex"));
		int edad = Integer.parseInt(req.getParameter("age"));
		
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setCp(cp);
		cliente.setSexo(sexo);
		cliente.setEdad(edad);
		
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		clienteDAO.create(cliente);
		
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
		
	}
	
}

