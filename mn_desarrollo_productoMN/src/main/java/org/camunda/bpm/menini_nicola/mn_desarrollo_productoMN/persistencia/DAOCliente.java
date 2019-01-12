package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Cliente;
import org.jfree.util.Log;

public class DAOCliente {
	
	public DAOCliente() {}
	
	public Cliente selectClientePresupuestoAprobado(Integer idPresupuesto)
	{
		Cliente cliente= new Cliente();
		
		AccesoBD accesoBD= new AccesoBD();
		Connection con= accesoBD.conectarBD();
		Consultas consultas= new Consultas();
		String select= consultas.selectClientePresupuestoAprobado();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			
			pstmt= con.prepareStatement(select);
			pstmt.setInt(1, idPresupuesto);
			rs= pstmt.executeQuery();
			
			while(rs.next())
			{
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setCelular(rs.getString("celular"));
				cliente.setRut(rs.getString("rut"));
				cliente.setRazonSocial(rs.getString("razonSocial"));
				cliente.setTipo(rs.getString("tipo"));
				cliente.setDireccion(rs.getString("direccion"));
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
		
		return cliente;
	}
	
	public Cliente selectCliente(Integer idCliente)
	{
		Cliente cliente= new Cliente();
		
		AccesoBD accesoBD= new AccesoBD();
		Connection con= accesoBD.conectarBD();
		Consultas consultas= new Consultas();
		String select= consultas.selectCliente();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			
			pstmt= con.prepareStatement(select);
			pstmt.setInt(1, idCliente);
			rs= pstmt.executeQuery();
			
			while(rs.next())
			{
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setCelular(rs.getString("celular"));
				cliente.setRut(rs.getString("rut"));
				cliente.setRazonSocial(rs.getString("razonSocial"));
				cliente.setTipo(rs.getString("tipo"));
				cliente.setDireccion(rs.getString("direccion"));
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
		
		return cliente;
	}
	
}
