package read;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import library.ParseDate;
import library.InputException;

/**
 * This class sort the csv files by the time.
 * This class extends @see {@link SortWigleWifi}.
 * @author Orel and Samuel.
 */
public class SortWigleWifiTime extends SortWigleWifi<SampleScan, CsvFile> {
	
	/**
	 * Constructor.
	 * @param arrayCsv.
	 */
	public SortWigleWifiTime() {                                   }

	/**
	 * This method fulfill the array of scan.
	 * @param arrayCsv
	 * @return array
	 */
	@Override
	public ArrayList<SampleScan> sortBy(ArrayList<CsvFile> arrayCsv) {
		ArrayList<SampleScan> array = new ArrayList<SampleScan>();
		for (CsvFile csvFile : arrayCsv) {
			for (WigleWifiLine line : csvFile.getWigleWifiLine()) { 
				if (array.size() != 0 && needToCreateObject(line.getFirstseen(), array.get(array.size() - 1))) {
					if (line.getType().equals("WIFI")) array.get(array.size() - 1).getArrayWifi().add(addWifi(line));
				}
				else if (!line.getFirstseen().contains("1970") && line.getType().equals("WIFI")) array.add(addScan(line));
			}
		}
		for (SampleScan scan: array) scan.sort();
		return array;
	}

	/**
	 * @param timeString
	 * @param scan
	 * @return true if the time did not change.
	 * @return false if the time has change.
	 */
	@Override
	public boolean needToCreateObject(String timeString, Object object) {
		SampleScan scan = (SampleScan) object;
		GregorianCalendar time = new GregorianCalendar();
		try {
			time = ParseDate.stringToDate(timeString); 
		}
		catch (InputException ex) {
			System.out.println("Error on the Firstseen of the csv file. " + ex);
		}
		return scan.getTime().equals(time);
	}

}
