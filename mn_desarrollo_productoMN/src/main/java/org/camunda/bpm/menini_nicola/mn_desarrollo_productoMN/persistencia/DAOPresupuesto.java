package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Presupuesto;
import org.jfree.util.Log;

public class DAOPresupuesto {
	
	public DAOPresupuesto() {}
	
	public List<Presupuesto> selectPresupuestosAprobados()
	{
		List<Presupuesto> presupuestosAprobados= new ArrayList<Presupuesto>();
		
		AccesoBD accesoBD= new AccesoBD();
		Connection con= accesoBD.conectarBD();
		Consultas consultas= new Consultas();
		String select= consultas.selectPresupuestosAprobados();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			pstmt= con.prepareStatement(select);
			rs= pstmt.executeQuery();
			while(rs.next())
			{
				Presupuesto presupuesto= new Presupuesto();
				presupuesto.setIdPresupuesto(rs.getInt("idPresupuesto"));
				presupuesto.setCotizacion(rs.getString("cotizacion"));
				presupuesto.setFecha(rs.getDate("fecha"));
				presupuesto.setMoneda(rs.getString("moneda"));
				presupuesto.setCosto(rs.getDouble("costo"));
				presupuesto.setCondicionesVenta(rs.getString("condicionesVenta"));
				presupuesto.setDescripcion(rs.getString("descripcion"));
				
				presupuestosAprobados.add(presupuesto);
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
		
		return presupuestosAprobados;
	}
	
	public Presupuesto selectPresupuesto(String cotizacion)
	{
		Presupuesto presupuesto= new Presupuesto();
		AccesoBD accesoBD= new AccesoBD();
		Connection con= accesoBD.conectarBD();
		Consultas consultas= new Consultas();
		String select= consultas.selectPresupuesto();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			
			pstmt= con.prepareStatement(select);
			pstmt.setString(1, cotizacion);
			rs= pstmt.executeQuery();
			
			while(rs.next())
			{
				presupuesto.setIdPresupuesto(rs.getInt("idPresupuesto"));
				presupuesto.setCotizacion(rs.getString("cotizacion"));
				presupuesto.setFecha(rs.getDate("fecha"));
				presupuesto.setMoneda(rs.getString("moneda"));
				presupuesto.setCosto(rs.getDouble("costo"));
				presupuesto.setCondicionesVenta(rs.getString("condicionesVenta"));
				presupuesto.setDescripcion(rs.getString("descripcion"));
				
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
		
		
		return presupuesto;
	}

}
