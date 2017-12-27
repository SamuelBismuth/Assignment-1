package libraries;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import objects.Difference;
import objects.SampleScan;

/**
 * This class contains helped functions for the csv we need to create for the repport.
 * @author Orel and Samuel.
 */
public class Comparison {
	
	/**
	 * This method create an {@link ArrayList} of {@link Difference}.
	 * In the object {@link Difference}, we have the absolut difference between the two gps coordinates we want.
	 * @param arrayBoaz.
	 * @param array.
	 * @param arrayDiff.
	 */
	public static void algo1(ArrayList<SampleScan> arrayBoaz, ArrayList<SampleScan> array, ArrayList<Difference> arrayDiff) {
		HashMap<String, EarthCoordinate> map = new HashMap<String, EarthCoordinate>();
		for (SampleScan scanBoaz : arrayBoaz) 
			map.put(scanBoaz.getArrayWifi().get(0).getMac(), scanBoaz.getPointLocation());
		EarthCoordinate coordinateBoaz = new EarthCoordinate();
		for (SampleScan scan : array) {
			coordinateBoaz = map.get(scan.getArrayWifi().get(0).getMac());
			diffCoordinate(coordinateBoaz, scan.getPointLocation(), scan.getArrayWifi().get(0).getMac(), arrayDiff);
		}
	}

	/**
	 * This method create an {@link ArrayList} of {@link Difference}.
	 * In the object {@link Difference}, we have the absolut difference between the two gps coordinates we want.
	 * @param arrayBoaz.
	 * @param array.
	 * @param arrayDiff.
	 */
	public static void algo2(ArrayList<SampleScan> arrayScanBoazAlgo2, ArrayList<SampleScan> arrayScanAlgo2, ArrayList<Difference> arrayDiffAlgo2) {
		HashMap<GregorianCalendar, EarthCoordinate> map = new HashMap<GregorianCalendar, EarthCoordinate>();
		for (SampleScan scanBoaz : arrayScanBoazAlgo2) 
			map.put(scanBoaz.getTime(), scanBoaz.getPointLocation());
		EarthCoordinate coordinateBoaz = new EarthCoordinate();
		for (SampleScan scan : arrayScanAlgo2) {
			coordinateBoaz = map.get(scan.getTime());
			diffCoordinate(coordinateBoaz, scan.getPointLocation(), scan.getTime().getTime().toString() , arrayDiffAlgo2);
		}
	}

	/**
	 * This method create a new object {@link Difference}.
	 * @param coordinateBoaz.
	 * @param coordinate.
	 * @param macName.
	 * @param array.
	 */
	private static void diffCoordinate(EarthCoordinate coordinateBoaz, EarthCoordinate coordinate, String macName, ArrayList<Difference> array) {
		if (coordinateBoaz != null) {
			array.add(
					new Difference(
							new EarthCoordinate(
									Math.abs(coordinateBoaz.getLatitude() - coordinate.getLatitude()),
									Math.abs(coordinateBoaz.getLongitude() - coordinate.getLongitude()),
									Math.abs(coordinateBoaz.getAltitude() - coordinate.getAltitude())
									),
							macName
							)
					);
		}
	}

}
