package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import libraries.ReadFolder;
import objects.CsvFile;
import objects.WigleWifiLine;

/**
 * This class extends {@link ReadCsv} and implements @see {@link ReadFile}.
 * This class reads a file and input into an array list the object @see {@link CsvFile}.
 * This class use the API commons-csv @see {@link https://commons.apache.org/proper/commons-csv/}.
 * This class implements @see {@link Read}.
 * @see NOTICE for more informations about how to run with the api.
 * @author Orel and Samuel.
 * @param <WigleWifiLine>.
 * @param <CsvFile>.
 */

public class ReadWigleWifi extends ReadCsv<CsvFile> implements ReadFile<WigleWifiLine> {

	public ReadWigleWifi(String folderName, ArrayList<CsvFile> array, File file) {
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
			String firstLine = br.readLine();
			ArrayList<WigleWifiLine> arrayLine = new ArrayList<WigleWifiLine>();
			if (checkTheFile(firstLine)) {
				Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(br);
				for (CSVRecord record : records) 
					arrayLine.add(inputObject(record, getId(firstLine)));
				array.add(new CsvFile(getId(firstLine), arrayLine));
				br.close();
			}
		}
		catch(IOException ex) { 
			System.out.println("Error reading file : " + ex);
			System.exit(0);
		}
	}

	/**
	 * This method create a new line.
	 * @param record.
	 * @param id.
	 * @return {@link WigleWifiLine}.
	 */
	@Override
	public WigleWifiLine inputObject(CSVRecord record, String id) {
		return new WigleWifiLine(
				record.get("MAC"),
				record.get("SSID"),
				record.get("AuthMode"),
				record.get("FirstSeen"),
				record.get("Channel"),
				record.get("RSSI"),
				record.get("CurrentLatitude"),
				record.get("CurrentLongitude"),
				record.get("AltitudeMeters"),
				record.get("AccuracyMeters"),
				record.get("Type"),
				id
				);
	}

	//Private unimplmented methods.

	/**
	 * The method finds from the first line the id.
	 * @param firstLine.
	 * @return the id.
	 */
	public static String getId(String firstLine) {
		for (int i = 0; i < firstLine.length(); i++) {
			if(firstLine.substring(i, i + 7).equals("display")) {
				int j = i + 8;
				while (firstLine.charAt(j) != ',' && j < firstLine.length() - 1) j ++;
				return firstLine.substring(i + 8, j);
			}
		}
		return "id";
	}

	/**
	 * The method checks the first line, by asking if contains the header "WigleWifi".
	 * @param firstLine.
	 * @return true if the file is a WigleWifi file.
	 * @return false if it's not.
	 */
	public static boolean checkTheFile(String firstLine) {
		if (firstLine.contains("WigleWifi") && firstLine.contains("display")) return true;
		return false;
	}

}
