package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.boehn.kmlframework.coordinates.EarthCoordinate;

import libraries.InputException;
import libraries.ParseDate;
import libraries.ReadFolder;
import objects.Wifi;

/**
 * This class extends @see {@link ReadCsv} and implements @see {@link Read}.
 * This class read a combo.
 * @author Orel and Samuel.
 * @param <SampleScan>.
 * @param <Wifi>.
 */
public class ReadCombo extends ReadCsv<SampleScan> implements ReadFile<Wifi> {

	/**
	 * Constructor.
	 * @param folderName
	 * @param array
	 * @param file
	 */
	public ReadCombo(String folderName, ArrayList<SampleScan> array, File file) {
		super(folderName, array, file);
	}

	/**
	 * This method reads the file from @see {@link ReadFolder} and put into an array list the data we need.
	 * We use here the API commons-csv.
	 * Attention : tu run with the API you need to import him into the project @see README.
	 * @exception IOException : print error reading file.
	 */
	@Override
	public void readBuffer() {
		try {
			BufferedReader br = readFile(folderName + file);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(Header.class).parse(br);
			for (CSVRecord record : records) {
				if(goodLine(record)) {
					ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
					for (int i = 0; i < Integer.parseInt(record.get(Header.wifiNetworks)); i++) 
						arrayWifi.add(inputObject(record, Integer.toString(i)));
					array.add(inputSampleScan(record, arrayWifi));
				}
			}
			br.close();
		}
		catch(IOException ex) { 
			System.out.println("Error reading file : " + ex);
			System.exit(0);
		}
	}

	/**
	 * This method input a new {@link SampleScan} into the array.
	 * @param record
	 * @param i
	 * @return {@link Wifi}.
	 */
	@Override
	public Wifi inputObject(CSVRecord record, String i) {
		int count = Integer.parseInt(i);
		if (record.get((count * 4) + 6).equals("")) count++;
		return new Wifi(
				record.get((count * 4) + 6),
				record.get((count * 4) +  7),
				Integer.parseInt(record.get((count * 4) + 8)),
				Double.parseDouble(record.get((count * 4) + 9))
				);
	}

	//private unimplemented methods

	/**
	 * This method create a new {@link SampleScan}.
	 * @param record
	 * @param array
	 * @return sampleScan.
	 * @exception NumberFormatException | {@link InputException} : error on the date.
	 */
	private SampleScan inputSampleScan(CSVRecord record, ArrayList<Wifi> array) {
		try {
			return new SampleScan (  
					ParseDate.stringToDate(record.get(Header.time)),
					record.get(Header.id),
					new EarthCoordinate(
							checkCoordinate(record.get(Header.latitude)),
							checkCoordinate(record.get(Header.longitude)),
							checkCoordinate(record.get(Header.altitude))
							),
					array
					);
		} 
		catch (NumberFormatException | InputException e) {
			System.out.println("Error on the date. ");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method check if the cordinate are known.
	 * @param coordinate.
	 * @return coordinate.
	 */
	private static Double checkCoordinate(String coordinate) {
		if (coordinate.equals("?") || coordinate.equals("null")) return 0.0;
		return Double.parseDouble(coordinate);
	}

	/**
	 * This method check if the line is good.
	 * @param record.
	 * @return true if the line is good.
	 * @return false if not.
	 */
	private static boolean goodLine(CSVRecord record) {
		if (!record.get(0).equals("") && !record.get(Header.wifiNetworks).contains("#Wifi networks")) return true;
		return false;
	}

}
