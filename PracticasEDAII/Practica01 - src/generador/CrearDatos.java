package generador;
import java.util.ArrayList;


public class CrearDatos {
		
	private ArrayList<String> almacen = new ArrayList<String>();
	
	
	public CrearDatos() {

		switch(Parametros.decision) {
		  case 2:
			   almacen.add("MEJOR CASO, (M,N)=("+Parametros.m+","+Parametros.n+")");
			   break;
		  case 3:
			   almacen.add("CASO PROMEDIO, (M,N)=("+Parametros.m+","+Parametros.n+")");
			   break;
		  case 4:
			   almacen.add("PEOR CASO, (M,N)=("+Parametros.m+","+Parametros.n+")");
			   break;
		}
		
		//Contadores
		ArrayList<Double> contadores = Contadores.crearContadores();
		
		//Manometros
		ArrayList<Double> manometros = Manometros.crearManometros();
		
		System.out.println("tamaño de contadores: "+contadores.size()+", tamaño de manometros: "+manometros.size());
		meterEnAlmacen(contadores,manometros);
		
		contadores.clear();
		manometros.clear();
	}
	
	//Generador:
	    //Se Contemplan diferentes escenarios: barrios mas ricos, mas pobres, con edificios mas altos o con menos, zonas verdes, piscina. 
		private void meterEnAlmacen(ArrayList<Double> contadores, ArrayList<Double> manometros) {
		  
			    
			  
				almacen.add("%MES "+(1)+" - CONTADORES");
				almacen.add("%---------------->contador general y principales");
				almacen.add(ObtenerContadores.obtenerPrincipales(contadores.subList(0, contadores.size()/2)));
				almacen.add("%---------------->contadores de manzanas. Cada línea corresponde a un contador principal de este a oeste. Orden de manzanas de oeste a este y de norte a sur.");
				almacen.add(ObtenerContadores.obtenerManzanas(contadores.subList(0, contadores.size()/2)));
				almacen.add("============================");
				almacen.add("\n");
				
				almacen.add("%MES "+(2)+" - CONTADORES");
				almacen.add("%---------------->contador general y principales");
				almacen.add(ObtenerContadores.obtenerPrincipales(contadores.subList(contadores.size()/2, contadores.size())));
				almacen.add("%---------------->contadores de manzanas. Cada línea corresponde a un contador principal de este a oeste. Orden de manzanas de oeste a este y de norte a sur.");
				almacen.add(ObtenerContadores.obtenerManzanas(contadores.subList(contadores.size()/2, contadores.size())));
				almacen.add("============================");
				almacen.add("\n");
			
			
			
			almacen.add("%MANOMETROS");
			almacen.add("%manometro general y ramales");
			almacen.add(ObtenerManometros.obtenerManometrosRamales(manometros));
			almacen.add("%manometros asociados a principales.Cada línea corresponde a un manometro principal de este a oeste. Orden de manometros de norte a sur.");
			almacen.add(ObtenerManometros.obtenerManometrosPrincipales(manometros));
			
		}
		
	public ArrayList<String> getAlmacen() {
		return almacen;
	}

	

}
