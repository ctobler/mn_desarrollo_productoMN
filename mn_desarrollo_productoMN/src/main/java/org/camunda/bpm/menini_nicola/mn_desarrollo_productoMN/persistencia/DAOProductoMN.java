package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProductoMN;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProveedorMN;
import org.jfree.util.Log;

public class DAOProductoMN {
	
	public DAOProductoMN() {}
	
	public int insertarProveedoresProductoMN(List<ProveedorMN> proveedoresMN)
	{
		int rowCount=0;
		DAOProveedorMN daoProveedorMN= new DAOProveedorMN();
		
		for(ProveedorMN proveedorMN : proveedoresMN) 
		{
			//idProveedorMN es auto-generado en BD
			proveedorMN.setIdProveedorMN(null);
			//por cada proveedor insertado en BD incrementar rowCount
			//y guardar el idProveedorMN auto-generado en la BD
			int cantidadInsertadaProveedorMN= daoProveedorMN.insertarProveedorMN(proveedorMN);
			if(cantidadInsertadaProveedorMN>0)
			{
				rowCount++;
				//obtener idProveedorMN auto-generado
				int idProveedorMN= proveedorMN.getIdProveedorMN();
				//setear idProveedorMN en objeto proveedorMN de la lista
				proveedorMN.setIdProveedorMN(idProveedorMN);
			}
			
		}
		
		return rowCount;
	}
	
	//cuando inserta un ProductoMN retorna 1 y setea el idProductoMN con el valor auto-generado en la BD
	public int insertarProductoMN(ProductoMN productoMN)
	{
		AccesoBD accesoBD= new AccesoBD();
		Connection con= accesoBD.conectarBD();
		Consultas consultas= new Consultas();
		String insert= consultas.insertarProductoMN();
		PreparedStatement pstmt= null;
		int rowCount=0;
		
		//insertar la lista de proveedoresMN  
		int cantidadProveedoresMN=0;
		cantidadProveedoresMN= insertarProveedoresProductoMN(productoMN.getProveedoresMN());
		
//		//setear los idProveedorMN auto-generado en la BD sobre la lista de proveedoresMN
//		if(cantidadProveedoresMN > 0)
//		{
//			List<ProveedorMN> proveedoresMN=  productoMN.getProveedoresMN();
//			for(ProveedorMN p: proveedoresMN)
//			{
//				int idProveedorMN= p.getIdProveedorMN();
//				p.setIdProveedorMN(idProveedorMN);
//			}
//		}
		
		//insertar los datos del productoMN
		try {
			pstmt= con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			
			if(productoMN.getTrabajoRealizado()==null)
				pstmt.setNull(1, java.sql.Types.CHAR);
			else
				pstmt.setString(1, productoMN.getTrabajoRealizado());
			
			if(productoMN.getCantidad()==null)
				pstmt.setNull(2, java.sql.Types.INTEGER);
			else
				pstmt.setInt(2, productoMN.getCantidad());
			
			if(productoMN.getNombre()==null)
				pstmt.setNull(3, java.sql.Types.CHAR);
			else
				pstmt.setString(3, productoMN.getNombre());
			
			if(productoMN.getMoneda()==null)
				pstmt.setNull(4, java.sql.Types.CHAR);
			else
				pstmt.setString(4, productoMN.getMoneda());
			
			if(productoMN.getTotal()==null)
				pstmt.setNull(5, java.sql.Types.DOUBLE);
			else
				pstmt.setDouble(5, productoMN.getTotal());
			
			if(productoMN.getIvaProducto()==null)
				pstmt.setNull(6, java.sql.Types.CHAR);
			else
				pstmt.setString(6, productoMN.getIvaProducto());
			
			if(productoMN.getSenia()==null)
				pstmt.setNull(7, java.sql.Types.DOUBLE);
			else
				pstmt.setDouble(7, productoMN.getSenia());
			
			if(productoMN.getFechaProduccion()==null)
				pstmt.setNull(8, java.sql.Types.DATE);
			else
				pstmt.setDate(8, (Date) productoMN.getFechaProduccion());
			
			if(productoMN.getFechaEntrega()==null)
				pstmt.setNull(9, java.sql.Types.DATE);
			else
				pstmt.setDate(9, (Date) productoMN.getFechaEntrega());
			
			if(productoMN.getIdClientePresupuesto()==null)
				pstmt.setNull(10,java.sql.Types.INTEGER);
			else
				pstmt.setInt(10, productoMN.getIdClientePresupuesto());
			
//			if(productoMN.getIdProductoMNProveedorMN()==null)
//				pstmt.setNull(8,java.sql.Types.INTEGER);
//			else
//				pstmt.setInt(8, productoMN.getIdProductoMNProveedorMN());
			
			rowCount= pstmt.executeUpdate();
			
			if(rowCount==0)
			{
				throw new SQLException("Falla al crear ProductoMN. No se inserto ningun productoMN.");
			}
			
			//obtener IdProductoMN auto-generado al hacer el INSERT en la BD
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                productoMN.setIdProductoMN(generatedKeys.getInt(1));
	            }
	            else {
	                throw new SQLException("Falla al crear productoMN, no se obtiene ID.");
	            }
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error(e);
			e.printStackTrace();
		}
		finally {
			try {
				pstmt.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Log.error(e);
				e.printStackTrace();

			}
			
		}
		return rowCount;
	}
		
}