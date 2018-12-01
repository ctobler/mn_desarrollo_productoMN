package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.ClientePresupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Presupuesto;
import org.jfree.util.Log;

public class DAOClientePresupuesto {
	
	public DAOClientePresupuesto() {}
	
	public ClientePresupuesto selectClientePresupuesto(int idPresupuesto)
	{
		ClientePresupuesto clientePresupuesto= new ClientePresupuesto();
		AccesoBD accesoBD= new AccesoBD();
		Connection con= accesoBD.conectarBD();
		Consultas consultas= new Consultas();
		String select= consultas.selectClientePresupuesto();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			
			pstmt= con.prepareStatement(select);
			pstmt.setInt(1, idPresupuesto);
			rs= pstmt.executeQuery();
			
			while(rs.next())
			{
				
				clientePresupuesto.setIdClientePresupuesto(rs.getInt("idClientePresupuesto"));
				clientePresupuesto.setEstado(rs.getInt("estado"));
				clientePresupuesto.setIdCliente(rs.getInt("idCliente"));
				clientePresupuesto.setIdPresupuesto(rs.getInt("idPresupuesto"));
				
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
		
		return clientePresupuesto;
	}
	
	
	public Integer updateEstadoPresupuesto(Integer idPresupuesto, Integer estado)
	{
		AccesoBD accesoBD= new AccesoBD();
		Connection con= accesoBD.conectarBD();
		Consultas consultas= new Consultas();
		String select= consultas.updateEstadoPresupuesto();
		PreparedStatement pstmt= null;
		
		int registrosAfectados=0;
		
		try {
			pstmt= con.prepareStatement(select);
			pstmt.setInt(1, estado);
			pstmt.setInt(2, idPresupuesto);
			registrosAfectados=pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return registrosAfectados;
	}

	
}
