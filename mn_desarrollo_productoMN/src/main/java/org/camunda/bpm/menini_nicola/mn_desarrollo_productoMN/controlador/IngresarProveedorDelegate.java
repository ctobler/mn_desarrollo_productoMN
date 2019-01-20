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
		//valores extaridos de dataProductoMN: total, proveedoresMN y trabajoRealizado 
		ProductoMN dataProductoMN = new ProductoMN();
		dataProductoMN= (ProductoMN)execution.getVariable("dataProductoMN");
		
		//extraer el resto de los valores del formulario
		String monedaPresupuesto= (String)execution.getVariable("MONEDA_PRESUPUESTO");
		String tipoCliente= (String)execution.getVariable("TIPO_CLIENTE");
		String tipoIva= "";
		if(tipoCliente.equals("CONSUMIDOR FINAL"))
			tipoIva="IVA INC.";
		else if (tipoCliente.equals("CORPORATIVO"))
			tipoIva="S/IVA";
		String nombreProducto=(String)execution.getVariable("NOMBRE_PRODUCTO");
		Integer cantidad=(Integer)execution.getVariable("UNIDADES_PRESUPUESTO"); 
		
		//traer cotizacion del dolar
		IFachada fachada= Fachada.getInstanciaSingleton();
		String cotizacionDolar= fachada.cotizacionDolar();
	  	
		//setear fecha de envío a produccion con fecha del dia
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date fechaProduccion = new java.sql.Date(utilDate.getTime());
						
		//leer de pantalla y llevar a VOProductoMN para persistirlo en BD
		VOProductoMN voProductoMN= new VOProductoMN(null,"",0,"","",(double)0,"",null,
				(double)0,null,null,null);
				
		//setear valores traidos desde el formulario
		voProductoMN.setIdProductoMN(null);//este valor es auto-generado en la BD
		voProductoMN.setTrabajoRealizado(dataProductoMN.getTrabajoRealizado());
		voProductoMN.setCantidad(cantidad);
		voProductoMN.setNombre(nombreProducto);
		voProductoMN.setMoneda(monedaPresupuesto);
		voProductoMN.setTotal((double)dataProductoMN.getTotal());
		voProductoMN.setIvaProducto(tipoIva);
//		voProductoMN.setSenia(Double.parseDouble(pago_moneda));
		voProductoMN.setFechaProduccion((java.sql.Date)fechaProduccion);
		voProductoMN.setProveedoresMN(dataProductoMN.getProveedoresMN());
		
		//obtener el idPresupuesto ingresado en: seleccionarPresupuesto-form.html
		//(es el indice de lo seleccionado en el combo de presupuestos)
		//y de este objeto tomar el idClientePresupuesto para setearlo en el VOProductoMN
		String idPresupuesto= (String) execution.getVariable("cotizacion");
		ClientePresupuesto clientePresupuesto= fachada.selectClientePresupuesto(Integer.parseInt(idPresupuesto));	
		voProductoMN.setIdClientePresupuesto(clientePresupuesto.getIdClientePresupuesto());
		
		//Insertar productoMN. Esto trae el idProductoMN auto-generado en BD en el voProductoMN.idProductoMN,
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
		
		//actualizar estado del presupuesto de: en proceso(2) -> en desarrollo(3)
		Integer estado=3; //HACER ESTO CON UNA CLASE DE ENUMERADOS !!!
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
	    //execution.setVariable("NOMBRE_CLIENTE", cliente.getNombre());
	    
	    //crear variable de proceso para almacenar idProducto auto-generado
	    execution.setVariable("ID_PRODUCTO_MN", voProductoMN.getIdProductoMN()); 
	    
	    //crear variable de proceso para almacenar el precio del presupuesto
	    //execution.setVariable("COSTO_PRESUPUESTO", presupuesto.getCosto());
	    
	    //crear variable de proceso para almacenar la moneda del presupuesto
	    //execution.setVariable("MONEDA_PRESUPUESTO", presupuesto.getMoneda());
	    
	    
	}

}