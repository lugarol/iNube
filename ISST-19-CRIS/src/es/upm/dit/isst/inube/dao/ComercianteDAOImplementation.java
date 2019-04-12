package es.upm.dit.isst.inube.dao;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;


import org.hibernate.Session;
import org.hibernate.query.Query;

import es.upm.dit.isst.inube.model.Comerciante;

public class ComercianteDAOImplementation implements ComercianteDAO {
	
	private static ComercianteDAOImplementation instance = null;
	private ComercianteDAOImplementation() {
	}
	
	public static ComercianteDAOImplementation getInstance() {
		if (instance == null) {
			instance = new ComercianteDAOImplementation();
		}
		return instance;
	}

	@Override
	public void create(Comerciante comerciante) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(comerciante);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public Comerciante read(int id) {
		Session session = SessionFactoryService.get().openSession();
		Comerciante comerciante = null;
		try {
			session.beginTransaction();
			comerciante = session.get(Comerciante.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return comerciante;
	}
	
	public Comerciante readFromUsuario(String usuario) {
		Session session = SessionFactoryService.get().openSession();
		
		Collection<Comerciante> comerciantes = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Comerciante where usuario = :user");
			query.setParameter("user", usuario);
			comerciantes = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		/* revisar quitar
		if (comerciantes == null) {
			System.out.println(" --- readFromUsuario - COMERCIANTES ES NULL");
		} else {
			System.out.println(" --- readFromUsuario - comerciantes.size(): " + comerciantes.size());
			System.out.println(" --- readFromUsuario - comerciantes.next(): " + comerciantes.iterator().next().toString());
		}
		*/
		return comerciantes.iterator().next();
	}

	@Override
	public void update(Comerciante comerciante) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(comerciante);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Comerciante comerciante) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(comerciante);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public Collection<Comerciante> readAll() {
		Session session = SessionFactoryService.get().openSession();
		Collection<Comerciante> comerciantes = null;
		try {
			session.beginTransaction();
			comerciantes = session.createQuery("from Comerciante").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return comerciantes;
	}


}

