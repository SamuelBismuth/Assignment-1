package assignment;

import java.util.ArrayList;

/**
 * This class extends @see {@link FilteringKml} and implements @see {@link Filtering}.
 * This class do not filter anything.
 * @author Orel and Samuel.
 */
public class FilteringKmlEmpty extends FilteringKml implements Filtering <Scan>{
	 
	/**
	 * @param array.
	 * @return {@link WriteKmlWithoutFilter}.
	 */
	public WriteFile filteringBy(ArrayList<Scan> array, ArrayList<Mac> arrayMac) {
		return new WriteKmlWithoutFilter(arrayMac);
	}

}
