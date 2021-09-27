package generador;

import java.util.ArrayList;
import java.util.Scanner;

import programa.mensajes;

public class Casos {
	
	private ArrayList<String> datos = new ArrayList<String>();
	private Scanner sc = new Scanner(System.in);
	
	
	//Se crearan segun la decision
	public Casos(int decision) {
		//Generamos dimensiones de la isla
		System.out.println(mensajes.M);
		int m = sc.nextInt();
		System.out.println(mensajes.N);
		int n = sc.nextInt();
		new Parametros(m,n,decision);
		CrearDatos c1 = new CrearDatos();
	    datos=c1.getAlmacen();
	    
	}
	

	public ArrayList<String> getDatos() {
		return datos;
	}


	public void setDatos(ArrayList<String> datos) {
		this.datos = datos;
	}
	
	

	

}
