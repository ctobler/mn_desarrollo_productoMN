package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			//idProveedorMN auto-generado en BD
			proveedorMN.setIdProveedorMN(null);
			//por cada proveedor insertado en BD incrementar rowCount
			rowCount=rowCount + daoProveedorMN.insertarProveedorMN(proveedorMN);
		}
		
		return rowCount;
	}

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
		//insertar los datos del productoMN
		try {
			pstmt= con.prepareStatement(insert);
			
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
			
			if(productoMN.getIdClientePresupuesto()==null)
				pstmt.setNull(7,java.sql.Types.INTEGER);
			else
				pstmt.setInt(7, productoMN.getIdClientePresupuesto());
			
			/*if(productoMN.getIdProductoMNProveedorMN()==null)
				pstmt.setNull(8,java.sql.Types.INTEGER);
			else
				pstmt.setInt(8, productoMN.getIdProductoMNProveedorMN());*/
			
			rowCount= pstmt.executeUpdate();
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