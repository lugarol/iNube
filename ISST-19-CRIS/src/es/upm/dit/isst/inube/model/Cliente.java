package es.upm.dit.isst.inube.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Cliente implements Serializable {

	@Id
	private int id;
	
	private int cp;
	private int sexo; // 0 es hombre, 1 es mujer
	private int edad;
	
	@OneToMany(mappedBy="persona", fetch=FetchType.EAGER)
	private Collection<Venta> participacionEnVentas;
	
	// constructor
	public Cliente() {
	}

	// getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Collection<Venta> getParticipacionEnVentas() {
		return participacionEnVentas;
	}

	public void setParticipacionEnVentas(Collection<Venta> participacionEnVentas) {
		this.participacionEnVentas = participacionEnVentas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
