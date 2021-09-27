package programa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import carga.CargarArchivoArrayListObjeto;
import carga.Manzana;
import generador.crearArchivo;
import generador.Parametros;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		System.gc();
		Parametros.esPracticaDos=false;
		//Bienvenida
		System.out.println(mensajes.BIENVENIDA+mensajes.AUTORES_1+mensajes.AUTORES_2+mensajes.AUTORES_3+mensajes.BARRA+mensajes.PRACTICA_1);
		//Scanner
		Scanner sc = new Scanner(System.in);
		//Menú principal
		System.out.println(mensajes.MENU_P+mensajes.MENU_PO1+mensajes.MENU_PO2+mensajes.MENU_PO3+mensajes.MENU_PO4+mensajes.MENU_P05+mensajes.NUM_OPCION);
		menu_principal(sc.nextInt(), sc);
		
        
	}
	
	public static void menu_principal(int decision, Scanner sc) throws IOException {
		
		switch(decision) {
		  case 1:
			   System.out.println(mensajes.MENU_P2+mensajes.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
			    //Menú principal
			   System.out.println(mensajes.MENU_P+mensajes.MENU_PO1+mensajes.MENU_PO2+mensajes.MENU_PO3+mensajes.MENU_PO4+mensajes.MENU_P05+mensajes.NUM_OPCION);
			   menu_principal(sc.nextInt(), sc);
			   break;
		  case 2:
			   System.out.println(mensajes.MENU_P3+mensajes.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
			   //Menú principal
				System.out.println(mensajes.MENU_P+mensajes.MENU_PO1+mensajes.MENU_PO2+mensajes.MENU_PO3+mensajes.MENU_PO4+mensajes.MENU_P05+mensajes.NUM_OPCION);
				menu_principal(sc.nextInt(), sc);
			   break;
		  case 3:
			   System.out.println(mensajes.MENU_P3+mensajes.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
		    	 //Menú principal
				System.out.println(mensajes.MENU_P+mensajes.MENU_PO1+mensajes.MENU_PO2+mensajes.MENU_PO3+mensajes.MENU_PO4+mensajes.MENU_P05+mensajes.NUM_OPCION);
				menu_principal(sc.nextInt(), sc);
			   break;
		  case 4:
			   System.out.println(mensajes.MENU_P3+mensajes.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
		    	 //Menú principal
				System.out.println(mensajes.MENU_P+mensajes.MENU_PO1+mensajes.MENU_PO2+mensajes.MENU_PO3+mensajes.MENU_PO4+mensajes.MENU_P05+mensajes.NUM_OPCION);
				menu_principal(sc.nextInt(), sc);
			   break;
		  default:
			   System.out.println("\nEl programa ha terminado con éxito.");
			   System.exit(0);
			
		}
		
	}
    
	
	public static void menu_secundario(int decision, int numPrueba, Scanner sc) throws IOException {
		
		if(decision == 1) {
			System.out.println(mensajes.RESULTADOS);
		    
			//cargarArchivo
			CargarArchivoArrayListObjeto cp = new CargarArchivoArrayListObjeto();
			//String directorioEntrada =  System.getProperty("user.dir") + File.separator + "Practica01 - src" + File.separator + "datos" + File.separator;
			String directorioEntrada =  System.getProperty("user.dir") + File.separator + "datos" + File.separator;
			
			
			
			cp.cargarArchivoArrayListObjeto(directorioEntrada + "prueba"+ numPrueba + ".txt");
		    
			//ejecutar algoritmo divide y venceras
			Algoritmo alg = new Algoritmo(cp);
			
		 
			
			//Devolver resultados
			
			ArrayList<String> datos = new ArrayList<String>();
			System.out.println("Inicio contadores...");
			double tiempo_contadores = System.currentTimeMillis();
			ArrayList<Manzana> contadores = alg.dyv_contadores(alg.almacenConsumoMes1);
			tiempo_contadores = System.currentTimeMillis() - tiempo_contadores;
			double tiempo_manometros = System.nanoTime();
			System.out.println("Inicio manometros...");
			
			ArrayList<Manzana> manometros = alg.combinar(alg.getResultado_manometros());
			manometros.addAll(alg.dyv_manometros(alg.almacenManometros));
			
			tiempo_manometros = System.nanoTime() - tiempo_manometros;
			
			
		    System.out.println("<< Las fugas (rotura grave, perdida de presión) son en: >>");
		    datos.add("<<Las fugas son en: >>\n");
		    System.out.println(manometros.toString());
		    datos.add(manometros.toString());
		    
		    System.out.println("<< Los escapes (rotura de segundo nivel, consumo excesivo) son en: >>");
		    datos.add("<<Los escapes son en: >>\n");
		    System.out.println(contadores.toString());
		    datos.add(contadores.toString());
		    
		    datos.add("::::::::: TIEMPO DE EJECUCIÓN ::::::::::\n");
		    datos.add("Tiempo de ejecución (manómetros): "+tiempo_manometros+" ns");
		    datos.add("Tiempo de ejecución (contadores): "+tiempo_contadores+" ms");
		   
		    
		    new exportarDatos(datos, numPrueba);
		    System.out.println("Tiempo de ejecución (manómetros): "+tiempo_manometros+" ns");
		    System.out.println("Tiempo de ejecución (contadores): "+tiempo_contadores+" ms");
		    System.out.println("Resultados generados. Fin del análisis.");
		    
		    datos.clear();
		    contadores.clear();
		    manometros.clear();
		    
		    
		}
		else if(decision > 1 && decision < 5) new crearArchivo(decision,numPrueba);
		
		
	}

}
