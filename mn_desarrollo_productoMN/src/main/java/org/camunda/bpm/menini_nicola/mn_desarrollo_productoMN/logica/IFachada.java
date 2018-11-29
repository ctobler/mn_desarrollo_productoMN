package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.logica;

import java.util.List;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Cliente;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ClientePresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Presupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOPresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProductoMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProductoMNProveedorMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.valueObject.VOProveedorMN;

public interface IFachada {

	int insertarProveedorMN(VOProveedorMN voProveedorMN);

	int insertarProductoMN(VOProductoMN voProductoMN);

	int insertarProductoMNProveedorMN(VOProductoMNProveedorMN voProductoMNproveedorMN);
	
	public Cliente selectClientePresupuestoAprobado(VOPresupuesto voPresupuesto);
	
	public List<Presupuesto> selectPresupuestosAprobados();

	Presupuesto selectPresupuesto(String cotizacion);

	ClientePresupuesto selectClientePresupuesto(int idPresupuesto);

	Presupuesto selectPresupuestoPorId(Integer idPresupuesto);

	Cliente selectCliente(Integer idCliente);

	Integer updateEstadoPresupuesto(Integer idPresupuesto, Integer estado);

}