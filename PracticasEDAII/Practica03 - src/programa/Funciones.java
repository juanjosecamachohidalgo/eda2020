package programa;

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

	

}
