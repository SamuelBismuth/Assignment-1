package libraries;

import java.util.ArrayList;

import objects.CsvFile;
import objects.Wifi;
import objects.WigleWifiLine;
import read.SampleScan;

/**
 * This class includes statics functions that hedlped to the filtering of the data.
 * @author Orel and Samuel.
 */
public class Filter {

	
	/**
	 * This method parse a {@link SampleScan} object into a {@link CsvFile} object.
	 * @param arrayScan
	 * @return array of {@link CsvFile}.
	 */
	public static ArrayList<CsvFile> fromScanToCvs(ArrayList<SampleScan> arrayScan) {
		ArrayList<CsvFile> arrayCsv = new ArrayList<CsvFile>();
		for (SampleScan scan : arrayScan) {
			ArrayList<WigleWifiLine> arrayWigle = new ArrayList<WigleWifiLine>();
			for (Wifi wifi : scan.getArrayStrongerWifi()) {
				arrayWigle.add(
						new WigleWifiLine(
								wifi.getMac(), 
								wifi.getName(), 
								"authMode", 
								scan.getTime().getTime().toString(), 
								Integer.toString(wifi.getFrequency()), 
								Double.toString(wifi.getSignal()), 
								Double.toString(scan.getPointLocation().getLatitude()), 
								Double.toString(scan.getPointLocation().getLongitude()), 
								Double.toString(scan.getPointLocation().getAltitude()), 
								"Accuracy metter", 
								"WIFI", 
								scan.getId()
								)
						);
			}

			arrayCsv.add(
					new CsvFile(
							scan.getId(),
							arrayWigle
							)
					);
		}
		return arrayCsv;
	}

}
