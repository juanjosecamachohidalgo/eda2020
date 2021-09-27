package programa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import carga.CargarArchivoArrayListObjeto;
import carga.Manzana;
import generador.crearArchivo;
import generador.Parametros;

public class Principal2 {

	public static void main(String[] args) throws IOException {
		
		Parametros.esPracticaDos=true;
		//Bienvenida
		System.out.println(mensajes2.BIENVENIDA+mensajes2.AUTORES_1+mensajes2.AUTORES_2+mensajes2.AUTORES_3+mensajes2.BARRA+mensajes2.PRACTICA_1);
		//Scanner
		Scanner sc = new Scanner(System.in);
		//Menú principal
		System.out.println(mensajes2.MENU_P+mensajes2.MENU_PO1+mensajes2.MENU_PO2+mensajes2.MENU_PO3+mensajes2.MENU_PO4+mensajes2.MENU_P05+mensajes2.NUM_OPCION);
		menu_principal(sc.nextInt(), sc);
		
        
	}
	
	public static void menu_principal(int decision, Scanner sc) throws IOException {
		
		switch(decision) {
		  case 1:
			   System.out.println(mensajes2.MENU_P2+mensajes2.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
			   System.out.println(mensajes2.MENU_P+mensajes2.MENU_PO1+mensajes2.MENU_PO2+mensajes2.MENU_PO3+mensajes2.MENU_PO4+mensajes2.MENU_P05+mensajes2.NUM_OPCION);
				menu_principal(sc.nextInt(), sc);
			   break;
		  case 2:
			   System.out.println(mensajes2.MENU_P3+mensajes2.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
			   System.out.println(mensajes2.MENU_P+mensajes2.MENU_PO1+mensajes2.MENU_PO2+mensajes2.MENU_PO3+mensajes2.MENU_PO4+mensajes2.MENU_P05+mensajes2.NUM_OPCION);
				menu_principal(sc.nextInt(), sc);
			   break;
		  case 3:
			   System.out.println(mensajes2.MENU_P3+mensajes2.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
			   System.out.println(mensajes2.MENU_P+mensajes2.MENU_PO1+mensajes2.MENU_PO2+mensajes2.MENU_PO3+mensajes2.MENU_PO4+mensajes2.MENU_P05+mensajes2.NUM_OPCION);
				menu_principal(sc.nextInt(), sc);
			   break;
		  case 4:
			   System.out.println(mensajes2.MENU_P3+mensajes2.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
			   System.out.println(mensajes2.MENU_P+mensajes2.MENU_PO1+mensajes2.MENU_PO2+mensajes2.MENU_PO3+mensajes2.MENU_PO4+mensajes2.MENU_P05+mensajes2.NUM_OPCION);
				menu_principal(sc.nextInt(), sc);
			   break;
		  default:
			  System.out.println("\nEl programa ha terminado con éxito.");
			   System.exit(0);
			
		}
		
	}
	
	public static void menu_secundario(int decision, int numPrueba, Scanner sc) throws IOException {
		
		if(decision == 1) {
			System.out.println(mensajes2.RESULTADOS);
		    
			//cargarArchivo
			CargarArchivoArrayListObjeto cp = new CargarArchivoArrayListObjeto();
			//String directorioEntrada =  System.getProperty("user.dir") + File.separator + "Practica01 - src" + File.separator + "datos" + File.separator;
			String directorioEntrada =  System.getProperty("user.dir") + File.separator + "datos" + File.separator;
			
			cp.cargarArchivoArrayListObjeto(directorioEntrada + "prueba"+ numPrueba + ".txt");
		    
			//ejecutar algoritmo greedy
			Greedy alg = new Greedy(cp);
			
			ArrayList<String> datos = new ArrayList<String>();
			double tiempo_contadores = System.nanoTime();
			ArrayList<Manzana> contadores = alg.greedy_c(cp.getAlmacenConsumoMes1());
			tiempo_contadores = System.nanoTime() - tiempo_contadores;
			ArrayList<Manzana> contadores_finales = alg.greedy_c_manzana(cp.getAlmacenConsumoMes1());
			double tiempo_manometros = System.nanoTime();
			ArrayList<Manzana> manometros = alg.greedy_m(cp.getAlmacenManometros());
			tiempo_manometros = System.nanoTime() - tiempo_manometros;
			//Devolver resultados
			
		    System.out.println("<< Las fugas son en: >>");
		    System.out.println(manometros.toString());
		    
		    System.out.println("<< Los escapes son en: >>");
		    System.out.println(contadores.toString());
		    
		    System.out.println("<< Los escapes en contadores finales son en: >>");
		    System.out.println(contadores_finales.toString());
		    
		    System.out.println("Tiempo en nanosegundos de los escapes: "+tiempo_manometros);
		    System.out.println("Tiempo en nanosegundos del exceso de consumo: "+tiempo_contadores);
		    
		    datos.add("Tiempo en nanosegundos de los escapes: "+tiempo_manometros);
		    datos.add("Tiempo en nanosegundos del exceso de consumo: "+tiempo_contadores);
		    
		    datos.add("<< Las fugas son en: >>");
		    datos.add(manometros.toString());
		    
		    datos.add("<< Los escapes son en: >>");
		    datos.add(contadores.toString());
		    
		    datos.add("<< Los escapes en contadores finales son en: >>");
		    datos.add(contadores_finales.toString());
		    
		   
		    datos.add("\n:::::: LISTA DE SEGMENTOS DE ROTURA :::::::::\n");
			   
		    datos.add(">> ROTURA GRAVE (FUGA) <<\n");
		    datos.addAll(alg.postprocesamiento(manometros, 0));
		    
		    datos.add(">> ROTURA DE SEGUNDO NIVEL (CONSUMO) <<\n");
		    datos.addAll(alg.postprocesamiento(contadores, 0));
		    
		    datos.add(">> INFORME DE ROTURA DE SEGUNDO NIVEL (CONSUMO) EN CONTADORES FINALES <<\n");
		    datos.addAll(alg.crearListado(contadores_finales));
		    
		    new exportarDatos2(datos, numPrueba);
		    System.out.println("Resultados generados. Fin del análisis.");
		    
		    datos.clear();
		    contadores.clear();
		    manometros.clear();
		    contadores_finales.clear();
		    
		    
		    
		}
		else if(decision > 1 && decision < 5) new crearArchivo(decision,numPrueba);
		
		
	}

}
