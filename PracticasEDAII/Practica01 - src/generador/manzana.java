package generador;

public class manzana {
	
	private double consumo;
	
    //Se define un edificio.
	/* 
	 * Consumo promedio: El nivel del depósito dividido entre el número de viviendas de toda la ciudad
	 * Consumo extra: Piscina: +5%.
	 * (Sobre el      Zona verde: +10%.
	 *   consumo      Clase alta (1): +40%.
	 *  promedio)     Clase media (2): +0%.
	 *                Clase baja (3): -40%.
	 *                Número de casas: consumo promedio * número de casas por manzana.
	 */
	
	//1=clase alta, 2=clase media, 3=clase baja
	public manzana(int clase, int piscina, int zona_verde) {
		consumo=0.0;
		int numero_Casas = Funciones.calcularAleatorio(Parametros.MIN_CASAS, Parametros.MAX_CASAS); //nº casas en manzana
		//Consumo máximo posible por manzana
		double consumoMaximo = Parametros.NIVEL_DEPOSITO/Parametros.numero_Manzanas;
		//Consumo promedio de una vivienda particular dentro de una manzana
		double consumoPromedio = Funciones.consumirEntreVariables(consumoMaximo*Parametros.CONSUMO_PROMEDIO/numero_Casas);
	
		//Extra de consumo según número de casas. 
		consumo+=consumoPromedio*numero_Casas;
		
		
		//Añade extras
		if(piscina==1) consumo+=0.05*consumo;
		if(zona_verde==1) consumo+=0.1*consumo;
		
		//Añade valor de clase
		if(clase==1)  consumo+=0.4*consumo;
		else if(clase==3) consumo-=0.4*consumo;
		
		
	}
	
	public double getConsumo() {
		return consumo;
	}
	



	

}
