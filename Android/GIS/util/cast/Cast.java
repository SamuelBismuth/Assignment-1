package cast;

import java.util.ArrayList;

/**
 * This interface defines only one method : cast.
 * Then, five classes extends this abstract class : 
 * One abstract Class : @see {@link CastFromCsvFile}.
 * Which is extends by two classes: @see {@link CastFromCsvFileToMac}, @see {@link CastFromCsvFileToSampleScan}.
 * And also @see {@link CastFromMacToLineAlgo1}, and @see {@link CastFromSampleScanToCsvFile}.
 *  
 * The main goal of interface is to cast an {@link ArrayList} of an object to an another ArrayList of another object.
 * 
 * @author Orel and Samuel
 * @param T.
 * @param Y.
 */
public interface Cast<T, Y> {

	public ArrayList<Y> cast(ArrayList<T> array);
	
}
