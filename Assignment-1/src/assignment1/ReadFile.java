package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * This class reads a file and input into an array list the object @see {@link Scan}.
 * This class use the API commons-csv @see {@link https://commons.apache.org/proper/commons-csv/}.
 * This class implements @see {@link Read} and extends @see {@link Verification}.
 * @see NOTICE for more informations about how to run with the api.
 * @author Orel and Samuel.
 */

public class ReadFile implements Read {

	private File file;
	private ArrayList<CsvFile> array;

	/**
	 * Test constructor.
	 */
	public ReadFile(File file, ArrayList<CsvFile> array) {
		this.file = file;
		this.array = array;
	}

	/**
	 * Constructor.
	 * @param file.
	 * @param folderName.
	 * @param array.
	 */
	protected ReadFile(File file, String folderName, ArrayList<CsvFile> array) {
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
			ArrayList<Line> arrayLine = new ArrayList<Line>();
			if (checkTheFile(firstLine)) {
				Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(br);
				for (CSVRecord record : records) 
						arrayLine.add(inputLine(record, getId(firstLine)));
				array.add(new CsvFile(getId(firstLine), arrayLine));
				in.close();
				br.close();
			}
		}
		catch(IOException ex) { // If there is an error.
			System.out.println("Error reading file : " + ex);
			System.exit(0);
		}
	}

	//Private methods.

	/**
	 * This method create a new line.
	 * @param record
	 * @return {@link Line}.
	 */
	private Line inputLine(CSVRecord record, String id) {
		return new Line(
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
	 * The method checks the first line, by asking if contains the header "WigleWifi".
	 * @param firstLine.
	 */
	protected boolean checkTheFile(String firstLine) {
		if (firstLine.contains("WigleWifi") && firstLine.contains("display")) return true;
		return false;
	}

	
}
