package read;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import libraries.Algorithm2;
import libraries.InputException;
import libraries.KmlUtil;
import libraries.ParseDate;
import objects.CsvFile;
import objects.Mac;
import objects.MacLocation;
import objects.MacLocationAlgo1;
import objects.Wifi;
import objects.WigleWifiLine;

/**
 * This abstract class defines two abtsracts methods : sortBy, needToCreateObject.
 * Then, two classes implements this interface : @see {@link SortWigleWifiMac}, @see {@link SortWigleWifiTime}.
 * 
 * The main goal of this abstract class is to sort the {@link CsvFile} like wished, either by the {@link Mac} adress, or by the Time.
 * 
 * All the functions defined here are static for the same reason of @see {@link Algorithm2} or, @see {@link KmlUtil}.
 * TODO : check if it's right to define static method into an abstract class.
 * 
 * @author Orel and Samuel
 * @param <T>.
 * @param <Y>.
 */
public abstract class SortWigleWifi<T, Y> {

	/**
	 * abstracts methods, we define it in the other classes.
	 */
	public abstract ArrayList<T> sortBy(ArrayList<Y> array);
	protected abstract boolean needToCreateObject(String str, Object object);
	
	/**
	 * This method create a new {@link Mac}.
	 * @param line.
	 * @return {@link Mac}.
	 * @exception InputException : print stack trace.
	 */
	protected static Mac addMac(WigleWifiLine line) {
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		if (line.getType().equals("WIFI")) array.add(addMacLocation(line));
		try {
			return new Mac(
					line.getMac(), 
					array,
					ParseDate.stringToDate(line.getFirstseen())
					);
		} 
		catch (InputException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method create a new {@link MacLocationAlgo1}.
	 * @param line.
	 * @return {@link MacLocationAlgo1}.
	 */
	protected static MacLocationAlgo1 addMacLocation(WigleWifiLine line) {
		return new MacLocationAlgo1(
				new EarthCoordinate(Double.parseDouble(line.getCurrentLongitude()),
						Double.parseDouble(line.getCurrentLatitude()), 
						Double.parseDouble(line.getAltitudeMeters())), 
				Double.parseDouble(line.getRssi()),
				new Wifi(
						line.getSsid(),
						line.getMac(),
						channelToFrequency(Integer.parseInt(line.getChannel())),
						Double.parseDouble(line.getRssi())
						)
				);
	}
	
	/**
	 * The method add a new scan.
	 * @param firstLine
	 * @return {@link SampleScan}.
	 * @exception InputException : Error on the Firstseen of the csv file.
	 */
	protected static SampleScan addScan(WigleWifiLine line) {
		GregorianCalendar time = new GregorianCalendar();
		try {
			time = ParseDate.stringToDate(line.getFirstseen()); 
		}
		catch (InputException ex) {
			System.out.println("Error on the Firstseen of the csv file. " + ex);
		}
		ArrayList<Wifi> array = new ArrayList<Wifi>();
		if (line.getType().equals("WIFI")) array.add(addWifi(line));
		return new SampleScan(
				time, 
				line.getId(), 
				new EarthCoordinate(Double.parseDouble(containsInterogation(line.getCurrentLongitude())),
						Double.parseDouble(containsInterogation(line.getCurrentLatitude())), 
						Double.parseDouble(containsInterogation(line.getAltitudeMeters()))), 
				array);
	}
	
	/**
	 * This method create a wifi.
	 * @param line
	 * @return wifi
	 */
	protected static Wifi addWifi(WigleWifiLine line) {
		return new Wifi(
				line.getSsid(),
				line.getMac(),
				channelToFrequency(Integer.parseInt(line.getChannel())),
				Double.parseDouble(line.getRssi())
				);
	}
	
	/**
	 * The method translate the channel to frequency.
	 * @param channel.
	 * @return String frequency.
	 */
	protected static int channelToFrequency(int channel) {
		if (channel >= 1 && channel <= 14) return 2400;
		else return 5000;
	}
	
	/**
	 * This method check if the localisation is known.
	 * @param str.
	 * @return str or "0".
	 */
	protected static String containsInterogation(String str) {
		if (str.equals("?")) return "0";
		return str;
	}

}
