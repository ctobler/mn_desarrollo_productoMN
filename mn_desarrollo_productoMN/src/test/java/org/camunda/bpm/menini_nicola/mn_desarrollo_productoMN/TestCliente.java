package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Cliente;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOCliente;

public class TestCliente {

	public static void main(String[] args) {
		
		DAOCliente daoCliente= new DAOCliente();
		Cliente cliente= new Cliente();
		
		int idPresupuesto=1;
		cliente= daoCliente.selectClientePresupuestoAprobado(idPresupuesto);
		System.out.println("Datos Cliente con presupuesto aprobado.");
		System.out.println("idPresupuesto: "+ idPresupuesto);
		System.out.println("idCliente: "+ cliente.getIdCliente());
		System.out.println("nombre: "+ cliente.getNombre());
		System.out.println("email: "+ cliente.getEmail());
		System.out.println("telefono: "+ cliente.getTelefono());
		System.out.println("celular: "+ cliente.getCelular());
	}
}
