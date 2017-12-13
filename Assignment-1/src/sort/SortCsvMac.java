package sort;

import java.util.ArrayList;
import java.util.Collections;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import assignment.CsvFile;
import assignment.Date;
import assignment.InputException;
import assignment.Line;
import assignment.Mac;
import assignment.MacLocation;

/**
 * This class sort the csv file by the mac.
 * This class extends @see {@link SortCsv}.
 * @author Orel and Samuel
 */
public class SortCsvMac implements SortCsv<Mac> {

	/**
	 * Empty constructor.
	 */
	public SortCsvMac() {}

	/**
	 * This method transforms the csvFiles into a list of {@link Mac} object.
	 * @param arrayCsv.
	 * @return arrayMac.
	 */
	public ArrayList<Mac> sortBy(ArrayList<CsvFile> arrayCsv) {
		ArrayList<Mac> array = new ArrayList<Mac>();
		for (CsvFile csvFile : arrayCsv) Collections.sort(csvFile.getLine());
		for (CsvFile csvFile : arrayCsv) {
			for (Line line : csvFile.getLine()) { 
				if (array.size() != 0 && needToCreateObject(line.getMac(), array.get(array.size() - 1))) { 
					if (line.getType().equals("WIFI")) array.get(array.size() - 1).getArrayMacLocation().add((MacLocation)addObject(line));
				}
				else if (!line.getFirstseen().contains("1970") && line.getType().equals("WIFI")) array.add((Mac) addMotherObject(line));
			}
		}
		for (Mac mac: array) mac.sort();
		return array;
	}

	/**
	 * @param mac.
	 * @param object.
	 * @return true if needs to create a new object.
	 * @return false if doesn't need. 
	 */
	public boolean needToCreateObject(String mac, Object object) {
		Mac macLocation = (Mac) object;
		return macLocation.getMacName().equals(mac);
	}

	/**
	 * This method create a new {@link Mac}.
	 * @param line.
	 * @return {@link Mac}.
	 * @exception InputException : print stack trace.
	 */
	public Object addMotherObject(Line line) {
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		if (line.getType().equals("WIFI")) array.add((MacLocation) addObject(line));
		try {
			return new Mac(
					line.getMac(), 
					array,
					Date.stringToDate(line.getFirstseen())
					);
		} 
		catch (InputException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method create a new {@link MacLocation}.
	 * @param line.
	 * @return {@link MacLocation}.
	 */
	public Object addObject(Line line) {
		return new MacLocation(
				new EarthCoordinate(Double.parseDouble(line.getCurrentLongitude()),
						Double.parseDouble(line.getCurrentLatitude()), 
						Double.parseDouble(line.getAltitudeMeters())), 
				Double.parseDouble(line.getRssi())
				);
	}

}
