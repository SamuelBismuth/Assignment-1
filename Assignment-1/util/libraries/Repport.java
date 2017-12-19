package libraries;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import algorithms.Difference;
import read.SampleScan;

public class Repport {
	
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
