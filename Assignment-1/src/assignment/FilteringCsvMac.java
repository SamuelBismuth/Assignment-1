package assignment;

import java.util.ArrayList;

/**
 * This class extends @see {@link FilteringCsv} and implements @see {@link Filtering}.
 * Not sure about the use of this class. 
 * This class should filtering the csv files by the choice of the user.
 * TODO : More understand the use of the algorithm 1.
 * More comments as follow.
 * @author Orel and Samuel.
 */
public class FilteringCsvMac extends FilteringCsv implements Filtering {
	
	private String macName;
	
	public FilteringCsvMac(String macName) {
		this.macName = macName;
	}

	public WriteFile filteringBy(ArrayList<?> arrayObject)  {
		ArrayList<Mac> array = (ArrayList<Mac>) arrayObject;
		Mac mac = findMac(array, macName);
		System.out.println(mac.getMacName());
		System.out.println(mac.getNumberOfMac());
		for (MacLocation macLocation : mac.getArrayMacLocation()) System.out.println(macLocation.toString());
		return null;
	}
	
	private Mac findMac(ArrayList<Mac> array, String macName) {
		for (Mac mac : array) if(mac.getMacName().equals(macName)) return mac;
		return null;
	}

}
