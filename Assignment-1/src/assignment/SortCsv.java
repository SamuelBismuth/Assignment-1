package assignment;

import java.util.ArrayList;

/**
 * This abstract class defines only for methods : sortBy, needToCreateObject, addMotherObject, addObject.
 * Then, two classes implements this interface : @see {@link SortCsvMac}, @see {@link SortCsvTime}.
 * This method extends @see {@link Date}.
 * @author Orel and Samuel
 */
public abstract class SortCsv extends Date {

	public abstract ArrayList<?> sortBy(ArrayList<CsvFile> arrayCsv);
	public abstract boolean needToCreateObject(String str, Object object);
	public abstract Object addMotherObject(Line line);
	public abstract Object addObject(Line line);

	/**
	 * The method translate the channel to frequency.
	 * @param channel.
	 * @return String frequency.
	 */
	protected static int channelToFrequency(int channel) {
		if (channel >= 1 && channel <= 14) return 2400;
		else return 5000;
	}
	
}
