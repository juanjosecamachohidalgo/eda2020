package generador;

import java.util.ArrayList;

public class barrio {
	
	private ArrayList<Double> manzanas = new ArrayList<Double>();
	private ArrayList<Double> principales = new ArrayList<Double>();
	private ArrayList<Double> manzanas2 = new ArrayList<Double>();
	private ArrayList<Double> principales2 = new ArrayList<Double>();
	

	/**
	 * Se define un Barrio como un ArrayList de Manzanas. Un barrio puede ser primer barrio en cuyo caso
	 * tiene el número de manzanas de un barrio promedio menos 1, es decir, el número de calles por 2.
	 * Si la ciudad es impar, el número de manzanas se reduce en una columna, es decir, en una avenida. 
	 * @param primerBarrio
	 * @param impar
	 */
	public barrio(boolean primerBarrio, boolean esImpar) {
		int numeroManzanas = 2*Parametros.n;
		if(primerBarrio) numeroManzanas-=1;
		if(esImpar) numeroManzanas -= Parametros.n;
		
		for(int i=0;i<numeroManzanas;i++) {
			manzana m= new manzana(Funciones.calcularAleatorio(1,3),Funciones.calcularAleatorio(1,2),Funciones.calcularAleatorio(1,2));
		    manzanas.add(m.getConsumo());
		    manzanas2.add(modificarValor(m.getConsumo(),3));
		   
		}
		
		
		int manzanasRecorridas=0;
	
		int primero=0, impar=0,inicioImpar=0, incremento=0;
		if(primerBarrio) primero=1;
		if(esImpar) {
			inicioImpar=2;
			impar=Parametros.n;
			incremento=1;
		}
		
		//Añadimos los 4 o 3 primeros
		double suma=0;
		for(int i=0;i<4-primero-inicioImpar;i++) {
			suma+=manzanas.get(i);
			manzanasRecorridas++;
		}
		principales.add(suma);
		suma=0;
		if(primerBarrio == false && esImpar == true) System.out.println("manzanas recorridas: "+manzanasRecorridas+", principales tamaño: "+principales.size());
		
	
			//Fase intermedia
			if(Parametros.n*2-impar>4-primero-inicioImpar) { 
			    
				for(int j=4-primero-inicioImpar;j<Parametros.n*2-impar-(4-inicioImpar)-primero;j+=2-incremento) {
					if(esImpar) suma+=manzanas.get(manzanasRecorridas);
					else suma+=manzanas.get(manzanasRecorridas)+manzanas.get(manzanasRecorridas+1);
					principales.add(suma);
					suma=0;
					manzanasRecorridas+=2-incremento;
					
					
				}
			}
			
			suma=0;
			if(primerBarrio == false && esImpar == true) System.out.println("manzanas recorridas: "+manzanasRecorridas+", principales tamaño: "+principales.size());
		//Fase final
			while(manzanasRecorridas<manzanas.size()) {
				suma+=manzanas.get(manzanasRecorridas);
				manzanasRecorridas++;
			}
			if(suma != 0) {
				principales.add(suma);
				
			}
		
		suma=0;
		if(primerBarrio == false && esImpar == true) System.out.println("manzanas recorridas: "+manzanasRecorridas+", principales tamaño: "+principales.size());
		
		
		
		//Hacemos que sea acumulativo
		for(int l=principales.size()-1;l>0;l--) {
			principales.set(l-1, principales.get(l)+principales.get(l-1));
			
		}
	
		
		//MES 2_____________________________________________________
		
		manzanasRecorridas=0;
		//Añadir al final del array los principales: principales...ramal.
		primero=0;
		impar=0;
		inicioImpar=0;
		if(primerBarrio) primero=1;
		if(esImpar) {
			inicioImpar=2;
			impar=Parametros.n;
		}
		
		//Añadimos los 4 o 3 primeros
				suma=0;
				for(int i=0;i<4-primero-inicioImpar;i++) {
					if(i<manzanas2.size()) suma+=manzanas2.get(i);
					manzanasRecorridas++;
				}
				principales2.add(suma);
				suma=0;
				
				
				
				
				//Fase intermedia
				if(Parametros.n*2-impar>4-primero-inicioImpar) { 
				    
					for(int j=4-primero-inicioImpar;j<Parametros.n*2-impar-(4-inicioImpar)-primero;j+=2-incremento) {
						if(esImpar) suma+=manzanas.get(manzanasRecorridas);
						else suma+=manzanas.get(manzanasRecorridas)+manzanas.get(manzanasRecorridas+1);
						principales2.add(suma);
						suma=0;
						manzanasRecorridas+=2-incremento;
						
						
					}
				}
				
					
					suma=0;
					
				
				
				
				
				//Fase final
				
					while(manzanasRecorridas<manzanas2.size()) {
						suma+=manzanas2.get(manzanasRecorridas);
						manzanasRecorridas++;
					}
					if(suma != 0) {
						principales2.add(suma);
						
					}
				
				suma=0;
				
				
				
				
				//Hacemos que sea acumulativo
				for(int l=principales2.size()-1;l>0;l--) {
					principales2.set(l-1, principales2.get(l)+principales2.get(l-1));
					
				}
				//Añadimos principales al mes 2
				for(int w=0;w<principales2.size();w++) {
					principales2.set(w,modificarValor(principales2.get(w),2));
				}
		
		
		
		
		
		
		
		
		
		
		
		
		
				
		
		//Añadimos principales y ramal
		manzanas.addAll(principales);
		manzanas.add(principales.get(0));
		
		
		
		manzanas2.addAll(principales2);
		manzanas2.add(modificarValor(principales2.get(0),1));
		
		
		
	}
	public ArrayList<Double> getManzanas2() {
		return manzanas2;
	}

	
	
	public ArrayList<Double> getManzanas() {
		return manzanas;
	}
	//Todo el mundo consume mas el segundo mes porque llega el verano y hace mas calor. 
    //La gente se ducha mas, hace falta mas riego a las plantas, la piscina necesita mas agua
    //ya que se evapora más y se consume más agua para reponer líquidos. 
	//TIPO 1: ramal, TIPO 2: principal, TIPO 3: manzana
     static Double modificarValor(Double valorInicial, int tipo) {
		
		//mejor caso = no hay escapes
		if(Parametros.decision == 2) {
			//modificamos el valor sumando un aleatorio entre el 30% y el 50% del valor inicial.
			if(valorInicial==0.0) return valorInicial;
			if(tipo == 3) return valorInicial*Funciones.calcularAleatorio(0.3, 0.5)+valorInicial;
			else return valorInicial;
			
			
		}
		//caso promedio = puede haber escapes
		else if(Parametros.decision == 3) {
			
			//Comprobamos si habrá escape
			//Hay escape: modificamos el valor en un 720% a 800%.
			if(Funciones.calcularAleatorio(0.0, 100.0) < Parametros.PROB_ESCAPE) {
				
				double valor_modificado = valorInicial*Funciones.calcularAleatorio(7.20, 8.00)+valorInicial;
				System.out.println("Hay consumo excesivo en "+valor_modificado);
				return valor_modificado;
			}
			//No hay escape
			else {
				
				//modificamos el valor en un valor variable entre el 20% y el 70%
				if(tipo == 3) return valorInicial*Funciones.calcularAleatorio(0.2, 0.7)+valorInicial;
				else return valorInicial;
			}
			
		}
		else if(Parametros.decision == 4) {
			//modificamos el valor en un 720% a 800%.
			
			return valorInicial*Funciones.calcularAleatorio(7.20, 8.00)+valorInicial;
		}
		return valorInicial;
}

	
	
	
	
}
