package es.upm.dit.isst.inube.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.dit.isst.inube.dao.ClienteDAO;
import es.upm.dit.isst.inube.dao.ClienteDAOImplementation;
import es.upm.dit.isst.inube.model.Cliente;

class TestClienteDAOImplementation {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreate() {
		ClienteDAO cDAO = ClienteDAOImplementation.getInstance();
		int numAnterior = cDAO.readAll().size();

		Cliente cliente = new Cliente();
		cliente.setId(12345678);
		cliente.setCp(28457);
		cliente.setEdad(55);
		cliente.setSexo(0);
		cDAO.create(cliente);

		Cliente cliente2 = new Cliente();
		cliente2.setId(87654321);
		cliente2.setCp(28012);
		cliente2.setEdad(32);
		cliente2.setSexo(1);
		cDAO.create(cliente2);

		int numPosterior = cDAO.readAll().size();

		assertEquals(2, numPosterior - numAnterior);
		cDAO.delete(cliente);
		cDAO.delete(cliente2);
	}

	@Test
	void testRead() {
		ClienteDAO cDAO = ClienteDAOImplementation.getInstance();

		Cliente cliente = new Cliente();
		cliente.setId(12345678);
		cliente.setCp(28457);
		cliente.setEdad(55);
		cliente.setSexo(0);
		cDAO.create(cliente);

		Cliente clienteLeido = cDAO.read(12345678);

		assertEquals(28457, clienteLeido.getCp());
		assertEquals(55, clienteLeido.getEdad());
		assertEquals(0, clienteLeido.getSexo());

		cDAO.delete(cliente);
	}

	@Test
	void testUpdate() {
		ClienteDAO cDAO = ClienteDAOImplementation.getInstance();

		Cliente cliente = new Cliente();
		cliente.setId(12345678);
		cliente.setCp(28457);
		cliente.setEdad(55);
		cliente.setSexo(0);
		cDAO.create(cliente);
		
		cliente.setCp(28000);
		cliente.setEdad(14);
		cliente.setSexo(1);
		cDAO.update(cliente);

		Cliente clienteActualizado = cDAO.read(12345678);

		assertEquals(28000, clienteActualizado.getCp());
		assertEquals(14, clienteActualizado.getEdad());
		assertEquals(1, clienteActualizado.getSexo());

		cDAO.delete(cliente);
	}

	@Test
	void testDelete() {
		ClienteDAO cDAO = ClienteDAOImplementation.getInstance();
		int numAnterior = cDAO.readAll().size();

		Cliente cliente = new Cliente();
		cliente.setId(12345678);
		cliente.setCp(28457);
		cliente.setEdad(55);
		cliente.setSexo(0);
		cDAO.create(cliente);

		Cliente cliente2 = new Cliente();
		cliente2.setId(87654321);
		cliente2.setCp(28012);
		cliente2.setEdad(32);
		cliente2.setSexo(1);
		cDAO.create(cliente2);

		cDAO.delete(cliente);
		
		int numPosterior = cDAO.readAll().size();
		
		assertEquals(1, numPosterior - numAnterior);
		
		cDAO.delete(cliente2);
	}

	@Test
	void testReadAll() {
		fail("Not yet implemented");
	}

}
