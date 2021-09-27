package generador;

public class Funciones {
	
	public static double calcularAleatorio(double minimo,double maximo) {
		double num=Math.random()*(maximo-minimo+1)+(minimo);
		if(num < 0.0) num = 0.0;
	    return num;
	}
	
	public static int calcularAleatorio(int minimo,int maximo){
        
	       int num=(int)Math.floor(Math.random()*(maximo-minimo+1)+(minimo));
	       if(num < 0) num = 0;
	       return num;
	   }

	
	//Buscamos un valor aleatorio de consumo entre el valor promedio de consumo menos la desviación 
	//asignada, y el mismo sumandole esa desviación.
	public static double consumirEntreVariables(double consumo) {
		double desviacion = 0.25;
		double min = consumo-desviacion;
		double max = consumo+desviacion;
		
		return calcularAleatorio(min,max);
	}
	
	public double consumoGeneral() {
		double ConsumoRealizado = Math.random()*Parametros.PORCENTAJE_CONSUMO_FIN+Parametros.PORCENTAJE_CONSUMO_INICIO;
		return Parametros.NIVEL_DEPOSITO*ConsumoRealizado/100;
	}
	public int presionGeneral() {
		return (int) (Math.random()*Parametros.PRESION_FIN+Parametros.PRESION_INICIO);
	}

}
