package filter;

import java.util.ArrayList;

import assignment.Date;
import assignment.InputException;
import assignment.Mac;
import assignment.Scan;
import write.WriteFile;

/**
 * This class extends @see {@link Date} and implements @see {@link Filtering}.
 * This abstract class is extended by : @see {@link FilteringKmlEmpty}, @see {@link FilteringKmlId}, @see {@link FilteringKmlPlace} and @see {@link FilteringKmlTime}.
 * @author Orel and Samuel.
 */
public abstract class FilteringKml implements Filtering<Scan> {

	public abstract WriteFile<Scan> filteringBy(ArrayList<Scan> array, ArrayList<Mac> arrayMac) throws InputException;

	/**
	 * @param latitude.
	 * @return true if the data is good.
	 * @return if the data is not good.
	 */
	protected boolean checkLatitude(double latitude) {
		if (latitude < -90 || latitude > 90) return false;
		return true;

	}

	/**
	 * @param longitude.
	 * @return true if the data is good.
	 * @return if the data is not good.
	 */
	protected boolean checkLongitude(double longitude) {
		if (longitude < 0 || longitude > 360) return false;
		return true;
	}

}
