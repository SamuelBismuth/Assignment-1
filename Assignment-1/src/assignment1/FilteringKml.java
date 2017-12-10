package assignment1;

import java.util.ArrayList;

/**
 * This interface defines only one method : filteringBy.
 * Then, three classes implements this interface : @see {@link FilteringKmlId}, @see {@link FilteringKmlTime}, @see {@link FilteringKmlPlace} @see {@link FilteringKmlEmpty}.
 * @author Orel and Samuel
 */
public abstract class FilteringKml extends Date implements Filtering {

	public abstract WriteFile filteringBy(ArrayList<?> array) throws InputException;

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
