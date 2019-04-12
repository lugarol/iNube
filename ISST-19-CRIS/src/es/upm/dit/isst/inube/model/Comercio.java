package es.upm.dit.isst.inube.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Comercio implements Serializable {
	
	@Id
	private String merchantId;
	
	private String nombreComercio;
	private String sector;
	private int cp;
	private String banco;
	
	@ManyToOne
	private Comerciante comerciante;
	
	@OneToMany(mappedBy="comercio", fetch=FetchType.EAGER)
	private Collection<Venta> ventas;
	
	// constructor
	public Comercio() {
	}

	// getters y setters
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getNombreComercio() {
		return nombreComercio;
	}

	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Comerciante getComerciante() {
		return comerciante;
	}

	public void setComerciante(Comerciante comerciante) {
		this.comerciante = comerciante;
	}

	public Collection<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Collection<Venta> ventas) {
		this.ventas = ventas;
	}	

}
