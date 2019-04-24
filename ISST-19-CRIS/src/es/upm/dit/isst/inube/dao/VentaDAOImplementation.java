package es.upm.dit.isst.inube.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
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
	public Collection<Venta> readAllFromCliente(int clienteId) {
		Session session = SessionFactoryService.get().openSession();
		Collection<Venta> ventas = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Venta where persona.id = :clienteId");
			query.setParameter("clienteId", clienteId);
			ventas = query.list();
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
			Query query = session.createQuery("from Venta where persona.id = :clienteId and comercio.merchantId = :merchantId");
			query.setParameter("clienteId", clienteId);
			query.setParameter("merchantId", merchantId);
			ventas = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
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
		return ventas;
	}
	
	@Override
	public Collection<Venta> readAllExceptForComercio(String merchantId) {
		Session session = SessionFactoryService.get().openSession();
		Collection<Venta> ventas = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Venta where comercio_merchantId != :merchantId");
			query.setParameter("merchantId", merchantId);
			ventas = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
			System.out.println(" --- VentaDAOImplementation - ¡Error!");
		} finally {
			session.close();
		}		
		return ventas;
	}
	
	@Override
	public Collection<Venta> readAllButMine(String merchantId, String sector, int cp) {
		Session session = SessionFactoryService.get().openSession();
		Collection<Venta> ventas = null;
		try {
			session.beginTransaction();
			//Query query = session.createQuery("from Venta where comercio_merchantId != :merchantId and cp");
			Query query = session.createQuery("from Venta as venta WHERE comercio.merchantId != :merchantId and comercio.cp = :cp and comercio.sector = :sector");
			query.setParameter("merchantId", merchantId);
			query.setParameter("cp", cp);
			query.setParameter("sector", sector);
			ventas = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return ventas;
	}
	
	@Override
	public Collection<Venta> readAllBetweenDates(Date from, Date to) {
		Session session = SessionFactoryService.get().openSession();
		Collection<Venta> ventas = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Venta where fecha >= :from and fecha <= :to");
			query.setParameter("from", from);
			query.setParameter("to", to);
			ventas = query.list();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return ventas;
	}

}



