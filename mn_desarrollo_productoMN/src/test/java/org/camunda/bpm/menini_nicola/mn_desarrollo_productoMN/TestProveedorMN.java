package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOProveedorMN;

public class TestProveedorMN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DAOProveedorMN daoProveedorMN = new DAOProveedorMN();
		int rowCount=0;
		
		//setear objeto ProveedorMN con datos reales 
		ProveedorMN proveedorMN= new ProveedorMN(null,"Tela","USD",(double)56,"IVA INC.","(Tela Dakota Bemaor)*");//,null);
		System.out.println("Insertar proveedor en BD");
		System.out.println("datos proveedor: "+proveedorMN.getTipoProveedor()+" "+proveedorMN.getMoneda()+" "+proveedorMN.getPrecio()+" "
											  +proveedorMN.getIvaProveedor()+" "+proveedorMN.getDetalles());

		//persistir objeto en BD
		rowCount= daoProveedorMN.insertarProveedorMN(proveedorMN);
		if(rowCount > 0)
			System.out.println("Se inserto proveedor en BD. Canitdad de registros afectados: "+rowCount);
		else 
			System.out.println("Cantidad de registros afectados: "+rowCount+". NO se inserto proveedor en BD");

	}

}
