package userChoice;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import filter.Filtering;
import filter.FilteringKmlPlace;
import libraries.InputException;
import libraries.KmlUtil;
import read.SampleScan;

/**
 * This class implements @see {@link UserChoice}.
 * This class ask to choose a place.
 * @author Orel and Samuel.
 */
public class UserChoiceKmlPlace implements UserChoice {

	/**
	 * This method ask the user a place.
	 * @exception InputException : there is such ...
	 */
	@SuppressWarnings("resource")
	@Override
	public Filtering<SampleScan> choice() throws InputException {
		try { 
			System.out.println("Input an latitude please :");
			double pointLatitude = Double.parseDouble(new Scanner(System.in).nextLine());
			if (!KmlUtil.checkLatitude(pointLatitude)) throw new InputException("There is no such latitude.");
			System.out.println("Input an longitude please :");
			double pointLongitude = Double.parseDouble(new Scanner(System.in).nextLine());
			if (!KmlUtil.checkLongitude(pointLongitude)) throw new InputException("There is no such longitude.");
			System.out.println("Input a radius please (meters) :");
			double radius = Double.parseDouble(new Scanner(System.in).nextLine());
			if (radius > 0) throw new InputException("There is no such radius.");
			EarthCoordinate coordinate = new EarthCoordinate(
					pointLatitude,
					pointLongitude,
					0.0 //By default.
					);
			
			return new FilteringKmlPlace(coordinate, radius);
		}
		catch (InputMismatchException ex) {
			System.out.println("Error on the input, try again.");
			return choice();
		}
	}

}
