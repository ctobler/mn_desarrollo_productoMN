package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo;

public class Pago {

	protected String moneda;
	protected Double senia;
	
	public Pago(String moneda, Double senia) {
		super();
		this.moneda = moneda;
		this.senia = senia;
	}

	public Pago() {
		super();
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Double getSenia() {
		return senia;
	}

	public void setSenia(Double senia) {
		this.senia = senia;
	}
	
}
