package es.upm.dit.isst.inube.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
import es.upm.dit.isst.inube.model.Comerciante;
import es.upm.dit.isst.inube.model.Comercio;

@WebServlet("/GestionarComercianteServlet")
public class GestionarComercianteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" GestionarComercianteServlet > doGet ");
		System.out.println(" ------------------------------------ ");
		
		// obtener mi usuario como comerciante
		Subject currentUser = SecurityUtils.getSubject();
		String currentUserPrincipal = (String) currentUser.getPrincipal();
		ComercianteDAO comercianteDao = ComercianteDAOImplementation.getInstance();
		Comerciante comerciante = comercianteDao.readFromUsuario(currentUserPrincipal);
		int idComerciante = comerciante.getId();
		
		/* revisar quitar
		if (comerciante != null) {
			System.out.println(" --- ComercianteServlet - comerciante.getUsuario(): " + comerciante.getUsuario());
			System.out.println(" --- ComercianteServlet - comerciante.getId(): " + comerciante.getId());
		} else {
			System.out.println(" --- ComercianteServlet: comerciante es null");
		}
		*/
		
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		/* revisar quitar
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
		*/
		
		Collection<Comercio> misComercios = comercioDAO.readAllFromComerciante(idComerciante);	
		
		/* revisar quitar
		req.getSession().setAttribute("cliente_list", clienteDao.readAll());
		req.getSession().setAttribute("comerciante_list", comercianteDao.readAll());
		req.getSession().setAttribute("comercio_list", comercioDao.readAll());
		req.getSession().setAttribute("venta_list", ventaDao.readAll());
		*/
		req.getSession().setAttribute("misComercios", misComercios);
		req.getSession().setAttribute("idComerciante", idComerciante);
		
		getServletContext().getRequestDispatcher("/GestionarComercianteView.jsp").forward(req, resp);
		
	}
}

