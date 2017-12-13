package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import assignment.CsvFile;
import assignment.Line;

public class ReadCombo implements Read {

	private ArrayList<CsvFile> array;
	private int count = 0;

	/**
	 * Constructor.
	 * @param file.
	 * @param folderName.
	 * @param array.
	 */
	protected ReadCombo(String folderName, ArrayList<CsvFile> array, File file) {
		this.array = array;
		read(file.toString() + ".csv");

	}

	/**
	 * This method reads the file from @see {@link ReadFolder} and put into an array list the data we need.
	 * We use here the API commons-csv.
	 * Attention : tu run with the API you need to import him into the project @see README.
	 * @param folderName.
	 * @exception IOException : print error reading file.
	 */
	public void read(String file) {
		try {
			Reader in = new FileReader(file);
			BufferedReader br = new BufferedReader(in);
			ArrayList<Line> arrayLine = new ArrayList<Line>();
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(Header.class).parse(in);
			for (CSVRecord record : records) {
				arrayLine.clear();
				if(goodLine(record)) {
					for (int i = 0; i < Integer.parseInt(record.get(Header.wifiNetworks)); i++) 
						arrayLine.add(inputLine(record, i));
					array.add(new CsvFile(record.get(Header.id), (ArrayList<Line>) arrayLine.clone()));
					count++;
				}
			}
			in.close();
			br.close();

		}
		catch(IOException ex) { // If there is an error.
			System.out.println("Error reading file : " + ex);
			System.exit(0);
		}
	}

	//private unimplemented methods

	/**
	 * @param record.
	 * @return true if the line is good.
	 * @return false if not.
	 */
	private boolean goodLine(CSVRecord record) {
		if(!record.get(0).equals("")) return true;
		return false;
	}

	/**
	 * This method create a line.
	 * @param record
	 * @param wifiNetwork
	 * @return {@link Line}.
	 */
	private Line inputLine(CSVRecord record, int i) {
		String time = containsSeconds(record.get(Header.time));
		return new Line(
				record.get((i * 4) +  7),
				record.get((i * 4) + 6),
				"AuthMode",
				time,
				record.get((i * 4) + 8),
				record.get((i * 4) + 9),
				record.get(Header.latitude),
				record.get(Header.longitude),
				record.get(Header.altitude),
				"AccuracyMeters",
				"WIFI",
				record.get(Header.id)
				);
	}

	private String containsSeconds(String str) {
		String strNew = str.substring(0, 15);
		if(str.substring(15, 17).equals("AM")) {
			if (count < 10) strNew += "0" + count;
			else strNew += count;
			return strNew;
		}
		return str;
	}

}
