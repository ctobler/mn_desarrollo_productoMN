package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.Fachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.IFachada;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Cliente;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ClientePresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Presupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOCliente;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOPresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOPresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProductoMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProductoMNProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProveedorMN;

public class TestFachada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IFachada fachada= Fachada.getInstanciaSingleton();
		
		int rowCount=0;
		
		//PRUEBO insertarProveedorMN
		System.lineSeparator();
		System.out.println("--insertarProveedorMN--");
		System.out.println();
		
		
		//setear objeto ProveedorMN con datos reales 
		VOProveedorMN voProveedorMN= new VOProveedorMN(null,"Tela","USD",(double)56,"IVA INC.","(Tela Dakota Bemaor)*");//,null);
		System.out.println("Insertar proveedor en BD");
		System.out.println("datos proveedor: "+voProveedorMN.getTipoProveedor()+" "+voProveedorMN.getMoneda()+" "+voProveedorMN.getPrecio()+" "
											  +voProveedorMN.getIvaProveedor()+" "+voProveedorMN.getDetalles());

		//persistir objeto en BD
		rowCount= fachada.insertarProveedorMN(voProveedorMN);
		if(rowCount > 0)
			System.out.println("Se inserto proveedor en BD. Canitdad de registros afectados: "+rowCount);
		else 
			System.out.println("Cantidad de registros afectados: "+rowCount+". NO se inserto proveedor en BD");

		
		//PRUEBO insertarProductoMN
		System.lineSeparator();
		System.out.println();
		System.out.println("--insertarProductoMN--");
		System.out.println();
		
		
		int rowCountProveedoresMN=0;
		int rowCountProductoMN=0;
		
		//insertar lista de ProveedorMN 
		List<ProveedorMN> proveedoresMN= new ArrayList<ProveedorMN>(); 
		ProveedorMN proveedorMN= new ProveedorMN(null,"Tapiceria","USD",(double)120,"IVA INC.","(Miguel)");//,null);
		ProveedorMN proveedorMN2= new ProveedorMN(null,"Carpinteria","USD",(double)14,"S/IVA","(Valsani)");//,null);
		proveedoresMN.add(proveedorMN);
		proveedoresMN.add(proveedorMN2);
		 
		//VOProductoMN voProductoMN= new VOProductoMN(null,"fabricacion",2,"silla BE","USD",(double)500,"IVA INC.",proveedoresMN,null);//,null);
		VOProductoMN voProductoMN= new VOProductoMN(null,"tapizado",3,"silla tapizada","USD",(double)300,"IVA INC.",proveedoresMN,1);
		//System.out.println("Insertar ProductoMN en BD");
		
		//presistir objeto en BD (insertar ProductoMN que tiene una lista de ProveedorMN)
		rowCountProductoMN= fachada.insertarProductoMN(voProductoMN);
		if(rowCountProductoMN > 0)
		{
			System.out.println("Se inserto productoMN en BD. Canitdad de registros afectados: "+rowCountProductoMN);
			System.out.println("El idProductoMN auto-generado en la BD es: "+voProductoMN.getIdProductoMN());
			System.out.println(voProductoMN.getIdProductoMN());
			System.out.println(voProductoMN.getTrabajoRealizado());
			System.out.println(voProductoMN.getCantidad());
			System.out.println(voProductoMN.getNombre());
			System.out.println(voProductoMN.getMoneda());
			System.out.println(voProductoMN.getTotal());
			System.out.println(voProductoMN.getIvaProducto());
			System.out.println(voProductoMN.getIdClientePresupuesto());
			System.out.println("Mostrar idProveedor de los proveedores del productoMN:");
			for(ProveedorMN p: voProductoMN.getProveedoresMN())
			{
				System.out.println("El idProveedorMN auto-generado en la BD es: "+p.getIdProveedorMN());
			}
			
		}
		else 
			System.out.println("Cantidad de registros afectados: "+rowCountProductoMN+". NO se inserto productoMN en BD");
	
		
		//PRUEBO insertarProductoMNProveedorMN
		System.lineSeparator();
		System.out.println();
		System.out.println("--insertarProductoMNProveedorMN--");
		System.out.println();
		
		int rowCountProductoMNProveedorMN=0;
		
		//setear objeto ProductoMNProveedorMN 
		VOProductoMNProveedorMN voProductoMNproveedorMN= new VOProductoMNProveedorMN(null,7,7);
		System.out.println("Insertar productoMNproveedorMN en BD");
		System.out.println("datos productoMNproveedorMN: "+ "idProductoMN: "
						   +voProductoMNproveedorMN.getIdProductoMN() + " idProveedorMN: "
						   +" "+voProductoMNproveedorMN.getIdProveedorMN());

		//persistir objeto en BD
		rowCountProductoMNProveedorMN= fachada.insertarProductoMNProveedorMN(voProductoMNproveedorMN);
		if(rowCountProductoMNProveedorMN > 0)
			System.out.println("Se inserto productoMNproveedorMN en BD. Canitdad de registros afectados: "+rowCountProductoMNProveedorMN);
		else 
			System.out.println("Cantidad de registros afectados: "+rowCountProductoMNProveedorMN+". NO se inserto productoMNproveedorMN en BD");
		
		
		//PRUEBO selectClientePresupuestoAprobado
		System.lineSeparator();
		System.out.println();
		System.out.println("--selectClientePresupuestoAprobado--");
		System.out.println();
		
		Cliente cliente= new Cliente();
		int idPresupuesto= 1;
		VOPresupuesto voPresupuesto=new VOPresupuesto();
		voPresupuesto.setIdPresupuesto(idPresupuesto);
		
		cliente= fachada.selectClientePresupuestoAprobado(voPresupuesto);
		System.out.println("Datos Cliente con presupuesto aprobado.");
		System.out.println("idPresupuesto: "+ idPresupuesto);
		System.out.println("idCliente: "+ cliente.getIdCliente());
		System.out.println("nombre: "+ cliente.getNombre());
		System.out.println("email: "+ cliente.getEmail());
		System.out.println("telefono: "+ cliente.getTelefono());
		System.out.println("celular: "+ cliente.getCelular());
		
		
		//PRUEBO selectPresupuestosAprobados()
		System.lineSeparator();
		System.out.println("--selectPresupuestosAprobados--");
		System.out.println();
		
		List<Presupuesto> presupuestosAprobados= new ArrayList<Presupuesto>();
		presupuestosAprobados= fachada.selectPresupuestosAprobados();
		
		int cantidadPresupuestosAprobados= presupuestosAprobados.size();
		System.out.println("Cantidad de presupuestos aprobados: "+cantidadPresupuestosAprobados);
		System.out.println("Datos de presupuestos aprobados: ");
		
		for(Presupuesto presupuestoAprobado: presupuestosAprobados)
		{
			System.lineSeparator();
			System.out.println("idPresupuesto: "+presupuestoAprobado.getIdPresupuesto());
			System.out.println("cotizacion: "+presupuestoAprobado.getCotizacion());
			System.out.println("fecha: "+presupuestoAprobado.getFecha());
			System.out.println("moneda: "+presupuestoAprobado.getMoneda());
			System.out.println("costo: "+presupuestoAprobado.getCosto());
			System.out.println("condiciones venta: "+presupuestoAprobado.getCondicionesVenta());
			System.out.println("descripcion: "+presupuestoAprobado.getDescripcion());
		}
		
		//PRUEBO selectPresupuesto
		Presupuesto presupuesto = new Presupuesto();
		String cotizacion= "180918-01";
		
		presupuesto= fachada.selectPresupuesto(cotizacion);
		
		System.lineSeparator();
		System.out.println();
		System.out.println("Traer los datos del presupuesto correspondiente a la cotizacion: "+presupuesto.getCotizacion());
		System.out.println("idPresupuesto: "+presupuesto.getIdPresupuesto());
		System.out.println("cotizacion: "+presupuesto.getCotizacion());
		System.out.println("fecha: "+presupuesto.getFecha());
		System.out.println("moneda: "+presupuesto.getMoneda());
		System.out.println("costo: "+presupuesto.getCosto());
		System.out.println("condiciones venta: "+presupuesto.getCondicionesVenta());
		System.out.println("descripcion: "+presupuesto.getDescripcion());
		
		//PRUEBO: selectClientePresupuesto
		ClientePresupuesto clientePresupuesto= new ClientePresupuesto();
		idPresupuesto= 1;
		
		clientePresupuesto= fachada.selectClientePresupuesto(idPresupuesto);		
		
		System.out.println();
		System.out.println("Traer los datos del ClientePresupuesto con idPresupuesto: "+idPresupuesto);
		System.out.println("idClientePresupuesto: "+ clientePresupuesto.getIdClientePresupuesto());
		System.out.println("estado: "+ clientePresupuesto.getEstado());
		System.out.println("idCliente: "+ clientePresupuesto.getIdCliente());
		System.out.println("idPresupuesto: "+ clientePresupuesto.getIdPresupuesto());
		
	}	
		
}
