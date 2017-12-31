package filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import libraries.InputException;
import objects.SampleScan;
import objects.Wifi;
import write.WriteFile;

/**
 * This interface defines only one method : filteringBy.
 * Then, fives classes implements this interface : 
 * For the kml file : @see {@link FilteringKmlEmpty}, @see {@link FilteringKmlId}, @see {@link FilteringKmlPlace} and @see {@link FilteringKmlTime}.
 * For the csv file : @see {@link CastFromMacToLineAlgo1} also implements this interface.
 * 
 * The main goal of this interface is to filtering the data.
 * For an array of object "Y", the function filteringBy filter the data, then, return one class which implements {@link WriteFile}, with the {@link GenericDeclaration} "T".
 * 
 * @author Orel and Samuel. 
 * @param <T>.
 */
public abstract class Filtering<T> {

	public abstract ArrayList<T> filteringBy(ArrayList<T> array) throws InputException;
	
	/**
	 * Thanks to Yehonathan, and Yshai, that's helped me to build this function.
	 * This method receive an {@link ArrayList} of {@link SampleScan}, and return the same arrayList, but without the duplicate mac.
	 * This method use a {@link HashMap}.
	 * @param array.
	 * @return array.
	 */
	public static void removeDuplicateMac(ArrayList<SampleScan> array) {
		Map<String, Wifi> map = new HashMap<>();
		array.forEach(sampleScan -> sampleScan.getArrayWifi().forEach(wifi -> {
			if (map.containsKey(wifi.getMac())) {
				if (wifi.compareTo(map.get(wifi.getMac())) == 1) {
					map.put(wifi.getMac(), wifi);
				}
			}
			else {
				map.put(wifi.getMac(), wifi);
			}
		}));
		array.forEach(sampleScan -> sampleScan.getArrayWifi()
				.removeIf(wifiSpot -> !wifiSpot.equals(map.get(wifiSpot.getMac()))));
	}

	
}
