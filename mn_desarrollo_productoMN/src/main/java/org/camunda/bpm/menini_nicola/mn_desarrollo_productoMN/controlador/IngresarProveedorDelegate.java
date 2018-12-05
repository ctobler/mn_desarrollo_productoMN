package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.controlador;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.Fachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.IFachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Cliente;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ClientePresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Presupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProductoMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProductoMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProductoMNProveedorMN;

public class IngresarProveedorDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		//extraer valores desde 'ingresarProveedor-from.html' y mapear a objeto Java 
		ProductoMN dataProductoMN = new ProductoMN();
		dataProductoMN= (ProductoMN)execution.getVariable("dataProductoMN");
		
		//setear fechaProduccion con fecha del dia
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date fechaProduccion = new java.sql.Date(utilDate.getTime());
						
		//transformar objeto leido de pantalla a VO y persistirlo en BD
		IFachada fachada= Fachada.getInstanciaSingleton();
		VOProductoMN voProductoMN= new VOProductoMN(null,"",0,"","",(double)0,"",null,
				(double)0,null,null,null);
				
		//setear valores traidos del formulario
		voProductoMN.setIdProductoMN(null);//este valor es auto-generado en la BD
		voProductoMN.setTrabajoRealizado(dataProductoMN.getTrabajoRealizado());
		voProductoMN.setCantidad(dataProductoMN.getCantidad());
		voProductoMN.setNombre(dataProductoMN.getNombre());
		//asumimos que la moneda del productoMN siempre es dolares
		voProductoMN.setMoneda("USD");
		voProductoMN.setTotal((double)dataProductoMN.getTotal());
		voProductoMN.setIvaProducto((String)dataProductoMN.getIvaProducto());
		voProductoMN.setSenia((double)dataProductoMN.getSenia());
		voProductoMN.setFechaProduccion((java.sql.Date)fechaProduccion);
		voProductoMN.setProveedoresMN(dataProductoMN.getProveedoresMN());
		
		//obtener el idPresupuesto que es el indice de lo seleccionado en el select
		//y usarlo para traer desde la BD un objeto ClientePresupuesto
		//y de este objeto tomar el idClientePresupuesto y setearlo en el voProductoMN
		String idPresupuesto= (String) execution.getVariable("cotizacion");
		ClientePresupuesto clientePresupuesto= fachada.selectClientePresupuesto(Integer.parseInt(idPresupuesto));	
		voProductoMN.setIdClientePresupuesto(clientePresupuesto.getIdClientePresupuesto());
		
		//insertar producto. Esto trae el idProductoMN auto-generado en BD en el voProductoMN.idProductoMN
		//y tambien trae los idProveedor auto-generado en la BD en la lista de proveedoresMN
		int rowCount= fachada.insertarProductoMN(voProductoMN);
		//si es insertado el ProductoMN entonces por cada proveedorMN hay que insertar
		//un registro en la tabla relacion ProductoMNProveedorMN
		if(rowCount>0)
		{
			//insertar pares (idProductoMN,idProveedor) en tabla ProductoMN_ProveedorMN
			//segun la cantidad de proveedoresMN
			List<ProveedorMN> proveedoresMN= voProductoMN.getProveedoresMN();
			for(ProveedorMN p: proveedoresMN)
			{
				VOProductoMNProveedorMN voProductoMNproveedorMN= new VOProductoMNProveedorMN();
				
				voProductoMNproveedorMN.setIdProductoMN(voProductoMN.getIdProductoMN());
				voProductoMNproveedorMN.setIdProveedorMN(p.getIdProveedorMN());
				
				fachada.insertarProductoMNProveedorMN(voProductoMNproveedorMN);
			}
		}
		
		//actualizar estado del presupuesto aprobado->desarrollo
		Integer estado=2; //HACER ESTO CON UNA CLASE DE ENUMERADOS !!!
	  	fachada.updateEstadoPresupuesto(Integer.parseInt(idPresupuesto), estado);
		
		//traer datos presupuesto
		Presupuesto presupuesto= new Presupuesto();
		presupuesto= fachada.selectPresupuestoPorId(Integer.parseInt(idPresupuesto));
		
		//mostrar nombre del presupuesto obtenido en pantalla
	    execution.setVariable("ORDEN_COMPRA",presupuesto.getCotizacion());
	    
	    //traer datos del cliente
	    Cliente cliente= new Cliente();
	    cliente= fachada.selectCliente(clientePresupuesto.getIdCliente());
	    
	    //mostrar nombre del cliente obtenido en pantalla
	    execution.setVariable("NOMBRE_CLIENTE", cliente.getNombre());
	    
	    //crear variable de proceso para idProducto creado
	    execution.setVariable("ID_PRODUCTO_MN", voProductoMN.getIdProductoMN()); 
	    
	    //actualizar variable de proceso 
	    
	}

}