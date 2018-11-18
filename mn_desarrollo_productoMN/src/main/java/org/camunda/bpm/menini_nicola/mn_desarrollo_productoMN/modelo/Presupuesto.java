package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo;

import java.sql.Date;

public class Presupuesto {
	
	Integer idPresupuesto;
	String cotizacion;
	Date fecha;
	String moneda;
	Double costo;
	String condicionesVenta;
	String descripcion;
	
	public Presupuesto() {
		super();
	}
	
	public Presupuesto(Integer idPresupuesto, String cotizacion, Date fecha, String moneda, Double costo,
			String condicionesVenta, String descripcion) {
		super();
		this.idPresupuesto = idPresupuesto;
		this.cotizacion = cotizacion;
		this.fecha = fecha;
		this.moneda = moneda;
		this.costo = costo;
		this.condicionesVenta = condicionesVenta;
		this.descripcion = descripcion;
	}
	
	public Integer getIdPresupuesto() {
		return idPresupuesto;
	}
	public void setIdPresupuesto(Integer idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}
	public String getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public String getCondicionesVenta() {
		return condicionesVenta;
	}
	public void setCondicionesVenta(String condicionesVenta) {
		this.condicionesVenta = condicionesVenta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
