package generador;

import java.util.ArrayList;

public class Manometros {
	
	    static ArrayList<Double> manometros = new ArrayList<Double>();
	    
	    
		static ArrayList<Double> crearManometros() {
		
		    int ramales = Parametros.m%2 == 1 ? Parametros.m/2+1 : Parametros.m/2;
			
			double presion_inicial = Parametros.PRESION_INICIO;
			Double valor=0.0;
			
			manometros.add(presion_inicial);
			
			int contador=0;
			//Añadimos manometros de ramales
			for(int i=1;i<=ramales;i++) {
				valor=modificarValor(i-1);
				manometros.add(valor);
			    contador++;
			}
			System.out.println("manometros ramales:"+contador+ ",ramales: "+ramales);
			int contador2=0;
			//Añadir manometros restantes.
			for(int i=0;i<ramales;i++) {
				valor=modificarValor(i+1);
				manometros.add(valor);
			    contador2++;
				for(int j=i*(Parametros.n-2)+1+ramales+1;j<i*(Parametros.n-2)+1+ramales+Parametros.n-2;j++) {
					valor=modificarValor(j-1);
					manometros.add(valor);
					
					contador2++;
				}
			}
			System.out.println("manometros principales:"+contador2);
			
			
			return manometros;
		}
		
		public static double modificarValor(int i) {
			
			 //mejor caso 
			if(Parametros.decision == 2) {
							//Probabilidad del 0% de que la presión vaya a bajar por debajo del 10%, y por tanto algo falle
							int porcentaje = Funciones.calcularAleatorio(0, 1);
							double valor_modificado = manometros.get(i)-manometros.get(i)*porcentaje/100;
							if(valor_modificado < Parametros.PRESION_FIN && manometros.get(i) >= Parametros.PRESION_FIN) valor_modificado = Parametros.PRESION_FIN;
							return valor_modificado;
						
						
		     }
			//caso promedio = valorVariable
			else if(Parametros.decision == 3) {
						
							//Probabilidad del 20% de que la presión vaya a bajar por debajo del 10%, y por tanto algo falle
							int porcentaje = (Funciones.calcularAleatorio(0, 1000) < Parametros.PROB_FUGAS) ? Funciones.calcularAleatorio(10, 20) : Funciones.calcularAleatorio(0, 1);
							if(porcentaje >= 10) {
								System.out.println("Manómetro con fuga con valor: "+manometros.get(i)); 
								return manometros.get(i)-manometros.get(i)*porcentaje/100;
							}
							else {
								double valor_modificado = manometros.get(i)-manometros.get(i)*porcentaje/100;
								if(valor_modificado < Parametros.PRESION_FIN && manometros.get(i) >= Parametros.PRESION_FIN) valor_modificado = Parametros.PRESION_FIN;
								return valor_modificado;
							}
							
							
						
			}
			else if(Parametros.decision == 4) {
						
							//Probabilidad del 100% de que la presión vaya a bajar por debajo del 10%, y por tanto algo falle
							double porcentaje = (Funciones.calcularAleatorio(0, 100) < 100) ? Funciones.calcularAleatorio(10, 30) : Funciones.calcularAleatorio(0, 1);
							return manometros.get(i)-manometros.get(i)*porcentaje/100;
							
						
			}
			return 0;
			
		}

}
