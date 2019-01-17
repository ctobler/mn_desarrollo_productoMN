package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.controlador;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.Fachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.IFachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Cliente;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ClientePresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Presupuesto;

public class CargarInfoPresupuestoClienteDelegate implements JavaDelegate {

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
	
		//actualizar estado del presupuesto de: aprobado(1) -> en proceso(2)
		Integer estado=2; //HACER ESTO CON UNA CLASE DE ENUMERADOS !!!
	  	fachada.updateEstadoPresupuesto(Integer.parseInt(idPresupuesto), estado);
	  	
	  	//traer nombre del producto desde la BD correspondiente al idPresupuesto
	  	String nombreProducto= fachada.selectNombreProducto(Integer.parseInt(idPresupuesto));
	  	
	  	//traer cotizacion del dolar
	  	String cotizacionDolar= fachada.cotizacionDolar();
	  	
	    //traer datos del cliente desde la BD
	    ClientePresupuesto clientePresupuesto= fachada.selectClientePresupuesto(Integer.parseInt(idPresupuesto));	
	    Cliente cliente= new Cliente();
	    cliente= fachada.selectCliente(clientePresupuesto.getIdCliente());
	 
	    String tipoCliente= "";
	    tipoCliente= cliente.getTipo();
	    
	    String nombreCliente="";
	    nombreCliente= cliente.getNombre();
	    
	    String direccionCliente="";
	    direccionCliente= cliente.getDireccion();
	    Integer unidadesPresupuesto= 0;
	    unidadesPresupuesto= presupuesto.getUnidades();
	    
	    Double costoPresupuesto= (double) 0;
	    costoPresupuesto=presupuesto.getCosto();
	    
	    //crear variables de proceso y almacenar valores para luego mostrar en formulario
	    execution.setVariable("MONEDA_PRESUPUESTO", presupuesto.getMoneda());
	    execution.setVariable("TIPO_CLIENTE", tipoCliente);
	    execution.setVariable("NOMBRE_CLIENTE", nombreCliente);
	    execution.setVariable("DIRECCION_ENTREGA", direccionCliente);
	    execution.setVariable("UNIDADES_PRESUPUESTO", unidadesPresupuesto);
	    execution.setVariable("COSTO_PRESUPUESTO", costoPresupuesto);
	    execution.setVariable("NOMBRE_PRODUCTO", nombreProducto);
	    execution.setVariable("COTIZACION_DOLAR", cotizacionDolar);
	}

}
