package filter;

import java.util.ArrayList;

import libraries.InputException;
import write.WriteFile;

/**
 * This interface defines only one method : filteringBy.
 * Then, fives classes implements this interface : 
 * For the kml file : @see {@link FilteringKmlEmpty}, @see {@link FilteringKmlId}, @see {@link FilteringKmlPlace} and @see {@link FilteringKmlTime}.
 * For the csv file : @see {@link FilteringCsvMac} also implements this interface.
 * 
 * The main goal of this interface is to filtering the data.
 * For an array of object "Y", the function filteringBy filter the data, then, return one class which implements {@link WriteFile}, with the {@link GenericDeclaration} "T".
 * 
 * @author Orel and Samuel. 
 * @param <T>.
 */
public interface Filtering<T> {

	public ArrayList<T> filteringBy(ArrayList<T> array) throws InputException;
	
}
