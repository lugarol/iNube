package es.upm.dit.isst.inube.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.dit.isst.inube.dao.ClienteDAO;
import es.upm.dit.isst.inube.dao.ClienteDAOImplementation;
import es.upm.dit.isst.inube.dao.ComercianteDAO;
import es.upm.dit.isst.inube.dao.ComercianteDAOImplementation;
import es.upm.dit.isst.inube.dao.ComercioDAO;
import es.upm.dit.isst.inube.dao.ComercioDAOImplementation;
import es.upm.dit.isst.inube.model.Cliente;
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
		comerciante.setUsuario("testCreateUser");
		comerciante.setPassword("testCreatePswd");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePruebaTestCreate");
		comercio.setSector("SectorPruebaTestCreate");
		comercio.setCp(28457);
		comercio.setBanco("BancoPruebaTestCreate");
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
		comerciante.setUsuario("testReadUser");
		comerciante.setPassword("testReadPswd");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePruebaTestRead");
		comercio.setSector("SectorPruebaTestRead");
		comercio.setCp(28457);
		comercio.setBanco("BancoPruebaTestRead");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);

		Comercio comercioLeido = comercioDAO.read("10101010");
	
		assertEquals("NombrePruebaTestRead", comercioLeido.getNombreComercio());
		assertEquals("SectorPruebaTestRead", comercioLeido.getSector());
		assertEquals(28457, comercioLeido.getCp());
		assertEquals("BancoPruebaTestRead", comercioLeido.getBanco());
		assertEquals("testReadUser", comercioLeido.getComerciante().getUsuario());
		assertEquals("testReadPswd", comercioLeido.getComerciante().getPassword());
		
		comercioDAO.delete(comercio);
		comercianteDAO.delete(comerciante);
	}

	@Test
	void testUpdate() {
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("testUpdateUser");
		comerciante.setPassword("testUpdatePswd");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePruebaTestUpdate");
		comercio.setSector("SectorPruebaTestUpdate");
		comercio.setCp(28457);
		comercio.setBanco("BancoPruebaTestUpdate");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);
		
		Comerciante comerciante_mod = new Comerciante();
		comerciante_mod.setUsuario("testUpdateUser_mod");
		comerciante_mod.setPassword("testUpdatePswd_mod");
		comercianteDAO.create(comerciante_mod);
		
		comercio.setNombreComercio("NombrePruebaTestUpdate_mod");
		comercio.setSector("SectorPruebaTestUpdate_mod");
		comercio.setCp(85746);
		comercio.setBanco("BancoPruebaTestUpdate_mod");
		comercio.setComerciante(comerciante_mod);
		
		comercioDAO.update(comercio);

		Comercio comercioActualizado = comercioDAO.read("10101010");
	
		assertEquals("NombrePruebaTestUpdate_mod", comercioActualizado.getNombreComercio());
		assertEquals("SectorPruebaTestUpdate_mod", comercioActualizado.getSector());
		assertEquals(85746, comercioActualizado.getCp());
		assertEquals("BancoPruebaTestUpdate_mod", comercioActualizado.getBanco());
		assertEquals("testUpdateUser_mod", comercioActualizado.getComerciante().getUsuario());
		assertEquals("testUpdatePswd_mod", comercioActualizado.getComerciante().getPassword());
		
		comercioDAO.delete(comercio);
		comercianteDAO.delete(comerciante);
		comercianteDAO.delete(comerciante_mod);
	}

	@Test
	void testDelete() {
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("testDeleteUser");
		comerciante.setPassword("testDeletePswd");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePruebaTestDelete");
		comercio.setSector("SectorPruebaTestDelete");
		comercio.setCp(28457);
		comercio.setBanco("BancoPruebaTestDelete");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);
		
		Comercio comercioLeido = comercioDAO.read("10101010");
		
		assertNotNull(comercioLeido);
		
		comercioDAO.delete(comercioLeido);
		
		Comercio comercioBorrado = comercioDAO.read("10101010");
		
		assertNull(comercioBorrado);
		
		comercianteDAO.delete(comerciante);
	}

	
	@Test
	void testReadAll() {
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		
		int numAnterior = comercioDAO.readAll().size();

		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("testReadAllUser");
		comerciante.setPassword("testReadAllPswd");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePruebaTestReadAllPswd");
		comercio.setSector("SectorPruebaTestReadAllPswd");
		comercio.setCp(28457);
		comercio.setBanco("BancoPruebaTestReadAllPswd");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);

		int numPosterior = comercioDAO.readAll().size();
		
		assertEquals(1, numPosterior - numAnterior);
		
		comercioDAO.delete(comercio);
		comercianteDAO.delete(comerciante);
	}

}
