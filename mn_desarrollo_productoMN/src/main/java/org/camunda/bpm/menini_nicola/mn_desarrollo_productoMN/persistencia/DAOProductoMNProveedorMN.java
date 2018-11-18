package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProductoMNProveedorMN;
import org.jfree.util.Log;

public class DAOProductoMNProveedorMN {
	
	public DAOProductoMNProveedorMN() {}
	
	public int insertarProductoMNProveedorMN(ProductoMNProveedorMN productoMNProveedorMN)
	{
		AccesoBD accesoBD= new AccesoBD();
		Connection con= accesoBD.conectarBD();
		Consultas consultas= new Consultas();
		String insert= consultas.insertarProductoMNProveedorMN();
		PreparedStatement pstmt= null;
		int rowCount=0;
		try {
			pstmt= con.prepareStatement(insert);
			if(productoMNProveedorMN.getIdProductoMN()==null)
				pstmt.setNull(1,java.sql.Types.INTEGER);
			else
				pstmt.setInt(1,productoMNProveedorMN.getIdProductoMN());
			if(productoMNProveedorMN.getIdProveedorMN()==null)
				pstmt.setNull(2,java.sql.Types.INTEGER);
			else
				pstmt.setInt(2,productoMNProveedorMN.getIdProveedorMN());
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
