package es.upm.dit.isst.inube.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Venta implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	
	private Date fecha;
	private double importe;
	
	@ManyToOne
	private Comercio comercio;
	
	@ManyToOne
	private Cliente persona;
	
	// constructor
	public Venta() {
	}

	// getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Comercio getComercio() {
		return comercio;
	}

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}

	public Cliente getPersona() {
		return persona;
	}

	public void setPersona(Cliente persona) {
		this.persona = persona;
	}
	
}
