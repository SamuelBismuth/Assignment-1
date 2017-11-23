package assignment1;

import java.util.ArrayList;

/**
 * This class interface define only one method : filteringBy.
 * Then, three classes implements this interface : @see {@link FilteringId}, @see {@link FilteringTime}, @see {@link FilteringPlace}.
 * @author Orel and Samuel
 */
public interface Filtering {

	public void filteringBy(ArrayList<Wifi> array) throws InputException;
	
}
