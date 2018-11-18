package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProductoMNProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOProductoMNProveedorMN;

public class TestProductoMNProveedorMN {
	
	public static void main(String[] args) {
		
		DAOProductoMNProveedorMN daoProductoMNProveedorMN = new DAOProductoMNProveedorMN();
		int rowCount=0;
		
		//setear objeto ProductoMNProveedorMN 
		ProductoMNProveedorMN productoMNproveedorMN= new ProductoMNProveedorMN(null,7,7);
		System.out.println("Insertar productoMNproveedorMN en BD");
		System.out.println("datos productoMNproveedorMN: "+ "idProductoMN: "
						   +productoMNproveedorMN.getIdProductoMN() + " idProveedorMN: "
						   +" "+productoMNproveedorMN.getIdProveedorMN());

		//persistir objeto en BD
		rowCount= daoProductoMNProveedorMN.insertarProductoMNProveedorMN(productoMNproveedorMN);
		if(rowCount > 0)
			System.out.println("Se inserto productoMNproveedorMN en BD. Canitdad de registros afectados: "+rowCount);
		else 
			System.out.println("Cantidad de registros afectados: "+rowCount+". NO se inserto productoMNproveedorMN en BD");
	}

}
