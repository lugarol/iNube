package es.upm.dit.isst.inube.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.dit.isst.inube.dao.ComercioDAO;
import es.upm.dit.isst.inube.dao.ComercioDAOImplementation;
import es.upm.dit.isst.inube.model.Comerciante;
import es.upm.dit.isst.inube.model.Comercio;

class TestComercioDAOImplementation {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreate() {
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		int numAnterior = comercioDAO.readAll().size();

		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("asdf");
		comerciante.setPassword("qwer");
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePrueba");
		comercio.setSector("SectorPrueba");
		comercio.setCp(28457);
		comercio.setBanco("BancoPrueba");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);

		int numPosterior = comercioDAO.readAll().size();

		assertEquals(1, numPosterior - numAnterior);
		comercioDAO.delete(comercio);
	}

	@Test
	void testRead() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testReadAll() {
		fail("Not yet implemented");
	}

	@Test
	void testReadAllFromComerciante() {
		fail("Not yet implemented");
	}

	@Test
	void testReadAllButMe() {
		fail("Not yet implemented");
	}

}
