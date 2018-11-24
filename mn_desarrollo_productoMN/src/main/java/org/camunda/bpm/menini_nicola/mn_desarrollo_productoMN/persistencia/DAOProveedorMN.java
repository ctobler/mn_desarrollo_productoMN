package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ProveedorMN;
import org.jfree.util.Log;

public class DAOProveedorMN {
	
	public DAOProveedorMN() {}
	
	public int insertarProveedorMN(ProveedorMN proveedorMN)
	{
		AccesoBD accesoBD= new AccesoBD();
		Connection con= accesoBD.conectarBD();
		Consultas consultas= new Consultas();
		String insert= consultas.insertarProveedorMN();
		PreparedStatement pstmt= null;
		int rowCount=0;
		try {
			pstmt= con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			
			if(proveedorMN.getTipoProveedor()==null)
				pstmt.setNull(1, java.sql.Types.CHAR);
			else
				pstmt.setString(1, proveedorMN.getTipoProveedor());
			
			if(proveedorMN.getMoneda()==null)
				pstmt.setNull(2, java.sql.Types.CHAR);
			else
				pstmt.setString(2, proveedorMN.getMoneda());
			
			if(proveedorMN.getPrecio()==null)
				pstmt.setNull(3, java.sql.Types.DOUBLE);
			else
				pstmt.setDouble(3, proveedorMN.getPrecio());
			
			if(proveedorMN.getIvaProveedor()==null)
				pstmt.setNull(4, java.sql.Types.CHAR);
			else
				pstmt.setString(4, proveedorMN.getIvaProveedor());
			
			if(proveedorMN.getDetalles()==null)
				pstmt.setNull(5, java.sql.Types.CHAR);
			else
				pstmt.setString(5, proveedorMN.getDetalles());
			/*
			if(proveedorMN.getIdProductoMNProveedorMN()==null)
				pstmt.setNull(6,java.sql.Types.INTEGER);
				//pstmt.setInt(6, (Integer) null);
			else
				pstmt.setInt(6, proveedorMN.getIdProductoMNProveedorMN());
			*/
			rowCount= pstmt.executeUpdate();
			
			if(rowCount==0)
			{
				throw new SQLException("Falla al crear ProveedorMN. No se inserto ningun proveedorMN.");
			}
			
			//obtener IdProveedorMN auto-generado al hacer el INSERT en la BD
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                proveedorMN.setIdProveedorMN(generatedKeys.getInt(1));
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
