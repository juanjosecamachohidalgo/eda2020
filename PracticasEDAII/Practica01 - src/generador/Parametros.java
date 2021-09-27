package generador;

public class Parametros {
	
	    //Valores principales
	    static int m; //Avenidas
	    static int n; //Calles
	    static int numero_Manzanas; //numero de manzanas total
	    static int decision; //Tipo de caso
	    static int numcontadoresRamales = m/2;
	    static int numContadoresBarrio = n - 2;
	    static int numcontadoresPrincipales = numcontadoresRamales * numContadoresBarrio; 
	    public static boolean esPracticaDos;
	   
	    
	    //Suponemos: probabilidad de fugas en caso promedio
	    static double PROB_FUGAS = 400; //sobre 1000% //probabilidad de perdida de presión
	    static int PROB_ESCAPE = 10;//sobre 100% - consumo de agua
	    
	    //Suponemos: un porcentaje de consumo promedio, que luego será variable de forma aleatoria.
	    static double CONSUMO_PROMEDIO = 0.5;
	    
	    //Suponemos: número de meses. Esto no es variable. 
		static int NUMERO_MESES = 2;
		
		//Suponemos: número de casas por edificio
		static int MIN_CASAS = 10;
		static int MAX_CASAS = 30;
		
		//Suponemos: nivel del deposito actual. 
		static double NIVEL_DEPOSITO=500000.0;
		
		//Suponemos: Se consume entre un 50% y un 80% del nivel de deposito
		static int PORCENTAJE_CONSUMO_INICIO=50; 
		static int PORCENTAJE_CONSUMO_FIN=80;
		
		//Dato dado: presión de distribución - de 110 kPA a 150 kPA. 
		static int PRESION_INICIO = 150000;
		static int PRESION_FIN = 110000;
		
		 public Parametros(int em, int en, int d) {
			    m = em;
			    n = en;
		    	numero_Manzanas = (Parametros.m * Parametros.n) - 1; 
		    	if(m%2==1) numcontadoresRamales=m/2+1;
		    	decision = d;
		    	esPracticaDos=false;
		 }

}
