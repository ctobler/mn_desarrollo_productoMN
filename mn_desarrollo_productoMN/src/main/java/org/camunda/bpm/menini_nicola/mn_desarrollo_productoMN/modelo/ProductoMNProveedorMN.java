package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo;

public class ProductoMNProveedorMN {
	
	Integer idProductoMNProveedorMN;
	Integer idProductoMN;
	Integer idProveedorMN;
	
	public ProductoMNProveedorMN() {
		super();
	}

	public ProductoMNProveedorMN(Integer idProductoMNProveedorMN, Integer idProductoMN, Integer idProveedorMN) {
		super();
		this.idProductoMNProveedorMN = idProductoMNProveedorMN;
		this.idProductoMN = idProductoMN;
		this.idProveedorMN = idProveedorMN;
	}

	public Integer getIdProductoMNProveedorMN() {
		return idProductoMNProveedorMN;
	}

	public void setIdProductoMNProveedorMN(Integer idProductoMNProveedorMN) {
		this.idProductoMNProveedorMN = idProductoMNProveedorMN;
	}

	public Integer getIdProductoMN() {
		return idProductoMN;
	}

	public void setIdProductoMN(Integer idProductoMN) {
		this.idProductoMN = idProductoMN;
	}

	public Integer getIdProveedorMN() {
		return idProveedorMN;
	}

	public void setIdProveedorMN(Integer idProveedorMN) {
		this.idProveedorMN = idProveedorMN;
	}

}
