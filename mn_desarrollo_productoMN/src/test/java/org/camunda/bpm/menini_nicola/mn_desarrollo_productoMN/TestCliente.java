package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Cliente;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOCliente;

public class TestCliente {

	public static void main(String[] args) {
		
		DAOCliente daoCliente= new DAOCliente();
		Cliente cliente= new Cliente();
		
		int idPresupuesto=3;
		cliente= daoCliente.selectClientePresupuestoAprobado(idPresupuesto);
		System.out.println("Datos Cliente con presupuesto aprobado.");
		System.out.println("idPresupuesto: "+ idPresupuesto);
		System.out.println("idCliente: "+ cliente.getIdCliente());
		System.out.println("nombre: "+ cliente.getNombre());
		System.out.println("email: "+ cliente.getEmail());
		System.out.println("telefono: "+ cliente.getTelefono());
		System.out.println("celular: "+ cliente.getCelular());
		System.out.println("rut: "+ cliente.getRut());
		System.out.println("razonSocial: "+ cliente.getRazonSocial());
		System.out.println("tipo: "+ cliente.getTipo());
		System.out.println("direccion: "+ cliente.getDireccion());
		
		
		int idCliente=3;
		Cliente cliente2= new Cliente();
		cliente2= daoCliente.selectCliente(idCliente);
		System.out.println("");
		System.out.println("");
		System.out.println("Obtener cliente segun id: "+idCliente);
		System.out.println("idCliente: "+ cliente2.getIdCliente());
		System.out.println("nombre: "+ cliente2.getNombre());
		System.out.println("email: "+ cliente2.getEmail());
		System.out.println("telefono: "+ cliente2.getTelefono());
		System.out.println("celular: "+ cliente2.getCelular());
		System.out.println("rut: "+ cliente2.getRut());
		System.out.println("razonSocial: "+ cliente2.getRazonSocial());
		System.out.println("tipo: "+ cliente2.getTipo());
		System.out.println("direccion: "+ cliente2.getDireccion());

	}
}
