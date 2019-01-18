package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.controlador;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.Fachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.IFachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProductoMN;

public class ActualizarProductoDelegate implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		 
		IFachada fachada= Fachada.getInstanciaSingleton();
		
		//traer variable de proceso: 'cotizacion' que es el indice de lo 
		//seleccionado en 'ingresarProveedor-form.html' y corresponde
		//al idPresupuesto
		Integer estado=4; //HACER ESTO CON UNA CLASE DE ENUMERADOS !!!		
		//traer variable de proceso cotizacion
		String idPresupuesto= (String) execution.getVariable("cotizacion");
		fachada.updateEstadoPresupuesto(Integer.parseInt(idPresupuesto), estado);
		
		//setear fechaEntrega con fecha del dia
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date fechaEntrega = new java.sql.Date(utilDate.getTime());
		
		//traer variable de proceso idProductoMN
		Integer idProductoMN= (Integer)execution.getVariable("ID_PRODUCTO_MN");
		fachada.updateFechaEntrega(idProductoMN, fechaEntrega);
	}

}
