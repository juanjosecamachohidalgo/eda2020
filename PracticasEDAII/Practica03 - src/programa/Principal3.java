package programa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class Principal3 {
	
public static void main(String[] args) throws IOException {
		
	
		//Bienvenida
		System.out.println(Mensajes3.BIENVENIDA+Mensajes3.AUTORES_1+Mensajes3.AUTORES_2+Mensajes3.AUTORES_3+Mensajes3.BARRA+Mensajes3.PRACTICA_1);
		//Scanner
		Scanner sc = new Scanner(System.in);
		//Menú principal
		System.out.println(Mensajes3.MENU_P+Mensajes3.MENU_PO1+Mensajes3.MENU_PO3+Mensajes3.MENU_P04+Mensajes3.NUM_OPCION);
		menu_principal(sc.nextInt(), sc);
		
        
	}
	
	public static void menu_principal(int decision, Scanner sc) throws IOException {
		
		switch(decision) {
		  case 1:
			   System.out.println(Mensajes3.MENU_P2+Mensajes3.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
			   System.out.println(Mensajes3.MENU_P+Mensajes3.MENU_PO1+Mensajes3.MENU_PO3+Mensajes3.MENU_P04+Mensajes3.NUM_OPCION);
				menu_principal(sc.nextInt(), sc);
			   break;
		  case 2:
			   System.out.println(Mensajes3.MENU_P3+Mensajes3.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
			   System.out.println(Mensajes3.MENU_P+Mensajes3.MENU_PO1+Mensajes3.MENU_PO3+Mensajes3.MENU_P04+Mensajes3.NUM_OPCION);
				menu_principal(sc.nextInt(), sc);
			   break;
		  default:
			  System.out.println("\nEl programa ha terminado con éxito.");
			   System.exit(0);
			
		}
		
	}
	
	public static void menu_secundario(int decision, int numPrueba, Scanner sc) throws IOException {
		
		//caso a
		if(decision == 1) {
			System.out.println(Mensajes3.RESULTADOS);
		    
			//cargarArchivo
			System.out.println(Mensajes3.RESULTADOS);
		    
			//cargarArchivo
			CargarArchivoArrayListObjeto cp = new CargarArchivoArrayListObjeto();
			//String directorioEntrada =  System.getProperty("user.dir") + File.separator + "Practica01 - src" + File.separator + "datos" + File.separator;
			String directorioEntrada =  System.getProperty("user.dir") + File.separator + "datos" + File.separator;
			
			cp.cargarArchivoArrayListObjeto(directorioEntrada + "prueba"+ numPrueba + ".txt");
			
			//mostrar datos*opcional
			System.out.println("Datos leídos:\n");
			System.out.println(cp.getAlmacenDatos().toString());
		    
			
			   
			System.out.println("Introduzca un límite de horas para el tiempo del listado A: ");
			int wtt=sc.nextInt();
			System.out.println("Introduzca un mínimo de beneficio para el listado B: ");
			int MI = sc.nextInt();
			
			//ejecutar algoritmo dinamica caso a
			Dinamica d = new Dinamica(cp.getAlmacenDatos(), wtt, 0, MI);
			
			ArrayList<String> datos = new ArrayList<String>();
			datos.add("______________LISTA A_______________________");
			double tiempo_a = System.currentTimeMillis();
			Runtime rt = Runtime.getRuntime();
		    long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024;
			ArrayList<Cliente> clientes = d.solucion(0).getClientes();
			Runtime rt2 = Runtime.getRuntime();
		    long usedMB2 = (rt2.totalMemory() - rt2.freeMemory()) / 1024;
			tiempo_a = System.currentTimeMillis() - tiempo_a;
			
			double peso=0,beneficio=0;
			for(Cliente c : clientes) {
				datos.add(c.toString());
				peso+=c.getPeso();
				beneficio+=c.getBeneficio();
			}
			
			datos.add("Requisito máximo: "+wtt+" horas.");
			datos.add("\n-->Peso (WTT): "+peso+", Beneficio (dinero ingresado - maximizado): "+beneficio+" dólares , tiempo algoritmo: "+tiempo_a+" ms");
			datos.add("Memoria usada: "+ (usedMB2-usedMB) + "KB");
			datos.add("\n");
			datos.add("_______________LISTA B______________________");
			//ejecutar algoritmo dinamica caso b
			Dinamica d2 = new Dinamica(cp.getAlmacenDatos(), wtt, 1, MI);
			
			double tiempo_b = System.currentTimeMillis();
			Runtime rt3 = Runtime.getRuntime();
		    long usedMB3 = (rt3.totalMemory() - rt3.freeMemory()) / 1024;
			ArrayList<Cliente> clientes2 = d2.solucion(1).getClientes();
			Runtime rt4 = Runtime.getRuntime();
		    long usedMB4 = (rt4.totalMemory() - rt4.freeMemory()) / 1024;
			tiempo_b = System.currentTimeMillis() - tiempo_b;
			Collections.sort(clientes2,new ClienteComparatorRatio());
			double peso2=0,beneficio2=0;
			for(Cliente c : clientes2) {
				datos.add(c.toString());
				peso2+=c.getPeso();
				beneficio2+=c.getBeneficio();
			}
			datos.add("Requisito mínimo: "+MI+" dólares.");
			datos.add("\n-->Peso (MI): "+peso2+", Beneficio (tiempo de trabajo - minimizado): "+beneficio2+", tiempo algoritmo: "+tiempo_b+" ms");
			datos.add("Memoria usada: "+ (usedMB4-usedMB3) + "KB");
			datos.add("_________________________________________");
			
			
			
			
			//Devolver resultados
			
		    new exportarDatos3(datos, numPrueba,"A");
		    System.out.println("Resultados generados. Fin del análisis.");
			
		
		    
		    
		    
		} 
		else if(decision==2) new CrearArchivo(decision,numPrueba);
		
		
	}

}
