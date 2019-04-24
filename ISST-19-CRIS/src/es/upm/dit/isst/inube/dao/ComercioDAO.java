package es.upm.dit.isst.inube.dao;

import java.util.Collection;

import es.upm.dit.isst.inube.model.*;

public interface ComercioDAO {
	
	public void create(Comercio comercio);
	public Comercio read(String merchantId);
	public void update(Comercio comercio);
	public void delete(Comercio comercio);
	public Collection<Comercio> readAll();
	public Collection<Comercio> readAllFromComerciante(int comercianteId);
	public Collection<Comercio> readAllButMe(String merchantId, String sector, int cp);

}