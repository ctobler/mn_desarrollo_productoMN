package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOProducto;

public class TestProducto {
	
	public static void main(String[] args) {
		
		DAOProducto daoProducto= new DAOProducto();
		
		int idPresupuesto=2;
		String nombreProducto= daoProducto.selectNombreProducto(idPresupuesto);
		System.out.println("idPresupuesto: "+idPresupuesto+" "+nombreProducto);
	}

}
