package programa;

public class mochila {
	private int n; //numero de elementos
	public mochila(int n) {
		this.n = n;
	
	}

    //Algoritmo de backtracking iterativo con poda, con toque de estilo branch and bound.
	/*
	 * Este algoritmo recibe por parámetro el listado de clientes (manzanas) de entrada, la capacidad máxima de la 
	 * mochila (capacidad), un array de enteros (X) representando a la mochila en su estado final, una variable
	 * entera para almacenar el peso final, y otra para el beneficio final. 
	 */
	public void Mochila(Cliente[] clientes, int capacidad, int[] X, int peso_final, int beneficio_final) {
		//Definimos el peso actual y el beneficio actual y lo inicializamos a 0. 
		int pesoActual=0;
		int beneficioActual=0;
		//Declaramos el array de enteros de la solucion actual, a tamaño n+1.
		int[] solucion = new int[n+1];
		//Inicializamos el array de solucion a -1. 
		for(int i=0;i<solucion.length;i++) {
			solucion[i]=-1;
		}
		//inicializamos la variable k que representa la etapa. 
		int k=0;
		//inicializamos el beneficio final a -1.
		beneficio_final=-1;
		
		//Desde i hasta n*n-1, número de veces a ejecutar el bucle
		for(int i=0;i<n*n-1;i++) {
			//Si k es menor que n, es decir, si la etapa es menor del número de clientes
			if(k<n) {
				    //mientras el peso actual mas el peso del cliente que posiblemente añadamos no supere la capacidad
					while(pesoActual+clientes[k].getPeso()<=capacidad) {
						//actualizamos el peso actual y el beneficio actual , sumando los del cliente a añadir.
						pesoActual=(int) (pesoActual+clientes[k].getPeso());
						beneficioActual= (int) (beneficioActual+clientes[k].getBeneficio());
						//esta solucion es elegida, y se sitúa con valor 1. Además se incrementa la etapa k. 
						solucion[k] = 1;
						k++;
						//en caso de que la etapa k sea superior o igual al número de clientes, salimos del bucle. 
						if(k>=n) break;
					}
			}
			//si la etapa es mayor de n-1, es decir, está en un nodo hoja
			if(k>n-1) {
				//El beneficio final y el peso final se les asigna los actuales
				beneficio_final = beneficioActual;
				peso_final = pesoActual;
				//Se reduce k a n-1, su padre.
				k = n-1;
				//Desde 0 hasta el tamaño de la solucion
				for(int j=0;j<solucion.length-1;j++) {
					    //Se añade el valor de la solucion actual (si se ha escogido o no, según 0 o 1) al array X. 
						X[j] = solucion[j];
				}
			//En caso contrario, se le asigna 0, a la solucion actual en el indice actual. No se coge ese objeto
			}else {
				solucion[k]=0;
			}
			//Mientras la cota sea menor o igual al beneficio final, es decir, no lo supere
			while(Cota(clientes,beneficioActual,pesoActual,k,capacidad)<=beneficio_final) {
				//mientras la etapa sea distinto de 0 y la solución en el índice actual distinto de 1, se decrementa k. 
				while(k!=0 && solucion[k]!=1) {
					k--;
				}
				//Si la etapa es 0, salimos del bucle, porque estamos en el nodo raiz. 
				if(k==0) break;
				//Asignamos al indice actual de la solucion un 0, como que no lo cogemos. 
				solucion[k]=0;
				// de esta forma, reducimos el peso actual y el beneficio actual por el cliente añadido. 
				pesoActual=(int) (pesoActual-clientes[k].getPeso());
				beneficioActual=(int) (beneficioActual-clientes[k].getBeneficio());
			}
			//incrementamos la etapa k. 
			k++;
		
	
		}
	}


	private int Cota(Cliente[] e, int b, int p, int k, int capacidad) {
		int beneficioActual=b;
		int pesoActual=p;
		for(int i=k;i<=n;i++) {
				pesoActual=(int) (pesoActual+e[i].getPeso());
				if(pesoActual<capacidad) beneficioActual=(int) (beneficioActual+e[i].getBeneficio());
				else return (int) (beneficioActual+(1-(pesoActual-capacidad)/e[i].getPeso())*(e[i].getBeneficio()));
		}
		return beneficioActual;
	}

}
