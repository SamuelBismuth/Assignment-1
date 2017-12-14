package read;

import java.util.ArrayList;

/**
 * This class represente the object csv file.
 * @author Orel and Samuel.
 */
public class CsvFile {

	private String id;
	private ArrayList<WigleWifiLine> arrayWigleWifiLine;
	
	/**
	 * Constructor.
	 * @param id.
	 * @param line.
	 */
	protected CsvFile(String id, ArrayList<WigleWifiLine> arrayWigleWifiLine) {
		this.id = id;
		this.arrayWigleWifiLine = arrayWigleWifiLine;
	}

	/**
	 * @return id.
	 */
	protected String getId() {
		return id;
	}

	/**
	 * @return arrayLine.
	 */
	public ArrayList<WigleWifiLine> getWigleWifiLine() {
		return arrayWigleWifiLine;
	}

}
