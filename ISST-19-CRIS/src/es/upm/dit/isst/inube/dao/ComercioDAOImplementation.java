package es.upm.dit.isst.inube.dao;

import java.util.Collection;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.upm.dit.isst.inube.model.Comerciante;
import es.upm.dit.isst.inube.model.Comercio;
import es.upm.dit.isst.inube.model.Venta;

public class ComercioDAOImplementation implements ComercioDAO {
	
	private static ComercioDAOImplementation instance = null;
	private ComercioDAOImplementation() {
	}
	
	public static ComercioDAOImplementation getInstance() {
		if (instance == null) {
			instance = new ComercioDAOImplementation();
		}
		return instance;
	}
	
	@Override
	public void create(Comercio comercio) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(comercio);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public Comercio read(String merchantId) {
		Session session = SessionFactoryService.get().openSession();
		Comercio comercio = null;
		try {
			session.beginTransaction();
			comercio = session.get(Comercio.class, merchantId);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return comercio;
	}

	@Override
	public void update(Comercio comercio) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(comercio);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Comercio comercio) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(comercio);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public Collection<Comercio> readAll() {
		Session session = SessionFactoryService.get().openSession();
		Collection<Comercio> comercios = null;
		try {
			session.beginTransaction();
			comercios = session.createQuery("from Comercio").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return comercios;
	}

	@Override
	public Collection<Comercio> readAllFromComerciante(int comercianteId) {
		Session session = SessionFactoryService.get().openSession();
		Collection<Comercio> comercios = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Comercio where comerciante.id = :id");
			query.setParameter("id", comercianteId);
			comercios = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		if (comercios == null) {
			System.out.println(" --- ComercioDAOImplementation - comercios es null");
		} else {
			System.out.println(" --- ComercioDAOImplementation - comercios.size(): " + comercios.size());
		}
		return comercios;
	}
	
	/*@Override
	public Collection<Comerciante> readAllFromUsuario(int id) {
		Session session = SessionFactoryService.get().openSession();
		Collection<Comerciante> comerciantes = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Comerciante where id = :id");
			query.setParameter("id", id);
			comerciantes = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return comerciantes;
	}*/

}


