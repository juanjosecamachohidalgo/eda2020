package programa;

import java.util.Arrays;

public class Solution {
	
	private int capacidad;
    private Cliente[] clientes;
 
    private int peso;
    private int beneficio;
    private int tipo;
    private int nivel;
 
    public Solution(int capacidad, int numElementos, int tipo) {
        this.capacidad = capacidad;
        this.clientes = new Cliente[numElementos];
        this.beneficio = 0;
        this.peso = 0;
        this.tipo = tipo;
        this.nivel=0;
    }
 
    public void aumentarNivel() {
    	nivel++;
    }
    public int getNivel() {
    	return this.nivel;
    }
    public Cliente[] getClientes() {
        return this.clientes;
    }
 
     public int getPeso() {
       return peso;
    }
     
    public void setPeso(int peso) {
        this.peso = peso;
    }
 
    public int getBeneficio() {
        return beneficio;
    }
 
    public void setBeneficio(int beneficio) {
        this.beneficio = beneficio;
    }
 
    public int getCapacidad() {
        return capacidad;
    }
 
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
 
    
    public void addCliente(Cliente e) {
 
        for (int i = 0; i<this.clientes.length; i++) {
            if (this.clientes[i] == null) {
                this.clientes[i] = e; 
                this.beneficio+=e.getBeneficio(); 
                this.peso+=e.getPeso(); 
                break;
            }
        }
 
    }
 
    public void vaciar() {
        this.peso=0;
        this.beneficio=0;
        for (int i = 0; i<this.clientes.length; i++) {
            this.clientes[i] = null;
        }
    }
 
    public void eliminarCliente(Cliente e) {
        for (int i = 0; i<this.clientes.length; i++) {
            if (this.clientes[i].equals(e)) {
                this.clientes[i] = null; 
                this.peso-=e.getPeso();
                this.beneficio-=e.getBeneficio(); 
                break;
            }
        }
    }
     
    public boolean existeCliente(Cliente e) {
        for (int i = 0; i<this.clientes.length; i++) {
            if (this.clientes[i] != null && this.clientes[i].equals(e)) {
                return true;
            }
        }
        return false;
    }
 
    public boolean comprobarCondicionesA() { 
		int cuenta=0;
		for(Cliente c : this.clientes) {
			if(c != null) cuenta+=c.getPeso();
		}
		return cuenta<=capacidad;
  }
    public boolean comprobarCondicionesB() { 
	    if(this.clientes == null) return false;
		int cuenta=0;
		for(Cliente c : this.clientes) {
			if(c != null) cuenta+=c.getBeneficio();
		}
		return cuenta>=capacidad;
  }
  
    public String toString() {
    	if(tipo == 0) Arrays.sort(this.clientes, new ClienteComparatorPeso());
        else if(tipo == 1) Arrays.sort(this.clientes, new ClienteComparatorRatio());
    	Arrays.sort(this.clientes, new ClienteComparatorPeso());
        String cadena="";
        for (int i = 0; i<this.clientes.length; i++) {
            if (this.clientes[i] != null) {
                cadena+=clientes[i]+"\n";
            }
        }
        cadena+="Peso: " + getPeso()+"\n";
        cadena+="Beneficio: " + getBeneficio()+"\n";
        return cadena;
    }

	

}
