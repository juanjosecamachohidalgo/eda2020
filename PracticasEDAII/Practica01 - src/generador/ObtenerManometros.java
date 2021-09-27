package generador;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class ObtenerManometros {
	
	private static int m = Parametros.m;
	
	static String obtenerManometrosPrincipales(ArrayList<Double> manometros) {
        String salida="";
		
		DecimalFormat formato = new DecimalFormat("#.00");
		DecimalFormatSymbols sep = new DecimalFormatSymbols();
		sep.setDecimalSeparator('.');
		formato.setDecimalFormatSymbols(sep);
		
		int contador=1;
		int tamaño = Parametros.n-2; //Numero de manometros por manometro principal
		int in = m%2==1? m/2+1 : m/2;
		
		for(int i=in+1;i<manometros.size();i++) {
			if(contador==tamaño) {
				salida+=formato.format(manometros.get(i))+"\n";
				contador=1;
			}
			else {
				salida+=formato.format(manometros.get(i))+",";
				contador++;
			}
			
		}
		
		return salida;
	}

	static String obtenerManometrosRamales(ArrayList<Double> manometros) {
		String salida=""+manometros.get(0)+"\n";
		
		DecimalFormat formato = new DecimalFormat("#.00");
		DecimalFormatSymbols sep = new DecimalFormatSymbols();
		sep.setDecimalSeparator('.');
		formato.setDecimalFormatSymbols(sep);
		int in = m%2==1? m/2+1 : m/2;
		
		for(int i=1;i<=in;i++) {
			
			salida+=formato.format(manometros.get(i))+",";
		}
		return salida;
	}

}
