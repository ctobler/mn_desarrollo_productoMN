package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Cliente;
import org.jfree.util.Log;

public class DAOProducto {

	public DAOProducto() {};
	
	public String selectNombreProducto(Integer idPresupuesto)
	{
		String nombreProducto= "";
		
		AccesoBD accesoBD= new AccesoBD();
		Connection con= accesoBD.conectarBD();
		Consultas consultas= new Consultas();
		String select= consultas.selectNombreProducto();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			
			pstmt= con.prepareStatement(select);
			pstmt.setInt(1, idPresupuesto);
			rs= pstmt.executeQuery();
			
			while(rs.next())
			{
				nombreProducto= rs.getString("nombre");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Log.error(e);
				e.printStackTrace();
			}
		}
		
		return nombreProducto;
	}
}
