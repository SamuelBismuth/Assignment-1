package assignment1;

import java.util.ArrayList;

/**
 * This interface defines only one method : filteringBy.
 * Then, two classes implements this interface : @see {@link FilteringCsvMac}, @see {@link FilteringCsvTime}.
 * @author Orel and Samuel
 */
public abstract class FilteringCsv extends Date {

	public abstract ArrayList<Object> filteringBy(ArrayList<CsvFile> arrayCsv);
	public abstract boolean needToCreateObject(String str, Object object);
	public abstract Object addMotherObject(Line line);
	public abstract Object addObject(Line line);

	/**
	 * The method translate the channel to frequency.
	 * @param channel.
	 * @return String frequency.
	 */
	protected int channelToFrequency(int channel) {
		if (channel >= 1 && channel <= 14) return 2400;
		else return 5000;
	}
	
}
