package programa;

import java.util.Comparator;


public class ClienteComparatorPeso implements Comparator<Cliente> {

    @Override
    public int compare(Cliente a, Cliente b) {
    	if(a == null) return -1;
    	else if(b == null) return 1;
    	else if(a.getPeso()-b.getPeso() == 0) return (int) -(a.getBeneficio()-b.getBeneficio());
    	else return a.getPeso()-b.getPeso() < 0 ? 1 : -1;
    }

	
}
