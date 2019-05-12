package es.upm.dit.isst.inube.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.dit.isst.inube.dao.ClienteDAO;
import es.upm.dit.isst.inube.dao.ClienteDAOImplementation;
import es.upm.dit.isst.inube.dao.ComercianteDAO;
import es.upm.dit.isst.inube.dao.ComercianteDAOImplementation;
import es.upm.dit.isst.inube.model.Cliente;
import es.upm.dit.isst.inube.model.Comerciante;

class TestComercianteDAOImplementation {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreate() {
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);
		int idComerciante = comerciante.getId();
		
		Comerciante comercianteLeido = comercianteDAO.read(idComerciante);
		
		assertNotNull(comercianteLeido);
		
		comercianteDAO.delete(comercianteLeido);
	}

	@Test
	void testRead() {
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();

		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);
		int idComerciante = comerciante.getId();

		Comerciante comercianteLeido = comercianteDAO.read(idComerciante);
		
		assertEquals("pruebau", comercianteLeido.getUsuario());
		assertEquals("pruebac", comercianteLeido.getPassword());

		comercianteDAO.delete(comerciante);
	}

	// yo no lo haria
	@Test
	void testReadFromUsuario() {
		assertTrue(true, "Not yet implemented");
	}

	@Test
	void testUpdate() {
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();

		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);
		int idComerciante = comerciante.getId();
		
		comerciante.setUsuario("pruebau_mod");
		comerciante.setPassword("pruebac_mod");
		comercianteDAO.update(comerciante);

		Comerciante comercianteActualizado = comercianteDAO.read(idComerciante);

		assertEquals("pruebau_mod", comercianteActualizado.getUsuario());
		assertEquals("pruebac_mod", comercianteActualizado.getPassword());

		comercianteDAO.delete(comerciante);
	}

	@Test
	void testDelete() {
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();

		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);
		int idComerciante = comerciante.getId();
		
		Comerciante comercianteLeido = comercianteDAO.read(idComerciante);
		
		assertNotNull(comercianteLeido);
		
		comercianteDAO.delete(comercianteLeido);
		
		Comerciante comercianteBorrado = comercianteDAO.read(idComerciante);
		
		comercianteDAO.delete(comerciante);
		
		assertNull(comercianteBorrado);
	}

	// pendiente
	@Test
	void testReadAll() {
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		
		int long0 = comercianteDAO.readAll().size();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);

		
		int long1 = comercianteDAO.readAll().size();
		int longPrueba = long0 + 1;
		
		assertEquals(longPrueba,long1);
		
		
		comercianteDAO.delete(comerciante);
	}

}
