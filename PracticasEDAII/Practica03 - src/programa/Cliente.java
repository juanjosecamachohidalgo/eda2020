package programa;



public class Cliente{
	
	private int IP; //numero de veces que se ha incrementado sobre valores medios anteriores (entre 8 y 30 mas o menos)
	private int BC; //consumo ultimo en hectómetros
	private String direccion; //Dirección: (Calle, Avenida)
	private int OP; //x.num_vecinos+y.superficie
	private static int BA = 60; //60 minutos de valor base
	private double AT; //Tiempo de atencion en minutos
	private double DT; //tiempo dependiente del tamaño de fuga
	
	public Cliente(String direccion,int BC, int IP, int OP) {
		this.direccion=direccion;
		this.BC=BC;
		this.IP=IP;
		this.OP=OP;
		this.DT = calcularDT();
		this.AT = calcularAT();
	}
	public Cliente(String direccion, int peso, int beneficio) {
		this.direccion=direccion;
		this.AT=peso;
		this.OP=beneficio;
	}
	
	private double calcularDT() {
	   	return 5*BC+12*(IP-7);
	}
	private double calcularAT() {
		return (BA + DT);
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

	public static int getBA() {
		return BA;
	}

	public static void setBA(int bA) {
		BA = bA;
	}

	public double getAT() {
		return AT;
	}

	public void setAT(double aT) {
		AT = aT;
	}

	public double getDT() {
		return DT;
	}

	public void setDT(double dT) {
		DT = dT;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
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
		Cliente other = (Cliente) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		return "Cliente [direccion=" + direccion + ", BC=" + BC + ", IP=" + IP + ", OP=" + OP + ", AT=" + AT + "]";
	}

	public double getPeso() {
		return this.getAT();
		
	}
	public double getBeneficio() {
		return this.getOP();
		
	}

	

}
