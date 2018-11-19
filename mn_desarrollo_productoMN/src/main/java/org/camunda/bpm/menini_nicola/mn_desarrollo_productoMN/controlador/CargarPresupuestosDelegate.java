package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.Fachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.IFachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Presupuesto;

public class CargarPresupuestosDelegate implements JavaDelegate{
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		//levantar lista de presupuestos aprobados para productosMN desde BD
		IFachada fachada= Fachada.getInstanciaSingleton();
		List<Presupuesto> presupuestosAprobados= new ArrayList<Presupuesto>();
		presupuestosAprobados= fachada.selectPresupuestosAprobados();
		
		//convertir lista de presupuestos a HashMap 
	    //(el formulario de SDK trabaja con HashMap)
		Map<Integer, String> presupuestosAprobadosMap= new HashMap<Integer, String>();
		Integer i=0;
		while(i < presupuestosAprobados.size())
		{
			presupuestosAprobadosMap.put(i, presupuestosAprobados.get(i).getCotizacion());
			i++;
		}
		
		//serializar datos de HashMap a json
	    ObjectValue presupuestosDataValue = Variables.objectValue(presupuestosAprobadosMap)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                .create();
	    
	    //bindear HashMap serializado (a JSON) sobre el control 'select' del formulario ingresarProveedor-form
	    execution.setVariable("PRESUPUESTOS_APROBADOS", presupuestosDataValue);
	  	   
		
	}

}
