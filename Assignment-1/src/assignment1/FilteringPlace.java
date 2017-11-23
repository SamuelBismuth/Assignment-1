package assignment1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class filtering the data by the place : only the wifi in the area that the user choosed will appear in the kml place.
 * This class implement @see {@link Filtering}, extends @see {@link Verification} to the use of some methods.
 * @author Orel and Samuel.
 */
public class FilteringPlace extends Verification implements Filtering {

	/**
	 * Empty constructor.
	 */
	public FilteringPlace(){}
	
	/**
	 * Constructor.
	 * @param array.
	 * @exception {@link InputException}  : There is no such latitude/longitude.
	 */
	protected FilteringPlace(ArrayList<Wifi> array) {
		try {
			filteringBy(array);
		} 
		catch (InputException ex) {
			System.out.println(ex);
			new FilteringPlace(array);
		}
	}

	/**
	 * This method ask the user to input the place.
	 * @param array.
	 * @exception InputMismatchException : Error on the input, try again.
	 */
	@SuppressWarnings("resource")
	public void filteringBy(ArrayList<Wifi> array) throws InputException {
		try { 
			System.out.println("Input an latitude please :");
			double pointLatitude = new Scanner(System.in).nextDouble();
			System.out.println("Input an longitude please :");
			double pointLongitude = new Scanner(System.in).nextDouble();
			System.out.println("Input a radius please (meters) :");
			double radius = new Scanner(System.in).nextDouble();
			if(checkLatitude(pointLatitude) && checkLongitude(pointLongitude) && radius >= 0) {
				EarthCoordinate pointLocation = new EarthCoordinate(pointLongitude, pointLatitude, 0.0); // Latitude 0.0 by default.
				new WriteKmlPlace(array, pointLocation, radius);
			}
			else throw new InputException("There is ne such latitude/longitude/radius.");
		}
		catch (InputMismatchException ex) {
			System.out.println("Error on the input, try again.");
			filteringBy(array);
		}
	}

}
