package programa;



public class Backtracking {
	/*
	 * Este método es un método recursivo de backtracking de mochila inversa.
	 * Recibe por parámetros dos objetos tipo Solution, que conforman una solución (base y óptima), uno 
	 * para almacenar la solución actual y otro para la solución óptima, un array de clientes (manzanas),
	 * con objetos de tipo Cliente y un booleano llena, para saber cuando la mochila está llena. 
	 */
	public static void llenarMochilaB(Solution base, Cliente[] clientes, boolean llena, Solution optima) {
		  
	      //si esta llena la mochila, es decir, si llena es true. 
	      if (llena) {
	    	
	          //compruebo si tiene menos beneficio que otra (minimizamos el beneficio)
	    	  //En este problema el beneficio es el peso del apartado a, por eso usamos
	    	  //getPeso
	          if (base.getPeso() < optima.getPeso()) {
	        	
	        	  //Declaramos un array de clientes, con los clientes de la solución base. 
	              Cliente[] clientesBase = base.getClientes();
	              //vaciamos la solución optima
	              optima.vaciar();
	 
	              //recorremos los clientes de la solución base, y si no es nulo, se añade a la solución óptima. 
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
	        	
	              //si no existe el cliente en la solución base, es decir, la actual. 
	              if (!base.existeCliente(clientes[i])) {
	                  //Si el peso de la mochila no se supera sumando el cliente que podríamos añadir a la solución
	            	  //(en este caso el peso es el beneficio del apartado a, de ahi el uso de getBeneficio
	                  if (base.getCapacidad() > base.getBeneficio() + clientes[i].getBeneficio()) {
	                      base.addCliente(clientes[i]); //añadimos el cliente a la solución base
	                      llenarMochilaB(base, clientes, false, optima); //llamamos al método recursivamente con la nueva solución base, la actual. 
	                      base.eliminarCliente(clientes[i]); //Despues de la llamada, eliminamos el cliente de la solución base.
	                  //En caso de que el peso de la mochila se supere
	                  } else {
	                	  //En caso de que lo acabe de superar, y quitando ese elemento esté por debajo del peso, se añade (para buscar el mínimo de MI).
	                	  if(base.getCapacidad() > base.getBeneficio() - clientes[i].getBeneficio()) base.addCliente(clientes[i]); //añadimos el cliente 
	                	  //realizamos una llamada recursiva a este metodo indicando que la mochila está llena. 
	                      llenarMochilaB(base, clientes, true, optima);
	                  }
	 
	              }
	 
	          }
	 
	      }
	 
	  }
	
	
	 



}
