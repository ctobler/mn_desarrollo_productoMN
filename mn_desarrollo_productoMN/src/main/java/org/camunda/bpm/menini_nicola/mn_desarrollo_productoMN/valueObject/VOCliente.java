package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject;

public class VOCliente {
	Integer idCliente;
	String nombre;
	String email;
	String telefono;
	String celular;
	
	public VOCliente() {
		super();
	}
	
	public VOCliente(Integer idCliente, String nombre, String email, String telefono, String celular) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.celular = celular;
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
}
