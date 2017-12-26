package read;

import java.util.ArrayList;
import java.util.Collections;

import libraries.Filter;
import objects.CsvFile;
import objects.Mac;
import objects.WigleWifiLine;

/**
 * This class sort the csv file by the mac adress.
 * This class extends @see {@link SortWigleWifi}.
 * @author Orel and Samuel.
 * @param <Mac, SampleScan>.
 */
public class SortWigleWifiMac extends SortWigleWifi<Mac, SampleScan> {

	/**
	 * Empty constructor.
	 * @param arrayCsv.
	 */
	public SortWigleWifiMac() {}

	/**
	 * This method transforms the csv file into a list of {@link Mac} object.
	 * @param arrayCsv.
	 * @return arrayMac.
	 */
	@Override
	public ArrayList<Mac> sortBy(ArrayList<SampleScan> arrayScan) {
		ArrayList<Mac> array = new ArrayList<Mac>();
		ArrayList<CsvFile> arrayCsvMultipliying = Filter.fromScanToCvs(arrayScan);
		CsvFile file = getUnionFromTheFile(arrayCsvMultipliying);
		Collections.sort(file.getWigleWifiLine());
		for (WigleWifiLine line : file.getWigleWifiLine()) { 
			if (array.size() != 0 && needToCreateObject(line.getMac(), array.get(array.size() - 1))) { 
				if (line.getType().equals("WIFI")) array.get(array.size() - 1).getArrayMacLocation().add(addMacLocation(line));
			}
			else if (!line.getFirstseen().contains("1970") && line.getType().equals("WIFI")) array.add(addMac(line));

		}
		for (Mac mac: array) mac.sort(mac.getArrayMacLocation());
		return array;
	}

	/**
	 * This method should say if needs to create a {@link Mac} object.
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

	private CsvFile getUnionFromTheFile(ArrayList<CsvFile> array) {
		ArrayList<WigleWifiLine> arrayLine = new ArrayList<WigleWifiLine>();
		for (CsvFile file : array) 
			for (WigleWifiLine line : file.getWigleWifiLine())
				arrayLine.add(line);
		return new CsvFile("id", arrayLine);
	}

}
