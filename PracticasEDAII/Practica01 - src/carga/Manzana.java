package carga;

public class Manzana {
	
	private int avenida;
	private int calle;
	private double valor;
	private boolean esRamal;
	private boolean esPrincipal;
	private int posicion;



	//m=numero de filas de manzanas de una ciudad
	//n=numero de columnas de manzanas de una ciudad
	//posicion en la lista de datos de la manzana
	public Manzana(int m, int n, int posicion, double valor, int numRamales, int numPrincipales) {
		this.valor=valor;
		this.posicion=posicion;
		esRamal=false;
		esPrincipal=false;
	
		
		//Si es general
		if(posicion == 0) {
			this.calle = n;
			this.avenida = m; 
		}
		
		//Si es ramal
		if(posicion >= 1 && posicion < numRamales+1) {
			this.esRamal=true;
			this.calle = n-1;
			this.avenida = m+1-2*posicion;
		}
		
		//Si es principal
		if(posicion >= numRamales + 1 && posicion < numRamales+1+numPrincipales) {
		    this.esPrincipal=true;
			int numPrincipalesBarrio = numPrincipales / numRamales;
			int numPrincipal = posicion - numRamales;
			int barrio=0;
			for(int i=0;i<numPrincipal;i+=numPrincipalesBarrio) {
					barrio++;
				
			}
			this.avenida= (m+1)-(2*barrio);
			int principalesBarriosAnteriores=(barrio-1)*numPrincipalesBarrio;
			int numPrincipalBarrio=numPrincipal-principalesBarriosAnteriores;
			
			this.calle = n-numPrincipalBarrio;
			
		}
		
		//Si es manzana
		if(posicion >= numRamales+numPrincipales+1) {
			int numBarrio = (posicion - numPrincipales - numRamales)/(2*n);
			int tamañoCiudad = (m*n-1)+numRamales+numPrincipales;
			int elementosHastaFinal=0;
			
			if(m%2 == 1) elementosHastaFinal = tamañoCiudad-n;
			else if(m%2 == 0) elementosHastaFinal =  tamañoCiudad-2*n;
				
			//Si es primer barrio
			if(posicion<numRamales+numPrincipales+n*2) {
				
					//Si es par
					if((posicion-numRamales-numPrincipales)%2 == 0 || (posicion-numRamales-numPrincipales) == 1) this.avenida=m-2;
					//Si es impar
					else this.avenida=m-1;	
					
					//Calculo de calle
					this.calle = n - ((posicion-numPrincipales-numRamales)/2);
					this.calle -=1;
				
			}
			//Si es barrio intermedio
			else if(posicion>=numRamales+numPrincipales+n*2 && posicion <= elementosHastaFinal) {
				//Si es par
				if((posicion-numRamales-numPrincipales)%2 == 0) this.avenida=m-2*numBarrio-2;
				//Si es impar
				else this.avenida=m-2*numBarrio-1;	
				
				//Calculo de calle
				this.calle = n - ((posicion-numRamales-numPrincipales)-((numBarrio-1)*2*n)-(2*n-1))/2;
				this.calle -=1;
			}
			//Si es barrio final
			else {
				
				//Si tiene una columna
				if(m%2 == 1) {
					this.avenida=0;
					
					//Calculo de calle
					this.calle = n - (posicion- ((((numRamales-1)*2*n)-1)+numRamales+numPrincipales));
					
				}
				//Si tiene dos columnas
				else if(m%2 == 0) {
					
					//Si es par
					if((posicion-numRamales-numPrincipales)%2 == 0) this.avenida=0;
					//Si es impar
					else this.avenida=1;	
					
					//Calculo de calle
					this.calle = (tamañoCiudad - posicion)/2;
					
				}
			}
		}
	}


	public int getPosicion() {
		return posicion;
	}


	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}


	public boolean isEsRamal() {
		return esRamal;
	}


	public void setEsRamal(boolean esRamal) {
		this.esRamal = esRamal;
	}


	public boolean isEsPrincipal() {
		return esPrincipal;
	}


	public void setEsPrincipal(boolean esPrincipal) {
		this.esPrincipal = esPrincipal;
	}


	public int getAvenida() {
		return avenida;
	}

	public void setAvenida(int avenida) {
		this.avenida = avenida;
	}

	public int getCalle() {
		return calle;
	}

	public void setCalle(int calle) {
		this.calle = calle;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manzana other = (Manzana) obj;
		if (avenida != other.avenida)
			return false;
		if (calle != other.calle)
			return false;
		if (esPrincipal != other.esPrincipal)
			return false;
		if (esRamal != other.esRamal)
			return false;
		if (posicion != other.posicion)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}


	@Override
	public String toString() {
		if(this.isEsPrincipal()==true) return "Manzana [avenida=" + avenida + ", calle=" + calle + ", valor=" + valor + ", Principal (linea suministro)]\n";
		if(this.isEsRamal()==true) return "Manzana [avenida=" + avenida + ", calle=" + calle + ", valor=" + valor + ", Ramal (linea principal)]\n";
		else return "Manzana [avenida=" + avenida + ", calle=" + calle + ", valor=" + valor + ", posicion="+ posicion + ", Contador final]\n";
		
	}


	
	

}
