package assignment;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class extends @see {@link FilteringKml} and implements @see {@link Filtering}.
 * This class filtering the data by the place : only the sacn in the area that the user choosed will appear in the kml place.
 * TODO : JUNIT on the radius.
 * @author Orel and Samuel.
 */
public class FilteringKmlPlace extends FilteringKml implements Filtering <Scan>{

	/**
	 * This method ask the user to input the place.
	 * @param array.
	 * @return {@link WriteKmlPlace}.
	 * @exception InputMismatchException : Error on the input, try again.
	 */
	@SuppressWarnings("resource")
	public WriteFile filteringBy(ArrayList<Scan> array, ArrayList<Mac> arrayMac) throws InputException {
		try { 
			System.out.println("Input an latitude please :");
			double pointLatitude = Double.parseDouble(new Scanner(System.in).nextLine());
			System.out.println("Input an longitude please :");
			double pointLongitude = Double.parseDouble(new Scanner(System.in).nextLine());
			System.out.println("Input a radius please (meters) :");
			double radius = Double.parseDouble(new Scanner(System.in).nextLine());
			if(checkLatitude(pointLatitude) && checkLongitude(pointLongitude) && radius >= 0) {
				EarthCoordinate pointLocation = new EarthCoordinate(pointLongitude, pointLatitude, 0.0); // Latitude 0.0 by default.
				return new WriteKmlPlace(pointLocation, radius, arrayMac);
			}
			else throw new InputException("There is ne such latitude/longitude/radius.");
		}
		catch (InputMismatchException ex) {
			System.out.println("Error on the input, try again.");
		}
		return filteringBy(array, arrayMac);
	}

}
