package programa;

import java.util.ArrayList;
import java.util.Scanner;

public class Caso {
	
	private int avenidas;
	private int calles;
	private int num_manzanas; //Seleccionamos una lista de 20 manzanas como entrada de datos. 
	private ArrayList<String> caso = new ArrayList<String>();
	private ArrayList<Manzana> manzanas = new ArrayList<Manzana>();
	
	public Caso() {
		this.avenidas = Funciones.calcularAleatorio(3, 500);
		this.calles = Funciones.calcularAleatorio(3, 500);
		System.out.println("Introduzca un número de manzanas con escapes hasta "+(this.avenidas*this.calles-1)+":");
		//this.num_manzanas = Funciones.calcularAleatorio(4, this.avenidas*this.calles-1);
		Scanner sc = new Scanner(System.in);
		this.num_manzanas = sc.nextInt();
		crearManzanas();
		
		caso.add("%CASO DE PRUEBA - CIUDAD ("+avenidas+" avenidas"+", "+calles+" calles)");
		caso.add("%LISTADO DE FUGAS DETECTADAS:");
		for(Manzana m: manzanas) {
			caso.add(m.toString());
		}
	}

	private void crearManzanas() {
		int contador=0;
		while(contador!=num_manzanas) {
			Manzana m = new Manzana (this.avenidas, this.calles);
			if(!manzanas.contains(m)) {
				manzanas.add(m);
				contador++;
			}
			
		}
		
	}

	public ArrayList<String> getCaso() {
		return caso;
	}

	public void setCaso(ArrayList<String> caso) {
		this.caso = caso;
	}

}
