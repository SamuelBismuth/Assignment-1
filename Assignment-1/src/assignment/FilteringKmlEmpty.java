package assignment;

import java.util.ArrayList;

/**
 * This class extends @see {@link FilteringKml} and implements @see {@link Filtering}.
 * This class do not filter anything.
 * @author Orel and Samuel.
 */
public class FilteringKmlEmpty extends FilteringKml implements Filtering{
	 
	/**
	 * @param array.
	 * @return {@link WriteKmlWithoutFilter}.
	 */
	public WriteFile filteringBy(ArrayList<?> array) {
		return new WriteKmlWithoutFilter();
	}

}
