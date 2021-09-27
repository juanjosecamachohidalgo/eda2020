package generador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import programa.Principal;
import programa.Principal2;

public class crearArchivo {
	
	private String directorioEntrada;
	private ArrayList<String> datos = new ArrayList<String>();

	public crearArchivo(int decision,int numeroArchivo) throws IOException {
		
		//directorioEntrada =  System.getProperty("user.dir") + File.separator + "Practica01 - src" + File.separator + "datos" + File.separator;
		directorioEntrada =  System.getProperty("user.dir") + File.separator + "datos" + File.separator;
		File directorio = new File (System.getProperty("user.dir") + File.separator + "datos");
		directorio.mkdir();
		File fichero = new File (directorioEntrada + "prueba" + numeroArchivo + ".txt");
		try {
			  if (fichero.createNewFile()) System.out.println("El fichero se ha creado correctamente: "+fichero);
			  else {
				  System.out.println("[ERROR: No ha podido ser creado el fichero. Fichero ya existente.]");
				  try {
						if(Parametros.esPracticaDos==false) Principal.main(new String[0]);
						else Principal2.main(new String[0]);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			  }
	    } catch (IOException ioe) {
	    	try {
	    		System.out.println("[ERROR: No ha podido ser creado el fichero. Fichero ya existente.]");
	    		if(Parametros.esPracticaDos==false) Principal.main(new String[0]);
				else Principal2.main(new String[0]);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		Casos data = new Casos(decision);
		datos = data.getDatos();

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
