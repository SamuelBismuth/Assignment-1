package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.boehn.kmlframework.coordinates.EarthCoordinate;

import libraries.ReadFolder;
import objects.SampleScan;
import objects.Wifi;

/**
 * This class extends @see {@link ReadCsv} and implements @see {@link Read}.
 * This class read a combo.
 * @author Orel and Samuel.
 * @param SampleScan.
 * @param Wifi.
 */
public class ReadComboAlgo1 extends ReadCsv<SampleScan> implements ReadFile<Wifi> {

	/**
	 * Constructor.
	 * @param array
	 * @param filePath
	 */
	public ReadComboAlgo1(String filePath, ArrayList<SampleScan> array) {
		super(filePath, array);
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
			BufferedReader br = readFile(filePath);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(HeaderAlgo1.class).parse(br);
			for (CSVRecord record : records) {
				ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
				arrayWifi.add(inputObject(record, ""));
				if (!record.get(HeaderAlgo1.latitude).contains("Lat")) array.add(inputSampleScan(record, arrayWifi));
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
		return new Wifi(
				record.get(HeaderAlgo1.ssid),
				record.get(HeaderAlgo1.macName),
				0, //not in use.
				0 //not in use.
				);

	}

	//private unimplemented methods

	/**
	 * This method create a new {@link SampleScan}.
	 * @param record
	 * @param array
	 * @return sampleScan.
	 * @exception NumberFormatException : error on the date.
	 */
	private SampleScan inputSampleScan(CSVRecord record, ArrayList<Wifi> array) {
		try {
			return new SampleScan (  
					null,
					"id", //not in use.
					new EarthCoordinate(
							Double.parseDouble(record.get(HeaderAlgo1.latitude)),
							Double.parseDouble(record.get(HeaderAlgo1.longitude)),
							Double.parseDouble(record.get(HeaderAlgo1.altitude))
							),
					array
					);
		} 
		catch (NumberFormatException e) {
			System.out.println("Error on the date. ");
			e.printStackTrace();
			return null;
		}
	}
	
}
