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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// recoger datos formulario
		String usuario = req.getParameter("usuario");
		String password = req.getParameter("password");
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario(usuario);
		comerciante.setPassword(new Sha256Hash(password).toString());
		
		ComercianteDAO comercianteDao = ComercianteDAOImplementation.getInstance();
		comercianteDao.create(comerciante);
		
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
		
		/*
		String name = req.getParameter( "name" );
		String password = req.getParameter( "password" );
		String email = req.getParameter( "email" );
		Professor professor = new Professor();
		professor.setName( name );
		professor.setEmail( email );
		
		professor.setPassword( new Sha256Hash( password ).toString() );
		
		ProfessorDAO pdao = ProfessorDAOImplementation.getInstance();
		pdao.create( professor );
		
		resp.sendRedirect( req.getContextPath() + "/AdminServlet" );
		*/
	}
}
