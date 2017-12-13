package filter;

import java.util.ArrayList;

import assignment.Mac;
import assignment.Scan;
import write.WriteFile;
import write.WriteKmlWithoutFilter;

/**
 * This class extends @see {@link FilteringKml} and implements @see {@link Filtering}.
 * This class do not filter anything.
 * @author Orel and Samuel.
 */
public class FilteringKmlEmpty extends FilteringKml implements Filtering<Scan> {
	 
	/**
	 * @param array.
	 * @return {@link WriteKmlWithoutFilter}.
	 */
	public WriteFile<Scan> filteringBy(ArrayList<Scan> array, ArrayList<Mac> arrayMac) {
		return new WriteKmlWithoutFilter(arrayMac);
	}

}
