package es.upm.dit.isst.inube.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryService {
	
	private SessionFactory sessionFactory;
	private static SessionFactoryService sfs;
	
	private SessionFactoryService() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static SessionFactory get() {
		if (sfs == null) {
			sfs = new SessionFactoryService();
		}
		return sfs.sessionFactory;
	}

}