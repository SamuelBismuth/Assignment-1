package filter;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import libraries.Filter;
import libraries.InputException;
import read.SampleScan;

/**
 * This class implements @see {@link Filtering}.
 * This class filtering the data by the time : only the scan on the period of time that the user choosed will appear in the kml place.
 * @author Orel and Samuel.
 * @param <SampleScan>.
 */
public class FilteringKmlTime implements Filtering<SampleScan> {
	
	private GregorianCalendar dateBeginning;
	private GregorianCalendar dateEnd;

	/**
	 * Constructor.
	 * @param dateBeginning
	 * @param dateEnd
	 */
	public FilteringKmlTime(GregorianCalendar dateBeginning, GregorianCalendar dateEnd) {
		this.dateBeginning = dateBeginning;
		this.dateEnd = dateEnd;
	}
	
	/**
	 * This method filter by the place.
	 * @param array.
	 * @return array.
	 */
	@Override
	public ArrayList<SampleScan> filteringBy(ArrayList<SampleScan> array) throws InputException {
		Filter.removeDuplicateMac(array);
		array.removeIf(SampleScan -> SampleScan.getTime().after(dateBeginning) && SampleScan.getTime().before(dateEnd));
		if (array.size() == 0) throw new InputException("There array is empty.");
		return array;
	}

}
