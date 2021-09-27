package programa;



public class Backtracking {
	/*
	 * Este m�todo es un m�todo recursivo de backtracking de mochila inversa.
	 * Recibe por par�metros dos objetos tipo Solution, que conforman una soluci�n (base y �ptima), uno 
	 * para almacenar la soluci�n actual y otro para la soluci�n �ptima, un array de clientes (manzanas),
	 * con objetos de tipo Cliente y un booleano llena, para saber cuando la mochila est� llena. 
	 */
	public static void llenarMochilaB(Solution base, Cliente[] clientes, boolean llena, Solution optima) {
		  
	      //si esta llena la mochila, es decir, si llena es true. 
	      if (llena) {
	    	
	          //compruebo si tiene menos beneficio que otra (minimizamos el beneficio)
	    	  //En este problema el beneficio es el peso del apartado a, por eso usamos
	    	  //getPeso
	          if (base.getPeso() < optima.getPeso()) {
	        	
	        	  //Declaramos un array de clientes, con los clientes de la soluci�n base. 
	              Cliente[] clientesBase = base.getClientes();
	              //vaciamos la soluci�n optima
	              optima.vaciar();
	 
	              //recorremos los clientes de la soluci�n base, y si no es nulo, se a�ade a la soluci�n �ptima. 
	              for (Cliente c : clientesBase) {
	                  if (c != null) {
	                      optima.addCliente(c);
	                  }
	 
	              }
	 
	          }
	      
	      //En caso de la mochila no estar llena
	      } else {
	          //Recorre los clientes del array. 
	          for (int i = 0; i < clientes.length; i++) {
	        	
	              //si no existe el cliente en la soluci�n base, es decir, la actual. 
	              if (!base.existeCliente(clientes[i])) {
	                  //Si el peso de la mochila no se supera sumando el cliente que podr�amos a�adir a la soluci�n
	            	  //(en este caso el peso es el beneficio del apartado a, de ahi el uso de getBeneficio
	                  if (base.getCapacidad() > base.getBeneficio() + clientes[i].getBeneficio()) {
	                      base.addCliente(clientes[i]); //a�adimos el cliente a la soluci�n base
	                      llenarMochilaB(base, clientes, false, optima); //llamamos al m�todo recursivamente con la nueva soluci�n base, la actual. 
	                      base.eliminarCliente(clientes[i]); //Despues de la llamada, eliminamos el cliente de la soluci�n base.
	                  //En caso de que el peso de la mochila se supere
	                  } else {
	                	  //En caso de que lo acabe de superar, y quitando ese elemento est� por debajo del peso, se a�ade (para buscar el m�nimo de MI).
	                	  if(base.getCapacidad() > base.getBeneficio() - clientes[i].getBeneficio()) base.addCliente(clientes[i]); //a�adimos el cliente 
	                	  //realizamos una llamada recursiva a este metodo indicando que la mochila est� llena. 
	                      llenarMochilaB(base, clientes, true, optima);
	                  }
	 
	              }
	 
	          }
	 
	      }
	 
	  }
	
	
	 



}
