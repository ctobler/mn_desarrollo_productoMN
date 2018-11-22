package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo;

public class ClientePresupuesto {
	
	protected Integer idClientePresupuesto;
	protected Integer estado;
	protected Integer idCliente;
	protected Integer idPresupuesto;
	
	public ClientePresupuesto() {
		super();
	}

	public ClientePresupuesto(Integer idClientePresupuesto, Integer estado, Integer idCliente, Integer idPresupuesto) {
		super();
		this.idClientePresupuesto = idClientePresupuesto;
		this.estado = estado;
		this.idCliente = idCliente;
		this.idPresupuesto = idPresupuesto;
	}

	public Integer getIdClientePresupuesto() {
		return idClientePresupuesto;
	}

	public void setIdClientePresupuesto(Integer idClientePresupuesto) {
		this.idClientePresupuesto = idClientePresupuesto;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdPresupuesto() {
		return idPresupuesto;
	}

	public void setIdPresupuesto(Integer idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}
	
	

}
