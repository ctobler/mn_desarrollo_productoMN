package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia;

public class Consultas {
	
	//CONSULTAS TABLA: mn_productoMN
	public String insertarProductoMN()
	{
		String insert="INSERT INTO mn_productoMN(trabajoRealizado,cantidad,nombre,moneda,total,ivaProducto,senia,fechaProduccion,fechaEntrega,idClientePresupuesto)\n" + 
				"VALUES (?,?,?,?,?,?,?,?,?,?);";
		return insert;
	}
	public String updateFechaEntrega()
	{
		String update="UPDATE mn_productoMN\n" + 
					  "SET fechaEntrega = ?\n" + 
					  "WHERE idProductoMN = ?";
				
		return update;
	}
	
	//CONSULTAS TABLA: mn_productoMN_proveedorMN
	public String insertarProductoMNProveedorMN()
	{
		String insert="INSERT INTO mn_productoMN_proveedorMN(idProductoMN,idProveedorMN) VALUES (?,?);";
		return insert;
	}
	//CONSULTAS TABLA: mn_proveedorMN
	public String insertarProveedorMN()
	{
		String insert="INSERT INTO mn_proveedorMN(tipoProveedor,moneda,precio,ivaProveedor,detalles)\n" + 
				"VALUES (?,?,?,?,?);";
		
		return insert;
	}
	
	//CORREGIR MAS ARRIBA !!!!
	//CONSULTAS TABLA: mn_presupuesto
	public String selectPresupuestosAprobados()
	{
		String select="SELECT \n" + 
				"presupuesto.idPresupuesto, \n" + 
				"CONCAT(presupuesto.cotizacion,' : ',cliente.nombre) as cotizacion,  \n" + 
				"presupuesto.fecha,\n" + 
				"presupuesto.moneda, \n" + 
				"presupuesto.costo,  \n" + 
				"presupuesto.condicionesVenta, \n" + 
				"presupuesto.descripcion, \n" +
				"presupuesto.unidades\n" + 
				"FROM mn_presupuesto presupuesto \n" + 
				"INNER JOIN mn_cliente_presupuesto cliePres ON presupuesto.idPresupuesto= cliePres.idPresupuesto \n" + 
				"INNER JOIN mn_cliente cliente ON cliePres.idCliente= cliente.idCliente\n" + 
				"INNER JOIN mn_producto producto ON producto.idPresupuesto= presupuesto.idPresupuesto\n" + 
				"WHERE (cliePres.estado= 1) ";	
		return select;
	}
	
	//CORREGIR MAS ARRIBA !!!!
	public String selectPresupuesto()
	{
		String select="\n" + 
				"select * from mn_presupuesto where cotizacion=?";
		return select;
	}
	
	//CORREGIR MAS ARRIBA !!!!
	public String selectPresupuestoPorId()
	{
		String select="SELECT \n" + 
				"presupuesto.idPresupuesto,\n" + 
				"presupuesto.cotizacion,\n" + 
				"presupuesto.fecha,\n" + 
				"presupuesto.moneda,\n" + 
				"presupuesto.costo,\n" + 
				"presupuesto.condicionesVenta,\n" + 
				"presupuesto.descripcion,\n" + 
				"presupuesto.unidades\n" + 
				"FROM mn_presupuesto presupuesto\n" + 
				"WHERE presupuesto.idPresupuesto=?";				
		return select;
	}
	

	//CONSULTAS TABLA: mn_cliente
	//obtiene los datos de un cliente que aprobo un presupuesto determinado 
	public String selectClientePresupuestoAprobado()
	{
		String select="\n" + 
				"select * from mn_cliente cliente\n" + 
				"inner join mn_cliente_presupuesto cliePres on\n" + 
				"			  cliente.idCliente= cliePres.idCliente\n" + 
				"inner join mn_presupuesto presupuesto on \n" + 
				"				presupuesto.idPresupuesto= cliePres.idPresupuesto\n" + 
				"where cliePres.estado = 1 and presupuesto.idPresupuesto= ?;";
		return select;
	}
	public String selectCliente()
	{
		String select="SELECT\n" + 
				" cliente.idCliente, \n" + 
				" cliente.nombre, \n" + 
				" cliente.email, \n" + 
				" cliente.telefono, \n" + 
				" cliente.celular,\n" + 
				" cliente.rut,\n" + 
				" cliente.razonSocial,\n" + 
				" cliente.tipo,\n" + 
				" cliente.direccion \n" + 
				"FROM mn_cliente cliente \n" + 
				"WHERE cliente.idCliente= ?";
		return select;
	}
	
	//CONSULTAS TABLA: mn_cliente_presupuesto
	public String selectClientePresupuesto()
	{
		String select="select * from mn_cliente_presupuesto where idPresupuesto=?;";
		return select;
	}
	public String updateEstadoPresupuesto()
	{
		String update="update mn_cliente_presupuesto\n" + 
				"set estado=?\n" + 
				"where idPresupuesto=?";
		return update;
	}
	//CONSULTAS TABLA: mn_producto
	public String selectNombreProducto()
	{
		String select="SELECT nombre FROM mn_producto WHERE idPresupuesto=?;";
		return select;
	}


}
