package assignment;

import java.util.ArrayList;

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
