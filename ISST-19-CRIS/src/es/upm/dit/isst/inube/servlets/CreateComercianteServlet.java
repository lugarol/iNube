package es.upm.dit.isst.inube.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;

import es.upm.dit.isst.inube.dao.ClienteDAO;
import es.upm.dit.isst.inube.dao.ClienteDAOImplementation;
import es.upm.dit.isst.inube.dao.ComercianteDAO;
import es.upm.dit.isst.inube.dao.ComercianteDAOImplementation;
import es.upm.dit.isst.inube.dao.ComercioDAO;
import es.upm.dit.isst.inube.dao.ComercioDAOImplementation;
import es.upm.dit.isst.inube.dao.VentaDAO;
import es.upm.dit.isst.inube.dao.VentaDAOImplementation;
import es.upm.dit.isst.inube.model.Comerciante;

@WebServlet("/CreateComercianteServlet")
public class CreateComercianteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" CreateComercianteServlet > doGet ");
		System.out.println(" ------------------------------------ ");
		
		// redirigir a SignInView.jsp
		getServletContext().getRequestDispatcher("/SignInView.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" CreateComercianteServlet > doPost ");
		System.out.println(" ------------------------------------ ");
		
		// recoger parámetros formulario
		String usuario = req.getParameter("usuario");
		String password1 = req.getParameter("password1");
		
		// crear y rellenar objeto comerciante
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario(usuario);
		comerciante.setPassword(new Sha256Hash(password1).toString());
		
		// crear comerciante en bbdd
		ComercianteDAO comercianteDao = ComercianteDAOImplementation.getInstance();
		comercianteDao.create(comerciante);
		
		// redirigir al login para que entren en su cuenta
		resp.sendRedirect(req.getContextPath() + "/LoginServlet");
	}
}
