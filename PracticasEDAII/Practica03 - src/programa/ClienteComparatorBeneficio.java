package programa;

import java.util.Comparator;


public class ClienteComparatorBeneficio implements Comparator<Cliente> {
	
    @Override
    public int compare(Cliente a, Cliente b) {
    	if(a == null) return -1;
    	else if(b == null) return 1;
    	if(a.getBeneficio()-b.getBeneficio() == 0) return (int) -(a.getPeso()-b.getPeso());
        return a.getBeneficio()-b.getBeneficio() < 0 ? 1 : -1;
    }

	
}
