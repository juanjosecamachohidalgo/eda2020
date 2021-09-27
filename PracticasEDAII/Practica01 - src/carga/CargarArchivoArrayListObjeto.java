package carga;

import java.util.ArrayList;
import java.util.Scanner;

import programa.Principal;
import programa.Principal2;

import java.io.File;
import java.io.IOException;


public class CargarArchivoArrayListObjeto {
	
	ArrayList<Manzana> almacenConsumoMes1 = new ArrayList<Manzana>();
	ArrayList<Manzana> almacenConsumoMes2 = new ArrayList<Manzana>();
	ArrayList<Manzana> almacenManometros = new ArrayList<Manzana>();
	ArrayList<Integer> mn = new ArrayList<Integer>();
	
	//Metodo que carga el archivo	
	public void cargarArchivoArrayListObjeto(String archivo) {
			
		String[] items;
		String linea;
		Scanner scan = null;
		int escribiendo = 0;
		int numPrincipales=0,numRamales=0;
		int c1=0,c2=0,c3=0;
		try {
			scan = new Scanner(new File(archivo));
		} catch (IOException e) {
			System.out.println("[ERROR: Error al cargar archivo. Probablemente no exista. Introduzca un nombre válido.]");
			try {
				Principal.main(new String[0]);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		while (scan.hasNextLine()) {
			linea = scan.nextLine();
			
			if (linea.isEmpty()) continue;
			
			
			if(linea.startsWith("%") || linea.startsWith("=")) {
				if(linea.equals("%MES 1 - CONTADORES")) escribiendo = 1;
				if(linea.equals("%MES 2 - CONTADORES")) escribiendo = 2;
				if(linea.equals("%MANOMETROS")) escribiendo = 3;
			}
				
			else {
				switch(escribiendo) {
				case 1:
					items = linea.split(",");
					for(String item : items) {
						if(item.isEmpty()) continue;
						Manzana p = new Manzana(mn.get(0),mn.get(1),c1,Double.parseDouble(item),numRamales,numPrincipales);
						almacenConsumoMes1.add(p);
						c1++;
						
						
					}
					break;
				case 2:
					items = linea.split(",");
					for(String item : items) {
						if(item.isEmpty()) continue;
						almacenConsumoMes2.add(new Manzana(mn.get(0),mn.get(1),c2,Double.parseDouble(item),numRamales,numPrincipales));
						c2++;
					}
					break;
				case 3:
					items = linea.split(",");
					for(String item : items) {
						if(item.isEmpty()) continue;
						almacenManometros.add(new Manzana(mn.get(0),mn.get(1),c3,Double.parseDouble(item),numRamales,numPrincipales));
						c3++;
					}
					break;
				default:
					String m = "",n = ""; 
					int fase = 0;
					for(char a : linea.toCharArray()) {
						if(a == ',') fase++;
						
						if(fase == 2 && Character.isDigit(a)) m+=a;
						else if(fase == 3 && Character.isDigit(a)) n+=a;
					}
					mn.add(Integer.parseInt(m));
					mn.add(Integer.parseInt(n));
					numRamales=mn.get(0)%2 == 1 ? mn.get(0)/2+1 : mn.get(0)/2;
					int numPrincipalesBarrio = mn.get(1)-2;
					if(numPrincipalesBarrio == 1) numPrincipalesBarrio = 2;
					if(numPrincipalesBarrio == 0) numPrincipalesBarrio = 1;
					numPrincipales= numPrincipalesBarrio*numRamales;
				
					
					
					
					
				}
			}

		
			
		}
		scan.close();
	}

	public ArrayList<Manzana> getAlmacenConsumoMes1() {
		return almacenConsumoMes1;
	}

	public void setAlmacenConsumoMes1(ArrayList<Manzana> almacenConsumoMes1) {
		this.almacenConsumoMes1 = almacenConsumoMes1;
	}

	public ArrayList<Manzana> getAlmacenConsumoMes2() {
		return almacenConsumoMes2;
	}

	public void setAlmacenConsumoMes2(ArrayList<Manzana> almacenConsumoMes2) {
		this.almacenConsumoMes2 = almacenConsumoMes2;
	}

	public ArrayList<Manzana> getAlmacenManometros() {
		return almacenManometros;
	}

	public void setAlmacenManometros(ArrayList<Manzana> almacenManometros) {
		this.almacenManometros = almacenManometros;
	}

	public ArrayList<Integer> getMn() {
		return mn;
	}

	public void setMn(ArrayList<Integer> mn) {
		this.mn = mn;
	}	
			
 
	
	
	

}
