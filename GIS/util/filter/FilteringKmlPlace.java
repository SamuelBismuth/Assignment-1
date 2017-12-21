package filter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.boehn.kmlframework.kml.Document;

import libraries.InputException;
import libraries.KmlUtil;
import libraries.UserChoice;
import read.SampleScan;
import write.WriteFile;
import write.WriteKmlPlace;

/**
 * This class implements @see {@link Filtering}.
 * This class filtering the data by the place : only the scan in the area that the user choosed will appear in the kml place.
 * @author Orel and Samuel.
 * @param <SampleScan>.
 */
public class FilteringKmlPlace implements Filtering <SampleScan> {

	/**
	 * This method ask the user to input the place.
	 * @param array.
	 * @return {@link WriteKmlPlace}.
	 * @exception InputMismatchException : Error on the input, try again.
	 * We define our own exception if the place it's not found.
	 */
	@SuppressWarnings("resource")
	@Override
	public WriteFile<SampleScan> filteringBy(ArrayList<SampleScan> array) throws InputException {
		try { 
			System.out.println("Input an latitude please :");
			double pointLatitude = Double.parseDouble(new Scanner(System.in).nextLine());
			System.out.println("Input an longitude please :");
			double pointLongitude = Double.parseDouble(new Scanner(System.in).nextLine());
			System.out.println("Input a radius please (meters) :");
			double radius = Double.parseDouble(new Scanner(System.in).nextLine());
			if(KmlUtil.checkLatitude(pointLatitude) && KmlUtil.checkLongitude(pointLongitude) && radius >= 0) {
				EarthCoordinate pointLocation = new EarthCoordinate(pointLongitude, pointLatitude, 0.0); // Altitude 0.0 by default.
				String fileName = UserChoice.getFileName("kml");
				Document document = new Document();
				return new WriteKmlPlace(fileName, document, pointLocation, radius);
			}
			else throw new InputException("There is no such latitude/longitude/radius.");
		}
		catch (InputMismatchException ex) {
			System.out.println("Error on the input, try again.");
		}
		return filteringBy(array);
	}

}
