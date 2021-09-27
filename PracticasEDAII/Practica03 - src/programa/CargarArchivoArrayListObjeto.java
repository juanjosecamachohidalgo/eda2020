package programa;

import java.util.ArrayList;
import java.util.Scanner;

import programa.Principal;
import programa.Principal2;

import java.io.File;
import java.io.IOException;


public class CargarArchivoArrayListObjeto {
	
	ArrayList<Cliente> almacenDatos = new ArrayList<Cliente>();
	
	//Metodo que carga el archivo	
	public void cargarArchivoArrayListObjeto(String archivo) {
			
		String[] items;
		String linea;
		Scanner scan = null;
		try {
			scan = new Scanner(new File(archivo));
		} catch (IOException e) {
			System.out.println("[ERROR: Error al cargar archivo. Probablemente no exista. Introduzca un nombre válido.]");
			try {
				Principal3.main(new String[0]);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		while (scan.hasNextLine()) {
			linea = scan.nextLine();
			if (linea.isEmpty() || linea.startsWith("%")) continue;
	
			items=linea.split("'");
			Cliente m = new Cliente(items[1], Integer.parseInt(items[3]), Integer.parseInt(items[5]), Integer.parseInt(items[7]));
			almacenDatos.add(m);
			
		}
	}

	
	public ArrayList<Cliente> getAlmacenDatos() {
		return almacenDatos;
	}

	public void setAlmacenDatos(ArrayList<Cliente> almacenDatos) {
		this.almacenDatos = almacenDatos;
	}
	
	
	
	
	

}
