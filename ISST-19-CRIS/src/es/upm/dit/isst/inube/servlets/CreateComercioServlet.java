package es.upm.dit.isst.inube.servlets;

import java.io.IOException;
import java.util.Collection;

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
import es.upm.dit.isst.inube.model.Comercio;

@WebServlet("/CreateComercioServlet")
public class CreateComercioServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// recoger datos formulario
		String merchantId = req.getParameter("merchantid");
		String nombreComercio = req.getParameter("name");
		String sector = req.getParameter("sector");
		int cp = Integer.parseInt(req.getParameter("cp"));
		String banco = req.getParameter("banco");
		
		
		int comercianteId = Integer.parseInt(req.getParameter("comerciante_id"));
		
		System.out.println(" --- CreateComercioServlet - parámetro comercianteId: " + req.getParameter("comerciante_id"));
		System.out.println(" --- CreateComercioServlet - parámetro comercianteId parsed: " + comercianteId);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId(merchantId);
		comercio.setNombreComercio(nombreComercio);
		comercio.setSector(sector);
		comercio.setCp(cp);
		comercio.setBanco(banco);
		
		ComercianteDAO comercianteDao = ComercianteDAOImplementation.getInstance();
		Comerciante comerciante = comercianteDao.read(comercianteId);
		System.out.println(" --- CreateComercioServlet - comerciante.toString(): " + comerciante.toString());
		System.out.println(" --- CreateComercioServlet - comerciante.getId(): " + comerciante.getId());
		comercio.setComerciante(comerciante);
		
		ComercioDAO comercioDao = ComercioDAOImplementation.getInstance();
		comercioDao.create(comercio);

		System.out.println(" --- CreateComercianteServlet - comercios.size(): " + comercioDao.readAll().size());
		
		resp.sendRedirect(req.getContextPath() + "/ComercianteServlet");
		
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

