package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia;

public class Consultas {
	
	//CONSULTAS TABLA: mn_productoMN
	public String insertarProductoMN()
	{
		String insert="INSERT INTO mn_productoMN(trabajoRealizado,cantidad,nombre,moneda,total,ivaProducto,senia,fechaProduccion,fechaEntrega,idClientePresupuesto)\n" + 
				"VALUES (?,?,?,?,?,?,?,?,?,?);";
		return insert;
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
	
	//CONSULTAS TABLA: mn_presupuesto
	public String selectPresupuestosAprobados()
	{
		String select="SELECT \n" + 
				"presupuesto.idPresupuesto,\n" + 
				"presupuesto.cotizacion,\n" + 
				"presupuesto.fecha,\n" + 
				"presupuesto.moneda,\n" + 
				"presupuesto.costo,\n" + 
				"presupuesto.condicionesVenta,\n" + 
				"presupuesto.descripcion\n" + 
				"FROM mn_presupuesto presupuesto INNER JOIN mn_cliente_presupuesto cliePres ON\n" + 
				"															 presupuesto.idPresupuesto = cliePres.idPresupuesto\n" + 
				"WHERE cliePres.estado=1";				
		return select;
	}
	
	public String selectPresupuesto()
	{
		String select="\n" + 
				"select * from mn_presupuesto where cotizacion=?";
		return select;
	}
	
	public String selectPresupuestoPorId()
	{
		String select="SELECT \n" + 
				"presupuesto.idPresupuesto,\n" + 
				"presupuesto.cotizacion,\n" + 
				"presupuesto.fecha,\n" + 
				"presupuesto.moneda,\n" + 
				"presupuesto.costo,\n" + 
				"presupuesto.condicionesVenta,\n" + 
				"presupuesto.descripcion\n" + 
				"FROM mn_presupuesto presupuesto\n" + 
				"WHERE presupuesto.idPresupuesto=?";				
		return select;
	}
	
	//obtiene los datos de los presupestos que fueron aprobados
	//que se corresponden solamente con los productos del tipo "ProductoMN"
//	public String selectPresupuestosAprobados()
//	{
//		String select="SELECT presupuesto.idPresupuesto,\n" + 
//				"presupuesto.cotizacion,\n" + 
//				"presupuesto.fecha,\n" + 
//				"presupuesto.moneda,\n" + 
//				"presupuesto.costo,\n" + 
//				"presupuesto.condicionesVenta,\n" + 
//				"presupuesto.descripcion\n" +  
//				"FROM mn_presupuesto presupuesto INNER JOIN mn_cliente_presupuesto cliePres ON\n" +  
//				"															 presupuesto.idPresupuesto = cliePres.idPresupuesto\n" +  
//				"WHERE cliePres.estado=1 AND (\n" +  
//				"		cliePres.idClientePresupuesto= (select idClientePresupuesto\n" + 
//				"												 from mn_productoMN\n" + 
//				"												 where idClientePresupuesto != 0) )\n" +
//				"ORDER BY presupuesto.fecha DESC;";					
//		return select;
//	}
	
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
		String select="SELECT cliente.idCliente,\n" + 
				"cliente.nombre,\n" + 
				"cliente.email,\n" + 
				"cliente.telefono,\n" + 
				"cliente.celular\n" + 
				" FROM mn_cliente cliente\n" + 
				" WHERE cliente.idCliente=?;";
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
		String select="update mn_cliente_presupuesto\n" + 
				"set estado=?\n" + 
				"where idPresupuesto=?";
		return select;
	}
}
