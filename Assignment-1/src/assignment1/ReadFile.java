package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class reads a file and input into an array list the object @see {@link Wifi}.
 * This class use the API commons-csv @see {@link https://commons.apache.org/proper/commons-csv/}.
 * This class implements @see {@link Read} and extends @see {@link Verification}.
 * @see NOTICE for more informations about how to run with the api.
 * @author Orel and Samuel.
 */

public class ReadFile extends Verification implements Read {

	private File file;
	private ArrayList<Wifi> array;
	
	/**
	 * Test constructor.
	 */
	public ReadFile(File file, ArrayList<Wifi> array) {
		this.file = file;
		this.array = array;
	}
	
	/**
	 * Constructor.
	 * @param file.
	 * @param folderName.
	 * @param array.
	 */
	protected ReadFile(File file, String folderName, ArrayList<Wifi> array) {
		this.file = file;
		this.array = array;
		read(folderName);
	}

	/**
	 * This method reads the file from @see {@link ReadFolder} and put into an array list the data we need.
	 * We use here the API commons-csv.
	 * Attention : tu run with the API you need to import him into the project @see README.
	 * @param folderName.
	 * @exception IOException | NumberFormatException : print error reading file.
	 */
	public void read(String folderName) {
		try {
			Reader in = new FileReader(folderName + "/" + file.getName());
			BufferedReader br = new BufferedReader(in);
			String firstLine = br.readLine();
			if (checkTheFile(firstLine)) {
				Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(br);	
				for (CSVRecord record : records) 
					if (record.get("Type").equals("WIFI") && !record.get("FirstSeen").contains("1970")) 
						inputWifi(record, firstLine);
				in.close();
				br.close();
			}
		}
		catch(IOException | NumberFormatException ex) { // If there is an error.
			System.out.println("Error reading file : " + ex);
			System.exit(0);
		}
	}

	//Private methods.

	/**
	 * The method add a new wifi into the array list.
	 * @param record
	 * @param firstLine
	 * @exception NullPointerException : //do nothing.
	 */
	private void inputWifi(CSVRecord record, String firstLine) {
		Wifi wifi = null;
		GregorianCalendar time = new GregorianCalendar();
		try {
			time = stringToDate(record.get("FirstSeen")); 
		}
		catch (InputException ex) {
			System.out.println("Error on the Firstseen of the csv file. " + ex);
		}
		String id = getId(firstLine);
		EarthCoordinate pointLocation = new EarthCoordinate(Double.parseDouble(record.get("CurrentLongitude")),
				Double.parseDouble(record.get("CurrentLatitude")), 
				Double.parseDouble(record.get("AltitudeMeters")));
		String name = noName(record.get("SSID"));
		String mac = record.get("MAC");
		int frequency = channelToFrequency(Integer.parseInt(record.get("Channel")));
		int signal = Integer.parseInt(record.get("RSSI"));
		if (checkAltitude(pointLocation.getAltitude()) && checkMac(mac)) 
			wifi = new Wifi(time, id, pointLocation, name, mac, frequency, signal);
		try {
			array.add(wifi);
		}
		catch (NullPointerException ex) {
			//do nothing
		}
	}

	/**
	 * The method finds from the first line the id.
	 * @param firstLine.
	 * @return the id.
	 */
	private String getId(String firstLine) {
		for (int i = 0; i < firstLine.length(); i++) {
			if(firstLine.substring(i, i + 7).equals("display")) {
				int j = i + 8;
				while (firstLine.charAt(j) != ',' && j < firstLine.length() - 1) j ++;
				return firstLine.substring(i + 8, j);
			}
		}
		return null;
	}

	/**
	 * The method translate the channel to frequency.
	 * @param channel.
	 * @return String frequency.
	 */
	private int channelToFrequency(int channel) {
		if (channel >= 1 && channel <= 14) return 2400;
		else return 5000;
	}

}
