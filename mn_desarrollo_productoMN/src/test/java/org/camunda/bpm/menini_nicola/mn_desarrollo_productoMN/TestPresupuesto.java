package org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.modelo.Presupuesto;
import org.camunda.bpm.menini_nicola.mn_desarrollo_productoMN.persistencia.DAOPresupuesto;

public class TestPresupuesto {

	public static void main(String[] args) {
		
		DAOPresupuesto daoPresupuesto= new DAOPresupuesto();
		List<Presupuesto> presupuestosAprobados= new ArrayList<Presupuesto>();
		presupuestosAprobados= daoPresupuesto.selectPresupuestosAprobados();
		
		int cantidadPresupuestosAprobados= presupuestosAprobados.size();
		System.out.println("Cantidad de presupuestos aprobados: "+cantidadPresupuestosAprobados);
		System.out.println("Datos de presupuestos aprobados: ");
		
		for(Presupuesto presupuestoAprobado: presupuestosAprobados)
		{
			System.lineSeparator();
			System.out.println("idPresupuesto: "+presupuestoAprobado.getIdPresupuesto());
			System.out.println("cotizacion: "+presupuestoAprobado.getCotizacion());
			System.out.println("fecha: "+presupuestoAprobado.getFecha());
			System.out.println("moneda: "+presupuestoAprobado.getMoneda());
			System.out.println("costo: "+presupuestoAprobado.getCosto());
			System.out.println("condiciones venta: "+presupuestoAprobado.getCondicionesVenta());
			System.out.println("descripcion: "+presupuestoAprobado.getDescripcion());
		}
		
		//prueba de conversión de lista de presupuestos a hashMap
		Map<Integer, String> presupuestosAprobadosMap= new HashMap<Integer, String>();
		Integer i=0;
		while(i < presupuestosAprobados.size())
		{
			presupuestosAprobadosMap.put(i, presupuestosAprobados.get(i).getCotizacion());
			i++;
		}
		
		//mostrar tamanio del hashMap por pantalla
		System.out.println();
		System.out.println("tamaño del hashMap: "+ presupuestosAprobadosMap.size());
	}
}
