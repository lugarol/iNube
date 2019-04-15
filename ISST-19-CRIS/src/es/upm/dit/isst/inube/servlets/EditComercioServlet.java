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

@WebServlet("/EditComercioServlet")
public class EditComercioServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" EditComercioServlet > doGet ");
		System.out.println(" ------------------------------------ ");
		
		// recoger parámetros
		String merchantId = req.getParameter("merchantId");
		
		// obtener comercio
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		Comercio comercio = comercioDAO.read(merchantId);
		
		req.getSession().setAttribute("comercio", comercio);
		
		getServletContext().getRequestDispatcher("/EditComercioView.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" EditComercioServlet > doPost ");
		System.out.println(" ------------------------------------ ");
		
		// recoger parámetros
		String merchantId = req.getParameter("merchantId");
		System.out.println("????????????" + merchantId);
		String nombreComercio = req.getParameter("nombreComercio");
		String sector = req.getParameter("sector");
		int cp = Integer.parseInt(req.getParameter("cp"));
		String banco = req.getParameter("banco");
		int idComerciante = Integer.parseInt(req.getParameter("idComerciante"));
		
		// obtener comerciante
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		Comerciante comerciante = comercianteDAO.read(idComerciante);
		
		// crear comercio y actualizar los valores
		Comercio comercio = new Comercio();
		comercio.setMerchantId(merchantId);
		comercio.setNombreComercio(nombreComercio);
		comercio.setSector(sector);
		comercio.setCp(cp);
		comercio.setBanco(banco);
		comercio.setComerciante(comerciante);
		
		// actualizar comercio en bbdd
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		comercioDAO.update(comercio);
		
		// redirigir a GestionarComercianteServlet
		resp.sendRedirect(req.getContextPath() + "/GestionarComercianteServlet");
		
		
	}
}

