package programa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Dinamica {
	
	
	  private Cliente[] clientes;
	  private int capacidad; //WTT
	  private int MI;
	 

	public Dinamica(ArrayList<Cliente> clientes, int capacidad, int tipo, int MI) {
		Collections.sort(clientes, new ClienteComparatorPeso()); //ordenamos por peso
	    this.clientes =  new Cliente[clientes.size()];
	    for(int i=0;i<this.clientes.length;i++) {
	    	this.clientes[i]=clientes.get(i);
	    }
	    this.capacidad = capacidad;
	    this.MI=MI;
	   
	  }
	  
	  public Solucion solucion(int tipo) {
		  System.out.println("SOLUCION: "+tipo+", capacidad: "+capacidad);
	    int tamañoClientes = clientes.length;
	    
	  
	    double[][] tabla = new double[tamañoClientes+1][capacidad+1];

	    // primera linea inicializada a 0
	    for (int i = 0; i <= capacidad; i++)
	    	tabla[0][i] = 0;
	    // iteramos en los clientes
	    for (int i = 1; i <= tamañoClientes; i++) {
	    	// iteramos en cada capacidad
	    	for (int j = 0; j <= capacidad; j++) {
	    		if (clientes[i - 1].getPeso() > j) tabla[i][j] = tabla[i-1][j];
	    		else tabla[i][j] = Math.max(tabla[i-1][j], tabla[i-1][(int) (j - clientes[i-1].getPeso())] + clientes[i-1].getBeneficio());

	    	    
	         }
	    }

	    ArrayList<Cliente> solucionEquipos = new ArrayList<Cliente>();
	    solucionEquipos = recuperarObjetosMochila(tabla,tamañoClientes, tipo);
	   
	    return verificar(tipo,solucionEquipos,tamañoClientes, tabla);
	  }
	 
	  
	  
	 

	private Solucion verificar(int tipo, ArrayList<Cliente> solucionEquipos, int tamañoEquipos,double [][] tabla) {
		 //Comprobar si cumple las condiciones la solucion
	    if(comprobarCondiciones(tipo,solucionEquipos, solucionEquipos.size())) System.out.println("La solución de la lista tipo "+tipo+" cumple las condiciones.");
	    else System.out.println("La solución de la lista tipo "+tipo+ " no cumple las condiciones.");
	    	
	    return new Solucion(solucionEquipos, tabla[tamañoEquipos][capacidad],tipo);
	 }
		
	

	//PARTE POSTPROCESAMIENTO DEL ALGORITMO: RECUPERAR OBJETOS MOCHILA
	 public ArrayList<Cliente> recuperarObjetosMochila(double [][] tabla, int tamañoClientes, int tipo){
		    double r = tabla[tamañoClientes][capacidad]; //Para ver otras soluciones habria que modificar esta variable
		    int w = capacidad;
		    if(tipo == 1) {
		    	for(int z = 0; z < capacidad ; z++) {
			    	r=tabla[tamañoClientes][z];
			    	w=z;
			    	if(r >= this.MI) break;
			    }
		    }
		    
		    ArrayList<Cliente> solucionEquipos = new ArrayList<>();

		    for (int i = tamañoClientes; i > 0  &&  r > 0; i--) {
		      if (r != tabla[i-1][w]) {
		        solucionEquipos.add(clientes[i-1]);
		        // eliminamos beneficio y peso del cliente
			     r -= clientes[i-1].getBeneficio();
			     w -= clientes[i-1].getPeso();
		      }
		    }
		    return solucionEquipos;
	  }
		 
	public boolean comprobarCondiciones(int tipo,ArrayList<Cliente> clientes, int numGrupos) {
		  if(tipo==0) return comprobarCondicionesA(clientes,numGrupos);
		  else return comprobarCondicionesB(clientes,numGrupos);
	  }
	  public boolean comprobarCondicionesA(ArrayList<Cliente> clientes, int numGrupos) { 
			int cuenta=0;
			for(Cliente c : clientes) {
				cuenta+=c.getPeso();
			}
			return cuenta<=capacidad;
	  }
	  public boolean comprobarCondicionesB(ArrayList<Cliente> clientes, int numGrupos) { 
		    if(clientes == null) return false;
			int cuenta=0;
			for(Cliente c : clientes) {
				cuenta+=c.getBeneficio();
			}
			return cuenta>=this.MI;
	  }
	  public Cliente[] getClientes() {
			return clientes;
		}

		public void setClientes(Cliente[] clientes) {
			this.clientes = clientes;
		}

	
	


}
