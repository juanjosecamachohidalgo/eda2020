package programa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import generador.Casos;
import generador.Parametros;

public class CrearArchivo {
	
	private String directorioEntrada;
	private ArrayList<String> datos = new ArrayList<String>();

	public CrearArchivo(int decision,int numeroArchivo) throws IOException {
		
		//directorioEntrada =  System.getProperty("user.dir") + File.separator + "Practica01 - src" + File.separator + "datos" + File.separator;
		directorioEntrada =  System.getProperty("user.dir") + File.separator + "datos" + File.separator;
		File directorio = new File (System.getProperty("user.dir") + File.separator + "datos");
		directorio.mkdir();
		File fichero = new File (directorioEntrada + "prueba" + numeroArchivo + ".txt");
		try {
			  if (fichero.createNewFile()) System.out.println("El fichero se ha creado correctamente: "+fichero);
			  else {
				  System.out.println("[ERROR: No ha podido ser creado el fichero. Fichero ya existente.]");
				  try { Principal3.main(new String[0]);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			  }
	    } catch (IOException ioe) {
	    	try {
	    		System.out.println("[ERROR: No ha podido ser creado el fichero. Fichero ya existente.]");
	    		Principal3.main(new String[0]);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//RELLENAR EL ARRAYLIST DE STRING DE DATOS CON LOS DATOS DEL CASO
        Caso c = new Caso();
        datos = c.getCaso();

		escribir(numeroArchivo, fichero);
		System.out.println("Los datos han sido creados correctamente");
		datos.clear();
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
        		if(Parametros.esPracticaDos==false) Principal.main(new String[0]);
				else Principal2.main(new String[0]);
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(-1);
			}
        	
            
        }
    }

}
