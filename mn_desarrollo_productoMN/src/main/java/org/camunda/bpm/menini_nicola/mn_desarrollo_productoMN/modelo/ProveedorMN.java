package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo;

public class ProveedorMN {

	protected Integer idProveedorMN;
	protected String tipoProveedor;
	protected String moneda;
	protected Double precio;
	protected String ivaProveedor;
	protected String detalles;
	//private Integer idProductoMNProveedorMN;
	
	public ProveedorMN() {
		super();
	}

	public ProveedorMN(Integer idProveedorMN, String tipoProveedor, String moneda, Double precio, String ivaProveedor,
			String detalles) { //, Integer idProductoMNProveedorMN) {
		super();
		this.idProveedorMN = idProveedorMN;
		this.tipoProveedor = tipoProveedor;
		this.moneda = moneda;
		this.precio = precio;
		this.ivaProveedor = ivaProveedor;
		this.detalles = detalles;
		//this.idProductoMNProveedorMN = idProductoMNProveedorMN;
	}

	public Integer getIdProveedorMN() {
		return idProveedorMN;
	}

	public void setIdProveedorMN(Integer idProveedorMN) {
		this.idProveedorMN = idProveedorMN;
	}

	public String getTipoProveedor() {
		return tipoProveedor;
	}

	public void setTipoProveedor(String tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getIvaProveedor() {
		return ivaProveedor;
	}

	public void setIvaProveedor(String ivaProveedor) {
		this.ivaProveedor = ivaProveedor;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	/*public Integer getIdProductoMNProveedorMN() {
		return idProductoMNProveedorMN;
	}

	public void setIdProductoMNProveedorMN(Integer idProductoMNProveedorMN) {
		this.idProductoMNProveedorMN = idProductoMNProveedorMN;
	}*/
	
	public String toString() {
	    return "ProveedorMN [idProveedorMN=" + idProveedorMN + ", tipoProveedor=" + tipoProveedor + ", moneda=" + moneda + ", precio=" + precio + 
	    					 ", ivaProveedor=" + ivaProveedor + ", detalles=" + detalles + "]";
	}
				
}