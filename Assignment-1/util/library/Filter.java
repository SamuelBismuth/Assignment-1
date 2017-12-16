package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import read.CsvFile;
import read.SampleScan;
import read.Wifi;
import read.WigleWifiLine;

public class Filter {

	/**
	 * Thanks to Yehonathan, and Yshai, that's helped me to build this function.
	 * This method receive an {@link ArrayList} of {@link SampleScan}, and return the same arrayList, but without the duplicate mac.
	 * @param array.
	 * @return array.
	 */
	public static void removeDuplicateMac(ArrayList<SampleScan> array) {
		Map<String, Wifi> map = new HashMap<>();
		array.forEach(sampleScan -> sampleScan.getArrayStrongerWifi().forEach(wifi -> {
			if (map.containsKey(wifi.getMac())) {
				if (wifi.compareTo(map.get(wifi.getMac())) == 1) {
					map.put(wifi.getMac(), wifi);
				}
			}
			else {
				map.put(wifi.getMac(), wifi);

			}
		}));
		array.forEach(sampleScan -> sampleScan.getArrayStrongerWifi()
				.removeIf(wifiSpot -> !wifiSpot.equals(map.get(wifiSpot.getMac()))));
	}

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
