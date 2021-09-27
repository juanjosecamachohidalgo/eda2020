package programa;

import java.util.Comparator;

public class ClienteComparatorTipoB implements Comparator<Cliente> {
    @Override
    public int compare(Cliente a, Cliente b) {
    	if(a.getOP()-b.getOP() == 0) return 0;
        return a.getOP()-b.getOP() < 0 ? 1 : -1;
    }

	
}
