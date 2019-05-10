package es.upm.dit.isst.inube.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import es.upm.dit.isst.inube.dao.ComercianteDAO;
import es.upm.dit.isst.inube.dao.ComercianteDAOImplementation;
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
		int numAnterior = comercianteDAO.readAll().size();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setId(9999);
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);
		
		int numPosterior = comercianteDAO.readAll().size();

		assertEquals(1, numPosterior - numAnterior);
		comercianteDAO.delete(comerciante);
	}

	// da error, como Comerciante tiene @GeneratedValue en id al crear un objeto le pone el id que quiere y no se sabe cuál es para leerlo
	@Test
	void testRead() {
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();

		Comerciante comerciante = new Comerciante();
		comerciante.setId(9999);
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);

		Comerciante comercianteLeido = comercianteDAO.read(9999);

		System.out.println(comercianteLeido.getUsuario());
		System.out.println(comercianteLeido.getPassword());
		
		assertEquals("pruebau", comercianteLeido.getUsuario());
		assertEquals("pruebac", comercianteLeido.getPassword());

		comercianteDAO.delete(comerciante);
	}

	// yo no lo haria
	@Test
	void testReadFromUsuario() {
		fail("Not yet implemented");
	}

	// da error, como Comerciante tiene @GeneratedValue en id al crear un objeto le pone el id que quiere y no se sabe cuál es para leerlo
	@Test
	void testUpdate() {
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();

		Comerciante comerciante = new Comerciante();
		comerciante.setId(9999);
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);
		
		comerciante.setUsuario("prueba2u");
		comerciante.setPassword("prueba2c");
		comercianteDAO.update(comerciante);

		Comerciante comercianteActualizado = comercianteDAO.read(9999);

		assertEquals("prueba2u", comercianteActualizado.getUsuario());
		assertEquals("prueba2c", comercianteActualizado.getPassword());

		comercianteDAO.delete(comerciante);
	}

	// da error, como Comerciante tiene @GeneratedValue en id al crear un objeto le pone el id que quiere y no se sabe cuál es para leerlo
	@Test
	void testDelete() {
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		int numAnterior = comercianteDAO.readAll().size();

		Comerciante comerciante = new Comerciante();
		comerciante.setId(9999);
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);

		Comerciante comerciante2 = new Comerciante();
		comerciante.setId(99999);
		comerciante.setUsuario("prueba2u");
		comerciante.setPassword("prueba2c");
		comercianteDAO.create(comerciante2);

		comercianteDAO.delete(comerciante);
		
		int numPosterior = comercianteDAO.readAll().size();
		
		assertEquals(1, numPosterior - numAnterior);
		
		comercianteDAO.delete(comerciante2);
	}

	@Test
	void testReadAll() {
		fail("Not yet implemented");
	}

}
