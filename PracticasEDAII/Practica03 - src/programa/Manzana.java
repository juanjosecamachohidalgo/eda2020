package programa;

public class Manzana {
	
	private int avenida;
	private int calle;
	private int num_vecinos;
	private int superficie;
	private static double valor_vecinos=75.0;
	private static double valor_superficie=22.5;
	private int IP; //numero de veces que se ha incrementado sobre valores medios anteriores (entre 8 y 30 mas o menos)
	private int BC; //consumo ultimo en hectómetros
	private String direccion; //Dirección: (Calle, Avenida)
	private int OP; //x.num_vecinos+y.superficie



	
	public Manzana(int avenidas, int calles) {
		obtenerDireccion(avenidas,calles);
		obtenerNumeroVecinos();
		obtenerSuperficie();
		obtenerIP();
		calcularBC();
		calcularOP();
		
	}
	private void obtenerDireccion(int avenidas,int calles) {
		this.avenida = Funciones.calcularAleatorio(1, avenidas);
		this.calle = Funciones.calcularAleatorio(1, calles);
		this.direccion = "("+this.calle+","+this.avenida+")";
	}
    private void obtenerSuperficie() { //En metros cuadrados
		this.superficie = Funciones.calcularAleatorio(200, 1000); //Entre 100 y 1000 metros cuadrados
	}
    private void obtenerNumeroVecinos() {
    	this.num_vecinos = Funciones.calcularAleatorio(1, 100); //Entre 1 y 100 vecinos
    }
    private void obtenerIP() {
		this.IP = Funciones.calcularAleatorio(8, 30);
	}
	private void calcularBC() { //En hectómetros
		this.BC = Funciones.calcularAleatorio(1, 15); //Entre 1 y 15
	}
	
	private void calcularOP() {
		this.OP = (int) (valor_vecinos*this.num_vecinos + valor_superficie*this.superficie);
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
	public int getNum_vecinos() {
		return num_vecinos;
	}
	public void setNum_vecinos(int num_vecinos) {
		this.num_vecinos = num_vecinos;
	}
	public int getSuperficie() {
		return superficie;
	}
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	public static double getValor_vecinos() {
		return valor_vecinos;
	}
	public static void setValor_vecinos(double valor_vecinos) {
		Manzana.valor_vecinos = valor_vecinos;
	}
	public static double getValor_superficie() {
		return valor_superficie;
	}
	public static void setValor_superficie(double valor_superficie) {
		Manzana.valor_superficie = valor_superficie;
	}
	public int getIP() {
		return IP;
	}
	public void setIP(int iP) {
		IP = iP;
	}
	public int getBC() {
		return BC;
	}
	public void setBC(int bC) {
		BC = bC;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getOP() {
		return OP;
	}
	public void setOP(int oP) {
		OP = oP;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + avenida;
		result = prime * result + calle;
		return result;
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
		return true;
	}
	@Override
	public String toString() {
		return "Manzana [direccion='" + direccion + "', BC='" + BC + "', IP='" + IP + "', OP='" + OP + "']";
	}
	


	
	

	
	

}
