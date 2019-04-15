package es.upm.dit.isst.inube.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.inube.dao.ComercianteDAO;
import es.upm.dit.isst.inube.dao.ComercianteDAOImplementation;
import es.upm.dit.isst.inube.dao.ComercioDAO;
import es.upm.dit.isst.inube.dao.ComercioDAOImplementation;
import es.upm.dit.isst.inube.model.Comerciante;
import es.upm.dit.isst.inube.model.Comercio;

@WebServlet("/CreateComercioServlet")
public class CreateComercioServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// recoger datos formulario
		String merchantId = req.getParameter("merchantId");
		String nombreComercio = req.getParameter("nombreComercio");
		String sector = req.getParameter("sector");
		int cp = Integer.parseInt(req.getParameter("cp"));
		String banco = req.getParameter("banco");
		int idComerciante = Integer.parseInt(req.getParameter("idComerciante"));

		// obtener comerciante
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		Comerciante comerciante = comercianteDAO.read(idComerciante);
		
		// crear objeto comercio y actualizar valores
		Comercio comercio = new Comercio();
		comercio.setMerchantId(merchantId);
		comercio.setNombreComercio(nombreComercio);
		comercio.setSector(sector);
		comercio.setCp(cp);
		comercio.setBanco(banco);
		comercio.setComerciante(comerciante);
		
		// crear comercio en bbdd
		ComercioDAO comercioDao = ComercioDAOImplementation.getInstance();
		comercioDao.create(comercio);

		// redirigir a GestionarComercianteServlet
		resp.sendRedirect(req.getContextPath() + "/GestionarComercianteServlet");
		
	}
	
}

