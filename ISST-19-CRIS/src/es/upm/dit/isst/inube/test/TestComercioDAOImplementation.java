package es.upm.dit.isst.inube.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.dit.isst.inube.dao.ComercianteDAO;
import es.upm.dit.isst.inube.dao.ComercianteDAOImplementation;
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
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();

		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("asdf");
		comerciante.setPassword("qwer");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePrueba");
		comercio.setSector("SectorPrueba");
		comercio.setCp(28457);
		comercio.setBanco("BancoPrueba");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);

		Comercio comercioLeido = comercioDAO.read("10101010");
		
		assertNotNull(comercioLeido);
		
		comercioDAO.delete(comercio);
		comercianteDAO.delete(comerciante);
	}

	@Test
	void testRead() {
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("asdf");
		comerciante.setPassword("qwer");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePrueba");
		comercio.setSector("SectorPrueba");
		comercio.setCp(28457);
		comercio.setBanco("BancoPrueba");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);

		Comercio comercioLeido = comercioDAO.read("10101010");
	
		assertEquals("NombrePrueba", comercioLeido.getNombreComercio());
		assertEquals("SectorPrueba", comercioLeido.getSector());
		assertEquals(28457, comercioLeido.getCp());
		assertEquals("BancoPrueba", comercioLeido.getBanco());
		assertEquals("asdf", comercioLeido.getComerciante().getUsuario());
		assertEquals("qwer", comercioLeido.getComerciante().getPassword());
		
		comercioDAO.delete(comercio);
		comercianteDAO.delete(comerciante);
	}

	@Test
	void testUpdate() {
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("asdf");
		comerciante.setPassword("qwer");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePrueba");
		comercio.setSector("SectorPrueba");
		comercio.setCp(28457);
		comercio.setBanco("BancoPrueba");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);
		
		Comerciante comerciante_mod = new Comerciante();
		comerciante_mod.setUsuario("jkln");
		comerciante_mod.setPassword("uiop");
		comercianteDAO.create(comerciante_mod);
		
		comercio.setNombreComercio("NombrePrueba_mod");
		comercio.setSector("SectorPrueba_mod");
		comercio.setCp(85746);
		comercio.setBanco("BancoPrueba_mod");
		comercio.setComerciante(comerciante_mod);
		
		comercioDAO.update(comercio);

		Comercio comercioActualizado = comercioDAO.read("10101010");
	
		assertEquals("NombrePrueba_mod", comercioActualizado.getNombreComercio());
		assertEquals("SectorPrueba_mod", comercioActualizado.getSector());
		assertEquals(85746, comercioActualizado.getCp());
		assertEquals("BancoPrueba_mod", comercioActualizado.getBanco());
		assertEquals("jkln", comercioActualizado.getComerciante().getUsuario());
		assertEquals("uiop", comercioActualizado.getComerciante().getPassword());
		
		comercioDAO.delete(comercio);
		comercianteDAO.delete(comerciante);
		comercianteDAO.delete(comerciante_mod);
	}

	@Test
	void testDelete() {
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("asdf");
		comerciante.setPassword("qwer");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePrueba");
		comercio.setSector("SectorPrueba");
		comercio.setCp(28457);
		comercio.setBanco("BancoPrueba");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);
		
		Comercio comercioLeido = comercioDAO.read("10101010");
		
		assertNotNull(comercioLeido);
		
		comercioDAO.delete(comercioLeido);
		
		Comercio comercioBorrado = comercioDAO.read("10101010");
		
		assertNull(comercioBorrado);
	}

	// pendiente
	@Test
	void testReadAll() {
		assertTrue(true, "Not yet implemented");
	}

}
