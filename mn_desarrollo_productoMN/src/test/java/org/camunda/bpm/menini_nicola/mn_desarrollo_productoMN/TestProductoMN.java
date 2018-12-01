package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN;

import java.util.ArrayList;
import java.util.List;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProductoMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOProductoMN;

public class TestProductoMN {
	
	public static void main(String[] args) {
	
		DAOProductoMN daoProductoMN = new DAOProductoMN();
		int rowCountProveedoresMN=0;
		int rowCountProductoMN=0;
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date fechaProduccion = new java.sql.Date(utilDate.getTime());
		java.sql.Date fechaEntrega = new java.sql.Date(utilDate.getTime());
				
		//crear una lista de ProveedorMN 
		List<ProveedorMN> proveedoresMN= new ArrayList<ProveedorMN>(); 
		ProveedorMN proveedorMN= new ProveedorMN(null,"Tapiceria","USD",(double)120,"IVA INC.","(Miguel)");//,null);
		ProveedorMN proveedorMN2= new ProveedorMN(null,"Carpinteria","USD",(double)14,"S/IVA","(Valsani)");//,null);
		proveedoresMN.add(proveedorMN);
		proveedoresMN.add(proveedorMN2);
		ProductoMN productoMN= new ProductoMN(null,"fabricacion",2,"silla BE","USD",(double)500,"IVA INC.",proveedoresMN,
				(double)300,(java.sql.Date)fechaProduccion,(java.sql.Date)fechaEntrega,null);
	
		System.out.println();
		
		//Pruebo insertar un ProductoMN que internamente tiene una lista de ProveedorMN
		System.out.println("--Pruebo insertar ProductoMN--");
		rowCountProductoMN= daoProductoMN.insertarProductoMN(productoMN);
		if(rowCountProductoMN > 0)
		{
			System.out.println("Se inserto productoMN en BD. Canitdad de registros afectados: "+rowCountProductoMN);
			//mostrar el idProductoMN que se genero en la BD al hacer el insert
			System.out.println("El idProductoMN auto-generado en la BD es: "+productoMN.getIdProductoMN());
		}
		else 
			System.out.println("Cantidad de registros afectados: "+rowCountProductoMN+". NO se inserto productoMN en BD");
		
		rowCountProveedoresMN= productoMN.getProveedoresMN().size();
		
		System.out.println("Cantidad de proveedores del productoMN: "+ rowCountProveedoresMN);
		System.out.println("Muestro los idProveedor");
		for(ProveedorMN p: productoMN.getProveedoresMN())
		{
			System.out.println("El idProveedorMN auto-generado en la BD es: "+p.getIdProveedorMN());
		}	
	}	
	
}
