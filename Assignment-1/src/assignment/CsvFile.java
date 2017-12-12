package assignment;

import java.util.ArrayList;

/**
 * This class represente the object csv file.
 * @author Orel and Samuel.
 */
public class CsvFile {

	private String id;
	private ArrayList<Line> arrayLine;
	
	/**
	 * Constructor.
	 * @param id.
	 * @param line.
	 */
	public CsvFile(String id, ArrayList<Line> arrayLine) {
		this.id = id;
		this.arrayLine = arrayLine;
	}

	/**
	 * @return id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return arrayLine.
	 */
	public ArrayList<Line> getLine() {
		return arrayLine;
	}

}
