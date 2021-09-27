package programa;

import java.util.ArrayList;

import carga.CargarArchivoArrayListObjeto;
import carga.Manzana;


public class Algoritmo {
	
	ArrayList<ArrayList<Manzana>> almacenConsumoMes1 = new ArrayList<ArrayList<Manzana>>();
	ArrayList<ArrayList<Manzana>> almacenConsumoMes2 = new ArrayList<ArrayList<Manzana>>();;
	ArrayList<ArrayList<Manzana>> almacenManometros = new ArrayList<ArrayList<Manzana>>();;
	private int m,n;
	private int numcontadoresRamales;
    private int numContadoresBarrio;
	private int numcontadoresPrincipales; 
 
	
    //Tenemos varios resultados
	private ArrayList<ArrayList<Manzana>> resultado_manometros = new ArrayList<ArrayList<Manzana>>();
	
	

	public Algoritmo(CargarArchivoArrayListObjeto cp) {
		
		this.m=cp.getMn().get(0);
		this.n=cp.getMn().get(1);
		this.numcontadoresRamales = (m%2==0)? m/2 : m/2+1;
	    this.numContadoresBarrio = n - 2;
	    if(numContadoresBarrio == 1) numContadoresBarrio = 2;
		if(numContadoresBarrio == 0) numContadoresBarrio = 1;
	    this.numcontadoresPrincipales = this.numcontadoresRamales * this.numContadoresBarrio; 
	  
	    //Añadir ramales y general
	  		this.almacenConsumoMes1.add(new ArrayList<Manzana>(cp.getAlmacenConsumoMes1().subList(0, numcontadoresRamales+1)));
	  		this.almacenConsumoMes2.add(new ArrayList<Manzana>(cp.getAlmacenConsumoMes2().subList(0, numcontadoresRamales+1)));
	  		this.almacenManometros.add(new ArrayList<Manzana>(cp.getAlmacenManometros().subList(0, numcontadoresRamales+1)));
	  		
	  		
	     //Añadir principales
	  		
	  		this.almacenConsumoMes1.add(new ArrayList<Manzana>(cp.getAlmacenConsumoMes1().subList(numcontadoresRamales+1, numcontadoresRamales+1+numcontadoresPrincipales)));
	  		this.almacenConsumoMes2.add(new ArrayList<Manzana>(cp.getAlmacenConsumoMes2().subList(numcontadoresRamales+1, numcontadoresRamales+1+numcontadoresPrincipales)));
	  		this.almacenManometros.add(new ArrayList<Manzana>(cp.getAlmacenManometros().subList(numcontadoresRamales+1, numcontadoresRamales+1+numcontadoresPrincipales)));
	  		
	  	 //Añadir manzanas
	  		this.almacenConsumoMes1.add(new ArrayList<Manzana>(cp.getAlmacenConsumoMes1().subList(numcontadoresRamales+1+numcontadoresPrincipales, cp.getAlmacenConsumoMes1().size())));
	  		this.almacenConsumoMes2.add(new ArrayList<Manzana>(cp.getAlmacenConsumoMes2().subList(numcontadoresRamales+1+numcontadoresPrincipales, cp.getAlmacenConsumoMes2().size())));
	  		
	  	 //Comprobar generales
	  	  ArrayList<Manzana> generales = new ArrayList<Manzana>();
	  	  //añadir general
	  	  if(obtenerFuga(almacenManometros.get(0).get(0),almacenManometros.get(0).get(1))) generales.add(almacenManometros.get(0).get(1));
	  	  //añadir ramales
	  	  for(int i=1;i<almacenManometros.get(0).size()-1;i++) {
			if(obtenerFuga(almacenManometros.get(0).get(i),almacenManometros.get(0).get(i+1))) generales.add(almacenManometros.get(0).get(i+1));
		  }
	  	  resultado_manometros.add(generales);
	  		
	}




	public ArrayList<Manzana> dyv_manometros(ArrayList<ArrayList<Manzana>> entrada){
		if(suficientementePequeño_m(entrada)) {
			return algoritmoEspecifico_m(entrada);
		}
		else {
			
			ArrayList<ArrayList<ArrayList<Manzana>>> descompuesto = descomponer_m(entrada);
			ArrayList<ArrayList<Manzana>> resultado = new ArrayList<ArrayList<Manzana>>();
			resultado.add(dyv_manometros(descompuesto.get(0)));
			resultado.add(dyv_manometros(descompuesto.get(1)));
			return combinar(resultado);	
		}	
	}
	public boolean suficientementePequeño_m(ArrayList<ArrayList<Manzana>> entrada) {
		return entrada.isEmpty() || entrada == null || entrada.get(0).isEmpty() || entrada.get(0).size()<=1 ? true:false;
		
	}
	public ArrayList<Manzana> algoritmoEspecifico_m(ArrayList<ArrayList<Manzana>> entrada){
		
		ArrayList<Manzana> salida = new ArrayList<Manzana>();
		boolean parar=false;
		
		if(!(entrada==null || entrada.isEmpty() || entrada.get(0).isEmpty())) {
			//Ramal
			if(obtenerFuga(entrada.get(0).get(0),entrada.get(1).get(0))) {
				salida.add(entrada.get(1).get(0));
				parar=true;
			}
			if(parar==false) {
				for(int i=1;i<=entrada.get(1).size()-1;i++) {
					if(obtenerFuga(entrada.get(1).get(i-1),entrada.get(1).get(i))) {
						salida.add(entrada.get(1).get(i));
						break;
					}
				}
			}
		}
		
		return salida;
		
	}
	private boolean obtenerFuga(Manzana current, Manzana next) {
		double diezporciento = 0.1*current.getValor();
		if(current.getValor()-next.getValor() >= diezporciento) return true;
		return false;
	}
	private boolean tramoFuga(Manzana current, Manzana fin, int tramo) {
		return ((current.getValor()-fin.getValor()) > (tramo*0.5)) || fin.getValor()==0; //Se contempla un 0,5% de perdida por salto. 
		
	}
	private ArrayList<ArrayList<ArrayList<Manzana>>> descomponer_m(ArrayList<ArrayList<Manzana>> entrada) {
		
		ArrayList<ArrayList<ArrayList<Manzana>>> descompuestoSalida = new ArrayList<ArrayList<ArrayList<Manzana>>>();
		ArrayList<ArrayList<Manzana>> parte1 = new ArrayList<ArrayList<Manzana>>();
		ArrayList<ArrayList<Manzana>> parte2 = new ArrayList<ArrayList<Manzana>>();
		int impar=0;
		boolean general=false;
	
			
				
				if(entrada.get(0).get(0).isEsPrincipal()==false && entrada.get(0).get(0).isEsRamal()==false) general=true;
				if(general) entrada.get(0).remove(0);
				
				//Si es impar
				if(entrada.get(0).size()%2==1) impar=1;
				
				//general y ramales
				int tramo=entrada.get(0).size()/2 + impar;
				tramo = tramo + tramo * numContadoresBarrio;
				if(tramoFuga(entrada.get(0).get(0),entrada.get(1).get((entrada.get(0).size()/2 + impar)  * numContadoresBarrio - 1),tramo)) {
					parte1.add(new ArrayList<Manzana>(entrada.get(0).subList(0, (entrada.get(0).size())/2 + impar))); //ramales
					parte1.add(new ArrayList<Manzana>(entrada.get(1).subList(0, (entrada.get(0).size()/2 + impar)  * numContadoresBarrio))); //principales
					
				}
				tramo=entrada.get(0).size() + entrada.get(1).size() - tramo;
				if(tramoFuga(entrada.get(0).get((entrada.get(0).size())/2 + impar),entrada.get(1).get(entrada.get(1).size()-1),tramo)) {
					parte2.add(new ArrayList<Manzana>(entrada.get(0).subList((entrada.get(0).size())/2 + impar,entrada.get(0).size()))); //ramales
					parte2.add(new ArrayList<Manzana>(entrada.get(1).subList(((entrada.get(0).size())/2 + impar) * numContadoresBarrio,entrada.get(1).size()))); //principales
					
				}
			descompuestoSalida.add(parte1);
			descompuestoSalida.add(parte2);
		
		return descompuestoSalida;
		
	}

	ArrayList<Manzana> combinar(ArrayList<ArrayList<Manzana>> solucionGeneral) {
		ArrayList<Manzana> salida = new ArrayList<Manzana>();
		for(ArrayList<Manzana> a : solucionGeneral) {
			for(Manzana b : a) {
				if(!salida.contains(b)) salida.add(b);
			}
		}
		return salida;
	}




	public ArrayList<Manzana> dyv_contadores(ArrayList<ArrayList<Manzana>> entrada){
		
		if(suficientementePequeño_c(entrada)) {
			return algoritmoEspecifico_c(entrada);
		}
		else {
			ArrayList<ArrayList<Manzana>> resultado = new ArrayList<ArrayList<Manzana>>();
			ArrayList<ArrayList<ArrayList<Manzana>>> descompuesto = descomponer_c(entrada, resultado);
			if(descompuesto != null) resultado.add(dyv_contadores(descompuesto.get(0)));
			if(descompuesto != null) resultado.add(dyv_contadores(descompuesto.get(1)));
			return combinar(resultado);	
		}	
	}
	
	


	public boolean suficientementePequeño_c(ArrayList<ArrayList<Manzana>> entrada) {
		
		if(entrada.size()<=1) return false;
		return entrada.get(1).size()==1  ? true:false;
		
	}
	public ArrayList<Manzana> algoritmoEspecifico_c(ArrayList<ArrayList<Manzana>> entrada){
		ArrayList<Manzana> salida = new ArrayList<Manzana>();
		
		if(entrada == null || entrada.size() < 3) return salida;
		if(superaConsumo(entrada.get(1).get(0))) salida.add(entrada.get(1).get(0));		
		for(int i=0;i<entrada.get(2).size();i++) {
				if(superaConsumo(entrada.get(2).get(i))) {
					salida.add(entrada.get(2).get(i));		
				}
			
	    }
		return salida;
		
	}
	private boolean comprobarDescomposicion(ArrayList<Manzana> entrada, ArrayList<ArrayList<Manzana>> resultado) {
		ArrayList<Manzana> almacenados = new ArrayList<Manzana>();
		
		for(int i=0;i<entrada.size();i++) {
			if(compruebaConsumo(entrada.get(i))) almacenados.add(entrada.get(i));
		}
		resultado.add(almacenados);
		return true;
	}


	private boolean compruebaConsumo(Manzana manzana) {
		double valorNext=0.0,valorCurrent=manzana.getValor();
	    
		if(manzana.isEsRamal()) valorNext=this.almacenConsumoMes2.get(0).get(manzana.getPosicion()).getValor();
		if(manzana.isEsPrincipal()) valorNext=this.almacenConsumoMes2.get(1).get(manzana.getPosicion()-almacenConsumoMes2.get(0).size()).getValor();
		return valorNext >= 7 && valorNext >= valorCurrent*7; 
	}
	
    //Supera 700% consumo
	private boolean superaConsumo(Manzana manzana) {
		double valorNext=0.0,valorCurrent=manzana.getValor();
		if(manzana.isEsPrincipal()) valorNext=this.almacenConsumoMes2.get(1).get(manzana.getPosicion()-almacenConsumoMes2.get(0).size()).getValor();
        else valorNext=this.almacenConsumoMes2.get(2).get(this.almacenConsumoMes1.get(2).indexOf(manzana)).getValor();
	
		return valorNext >= 7 && valorNext >= valorCurrent*7; 
	}



	private ArrayList<ArrayList<ArrayList<Manzana>>> descomponer_c(ArrayList<ArrayList<Manzana>> entrada, ArrayList<ArrayList<Manzana>> resultado) {
	
		ArrayList<ArrayList<ArrayList<Manzana>>> descompuestoSalida = new ArrayList<ArrayList<ArrayList<Manzana>>>();
		ArrayList<ArrayList<Manzana>> parte1 = new ArrayList<ArrayList<Manzana>>();
		ArrayList<ArrayList<Manzana>> parte2 = new ArrayList<ArrayList<Manzana>>();
		int impar=0;
		boolean general=false;
	
		if(entrada.size()<3) return null;
		if(entrada.get(1).size()==1) return null;
		
		if(entrada.get(0).get(0).isEsPrincipal()==false && entrada.get(0).get(0).isEsRamal()==false) general=true;
		if(general) entrada.get(0).remove(0);
		
		//Si es impar
		if(entrada.get(0).size()%2==1) impar=1;
		
		
		//Si hay que dividir ramales
		
		if(entrada.get(0).size()>1) {
			
			int numRamales = entrada.get(0).size()/2 + impar; //numero de ramales en los que se realiza la division de partes
			
			if(comprobarDescomposicion(new ArrayList<Manzana>(entrada.get(0).subList(0, numRamales)), resultado)){
				parte1.add(new ArrayList<Manzana>(entrada.get(0).subList(0, numRamales))); //ramales
				parte1.add(new ArrayList<Manzana>(entrada.get(1).subList(0, numRamales  * numContadoresBarrio))); //principales
				
				//manzanas - si contiene primer barrio
				if(parte1.get(0).get(0).getPosicion() == 1) parte1.add(new ArrayList<Manzana>(entrada.get(2).subList(0, (n*2*numRamales)-1)));
				else parte1.add(new ArrayList<Manzana>(entrada.get(2).subList(0, n*2*numRamales))); 
			}
			if(comprobarDescomposicion(new ArrayList<Manzana>(entrada.get(0).subList(numRamales,entrada.get(0).size())), resultado)){
				parte2.add(new ArrayList<Manzana>(entrada.get(0).subList(numRamales,entrada.get(0).size()))); //ramales
				parte2.add(new ArrayList<Manzana>(entrada.get(1).subList(numRamales * numContadoresBarrio,entrada.get(1).size()))); //principales
				//Manzanas - si parte 1 contiene primer barrio
				if(parte2.get(0).get(0).getPosicion() == 1) parte2.add(new ArrayList<Manzana>(entrada.get(2).subList(((n*2*numRamales) - 1)-1,entrada.get(2).size()))); //manzans
				else parte2.add(new ArrayList<Manzana>(entrada.get(2).subList(n*2*numRamales-1,entrada.get(2).size()))); 	
			}
			
		}
		if(entrada.get(1).size()%2==0) impar=0;
		else impar=1;
		
		//Si hay que dividir principales
		if(entrada.get(0).size()==1 && entrada.get(1).size()>1) {
			int numContadores = (entrada.get(1).size()/2 + impar); //numero de contadores en los que se hace la division en partes.
			parte1.add(new ArrayList<Manzana>(entrada.get(0).subList(0, 1)));//ramal
			parte2.add(new ArrayList<Manzana>(entrada.get(0).subList(0, 1)));
			int numBarrios = (m%2 == 1) ? m/2+1 : m/2;
		   
			
			//PRIMERA PARTE
			if(comprobarDescomposicion(new ArrayList<Manzana>(entrada.get(1).subList(0, numContadores)), resultado)){
				parte1.add(new ArrayList<Manzana>(entrada.get(1).subList(0, numContadores))); //principales
				
				//Si es primer barrio
				if(parte1.get(0).get(0).getPosicion() == 1){
					//Añadir manzanas
					//Si son mas de dos contadores y la division ocupa la primera zona
					if(parte1.get(1).get(0).getCalle() >= n-2) parte1.add(new ArrayList<Manzana>(entrada.get(2).subList(0, (3+( numContadores -1 )*2)))); 
					else parte1.add(new ArrayList<Manzana>(entrada.get(2).subList(0, numContadores*2))); 
				}
				//Si es ultimo barrio
				else if(parte1.get(0).get(0).getPosicion() == numBarrios ) {
					
				    //Si es primera zona
					if(parte1.get(1).get(0).getCalle() >= n-2) {
						 //si es impar
						if(m%2 == 1) parte1.add(new ArrayList<Manzana>(entrada.get(2).subList(0, 2+(numContadores-1))));
						else parte1.add(new ArrayList<Manzana>(entrada.get(2).subList(0, 4+(numContadores-1)*2)));
					}
					else {
						
						 //si es impar
						if(m%2 == 1) parte1.add(new ArrayList<Manzana>(entrada.get(2).subList(0, numContadores)));
						else parte1.add(new ArrayList<Manzana>(entrada.get(2).subList(0, numContadores*2)));
					}
					   
				}
				//barrio intermedio
				else {
					//Si es primera zona
					if(parte1.get(1).get(0).getCalle() >= n-2) parte1.add(new ArrayList<Manzana>(entrada.get(2).subList(0, 4+(numContadores-1)*2)));
					else parte1.add(new ArrayList<Manzana>(entrada.get(2).subList(0, numContadores*2)));
					
				}
			}
			
			 //SEGUNDA PARTE
			 if(comprobarDescomposicion(new ArrayList<Manzana>(entrada.get(1).subList(numContadores,entrada.get(1).size())), resultado)){
				parte2.add(new ArrayList<Manzana>(entrada.get(1).subList(numContadores,entrada.get(1).size()))); //principales
				
				//Si es primer barrio
				if(parte2.get(0).get(0).getPosicion() == 1){
					//Añadir manzanas
					//la division parte 1 ocupa la primera zona
					if(parte1.get(1).get(0).getCalle() >= n-2)  parte2.add(new ArrayList<Manzana>(entrada.get(2).subList((3+( numContadores -1 )*2), entrada.get(2).size())));
					else parte2.add(new ArrayList<Manzana>(entrada.get(2).subList(numContadores*2, entrada.get(2).size())));
					
				}
				//Si es ultimo barrio
				
				else if(parte2.get(0).get(0).getPosicion() == numBarrios ) {
					//Si es primera zona parte 1
					
					if(parte1.get(1).get(0).getCalle() == n-1) {
						 //si es impar
						if(m%2 == 1) parte2.add(new ArrayList<Manzana>(entrada.get(2).subList(2+(numContadores-1),entrada.get(2).size())));
						else parte2.add(new ArrayList<Manzana>(entrada.get(2).subList(4+(numContadores-1)*2,entrada.get(2).size())));
					}
					else {
						 //si es impar
						if(m%2 == 1) parte2.add(new ArrayList<Manzana>(entrada.get(2).subList(numContadores,entrada.get(2).size())));
						else parte2.add(new ArrayList<Manzana>(entrada.get(2).subList(numContadores*2,entrada.get(2).size())));
					}
					   
				}
				//barrio intermedio
				else {
					//Si es primera zona parte 1
					if(parte1.get(1).get(0).getCalle() >= n-2)   parte2.add(new ArrayList<Manzana>(entrada.get(2).subList(4+(numContadores-1)*2,entrada.get(2).size())));
					else parte2.add(new ArrayList<Manzana>(entrada.get(2).subList(numContadores*2,entrada.get(2).size())));
					
				}
			 }
			
		}
		
		
		descompuestoSalida.add(parte1);
		descompuestoSalida.add(parte2);
		
		
		return descompuestoSalida;
	}
	
	
	
		public ArrayList<ArrayList<Manzana>> getResultado_manometros() {
			return resultado_manometros;
		}



		public void setResultado_manometros(ArrayList<ArrayList<Manzana>> resultado_manometros) {
			this.resultado_manometros = resultado_manometros;
		}



	


	
	

}
