package filter;

import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import libraries.InputException;
import objects.SampleScan;

/**
 * This class extends @see {@link Filtering}.
 * This class filtering the data by the place : only the scan in the area that the user choosed will appear in the kml place.
 * @author Orel and Samuel.
 * @param <SampleScan>.
 */
public class FilteringKmlPlace extends Filtering <SampleScan> {

	private EarthCoordinate coordinate;
	private double radius;

	/**
	 * Constructor.
	 * @param coordinate
	 * @param radius
	 */
	public FilteringKmlPlace(EarthCoordinate coordinate, double radius) {
		this.coordinate = coordinate;
		this.radius = radius;
	}

	/**
	 * This method filter by the place.
	 * @param array.
	 * @return array.
	 */
	@Override
	public ArrayList<SampleScan> filteringBy(ArrayList<SampleScan> array) throws InputException {
		removeDuplicateMac(array);
		array.removeIf(SampleScan -> SampleScan.getPointLocation().distanceTo(coordinate) > radius);
		if (array.size() == 0) throw new InputException("There array is empty.");
		return array;
	}
	
}