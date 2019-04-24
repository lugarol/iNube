package es.upm.dit.isst.inube.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.inube.dao.ComercianteDAO;
import es.upm.dit.isst.inube.dao.ComercianteDAOImplementation;
import es.upm.dit.isst.inube.dao.ComercioDAO;
import es.upm.dit.isst.inube.dao.ComercioDAOImplementation;
import es.upm.dit.isst.inube.model.Comerciante;
import es.upm.dit.isst.inube.model.Comercio;

@WebServlet("/SeleccionarComercioEstadComparServlet")
public class SeleccionarComercioEstadComparServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" SeleccionarComercioEstadComparServlet > doGet ");
		System.out.println(" ------------------------------------ ");
		
		// obtener mi usuario como comerciante
		Subject currentUser = SecurityUtils.getSubject();
		String currentUserPrincipal = (String) currentUser.getPrincipal();
		ComercianteDAO comercianteDao = ComercianteDAOImplementation.getInstance();
		Comerciante comerciante = comercianteDao.readFromUsuario(currentUserPrincipal);
		int idComerciante = comerciante.getId();
		
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		
		Collection<Comercio> misComercios = comercioDAO.readAllFromComerciante(idComerciante);	
		
		req.getSession().setAttribute("misComercios", misComercios);
		req.getSession().setAttribute("idComerciante", idComerciante);
		
		getServletContext().getRequestDispatcher("/SeleccionarComercioEstadComparView.jsp").forward(req, resp);
		
	}
}

