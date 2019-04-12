package es.upm.dit.isst.inube.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
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

@WebServlet({ "/LoginServlet", "/" })
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" LoginServlet > doGet ");
		System.out.println(" ------------------------------------ ");
		
		/*
		ClienteDAO clienteDao = ClienteDAOImplementation.getInstance();
		ComercianteDAO comercianteDao = ComercianteDAOImplementation.getInstance();
		ComercioDAO comercioDao = ComercioDAOImplementation.getInstance();
		VentaDAO ventaDao = VentaDAOImplementation.getInstance();
		
		req.getSession().setAttribute("cliente_list", clienteDao.readAll());
		req.getSession().setAttribute("comerciante_list", comercianteDao.readAll());
		req.getSession().setAttribute("comercio_list", comercioDao.readAll());
		req.getSession().setAttribute("venta_list", ventaDao.readAll());
		*/
		
		// redirigir a LoginView.jsp
		getServletContext().getRequestDispatcher("/LoginView.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" LoginServlet > doPost ");
		System.out.println(" ------------------------------------ ");
		
		// recoger parámetros formulario
		String usuario = req.getParameter("usuario");
		String password = req.getParameter("password");
		
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(usuario, password);
			//token.setRememberMe(true);
			try {
				currentUser.login(token);
				if (currentUser.hasRole("admin")) {												// si el usuario es admin, redirigir a AdminServlet
					resp.sendRedirect(req.getContextPath() + "/AdminServlet");
				} else if (currentUser.hasRole("comerciante")) {								// si el usuario es comerciante, redirigir a MenuComercianteServlet
					// pasar parámetros token y usuario
					/* revisar quitar
					req.getSession().setAttribute("token", token);
					req.getSession().setAttribute("usuario", usuario);
					*/
					resp.sendRedirect(req.getContextPath() + "/MenuComercianteServlet");
				} else {																		// redirigir a LoginServlet
					resp.sendRedirect(req.getContextPath() + "/LoginServlet");
				}
			} catch(Exception e) {																// en caso de error, redirigir a LoginServlet
				resp.sendRedirect(req.getContextPath() + "/LoginServlet");
			}
		} else {																				// en caso de error, redirigir a LoginServlet
			resp.sendRedirect(req.getContextPath() + "/LoginServlet");
		}
	
	}

}
