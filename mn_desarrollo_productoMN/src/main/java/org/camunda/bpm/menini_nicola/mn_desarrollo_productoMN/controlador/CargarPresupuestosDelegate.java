package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.controlador;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.Fachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.IFachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Presupuesto;

public class CargarPresupuestosDelegate implements JavaDelegate{
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		//cargar presupuestos aprobados
		IFachada fachada= Fachada.getInstanciaSingleton();
		List<Presupuesto> presupuestosAprobados= new ArrayList<Presupuesto>();
		presupuestosAprobados= fachada.selectPresupuestosAprobados();
		
		//serializar lista de presupuestos a Json
		
	}

}
