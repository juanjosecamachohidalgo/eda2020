package programa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class exportarDatos4 {
	
	private String directorioEntrada;
	private ArrayList<String> datos = new ArrayList<String>();

	public exportarDatos4(ArrayList<String> datos,int numeroArchivo,String caso) throws IOException {
		
		//directorioEntrada =  System.getProperty("user.dir") + File.separator + "Practica01 - src" + File.separator + "resultados" + File.separator;
		String resultados="resultados"+caso;
		directorioEntrada =  System.getProperty("user.dir") + File.separator + resultados + File.separator;
		
		File directorio = new File (System.getProperty("user.dir") + File.separator + resultados);
		directorio.mkdir();
		File fichero = new File (directorioEntrada + "prueba" + numeroArchivo + ".txt");
		try {
			  if (fichero.createNewFile()) System.out.println("El fichero se ha creado correctamente: "+fichero);
			  else {
				  System.out.println("[ERROR: No ha podido ser creado el fichero. Fichero ya existente.]");
				  try {
						Principal4.main(new String[0]);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			  }
	    } catch (IOException ioe) {
	    	System.out.println("[ERROR: No ha podido ser creado el fichero. Fichero ya existente.]");
			  try {
					Principal4.main(new String[0]);
				} catch (IOException e1) {
					e1.printStackTrace();
					System.exit(-1);
				}
			  
		}
	
		this.datos = datos;

		escribir(numeroArchivo, fichero);
		System.out.println("Los datos han sido creados correctamente");
		
	}


	public void escribir(int numeroArchivo, File file) throws IOException {
        try {
            FileWriter escribir = new FileWriter(file, true);
            for (String a : datos) {
            	escribir.write(a+"\n");
            	
            }
            escribir.close();
        } catch (IOException e) {
        	try {
        		System.out.println("[ERROR: Error en la escritura.]");
				Principal4.main(new String[0]);
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(-1);
			}
        	
        }
    }
	

              
}
