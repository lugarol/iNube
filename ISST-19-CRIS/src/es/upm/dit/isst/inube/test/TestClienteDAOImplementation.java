package es.upm.dit.isst.inube.test;

import static org.junit.jupiter.api.Assertions.*;

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
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		int numAnterior = clienteDAO.readAll().size();

		Cliente cliente = new Cliente();
		cliente.setId(12345678);
		cliente.setCp(28457);
		cliente.setEdad(55);
		cliente.setSexo(0);
		clienteDAO.create(cliente);

		int numPosterior = clienteDAO.readAll().size();

		assertEquals(1, numPosterior - numAnterior);
		clienteDAO.delete(cliente);
	}

	@Test
	void testRead() {
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();

		Cliente cliente = new Cliente();
		cliente.setId(12345678);
		cliente.setCp(28457);
		cliente.setEdad(55);
		cliente.setSexo(0);
		clienteDAO.create(cliente);

		Cliente clienteLeido = clienteDAO.read(12345678);

		assertEquals(28457, clienteLeido.getCp());
		assertEquals(55, clienteLeido.getEdad());
		assertEquals(0, clienteLeido.getSexo());

		clienteDAO.delete(cliente);
	}

	@Test
	void testUpdate() {
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();

		Cliente cliente = new Cliente();
		cliente.setId(12345678);
		cliente.setCp(28457);
		cliente.setEdad(55);
		cliente.setSexo(0);
		clienteDAO.create(cliente);
		
		cliente.setCp(28000);
		cliente.setEdad(14);
		cliente.setSexo(1);
		clienteDAO.update(cliente);

		Cliente clienteActualizado = clienteDAO.read(12345678);

		assertEquals(28000, clienteActualizado.getCp());
		assertEquals(14, clienteActualizado.getEdad());
		assertEquals(1, clienteActualizado.getSexo());

		clienteDAO.delete(cliente);
	}

	@Test
	void testDelete() {
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		int numAnterior = clienteDAO.readAll().size();

		Cliente cliente = new Cliente();
		cliente.setId(12345678);
		cliente.setCp(28457);
		cliente.setEdad(55);
		cliente.setSexo(0);
		clienteDAO.create(cliente);

		Cliente cliente2 = new Cliente();
		cliente2.setId(87654321);
		cliente2.setCp(28012);
		cliente2.setEdad(32);
		cliente2.setSexo(1);
		clienteDAO.create(cliente2);

		clienteDAO.delete(cliente);
		
		int numPosterior = clienteDAO.readAll().size();
		
		assertEquals(1, numPosterior - numAnterior);
		
		clienteDAO.delete(cliente2);
	}

	@Test
	void testReadAll() {
		assertTrue(true, "Not yet implemented");
	}

}
