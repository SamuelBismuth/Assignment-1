package libraries;

import java.util.ArrayList;
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
