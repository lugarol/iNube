package es.upm.dit.isst.inube.dao;

import java.util.Collection;
import java.util.Date;

import es.upm.dit.isst.inube.model.*;

public interface VentaDAO {
	
	public void create(Venta venta);
	public Venta read(int id);
	public void update(Venta venta);
	public void delete(Venta venta);
	public Collection<Venta> readAll();
	public Collection<Venta> readAllFromCliente(int clienteId);
	public Collection<Venta> readAllFromClienteForComercio(int clienteId, String merchantId);
	public Collection<Venta> readAllForComercio(String merchantId);
	public Collection<Venta> readAllExceptForComercio(String merchantId);
	public Collection<Venta> readAllButMine(String merchantId, String sector, int cp);
	public Collection<Venta> readAllBetweenDates(Date from, Date to);

}