package es.upm.dit.isst.inube.dao;

import java.util.Collection;

import es.upm.dit.isst.inube.model.*;

public interface VentaDAO {
	
	public void create(Venta venta);
	public Venta read(int id);
	public void update(Venta venta);
	public void delete(Venta venta);
	public Collection<Venta> readAll();
	public Collection<Venta> readAllFromClienteForComercio(int clienteId, String merchantId);
	//public Collection<Venta> readAllFromCliente(int clienteId);
	public Collection<Venta> readAllForComercio(String merchantId);

}