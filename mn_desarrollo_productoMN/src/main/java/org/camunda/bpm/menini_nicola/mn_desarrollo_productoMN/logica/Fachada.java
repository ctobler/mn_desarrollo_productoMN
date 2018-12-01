package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Cliente;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ClientePresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Presupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProductoMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProductoMNProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOCliente;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOClientePresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOPresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOProductoMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOProductoMNProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOCliente;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOPresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProductoMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProductoMNProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProveedorMN;

public class Fachada implements IFachada {

    private static IFachada fachada;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private Fachada() {
      
    }

    public static IFachada getInstanciaSingleton() {
        if (fachada == null){
            fachada = new Fachada();
        } 
        return fachada;
    }
   
    //retorna 1 cuando inserta un proveedorMN y setea el idProveedorMN con el valor auto-generado en la BD
    @Override
	public int insertarProveedorMN(VOProveedorMN voProveedorMN)
    {
    	int rowCount=0;
    	ProveedorMN proveedorMN= new ProveedorMN();
    	DAOProveedorMN daoProveedorMN= new DAOProveedorMN();
    	
    	//desenvolver objeto value object
    	proveedorMN.setIdProveedorMN(voProveedorMN.getIdProveedorMN());
    	proveedorMN.setTipoProveedor(voProveedorMN.getTipoProveedor());
    	proveedorMN.setMoneda(voProveedorMN.getMoneda());
    	proveedorMN.setPrecio(voProveedorMN.getPrecio());
    	proveedorMN.setIvaProveedor(voProveedorMN.getIvaProveedor());
    	proveedorMN.setDetalles(voProveedorMN.getDetalles());
    	//proveedorMN.setIdProductoMNProveedorMN(voProveedorMN.getIdProductoMNProveedorMN());
    	
    	//persistir a BD
    	rowCount=daoProveedorMN.insertarProveedorMN(proveedorMN);
    	
    	//obtener valor auto-generado por el insert del idProvedoorMN
     	voProveedorMN.setIdProveedorMN(proveedorMN.getIdProveedorMN());
    	    	    	
    	return rowCount;
    }
    
    //retorna 1 cuando inserta un productoMN en BD y setea el idProductoMN con el valor auto-generado en la BD 
    @Override
	public int insertarProductoMN(VOProductoMN voProductoMN)
    {
    	int rowCount=0;
    	ProductoMN productoMN= new ProductoMN();
    	DAOProductoMN daoProductoMN= new DAOProductoMN();
    	
    	java.util.Date fechaProduccionJava = new java.util.Date();
    	//leer fecha traida de VO
    	fechaProduccionJava= voProductoMN.getFechaProduccion();
    	//convertir fecha formato java a formato sql
    	java.sql.Date fechaProduccion=null;
    	if(fechaProduccionJava != null)
    		fechaProduccion = new java.sql.Date(fechaProduccionJava.getTime());
		
		java.util.Date fechaEntregaJava = new java.util.Date();
		//leer fecha traida de VO
		fechaEntregaJava= voProductoMN.getFechaEntrega();
		//convertir fecha formato java a formato sql
		java.sql.Date fechaEntrega = null;
		if(fechaEntregaJava != null)
			fechaEntrega= new java.sql.Date(fechaEntregaJava.getTime());
		
     	//desenvolver objeto value object
     	productoMN.setIdProductoMN(voProductoMN.getIdProductoMN());
     	productoMN.setTrabajoRealizado(voProductoMN.getTrabajoRealizado());
     	productoMN.setCantidad(voProductoMN.getCantidad());
     	productoMN.setNombre(voProductoMN.getNombre());
     	productoMN.setMoneda(voProductoMN.getMoneda());
     	productoMN.setTotal(voProductoMN.getTotal());
     	productoMN.setIvaProducto(voProductoMN.getIvaProducto());
     	productoMN.setSenia(voProductoMN.getSenia());
     	productoMN.setFechaProduccion(fechaProduccion);
     	productoMN.setFechaEntrega(fechaEntrega);
     	productoMN.setProveedoresMN(voProductoMN.getProveedoresMN());
     	productoMN.setIdClientePresupuesto(voProductoMN.getIdClientePresupuesto());
     	//productoMN.setIdProductoMNProveedorMN(voProductoMN.getIdProductoMNProveedorMN());
    	
     	//persistir a BD
     	rowCount= daoProductoMN.insertarProductoMN(productoMN);
     	
     	//obtener valor auto-generado por el insert del idProductoMN
     	voProductoMN.setIdProductoMN(productoMN.getIdProductoMN());
     	//obtener lista de ProveedorMN con los idProveedor auto-generado por el insert
     	voProductoMN.setProveedoresMN(productoMN.getProveedoresMN());
    	
     	return rowCount;
    }
    
    /* (non-Javadoc)
	 * @see org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica.IFachada#insertarProductoMNProveedorMN(org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProductoMNProveedorMN)
	 */
    @Override
	public int insertarProductoMNProveedorMN(VOProductoMNProveedorMN voProductoMNproveedorMN)
    {
    	int rowCount=0;
    	ProductoMNProveedorMN productoMNProveedorMN= new ProductoMNProveedorMN();
    	DAOProductoMNProveedorMN daoProductoMNProveedorMN= new DAOProductoMNProveedorMN();
    	
    	//desenvolver objeto value object
    	productoMNProveedorMN.setIdProductoMNProveedorMN(voProductoMNproveedorMN.getIdProductoMNProveedorMN());
    	productoMNProveedorMN.setIdProductoMN(voProductoMNproveedorMN.getIdProductoMN());
    	productoMNProveedorMN.setIdProveedorMN(voProductoMNproveedorMN.getIdProveedorMN());
    	
    	//persistir a BD
    	rowCount= daoProductoMNProveedorMN.insertarProductoMNProveedorMN(productoMNProveedorMN);
    	
    	return rowCount;
    }
    
    @Override
    public Cliente selectClientePresupuestoAprobado(VOPresupuesto voPresupuesto)
    {
    	Cliente cliente= new Cliente();
    	DAOCliente daoCliente= new DAOCliente();
    	
    	Integer idPresupuesto= voPresupuesto.getIdPresupuesto();
    	cliente= daoCliente.selectClientePresupuestoAprobado(idPresupuesto);
    	
    	return cliente;
    }
    
    @Override
    public Cliente selectCliente(Integer idCliente)
    {
    	Cliente cliente= new Cliente();
    	DAOCliente daoCliente= new DAOCliente();
    	
    	cliente= daoCliente.selectCliente(idCliente);
    	
    	return cliente;
    }
    
    @Override
    public List<Presupuesto> selectPresupuestosAprobados()
    {
    	List<Presupuesto> presupuestosAprobados= new ArrayList<Presupuesto>();
    	DAOPresupuesto daoPresupuesto = new DAOPresupuesto();
    	
    	presupuestosAprobados= daoPresupuesto.selectPresupuestosAprobados();
    	
    	return presupuestosAprobados;
    }
    
    @Override
    public Presupuesto selectPresupuesto(String cotizacion)
    {
    	Presupuesto presupuesto= new Presupuesto();
    	DAOPresupuesto daoPresupuesto = new DAOPresupuesto();
    	
    	presupuesto= daoPresupuesto.selectPresupuesto(cotizacion);
    	
    	return presupuesto;
    }
    
    @Override
    public Presupuesto selectPresupuestoPorId(Integer idPresupuesto)
    {
    	Presupuesto presupuesto= new Presupuesto();
    	DAOPresupuesto daoPresupuesto = new DAOPresupuesto();
    	
    	presupuesto= daoPresupuesto.selectPresupuestoPorId(idPresupuesto);
    	
    	return presupuesto;

    }
    
    @Override
    public ClientePresupuesto selectClientePresupuesto(int idPresupuesto)
    {
    	ClientePresupuesto clientePresupuesto= new ClientePresupuesto();
    	DAOClientePresupuesto daoClientePresupuesto= new DAOClientePresupuesto();
    	
    	clientePresupuesto= daoClientePresupuesto.selectClientePresupuesto(idPresupuesto);
    	
    	return clientePresupuesto;
    }
    
    @Override
    public Integer updateEstadoPresupuesto(Integer idPresupuesto, Integer estado)
    {
    	int registrosAfectados= 0;
    	DAOClientePresupuesto daoClientePresupuesto= new DAOClientePresupuesto();
    	
    	registrosAfectados= daoClientePresupuesto.updateEstadoPresupuesto(idPresupuesto, estado);
    	
    	return registrosAfectados;
    	
    }
}