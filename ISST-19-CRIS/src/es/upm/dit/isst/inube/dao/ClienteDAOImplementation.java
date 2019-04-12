package es.upm.dit.isst.inube.dao;

import java.util.Collection;

import org.hibernate.Session;

import es.upm.dit.isst.inube.model.Cliente;

public class ClienteDAOImplementation implements ClienteDAO {
	
	private static ClienteDAOImplementation instance = null;
	private ClienteDAOImplementation() {
	}
	
	public static ClienteDAOImplementation getInstance() {
		if (instance == null) {
			instance = new ClienteDAOImplementation();
		}
		return instance;
	}

	@Override
	public void create(Cliente cliente) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(cliente);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public Cliente read(int id) {
		Session session = SessionFactoryService.get().openSession();
		Cliente cliente = null;
		try {
			session.beginTransaction();
			cliente = session.get(Cliente.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return cliente;
	}

	@Override
	public void update(Cliente cliente) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(cliente);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Cliente cliente) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(cliente);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public Collection<Cliente> readAll() {
		Session session = SessionFactoryService.get().openSession();
		Collection<Cliente> clientes = null;
		try {
			session.beginTransaction();
			clientes = session.createQuery("from Cliente").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return clientes;
	}

}
