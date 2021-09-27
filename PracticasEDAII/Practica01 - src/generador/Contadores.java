package generador;

import java.util.ArrayList;

public class Contadores {
	
	static ArrayList<Double> crearContadores() {
		
		ArrayList<Double> contadores = new ArrayList<Double>();
		ArrayList<Double> contadores2 = new ArrayList<Double>();
		
		
		boolean impar = Parametros.m%2 == 0 ? false : true;
		//Creamos primer barrio (junto a deposito)
		barrio b1 = new barrio(true, false);
		contadores.addAll(b1.getManzanas());
	
		contadores2.addAll(b1.getManzanas2());
		System.out.println("Primer barrio:"+contadores.size());
	 
		int m = Parametros.m;
	    int valor = m%2 == 1 ? m/2+1 : m/2;
	 
	
		//Creamos barrios intermedios
		for(int z=1;z<=valor-2;z++) {
			barrio b = new barrio(false,false);
			contadores.addAll(b.getManzanas());
			contadores2.addAll(b.getManzanas2());
			
			if(z==2) System.out.println("intermedio:"+b.getManzanas().size());
		
		
		    
		}
		
		 System.out.println("intermedios:"+contadores.size());
	
		
		//Creamos ultimo barrio
		barrio b2 = new barrio(false,impar);
		contadores.addAll(b2.getManzanas());
		contadores2.addAll(b2.getManzanas2());
		 System.out.println("b2-manz Ultimo: "+b2.getManzanas().size());
		 System.out.println("Ultimo:"+contadores.size());
		//Mes 2
		System.out.println(contadores.toString());
	    contadores.addAll(contadores2);
	    
	    System.out.println("TAMAÑO CONTADORES SIN GENERAL:"+contadores.size());
		return contadores;
	}
	
	 
	

}
