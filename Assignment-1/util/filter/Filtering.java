package filter;

import java.util.ArrayList;

import library.InputException;
import write.WriteFile;

/**
 * This interface defines only one method : filteringBy.
 * Then, height classes implements this interface : 
 * Two abstract classes @see {@link FilteringCsv} and @see {@link FilteringKml}.
 * FilteringCsv is extended by : @see {@link FilteringCsvMac} and {@link FilteringCsvPlace}
 * FilteringKml is extended by : @see {@link FilteringKmlEmpty}, @see {@link FilteringKmlId}, @see {@link FilteringKmlPlace} and @see {@link FilteringKmlTime}.
 * @author Orel and Samuel.
 */
public interface Filtering<T, Y> {

	public WriteFile<T> filteringBy(ArrayList<Y> array) throws InputException;
	
}
