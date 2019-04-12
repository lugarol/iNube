package es.upm.dit.isst.inube.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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
import es.upm.dit.isst.inube.model.Cliente;
import es.upm.dit.isst.inube.model.Comerciante;
import es.upm.dit.isst.inube.model.Comercio;
import es.upm.dit.isst.inube.model.Venta;

@WebServlet("/CreateVentaServlet")
public class CreateVentaServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// recoger datos formulario
		
		String diaHora = req.getParameter("date");
		System.out.println(" --- CreateVentaServlet - diaHora: " + diaHora);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		
		Date fecha = null;
		try {
			//Date fecha = DateFormat.getInstance().parse(diaHora);
			fecha = formatter.parse(diaHora);
			System.out.println(fecha);
		} catch (Exception e) {
			System.out.println(" --- CreateVentaServlet - ERROR PARSEANDO FECHA");
		}
		
		double importe = Double.parseDouble(req.getParameter("price"));
		String comercioMerchantId = req.getParameter("comercio");
		int compradorId = Integer.parseInt(req.getParameter("comprador"));
		
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		Comercio comercio = comercioDAO.read(comercioMerchantId);
		
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		Cliente cliente = clienteDAO.read(compradorId);
		
		Venta venta = new Venta();
		venta.setImporte(importe);
		venta.setComercio(comercio);
		venta.setPersona(cliente);
		venta.setFecha(fecha);
		
		VentaDAO ventaDao = VentaDAOImplementation.getInstance();
		ventaDao.create(venta);
	
		
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
		
		/*
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
		*/
		
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

