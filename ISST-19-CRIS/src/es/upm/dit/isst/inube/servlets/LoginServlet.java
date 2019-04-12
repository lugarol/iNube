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

@WebServlet({ "/LoginServlet", "/" })
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" LoginServlet > doGet ");
		System.out.println(" ------------------------------------ ");
		
		ClienteDAO clienteDao = ClienteDAOImplementation.getInstance();
		ComercianteDAO comercianteDao = ComercianteDAOImplementation.getInstance();
		ComercioDAO comercioDao = ComercioDAOImplementation.getInstance();
		VentaDAO ventaDao = VentaDAOImplementation.getInstance();
		
		req.getSession().setAttribute("cliente_list", clienteDao.readAll());
		req.getSession().setAttribute("comerciante_list", comercianteDao.readAll());
		req.getSession().setAttribute("comercio_list", comercioDao.readAll());
		req.getSession().setAttribute("venta_list", ventaDao.readAll());
		
		getServletContext().getRequestDispatcher("/LoginView.jsp").forward(req, resp);
		
		/*
		ProfessorDAO pdao = ProfessorDAOImplementation.getInstance();
		req.getSession().setAttribute( "professor_list", pdao.readAll() );
		getServletContext().getRequestDispatcher( "/LoginView.jsp" ).forward( req, resp );
		*/
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" LoginServlet > doPost ");
		System.out.println(" ------------------------------------ ");
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(email, password);
			//token.setRememberMe(true);
			try {
				currentUser.login(token);
				System.out.println(" --- LoginServlet - currentUser.getPrincipal(): " + currentUser.getPrincipal());
				if (currentUser.hasRole("admin")) {
					resp.sendRedirect(req.getContextPath() + "/AdminServlet");
				} else if (currentUser.hasRole("comerciante")) {
					req.getSession().setAttribute("token", token);
					req.getSession().setAttribute("email", email);
					resp.sendRedirect(req.getContextPath() + "/ComercianteServlet");
				} else {
					resp.sendRedirect(req.getContextPath() + "/LoginServlet");
				}
			} catch(Exception e) {
				resp.sendRedirect(req.getContextPath() + "/LoginServlet");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/LoginServlet");
		}
		
		/*
		String email = req.getParameter( "email" );
		String pass = req.getParameter( "password" );
		Subject currentUser = SecurityUtils.getSubject();
		if ( !currentUser.isAuthenticated() ) {
			UsernamePasswordToken token = new UsernamePasswordToken( email, pass );
			try {
				currentUser.login( token );
				if ( currentUser.hasRole( "admin" ) )
					resp.sendRedirect( req.getContextPath() + "/AdminServlet" );
				else if ( currentUser.hasRole( "professor" ) )
					resp.sendRedirect( req.getContextPath() + "/ProfessorServlet?email=" + currentUser.getPrincipal() );
				else
					resp.sendRedirect( req.getContextPath() + "/TFGServlet?email=" + currentUser.getPrincipal() );
			} catch ( Exception e ) {
				resp.sendRedirect( req.getContextPath() + "/LoginServlet" );
			}
		} else {
			resp.sendRedirect( req.getContextPath() + "/LoginServlet" );
		}
		*/
	}
}
