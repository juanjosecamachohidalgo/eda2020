package programa;

import java.util.ArrayList;
import java.util.Collections;



public class Solucion {

	public ArrayList<Cliente> clientes;
	public double dinero;
	private int tipo;
	
	public Solucion(ArrayList<Cliente> clientes, double dinero, int tipo) {
		this.clientes = clientes;
		this.dinero = dinero;
	}
	
	public void mostrar() {
		if (clientes != null  &&  !clientes.isEmpty()){
			System.out.println("\nSolucion Grupos Clientes");
			System.out.println("Dinero = " + dinero);
			System.out.println("Grupos a elegir :");
			
			for (Cliente c : clientes) {
				System.out.println("- " + c.toString());
			}
		}
	}

	
	public ArrayList<Cliente> getClientes() {
		if(tipo == 0) Collections.sort(clientes, new ClienteComparatorTipoA());
		else Collections.sort(clientes, new ClienteComparatorTipoB());
		
		return clientes;
	}
	
	

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	
}
