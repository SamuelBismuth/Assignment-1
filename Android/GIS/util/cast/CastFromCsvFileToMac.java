package cast;

import java.util.ArrayList;

import objects.CsvFile;
import objects.Mac;
import objects.WigleWifiLine;

/**
 * This class extends @see {@link CastFromCsvFile} and implements @see {@link Cast}.
 * This class cast the {@link ArrayList} of {@link CsvFile} to an {@link ArrayList} of {@link Mac}.
 * @author Orel and Samuel.
 */
public class CastFromCsvFileToMac extends CastFromCsvFile<Mac> implements Cast<CsvFile, Mac> {

	/**
	 * Empty constructor.
	 */
	public CastFromCsvFileToMac() {}

	/**
	 * This method transforms the csv file into a list of {@link Mac} object.
	 * @param arrayCsv.
	 * @return arrayMac.
	 */
	@Override
	public ArrayList<Mac> cast(ArrayList<CsvFile> arrayCsvFile) {
		ArrayList<Mac> arrayMac = new ArrayList<Mac>();
		for (CsvFile csvFile : arrayCsvFile)
			for (WigleWifiLine line : csvFile.getWigleWifiLine()) { 
				if (arrayMac.size() != 0 && needToCreateObject(line.getMac(), arrayMac.get(arrayMac.size() - 1))) { 
					if (line.getType().equals("WIFI")) arrayMac.get(arrayMac.size() - 1).getArrayMacLocation().add(newMacLocation(line));
				}
				else if (!line.getFirstseen().contains("1970") && line.getType().equals("WIFI")) arrayMac.add(newMac(line));

			}
		for (Mac mac: arrayMac) mac.sort(mac.getArrayMacLocation());
		return arrayMac;
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

}
