package programa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;



public class Principal4 {
	
public static void main(String[] args) throws IOException {
		
	
		//Bienvenida
		System.out.println(Mensajes4.BIENVENIDA+Mensajes4.AUTORES_1+Mensajes4.AUTORES_2+Mensajes4.AUTORES_3+Mensajes4.BARRA+Mensajes4.PRACTICA_1);
		//Scanner
		Scanner sc = new Scanner(System.in);
		//Menú principal
		System.out.println(Mensajes4.MENU_P+Mensajes4.MENU_PO1+Mensajes4.MENU_PO3+Mensajes4.MENU_PO2+Mensajes4.MENU_P04+Mensajes4.NUM_OPCION);
		menu_principal(sc.nextInt(), sc);
		
        
	}
	
	public static void menu_principal(int decision, Scanner sc) throws IOException {
		
		switch(decision) {
		  case 1:
			   System.out.println(Mensajes4.MENU_P2+Mensajes4.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
			   System.out.println(Mensajes4.MENU_P+Mensajes4.MENU_PO1+Mensajes4.MENU_PO3+Mensajes4.MENU_PO2+Mensajes4.MENU_P04+Mensajes4.NUM_OPCION);
				menu_principal(sc.nextInt(), sc);
			   break;
		  case 2:
			   System.out.println(Mensajes4.MENU_P3+Mensajes4.NUM_PRUEBA);
			   menu_secundario(decision,sc.nextInt(), sc);
			   System.out.println(Mensajes4.MENU_P+Mensajes4.MENU_PO1+Mensajes4.MENU_PO3+Mensajes4.MENU_PO2+Mensajes4.MENU_P04+Mensajes4.NUM_OPCION);
				menu_principal(sc.nextInt(), sc);
			   break;
		  case 3:
			   System.out.println(Mensajes4.MENU_P2+Mensajes4.NUM_PRUEBA +"- Listado B");
			   menu_secundario(decision,sc.nextInt(), sc);
			   System.out.println(Mensajes4.MENU_P+Mensajes4.MENU_PO1+Mensajes4.MENU_PO3+Mensajes4.MENU_PO2+Mensajes4.MENU_P04+Mensajes4.NUM_OPCION);
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
			System.out.println(Mensajes4.RESULTADOS);
		    
			//cargarArchivo
			System.out.println(Mensajes4.RESULTADOS);
		    
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
			
			
			//ejecutar algoritmo dinamica caso a
			
			
			
			Cliente[] client = cp.getAlmacenDatos().toArray(new Cliente[cp.getAlmacenDatos().size()]);
			int [] solucion = new int[client.length+1];
			int [] mochila_final = new int[client.length+1];
			for(int i=0;i<solucion.length-1;i++) {
				solucion[i] = -1;
				mochila_final[i] = -1;
			}
			Solution optima = new Solution(wtt, client.length-1,0);
			mochila ba = new mochila(client.length-1);
			Arrays.sort(client, new ClienteComparatorRatio());
			
			ArrayList<String> datos = new ArrayList<String>();
			
			datos.add("______________LISTA A_______________________");
			double tiempo_a = System.currentTimeMillis();
			Runtime rt = Runtime.getRuntime();
		    long usedMB = (rt.totalMemory() - rt.freeMemory());
		    
		    ba.Mochila(client, wtt, mochila_final, 0, 0);
		    
		
		    
			Runtime rt2 = Runtime.getRuntime();
		    long usedMB2 = (rt2.totalMemory() - rt2.freeMemory());
			tiempo_a = System.currentTimeMillis() - tiempo_a;
	
			for(int z=0;z<mochila_final.length-1;z++) {
				if(mochila_final[z] == 1) optima.addCliente(client[z]);
			}

			if(optima.comprobarCondicionesA()) datos.add("La solución cumple las condiciones");
			else datos.add("La solución no cumple las condiciones");
			
			datos.add(optima.toString());
			datos.add("Requisito máximo: "+wtt+" horas.");
			datos.add("\n-->Peso (WTT): "+optima.getPeso()+" horas, Beneficio (dinero ingresado - maximizado): "+optima.getBeneficio()+" dólares , tiempo algoritmo: "+tiempo_a+" ms");
			datos.add("Memoria usada: "+ (usedMB2-usedMB) + "Bytes");
			datos.add("\n");
			
			
			
			//Devolver resultados
			
		    new exportarDatos4(datos, numPrueba,"A");
		    System.out.println("Resultados generados. Fin del análisis.");
			
		
		    
		    
		    
		} 
		else if(decision==2) new CrearArchivo(decision,numPrueba);
		//caso b
		else if(decision == 3) {
			System.out.println(Mensajes4.RESULTADOS);
		    
			//cargarArchivo
			System.out.println(Mensajes4.RESULTADOS);
		    
			//cargarArchivo
			CargarArchivoArrayListObjeto cp = new CargarArchivoArrayListObjeto();
			//String directorioEntrada =  System.getProperty("user.dir") + File.separator + "Practica01 - src" + File.separator + "datos" + File.separator;
			String directorioEntrada =  System.getProperty("user.dir") + File.separator + "datos" + File.separator;
			
			cp.cargarArchivoArrayListObjeto(directorioEntrada + "prueba"+ numPrueba + ".txt");
			
			//mostrar datos*opcional
			System.out.println("Datos leídos:\n");
			System.out.println(cp.getAlmacenDatos().toString());
		    
			System.out.println("Introduzca un mínimo de beneficio para el listado B: ");
			int MI = sc.nextInt();
			
			
			
			Cliente[] clientes = new Cliente[cp.getAlmacenDatos().size()];
			for(int i=0;i<clientes.length;i++) {
				clientes[i] = cp.getAlmacenDatos().get(i);
			}
			
			
			ArrayList<String> datos = new ArrayList<String>();
			
			
			datos.add("_______________LISTA B______________________");
			//ejecutar algoritmo dinamica caso b
			Solution base2 = new Solution(MI, clientes.length,1);
			Solution optima2 = new Solution(MI, clientes.length,1);
			optima2.setPeso(Integer.MAX_VALUE);
			
			double tiempo_b = System.currentTimeMillis();
			Runtime rt3 = Runtime.getRuntime();
		    long usedMB3 = (rt3.totalMemory() - rt3.freeMemory());
		    Backtracking.llenarMochilaB(base2,clientes,false,optima2);
			Runtime rt4 = Runtime.getRuntime();
		    long usedMB4 = (rt4.totalMemory() - rt4.freeMemory());
			tiempo_b = System.currentTimeMillis() - tiempo_b;
			
			if(optima2.comprobarCondicionesB()) datos.add("La solución cumple las condiciones");
			else datos.add("La solución no cumple las condiciones");
			
			datos.add(optima2.toString());
			datos.add("Requisito mínimo: "+MI+" dólares.");
			datos.add("\n-->Peso (MI): "+optima2.getBeneficio()+" dólares , Beneficio (tiempo - minimizado): "+optima2.getPeso()+" horas , tiempo algoritmo: "+tiempo_b+" ms");
			datos.add("Memoria usada: "+ (usedMB4-usedMB3) + "Bytes");
			datos.add("_________________________________________");
			
			
			
			
			//Devolver resultados
			
		    new exportarDatos4(datos, numPrueba,"B");
		    System.out.println("Resultados generados. Fin del análisis.");
			
			
		}
		
		
	}

}
