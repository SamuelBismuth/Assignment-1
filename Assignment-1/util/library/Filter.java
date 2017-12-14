package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import read.SampleScan;
import read.Wifi;

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
	
}
