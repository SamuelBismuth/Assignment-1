package cast;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import libraries.Algorithm2;
import libraries.Format;
import libraries.InputException;
import libraries.KmlUtil;
import libraries.ParseDate;
import objects.CsvFile;
import objects.Mac;
import objects.MacInformation;
import objects.MacInformationAlgo1;
import objects.SampleScan;
import objects.Wifi;
import objects.WigleWifiLine;

/**
 * This abstract class defines two abtsracts methods : cast and needToCreateObject.
 * Then, two classes extends this abstract class : @see {@link CastFromCsvFileToMac}, @see {@link CastFromCsvFileToSampleScan}.
 *  
 * The main goal of this abstract class is to cast an {@link ArrayList} of {@link CsvFile} to an another ArrayList.
 * 
 * All the functions defined here are static for the same reason of @see {@link Algorithm2} or, @see {@link KmlUtil}.
 * 
 * @author Orel and Samuel
 * @param Y.
 */
public abstract class CastFromCsvFile<Y> implements Cast<CsvFile, Y> {

	/**
	 * Abstract method, we define it in the others classes.
	 */
	public abstract ArrayList<Y> cast(ArrayList<CsvFile> array);
	public abstract boolean needToCreateObject(String mac, Object object);
	
	/**
	 * This method create a new {@link Mac}.
	 * @param line.
	 * @return {@link Mac}.
	 * @exception InputException : print stack trace.
	 */
	protected static Mac newMac(WigleWifiLine line) {
		ArrayList<MacInformation> array = new ArrayList<MacInformation>();
		if (line.getType().equals("WIFI")) array.add(newMacLocation(line));
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
	 * This method create a new {@link MacInformationAlgo1}.
	 * @param line.
	 * @return {@link MacInformationAlgo1}.
	 */
	protected static MacInformationAlgo1 newMacLocation(WigleWifiLine line) {
		return new MacInformationAlgo1(
				new EarthCoordinate(Double.parseDouble(line.getCurrentLongitude()),
						Double.parseDouble(line.getCurrentLatitude()), 
						Double.parseDouble(line.getAltitudeMeters())), 
				new Wifi(
						line.getSsid(),
						line.getMac(),
						Format.channelToFrequency(Integer.parseInt(line.getChannel())),
						Double.parseDouble(line.getRssi())
						),
				Double.parseDouble(line.getRssi())
				);
	}
	
	/**
	 * The method add a new scan.
	 * @param firstLine
	 * @return {@link SampleScan}.
	 * @exception InputException : Error on the Firstseen of the csv file.
	 */
	protected static SampleScan newSampleScan(WigleWifiLine line) {
		GregorianCalendar time = new GregorianCalendar();
		try {
			time = ParseDate.stringToDate(line.getFirstseen()); 
		}
		catch (InputException ex) {
			System.out.println("Error on the Firstseen of the csv file. " + ex);
		}
		ArrayList<Wifi> array = new ArrayList<Wifi>();
		if (line.getType().equals("WIFI")) array.add(newWifi(line));
		return new SampleScan(
				time, 
				line.getId(), 
				new EarthCoordinate(Double.parseDouble(Format.containsInterogation(line.getCurrentLongitude())),
						Double.parseDouble(Format.containsInterogation(line.getCurrentLatitude())), 
						Double.parseDouble(Format.containsInterogation(line.getAltitudeMeters()))), 
				array);
	}
	
	/**
	 * This method create a wifi.
	 * @param line
	 * @return wifi
	 */
	protected static Wifi newWifi(WigleWifiLine line) {
		return new Wifi(
				line.getSsid(),
				line.getMac(),
				Format.channelToFrequency(Integer.parseInt(line.getChannel())),
				Double.parseDouble(line.getRssi())
				);
	}
}
