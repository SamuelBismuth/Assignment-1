package assignment;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class sort the csv files by the time.
 * This class extends @see {@link SortCsv}.
 * @author Orel and Samuel.
 */
public class SortCsvTime implements SortCsv<Scan> {

	/**
	 * Empty constructor.
	 */
	public SortCsvTime() {}

	/**
	 * This method fulfill the array of scan.
	 * @param arrayCsv
	 * @return array
	 */
	public ArrayList<Scan> sortBy(ArrayList<CsvFile> arrayCsv) {
		ArrayList<Scan> array = new ArrayList<Scan>();
		for (CsvFile csvFile : arrayCsv) {
			for (Line line : csvFile.getLine()) { 
				if (array.size() != 0 && needToCreateObject(line.getFirstseen(), array.get(array.size() - 1))) {
					if (line.getType().equals("WIFI")) array.get(array.size() - 1).getArrayWifi().add((Wifi) addObject(line));
				}
				else if (!line.getFirstseen().contains("1970") && line.getType().equals("WIFI")) array.add((Scan) addMotherObject(line));
			}
		}
		for (Scan scan: array) scan.sort();
		return array;
	}

	/**
	 * @param timeString
	 * @param scan
	 * @return true if the time did not change.
	 * @return false if the time has change.
	 */
	public boolean needToCreateObject(String timeString, Object object) {
		Scan scan = (Scan) object;
		GregorianCalendar time = new GregorianCalendar();
		try {
			time = Date.stringToDate(timeString); 
		}
		catch (InputException ex) {
			System.out.println("Error on the Firstseen of the csv file. " + ex);
		}
		return scan.getTime().equals(time);
	}

	/**
	 * The method add a new scan.
	 * @param record
	 * @param firstLine
	 * @return {@link Scan}.
	 * @exception NullPointerException : //do nothing.
	 */
	public Object addMotherObject(Line line) {
		GregorianCalendar time = new GregorianCalendar();
		try {
			time = Date.stringToDate(line.getFirstseen()); 
		}
		catch (InputException ex) {
			System.out.println("Error on the Firstseen of the csv file. " + ex);
		}
		ArrayList<Wifi> array = new ArrayList<Wifi>();
		if (line.getType().equals("WIFI")) array.add((Wifi) addObject(line));
		return new Scan(
				time, 
				line.getId(), 
				new EarthCoordinate(Double.parseDouble(line.getCurrentLongitude()),
						Double.parseDouble(line.getCurrentLatitude()), 
						Double.parseDouble(line.getAltitudeMeters())), 
				array);
	}

	/**
	 * This method create a wifi.
	 * @param line
	 * @return wifi
	 */
	public Object addObject(Line line) {
		return new Wifi(
				line.getSsid(),
				line.getMac(),
				channelToFrequency(Integer.parseInt(line.getChannel())),
				Integer.parseInt(line.getRssi())
				);
	}
	
	/**
	 * The method translate the channel to frequency.
	 * @param channel.
	 * @return String frequency.
	 */
	private static int channelToFrequency(int channel) {
		if (channel >= 1 && channel <= 14) return 2400;
		else return 5000;
	}
	

}
