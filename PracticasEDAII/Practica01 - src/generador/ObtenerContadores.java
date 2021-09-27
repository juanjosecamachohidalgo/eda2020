package generador;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class ObtenerContadores {
	
	
	
	
	static String obtenerManzanas(List<Double> contadores) {
		System.out.println("ENTRADA:"+contadores.toString());
		int n = Parametros.n;
		int contador=0;
		String salida="";
		int tamaño = n*2;
		
		
		
		DecimalFormat formato = new DecimalFormat("#.00");
		DecimalFormatSymbols sep = new DecimalFormatSymbols();
		sep.setDecimalSeparator('.');
		formato.setDecimalFormatSymbols(sep);
		
		//obtener manzanas - primer barrio
		for(int i=0;i<tamaño-1;i++) {
					if(i==tamaño-2) salida+=formato.format(contadores.get(i))+"\n";
					else salida+=formato.format(contadores.get(i))+",";
					contador++;
					
		}
		System.out.println("PUES: "+contadores.get(tamaño-2));
		System.out.println("Contador1: "+contador);
		int numeroBarriosIntermedios = Parametros.m % 2 == 0 ? Parametros.m/2 : Parametros.m/2 + 1;
		numeroBarriosIntermedios -= 2;
		int contadors = n-2; 
		if(contadors<2) contadors =2;
		if(contadors<1) contadors=1;
		//obtener manzanas - intermedio
		//Para cada barrio...
		int ini=(n*2-1)+contadors+1;
		int fin=ini;
		for(int i=0;i<numeroBarriosIntermedios;i++) {
			for(int j=(ini)+tamaño*i;j<(ini)+tamaño*i+n*2;j++) {
				salida+=formato.format(contadores.get(j))+",";
				contador++;
				if(i==numeroBarriosIntermedios-1) fin=(ini)+tamaño*i+n*2+contadors+1;
			}
			System.out.println("POSICION FINAL J: "+((ini)+tamaño*i));
			System.out.println("FIN:"+fin);
			salida+="\n";
			ini+=contadors+1;
			
		}
	
		
		System.out.println("Contador2: "+contador);
		//Obtener manzanas - ultimo barrio
		try {
			int sum = Parametros.m%2==1 ? n:n*2;
			for(int z=fin;z<fin+sum;z++) {
				
				salida+=formato.format(contadores.get(z))+",";
				contador++;
		    }	
			System.out.println("Contador3: "+contador);
			salida+="\n";
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return salida;
		
	}

	
	static String obtenerPrincipales(List<Double> contadores) {
		System.out.println("Almacenando mitad...");
		DecimalFormat formato = new DecimalFormat("#.00");
		DecimalFormatSymbols sep = new DecimalFormatSymbols();
		sep.setDecimalSeparator('.');
		formato.setDecimalFormatSymbols(sep);
		
		int n = Parametros.n;
		int m = Parametros.m;
		String salida="";
		List<Double> principales = new ArrayList<Double>();
		List<Double> ramales = new ArrayList<Double>();
		double general=0.0;
		int tamaño = n*2;
		
		int impar=0;
		if(m%2==1) impar=1;
		
		//int numeroAvenidasIntermedias = m-impar-2;
		int numeroBarriosIntermedios=(m/2+impar)-2;
		//if(numeroAvenidasIntermedias>0) numeroBarriosIntermedios = numeroAvenidasIntermedias/2;
		int numContadoresBarrio = n-2;
		if(numContadoresBarrio < 1) numContadoresBarrio = 1;
		
		
		//obtener principales - primer barrio
		principales.addAll(new ArrayList<Double>(contadores.subList(tamaño-1, (tamaño-1)+numContadoresBarrio)));
		ramales.add(contadores.get((tamaño-1)+numContadoresBarrio));
		
		//obtener principales - intermedio
		
		int inicio=(tamaño-1); inicio+=numContadoresBarrio+1; inicio+=tamaño; 
		for(int z=0;z<numeroBarriosIntermedios;z++) {
			   
			    
				principales.addAll(new ArrayList<Double>(contadores.subList(inicio+z*(tamaño+numContadoresBarrio+1), inicio+z*(tamaño+numContadoresBarrio+1)+numContadoresBarrio)));
				ramales.add(contadores.get(inicio+z*(tamaño+numContadoresBarrio+1)+numContadoresBarrio));	
				
		}
		
		
		//Obtener principales - ultimo barrio
		principales.addAll(new ArrayList<Double>(contadores.subList(contadores.size()-1-numContadoresBarrio, contadores.size()-1)));		
		ramales.add(contadores.get(contadores.size()-1));
		
		
		
	
		//obtener general
		for(Double d: ramales) {
			general+=d;
		}
		
		//Crear cadena de salida
		salida+=formato.format(general)+"\n";
		for(Double d: ramales) {
			if(!salida.endsWith("\n")) salida+=","+formato.format(d);
		    else salida+=formato.format(d);
		}
		salida+="\n";
		
		int cambio = 0;
		
		for(Double a: principales) {
			
			if(cambio%numContadoresBarrio == 0) salida+="\n";
			     if(!salida.endsWith("\n")) salida+=","+formato.format(a);
			     else salida+=formato.format(a);
			     cambio++;
		}
		return salida;
	}

	
}
