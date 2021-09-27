package programa;


import java.util.ArrayList;

import carga.CargarArchivoArrayListObjeto;
import carga.Manzana;
import carga.Pair;


public class Greedy {
	
	ArrayList<ArrayList<Manzana>> almacenConsumoMes1 = new ArrayList<ArrayList<Manzana>>();
	ArrayList<ArrayList<Manzana>> almacenConsumoMes2 = new ArrayList<ArrayList<Manzana>>();;
	ArrayList<ArrayList<Manzana>> almacenManometros = new ArrayList<ArrayList<Manzana>>();;
	private int m,n;
	private int numcontadoresRamales;
    private int numContadoresBarrio;
	private CargarArchivoArrayListObjeto cp;
 
	
  
	
	

	public Greedy(CargarArchivoArrayListObjeto cp) {
		this.cp=cp;
		this.m=cp.getMn().get(0);
		this.n=cp.getMn().get(1);
		this.numcontadoresRamales = (m%2==0)? m/2 : m/2+1;
	    this.numContadoresBarrio = n - 2;
	    if(numContadoresBarrio == 1) numContadoresBarrio = 2;
		if(numContadoresBarrio == 0) numContadoresBarrio = 1;
	    
	}

	public ArrayList<Manzana> greedy_m(ArrayList<Manzana> almacenManometros) {
		ArrayList<Manzana> resultado_manometros = new ArrayList<Manzana>(); //irá guardando la solución
		
		if(almacenManometros.isEmpty()) return null; //No cumple la función objetivo. 
		boolean primeroDeBarrio = false;

		for(int i=0;i<almacenManometros.size();i++) { //FUNCION DE SOLUCION:  no es solucion = contador sea menor del tamaño original del los manometros.
			//Funcion de seleccion: se selecciona el más cercano en la lista de manómetros. Al trabajar por indices, no se vuelve a considerar. Y omitimos el remove. 
			Manzana x = almacenManometros.get(i); 
			//FUNCION DE FACTIBILIDAD: si existe una fuga (obtenerFuga) desde el objeto seleccionado al anterior de la línea en la ciudad, entonces este objeto es una solución parcial
			if(x.isEsRamal()) if(obtenerFuga(almacenManometros.get(i-1),almacenManometros.get(i))) resultado_manometros.add(almacenManometros.get(i));//Si es un ramal--> comprobar con el anterior
			else if(x.isEsPrincipal()){ 
				//Comprobar si es primero de barrio
				if(i-this.numcontadoresRamales-1+this.numContadoresBarrio % numContadoresBarrio == 0) primeroDeBarrio = true;
				
				if(!primeroDeBarrio) {  //No es primeroDeBarrio
					if(obtenerFuga(almacenManometros.get(i-1),almacenManometros.get(i))) resultado_manometros.add(almacenManometros.get(i));
				}
				else { //Es primero de barrio
					if(obtenerFuga(almacenManometros.get(i-this.numcontadoresRamales-1+this.numContadoresBarrio / numContadoresBarrio),almacenManometros.get(i))) {
						resultado_manometros.add(almacenManometros.get(i));
					}
				}
				
			}
			//Si es general, no puede haber fuga. La fuga la consideramos donde la presión ha bajado. 
			
		}
		return resultado_manometros; //Función objetivo: devuelve solución alcanzada (distinto de null)
		
	}
	//el número 5 equivale al 500% de exceso de consumo
	public ArrayList<Manzana> greedy_c(ArrayList<Manzana> almacenConsumoMes1) {
		ArrayList<Manzana> resultado_contadores = new ArrayList<Manzana>();
		Manzana x;
		int contador=1;
		
		if(almacenConsumoMes1.isEmpty()) return null; //no es posible alcanzar la función objetivo
		while(contador<almacenConsumoMes1.size()) { //Función de solución: contador < almacenConsumoMes1.size
			x = almacenConsumoMes1.get(contador);//función de selección: se selecciona el más cercano en la lista de datos de nuestra ciudad. Su eficiencia depende de como los datos estén preordenados. 
			if(compruebaConsumo(x,5)) resultado_contadores.add(x); //Funcion factibilidad: compruebaConsumo
			contador++;
				
		}
		
		return resultado_contadores; //Función objetivo: devuelve solución alcanzada (distinto de null)
	}
	//el número 7 equivale al 700% de exceso de consumo
	public ArrayList<Manzana> greedy_c_manzana(ArrayList<Manzana> almacenConsumoMes1) {
		ArrayList<Manzana> resultado_contadores = new ArrayList<Manzana>();
		Manzana x;
		int contador=1;
		
		if(almacenConsumoMes1.isEmpty()) return null;
		while(contador<almacenConsumoMes1.size()) {
			x = almacenConsumoMes1.get(contador);
			if(compruebaConsumo(x,7) && !x.isEsPrincipal() && !x.isEsRamal()) resultado_contadores.add(x);
			contador++;
				
		}
		return resultado_contadores;
	}
	
	
	public boolean obtenerFuga(Manzana current, Manzana next) {
		double diezporciento = 0.1*current.getValor();
		double diferencia = current.getValor()-next.getValor();
		if(diferencia > diezporciento) return true;
		return false;
	}
	
	public boolean compruebaConsumo(Manzana manzana, double valorExceso) {
		
		double valorCurrent = manzana.getValor();
		double valorNext = cp.getAlmacenConsumoMes2().get(manzana.getPosicion()).getValor();
		if(manzana.isEsPrincipal()) { //es principal
			//Es el ultimo principal del barrio
			if(!(manzana.getCalle() == 2 || this.n < 4)) {
				valorCurrent= manzana.getValor()-cp.getAlmacenConsumoMes1().get(manzana.getPosicion()+1).getValor();
				valorNext= valorNext-cp.getAlmacenConsumoMes2().get(manzana.getPosicion()+1).getValor();
			}
		}
		if(valorNext > valorExceso && valorNext >= (valorCurrent*valorExceso)) return true;
		return false; 
	}
	
	//puntoDeToma: 0 - a la izquierda abajo, 1+ - a la derecha arriba
		//Solo quita tramos que estén dentro de otros tramos. El resto se mantienen. 
			public ArrayList<String> postprocesamiento(ArrayList<Manzana> entrada, int puntoDeToma) {
				ArrayList<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>> tramos = new ArrayList<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>();
				ArrayList<String> salida = new ArrayList<String>();
			
				int inicioAvenida=0,inicioCalle=0,finAvenida=0,finCalle=0;
				for(Manzana a: entrada) {
					
					if(a.isEsRamal()) {
						if(puntoDeToma == 0) {
							if(a.getAvenida()==0) inicioAvenida=a.getAvenida();
							else inicioAvenida = a.getAvenida()-1;
							finAvenida = a.getAvenida();
							inicioCalle = a.getCalle();
							finCalle = 0;
						}
						else {
							inicioAvenida = a.getAvenida();
							finAvenida = a.getAvenida()+1;
							inicioCalle = a.getCalle()+1;
							finCalle = 1;
						}
						
					}
					else if(a.isEsPrincipal()) {
						
						if(puntoDeToma == 0) {
							if(a.getAvenida()==0) inicioAvenida=a.getAvenida();
							else inicioAvenida = a.getAvenida()-1;
							
							finAvenida = a.getAvenida();
							finCalle = a.getCalle();
							if(a.getCalle()==(2)) inicioCalle = 0;
							else inicioCalle = a.getCalle()-1;
							
						
							
							
						}
						else {
							
							inicioAvenida = a.getAvenida();
							finAvenida = a.getAvenida()+1;
							finCalle = a.getCalle();
							if(a.getCalle()==(2)) inicioCalle = 1;
							else inicioCalle = a.getCalle();
						}
						
					}
					//Es manzana
					else {
						if(puntoDeToma == 0) {
							
							inicioAvenida = a.getAvenida();
							finAvenida = a.getAvenida();
							inicioCalle = a.getCalle();
							finCalle = a.getCalle();
							
							
						}
						else {
							
							inicioAvenida = a.getAvenida()+1;
							finAvenida = a.getAvenida()+1;
							inicioCalle = a.getCalle()+1;
							finCalle = a.getCalle()+1;
						}
						
					}
					
					Pair<Integer,Integer> avenidas = new Pair<Integer,Integer>(inicioAvenida,finAvenida);
					Pair<Integer,Integer> calles = new Pair<Integer,Integer>(inicioCalle,finCalle);
					Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> tramo = new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(avenidas,calles);
					tramos.add(tramo);
					
					
					
					
				}
				
				for(Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> b : tramos) {
					boolean decision=true;
				
					for(Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> tramo : tramos) {
						//Avenidas
						if(b.getKey().getKey() >= tramo.getKey().getKey() && b.getKey().getValue() <= tramo.getKey().getValue() &&
								b.getValue().getKey() >= tramo.getValue().getKey() && b.getValue().getValue() <= tramo.getValue().getValue() && !(b.equals(tramo))) { //Calles
							decision = false;
							
							
						}
						
					}
					
					if(decision==true) {
						
						salida.add("Tramo a evaluar: ("+(b.getKey().getKey()+1)+","+(b.getValue().getKey()+1)+") a ("+(b.getKey().getValue()+1)+","+(b.getValue().getValue()+1)+")\n");
						
					}
				}
				return salida;
				
			}
			//Se adapta para que las avenidas no empiecen en 0, ni las calles en 1. 
			public ArrayList<String> crearListado(ArrayList<Manzana> entrada) {
				ArrayList<String> salida = new ArrayList<String>();
				for(Manzana a: entrada) {
					salida.add("Aviso de consumo excesivo a Manzana ("+(a.getAvenida()+1)+","+(a.getCalle()+1)+") :\n");
					salida.add("      [Consumo actual excesivo: "+cp.getAlmacenConsumoMes2().get(a.getPosicion()).getValor()+"]\n ");
					salida.add("      [Consumo medio anterior: "+cp.getAlmacenConsumoMes1().get(a.getPosicion()).getValor()+"]\n");
					double exceso = cp.getAlmacenConsumoMes2().get(a.getPosicion()).getValor()-cp.getAlmacenConsumoMes1().get(a.getPosicion()).getValor();
					salida.add("      [Consumo excedido en: "+exceso+"]\n\n");
				}
				return salida;
			}



	

	
	
	

}
