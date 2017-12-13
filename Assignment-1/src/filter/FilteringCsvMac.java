package filter;

import java.util.ArrayList;

import assignment.Mac;
import assignment.SampleAlgo1;
import assignment.Scan;
import assignment.Wifi;
import write.WriteComboAlgo1;
import write.WriteFile;

/**
 * This class extends @see {@link FilteringCsv} and implements @see {@link Filtering}.
 * Not sure about the use of this class. 
 * This class should filtering the csv files by the choice of the user.
 * TODO : More understand the use of the algorithm 1.
 * More comments as follow.
 * @author Orel and Samuel.
 */
public class FilteringCsvMac extends FilteringCsv implements Filtering<SampleAlgo1> {
		
	public FilteringCsvMac() {}

	public WriteFile<SampleAlgo1> filteringBy(ArrayList<Scan> arrayScan, ArrayList<Mac> arrayMac)  {
		ArrayList<SampleAlgo1> array = new ArrayList<SampleAlgo1>();
		int index = 0;
		for (Mac mac : arrayMac) {
			Wifi wifi = findMac(arrayScan, mac.getMacName());
			array.add(new SampleAlgo1 (
					index++,
					mac.getMacName(),
					wifi.getName(),
					wifi.getFrequency(), 
					wifi.getSignal(),
					mac.getWeightCenter(),
					mac.getDate()
					));
		}
		return new WriteComboAlgo1(array);
	}
	
	private Wifi findMac(ArrayList<Scan> arrayScan, String macName) {
		for (Scan scan : arrayScan) 
			for (Wifi wifi : scan.getArrayStrongerWifi())
				if (wifi.getMac().equals(macName)) 
					return wifi;
		return null;
	}

}
