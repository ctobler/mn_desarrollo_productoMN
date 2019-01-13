package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.controlador;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.Fachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.IFachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Presupuesto;

public class CancelarPresupuestoDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		IFachada fachada= Fachada.getInstanciaSingleton();
		
		//obtener el idPresupuesto ingresado en: seleccionarPresupuesto-form.html
		//(es el indice de lo seleccionado en el combo de presupuestos)
		String idPresupuesto= (String) execution.getVariable("cotizacion");
		//traer datos del presupuesto desde la BD
		Presupuesto presupuesto= new Presupuesto();
		presupuesto= fachada.selectPresupuestoPorId(Integer.parseInt(idPresupuesto));
		
		//actualizar estado del presupuesto de: en proceso(2) -> aprobado(1)
		Integer estado=1; //HACER ESTO CON UNA CLASE DE ENUMERADOS !!!
	    fachada.updateEstadoPresupuesto(Integer.parseInt(idPresupuesto), estado);
	}

}
