package filter;

import java.util.ArrayList;
import java.util.Scanner;

import algorithms.LineAlgo1;
import algorithms.Mac;
import write.WriteComboAlgo1;
import write.WriteFile;

/**
 * This class implements @see {@link Filtering}.
 * This class filter the {@link ArrayList} of {@link Mac} to give the new coordinates by the algorithm 1.
 * @author Orel and Samuel.
 */
public class FilteringCsvMac implements Filtering<Mac, Mac> {

	/**
	 * Empty constructor.
	 */
	public FilteringCsvMac() {}

	/**
	 * This method create an {@link ArrayList} of {@link LineAlgo1} from an ArrayList of {@link Mac}.
	 * In the object {@link LineAlgo1} is found all the information needed to create the new coordinates.
	 * @param arrayMac.
	 */
	@Override
	public WriteFile<Mac> filteringBy(ArrayList<Mac> arrayMac)  {
		int index = 0;
		for (Mac mac : arrayMac) {
			mac.setLineAlgo1(
					new LineAlgo1(
							index++,
							mac.getMacName(),
							mac.getArrayMacLocation().get(0).getWifi().getName(),
							mac.getArrayMacLocation().get(0).getWifi().getFrequency(), 
							mac.getArrayMacLocation().get(0).getWifi().getSignal(),
							mac.getWeightCenter(),
							mac.getDate()
							)
					);
		}
		
		String fileName = getFileName();
		return new WriteComboAlgo1(fileName);
	}

	@SuppressWarnings("resource")
	private static String getFileName() {
		System.out.println("Input a name for the csv file you want to create : ");
		return new Scanner(System.in).nextLine() + ".csv";
	}
}
