package read;

import java.util.ArrayList;
import java.util.Collections;

import algorithm1.Mac;

/**
 * This class sort the csv file by the mac.
 * This class extends @see {@link SortWigleWifi}.
 * @author Orel and Samuel
 */
public class SortWigleWifiMac extends SortWigleWifi<Mac> {
	
	/**
	 * Empty constructor.
	 * @param arrayCsv.
	 */
	public SortWigleWifiMac() {}

	/**
	 * This method transforms the csvFiles into a list of {@link Mac} object.
	 * @param arrayCsv.
	 * @return arrayMac.
	 */
	@Override
	public ArrayList<Mac> sortBy(ArrayList<CsvFile> arrayCsv) {
		ArrayList<Mac> array = new ArrayList<Mac>();
		for (CsvFile csvFile : arrayCsv) Collections.sort(csvFile.getWigleWifiLine());
		for (CsvFile csvFile : arrayCsv) {
			for (WigleWifiLine line : csvFile.getWigleWifiLine()) { 
				if (array.size() != 0 && needToCreateObject(line.getMac(), array.get(array.size() - 1))) { 
					if (line.getType().equals("WIFI")) array.get(array.size() - 1).getArrayMacLocation().add(addMacLocation(line));
				}
				else if (!line.getFirstseen().contains("1970") && line.getType().equals("WIFI")) array.add(addMac(line));
			}
		}
		for (Mac mac: array) mac.sort();
		return array;
	}

	/**
	 * @param mac.
	 * @param object.
	 * @return true if needs to create a new object.
	 * @return false if doesn't need. 
	 */
	@Override
	public boolean needToCreateObject(String mac, Object object) {
		Mac macLocation = (Mac) object;
		return macLocation.getMacName().equals(mac);
	}

}
