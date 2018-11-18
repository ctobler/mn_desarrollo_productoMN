package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.controlador;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.Fachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.IFachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProductoMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProductoMN;

public class IngresarProveedorDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		//extraer valores desde 'ingresarProveedor-from.html' y mapear a objeto Java 
		ProductoMN dataProductoMN = new ProductoMN();
		dataProductoMN= (ProductoMN)execution.getVariable("dataProductoMN");
		
		//transformar objeto leido de pantalla a VO y persistirlo en BD
		IFachada fachada= Fachada.getInstanciaSingleton();
		VOProductoMN voProductoMN= new VOProductoMN();
		
		voProductoMN.setIdProductoMN(null);
		voProductoMN.setTrabajoRealizado(dataProductoMN.getTrabajoRealizado());
		voProductoMN.setCantidad(dataProductoMN.getCantidad());
		voProductoMN.setNombre(dataProductoMN.getNombre());
		voProductoMN.setMoneda(dataProductoMN.getMoneda());
		voProductoMN.setTotal(dataProductoMN.getTotal());
		voProductoMN.setIvaProducto(dataProductoMN.getIvaProducto());
		voProductoMN.setProveedoresMN(dataProductoMN.getProveedoresMN());
		//ACTUALMENTE ES NULL PERO ESTE VALOR DEBE SER TOMADO POR PANTALLA
		voProductoMN.setIdClientePresupuesto(null);
		
		fachada.insertarProductoMN(voProductoMN);	
		
	}

}
