package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOClientePresupuesto;

public class TestClientePresupuesto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DAOClientePresupuesto daoClienteProducto= new DAOClientePresupuesto();
		int registrosAfectados= 0;
		
		int idPresupuesto= 1;
		int estado=2;
		System.out.println("--pruebo actualizar el estado de un ClientePresupuesto--");
		System.out.println("idPresupuesto: "+idPresupuesto+" - estado: "+estado);
		registrosAfectados= daoClienteProducto.updateEstadoPresupuesto(idPresupuesto,estado);
		
		System.out.println("cantidad de registros afectados: "+registrosAfectados);
		

	}

}
