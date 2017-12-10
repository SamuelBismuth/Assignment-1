package assignment;

import java.util.ArrayList;

/**
 * This class implement @see {@link FilteringKml}.
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
