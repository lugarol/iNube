package es.upm.dit.isst.inube.dao;

import java.util.Collection;

import es.upm.dit.isst.inube.model.*;

public interface ClienteDAO {
	
	public void create(Cliente cliente);
	public Cliente read(int id);
	public void update(Cliente cliente);
	public void delete(Cliente cliente);
	public Collection<Cliente> readAll();

}