package es.upm.dit.isst.inube.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Comerciante implements Serializable {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String usuario;
	private String password;
	
	@OneToMany(mappedBy="comerciante", fetch=FetchType.EAGER)
	private Collection<Comercio> comercios;
	
	// constructor
	public Comerciante() {
	}

	// getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Comercio> getComercios() {
		return comercios;
	}

	public void setComercios(Collection<Comercio> comercios) {
		this.comercios = comercios;
	}
	
	public String toString() {
		return usuario;
	}
	
}
