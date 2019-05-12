package es.upm.dit.isst.inube.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

class TestVentaDAOImplementation {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreate() {
		VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePrueba");
		comercio.setSector("SectorPrueba");
		comercio.setCp(28457);
		comercio.setBanco("BancoPrueba");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);
		
		Cliente persona = new Cliente();
		persona.setId(12345678);
		persona.setCp(28457);
		persona.setEdad(55);
		persona.setSexo(0);
		clienteDAO.create(persona);
		
		Venta venta = new Venta();
		venta.setFecha(new Date());
		venta.setImporte(45.18);
		venta.setComercio(comercio);
		venta.setPersona(persona);
		ventaDAO.create(venta);
		int idVenta = venta.getId();
		
		Venta ventaLeida = ventaDAO.read(idVenta);
		
		assertNotNull(ventaLeida);
		
		ventaDAO.delete(venta);
		comercioDAO.delete(comercio);
		comercianteDAO.delete(comerciante);
		clienteDAO.delete(persona);
	}

	@Test
	void testRead() {
		VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePrueba");
		comercio.setSector("SectorPrueba");
		comercio.setCp(28457);
		comercio.setBanco("BancoPrueba");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);
		
		Cliente persona = new Cliente();
		persona.setId(12345678);
		persona.setCp(28457);
		persona.setEdad(55);
		persona.setSexo(0);
		clienteDAO.create(persona);
		
		Venta venta = new Venta();
		Date fecha = new Date();
		venta.setFecha(fecha);
		venta.setImporte(45.18);
		venta.setComercio(comercio);
		venta.setPersona(persona);
		ventaDAO.create(venta);
		int idVenta = venta.getId();
		
		Venta ventaLeida = ventaDAO.read(idVenta);
		
		assertEquals(fecha, ventaLeida.getFecha());
		assertEquals(45.18, ventaLeida.getImporte());
		assertEquals(comercio.getNombreComercio(), ventaLeida.getComercio().getNombreComercio());
		assertEquals(persona.getCp(), ventaLeida.getPersona().getCp());
		
		ventaDAO.delete(venta);
		comercioDAO.delete(comercio);
		comercianteDAO.delete(comerciante);
		clienteDAO.delete(persona);
	}

	@Test
	void testUpdate() {
		VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePrueba");
		comercio.setSector("SectorPrueba");
		comercio.setCp(28457);
		comercio.setBanco("BancoPrueba");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);
		
		Cliente persona = new Cliente();
		persona.setId(12345678);
		persona.setCp(28457);
		persona.setEdad(55);
		persona.setSexo(0);
		clienteDAO.create(persona);
		
		Venta venta = new Venta();
		Date fecha = new Date();
		venta.setFecha(fecha);
		venta.setImporte(45.18);
		venta.setComercio(comercio);
		venta.setPersona(persona);
		ventaDAO.create(venta);
		int idVenta = venta.getId();
		
		Comerciante comerciante_mod = new Comerciante();
		comerciante_mod.setUsuario("pruebau_mod");
		comerciante_mod.setPassword("pruebac_mod");
		comercianteDAO.create(comerciante_mod);
		
		Comercio comercio_mod = new Comercio();
		comercio_mod.setMerchantId("01010101");
		comercio_mod.setNombreComercio("NombrePrueba_mod");
		comercio_mod.setSector("SectorPrueba_mod");
		comercio_mod.setCp(85746);
		comercio_mod.setBanco("BancoPrueba_mod");
		comercio_mod.setComerciante(comerciante_mod);
		comercioDAO.create(comercio_mod);
		
		Cliente persona_mod = new Cliente();
		persona_mod.setId(87654321);
		persona_mod.setCp(85746);
		persona_mod.setEdad(66);
		persona_mod.setSexo(1);
		clienteDAO.create(persona_mod);
		
		Date fecha_mod = new Date();
		venta.setFecha(fecha_mod);
		venta.setImporte(54.81);
		venta.setComercio(comercio_mod);
		venta.setPersona(persona_mod);
		ventaDAO.update(venta);
		
		Venta ventaActualizada = ventaDAO.read(idVenta);
		
		assertEquals(fecha_mod, ventaActualizada.getFecha());
		assertEquals(54.81, ventaActualizada.getImporte());
		assertEquals(comercio_mod.getNombreComercio(), ventaActualizada.getComercio().getNombreComercio());
		assertEquals(comercio_mod.getSector(), ventaActualizada.getComercio().getSector());
		assertEquals(persona_mod.getCp(), ventaActualizada.getPersona().getCp());
		assertEquals(persona_mod.getEdad(), ventaActualizada.getPersona().getEdad());
		
		ventaDAO.delete(venta);
		comercioDAO.delete(comercio);
		comercioDAO.delete(comercio_mod);
		comercianteDAO.delete(comerciante);
		comercianteDAO.delete(comerciante_mod);
		clienteDAO.delete(persona);
		clienteDAO.delete(persona_mod);
		
	}

	@Test
	void testDelete() {
		VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePrueba");
		comercio.setSector("SectorPrueba");
		comercio.setCp(28457);
		comercio.setBanco("BancoPrueba");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);
		
		Cliente persona = new Cliente();
		persona.setId(12345678);
		persona.setCp(28457);
		persona.setEdad(55);
		persona.setSexo(0);
		clienteDAO.create(persona);
		
		Venta venta = new Venta();
		venta.setFecha(new Date());
		venta.setImporte(45.18);
		venta.setComercio(comercio);
		venta.setPersona(persona);
		ventaDAO.create(venta);
		int idVenta = venta.getId();
		
		Venta ventaLeida = ventaDAO.read(idVenta);
		
		assertNotNull(ventaLeida);
		
		ventaDAO.delete(venta);
		
		Venta ventaBorrada = ventaDAO.read(idVenta);
		
		assertNull(ventaBorrada);
		
		comercioDAO.delete(comercio);
		comercianteDAO.delete(comerciante);
		clienteDAO.delete(persona);
	}

	// pendiente
	@Test
	void testReadAll() {
		VentaDAO ventaDAO = VentaDAOImplementation.getInstance();
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		
		int numAnterior = ventaDAO.readAll().size();
		
		Comerciante comerciante = new Comerciante();
		comerciante.setUsuario("pruebau");
		comerciante.setPassword("pruebac");
		comercianteDAO.create(comerciante);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId("10101010");
		comercio.setNombreComercio("NombrePrueba");
		comercio.setSector("SectorPrueba");
		comercio.setCp(28457);
		comercio.setBanco("BancoPrueba");
		comercio.setComerciante(comerciante);
		comercioDAO.create(comercio);
		
		Cliente persona = new Cliente();
		persona.setId(12345678);
		persona.setCp(28457);
		persona.setEdad(55);
		persona.setSexo(0);
		clienteDAO.create(persona);
		
		Venta venta = new Venta();
		venta.setFecha(new Date());
		venta.setImporte(45.18);
		venta.setComercio(comercio);
		venta.setPersona(persona);
		ventaDAO.create(venta);
		
		int numPosterior = ventaDAO.readAll().size();
		
		assertEquals(1, numPosterior - numAnterior);
		
		ventaDAO.delete(venta);
		comercioDAO.delete(comercio);
		comercianteDAO.delete(comerciante);
		clienteDAO.delete(persona);
	}

}
