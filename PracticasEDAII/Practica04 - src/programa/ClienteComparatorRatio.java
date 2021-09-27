package programa;

import java.util.Comparator;


public class ClienteComparatorRatio implements Comparator<Cliente> {
	
    @Override
    public int compare(Cliente a, Cliente b) {
    	if(a == null) return -1;
    	else if(b == null) return 1;
    	return (int) (a.getBeneficio()*a.getPeso()-b.getBeneficio()*b.getPeso());
    }

	
}
