package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.controlador;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.Fachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.IFachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ClientePresupuesto;
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
		VOProductoMN voProductoMN= new VOProductoMN(null,"tapizado",3,"silla tapizada","USD",(double)300,"IVA INC.",null,0);
				
		//setear valores traidos del formulario
		voProductoMN.setIdProductoMN(null);//este valor es auto-generado en la BD
		voProductoMN.setTrabajoRealizado(dataProductoMN.getTrabajoRealizado());
		voProductoMN.setCantidad(dataProductoMN.getCantidad());
		voProductoMN.setNombre(dataProductoMN.getNombre());
		//voProductoMN.setMoneda(dataProductoMN.getMoneda());
				
		voProductoMN.setProveedoresMN(dataProductoMN.getProveedoresMN());
		
		//obtener el idPresupuesto que es el indice de lo seleccionado en el select
		//y usarlo para traer desde la BD un objeto ClientePresupuesto
		//y de este objeto tomar el idClientePresupuesto y setearlo en el voProductoMN
		String idPresupuesto= (String) execution.getVariable("cotizacion");
		ClientePresupuesto clientePresupuesto= fachada.selectClientePresupuesto(Integer.parseInt(idPresupuesto));
		
		voProductoMN.setIdClientePresupuesto(clientePresupuesto.getIdClientePresupuesto());
		
		//int cantidadRegistrosInsertados=fachada.insertarProductoMN(voProductoMN);	
		
		int rowCountProductoMN=0;
		//presistir objeto en BD (insertar ProductoMN que tiene una lista de ProveedorMN)
		rowCountProductoMN= fachada.insertarProductoMN(voProductoMN);
	}

}