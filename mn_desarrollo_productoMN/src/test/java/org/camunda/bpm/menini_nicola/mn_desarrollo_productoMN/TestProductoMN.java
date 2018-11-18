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
		
		//insertar lista de ProveedorMN 
		List<ProveedorMN> proveedoresMN= new ArrayList<ProveedorMN>(); 
		ProveedorMN proveedorMN= new ProveedorMN(null,"Tapiceria","USD",(double)120,"IVA INC.","(Miguel)");//,null);
		ProveedorMN proveedorMN2= new ProveedorMN(null,"Carpinteria","USD",(double)14,"S/IVA","(Valsani)");//,null);
		proveedoresMN.add(proveedorMN);
		proveedoresMN.add(proveedorMN2);
		 
		ProductoMN productoMN= new ProductoMN(null,"fabricacion",2,"silla BE","USD",(double)500,"IVA INC.",proveedoresMN,null);//,null);
		System.out.println("Insertar lista de proveedores en BD");
		
		rowCountProveedoresMN= daoProductoMN.insertarProveedoresProductoMN(proveedoresMN);
		if(rowCountProveedoresMN > 0)
			System.out.println("Se inserto lista de proveedoresMN en BD. Canitdad de registros afectados: "+rowCountProveedoresMN);
		else 
			System.out.println("Cantidad de registros afectados: "+rowCountProveedoresMN+". NO se inserto lista de proveedores en BD");
	
		//insertar ProductoMN que tiene una lista de ProveedorMN
		rowCountProductoMN= daoProductoMN.insertarProductoMN(productoMN);
		if(rowCountProductoMN > 0)
			System.out.println("Se inserto productoMN en BD. Canitdad de registros afectados: "+rowCountProductoMN);
		else 
			System.out.println("Cantidad de registros afectados: "+rowCountProductoMN+". NO se inserto productoMN en BD");
	
	}	
	

}
