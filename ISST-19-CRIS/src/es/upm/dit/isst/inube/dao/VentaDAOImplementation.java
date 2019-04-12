package es.upm.dit.isst.inube.dao;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.upm.dit.isst.inube.model.Comercio;
import es.upm.dit.isst.inube.model.Venta;

public class VentaDAOImplementation implements VentaDAO {
	
	private static VentaDAOImplementation instance = null;
	private VentaDAOImplementation() {
	}
	
	public static VentaDAOImplementation getInstance() {
		if (instance == null) {
			instance = new VentaDAOImplementation();
		}
		return instance;
	}

	@Override
	public void create(Venta venta) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(venta);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public Venta read(int id) {
		Session session = SessionFactoryService.get().openSession();
		Venta venta = null;
		try {
			session.beginTransaction();
			venta = session.get(Venta.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return venta;
	}

	@Override
	public void update(Venta venta) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(venta);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Venta venta) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(venta);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public Collection<Venta> readAll() {
		Session session = SessionFactoryService.get().openSession();
		Collection<Venta> ventas = null;
		try {
			session.beginTransaction();
			ventas = session.createQuery("from Venta").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return ventas;
	}
	
	@Override
	public Collection<Venta> readAllFromClienteForComercio(int clienteId, String merchantId) {
		Session session = SessionFactoryService.get().openSession();
		Collection<Venta> ventas = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Venta where persona.id = :id and comercio.merchantId = :merchantId");
			query.setParameter("id", clienteId);
			query.setParameter("merchantId", merchantId);
			ventas = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		if (ventas == null) {
			System.out.println(" --- VentaDAOImplementation - ventas es null");
		} else {
			System.out.println(" --- VentaDAOImplementation - ventas.size(): " + ventas.size());
		}
		return ventas;
	}

	@Override
	public Collection<Venta> readAllForComercio(String merchantId) {
		Session session = SessionFactoryService.get().openSession();
		Collection<Venta> ventas = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Venta where comercio_merchantId = :merchantId");
			query.setParameter("merchantId", merchantId);
			ventas = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		if (ventas == null) {
			System.out.println(" --- VentaDAOImplementation - ventas es null");
		} else {
			System.out.println(" --- VentaDAOImplementation - ventas.size(): " + ventas.size());
		}
		return ventas;
	}

}



