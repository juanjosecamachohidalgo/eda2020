package programa;

import java.util.Comparator;

public class ClienteComparatorTipoA implements Comparator<Cliente> {
    @Override
    public int compare(Cliente a, Cliente b) {
    	if(a.getAT()-b.getAT() == 0) return 0;
        return a.getAT()-b.getAT() < 0 ? 1 : -1;
    }

	
}
